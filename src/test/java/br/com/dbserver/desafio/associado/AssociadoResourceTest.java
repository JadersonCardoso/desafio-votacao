package br.com.dbserver.desafio.associado;

import br.com.dbserver.desafio.mapper.MockAssociado;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
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

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssociadoResourceTest {

    private static   MockAssociado input;
    private RequestSpecification specification;
    private static ObjectMapper objectMapper;
    @LocalServerPort
    private int port;
    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        input = new MockAssociado();
    }

    @Test
    @DisplayName("Retorna Lisgemt dos associados com sucesso.")
    void findAllTest() throws Exception {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/v1/associados")
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

        List<AssociadoDTO> associados = objectMapper.readValue(contentNode.toString(),
                new TypeReference<List<AssociadoDTO>>() {});

        AssociadoDTO associadoDTO = associados.get(0);
        assertNotNull(associadoDTO.id());

    }


}
