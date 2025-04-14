package br.com.dbserver.desafio.sessao;

import br.com.dbserver.desafio.pauta.PautaDTO;

import java.time.Instant;
import java.util.UUID;

public record SessaoDTO(UUID id, Instant inicio, Instant fim, PautaDTO pauta) {
}
