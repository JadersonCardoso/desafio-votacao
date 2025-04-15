package br.com.dbserver.desafio.voto;

import br.com.dbserver.desafio.mapper.MockVoto;
import br.com.dbserver.desafio.payloads.ResponseApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VotoResourceTest {

    @LocalServerPort
    private int port;
    private RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static VotoRequestDTO requestDTO;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        MockVoto input = new MockVoto();
        requestDTO = input.mockRequest();
    }
    @Test
    @DisplayName("Deve retornar voto salvo com suvesso.")
    void votarTest() throws JsonProcessingException {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/v1/votos/votar")
                .setPort(port)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .body(requestDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                .extract()
                    .body()
                        .asString();

        ResponseApi response = objectMapper.readValue(content, ResponseApi.class);

        Assertions.assertTrue(response.sucesso());
        Assertions.assertTrue(response.mensagem().contains("Voto realizado com sucesso."));

    }
}