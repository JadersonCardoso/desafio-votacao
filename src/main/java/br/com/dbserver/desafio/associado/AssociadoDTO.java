package br.com.dbserver.desafio.associado;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record AssociadoDTO(
        UUID id,
        @NotEmpty(message = "O campo cpf é obrigatório.") String cpf) {

}
