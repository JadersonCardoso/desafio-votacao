package br.com.dbserver.desafio.sessao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/sessoes")
public class SessaoResource implements SessaoResourceDocs {

    private final SessaoService service;
    public SessaoResource(SessaoService service) {
        this.service = service;
    }
    @Override
    public ResponseEntity<SessaoDTO> abrirSessao(UUID pautaId, Long duracao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.executarAberturaSessao(pautaId, duracao));
    }

    @Override
    public ResponseEntity<Page<SessaoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }
}
