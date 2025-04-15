package br.com.dbserver.desafio.pauta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record PautaDTO(
        UUID id,
        @NotBlank(message = "O Campo título é obrigatório.") String titulo) {

}
