package br.com.dbserver.desafio.pauta;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record PautaDTO(
        UUID id,
        @NotEmpty(message = "O Campo título é obrigatório.") String titulo) {

}
