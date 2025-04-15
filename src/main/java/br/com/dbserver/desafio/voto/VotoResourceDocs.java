package br.com.dbserver.desafio.voto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Votos", description = "Endpoints para gerancimento de Votos")
public interface VotoResourceDocs {
    @PostMapping(value = "/votar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Cria voto de um associado para uma pauta",
            description = "Cria voto de um associado para uma pauta",
            tags ={"Votos"},
            responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VotoModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<?> votar(@RequestBody VotoRequestDTO request);
}
