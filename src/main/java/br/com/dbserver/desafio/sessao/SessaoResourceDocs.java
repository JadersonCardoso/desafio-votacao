package br.com.dbserver.desafio.sessao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Tag(name ="Sessoes", description = "Endpoints para gerenciamento de sessões")
public interface SessaoResourceDocs {

    @PostMapping("/abertura")
    @Operation(summary = "Abre uma nova sessão",
            description = "Abre uma nova sessão",
            tags = "Sessoes",
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = SessaoDTO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

            }
    )
    ResponseEntity<SessaoDTO> abrirSessao(@RequestParam(value = "pautaId") UUID pautaId,
                                          @RequestParam(value = "duracao",required = false) Long duracao);
    @GetMapping
    @Operation(summary = "Listas todas as Sessões", description = "Lista todas as sessãoes", tags = "Sessoes",
    responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = SessaoDTO.class)))}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    ResponseEntity<Page<SessaoDTO>> findAll(@PageableDefault(page = 0, size = 10)Pageable pageable);


}
