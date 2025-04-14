package br.com.dbserver.desafio.sessao;

import br.com.dbserver.desafio.mapper.MockSessao;
import br.com.dbserver.desafio.pauta.PautaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessaoResourceTest {
    @LocalServerPort
    private int port;
    private RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static SessaoDTO sessaoDTO;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        MockSessao input = new MockSessao();
        sessaoDTO = input.mockDtoRequest();
    }

    @Test
    @Order(1)
    @DisplayName("Debeba abrir um sessao com sucesso")
    void abrirSessaoTest() throws Exception {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/v1/sessoes/abertura")
                .setPort(port)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .queryParam("pautaId", "0e059f61-8030-4ed8-a9a8-f60c1f7c7681")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                .extract()
                    .body()
                .asString();

        SessaoDTO sessaoCriada = objectMapper.readValue(content,SessaoDTO.class);
        sessaoDTO = sessaoCriada;

        assertNotNull(sessaoCriada.id());

    }

    @Test
    @Order(2)
    @DisplayName("Deve listar as sessoes com sucesso")
    void findAllTest() throws JsonProcessingException {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/v1/sessoes")
                .setPort(port)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .queryParam("page", 0, "size",10)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                    .body()
                        .asString();

        JsonNode root = objectMapper.readTree(content);
        JsonNode contentNode = root.path("content");

        List<SessaoDTO> sessoes = objectMapper.readValue(contentNode.toString(),
                new TypeReference<List<SessaoDTO>>() {});

        SessaoDTO sessaoDTO = sessoes.get(0);
        assertNotNull(sessaoDTO.id());
    }
}