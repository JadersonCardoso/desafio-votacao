package br.com.dbserver.desafio.pauta;

import br.com.dbserver.desafio.mapper.MockPauta;
import br.com.dbserver.desafio.testeconteiners.AbstractIntegrationTest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PautaResourceTest extends AbstractIntegrationTest {

    private RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PautaDTO pauta;
    @LocalServerPort
    private int port;
    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MockPauta input = new MockPauta();
        pauta = input.mockDtoRequest();
    }
    @Test
    @DisplayName("Deve cadastrar uma pauta com sucesso.")
    void createTest() throws Exception {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/v1/pautas")
                .setPort(port)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(pauta)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                .extract()
                    .body()
                     .asString();

        PautaDTO createPauta = objectMapper.readValue(content, PautaDTO.class);
        pauta = createPauta;

        assertNotNull(createPauta.id());
        assertEquals("Pauta Teste", createPauta.titulo());

    }


}