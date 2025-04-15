package br.com.dbserver.desafio.resultado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Tag(name = "Resultados", description = "Endpoints para gerenciamento de resultados dos Votos")
public interface ResultadoVotacaoResourceDocs {

    @GetMapping("/{pautaId}")
    @Operation(summary = "Retorna resultado da votação", description = "Retorna o resultado da votação", tags = "Resultados",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ResultadoVotacaoDTO.class)))}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

            })
    ResponseEntity<ResultadoVotacaoDTO> obterResultado(@PathVariable UUID pautaId);
}
