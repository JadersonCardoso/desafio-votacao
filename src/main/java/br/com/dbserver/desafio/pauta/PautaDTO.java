package br.com.dbserver.desafio.pauta;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PautaDTO(
        UUID id,
        @NotNull(message = "O Campo título é obrigatório.") String titulo) {

}
