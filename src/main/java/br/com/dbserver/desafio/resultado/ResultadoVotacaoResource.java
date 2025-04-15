package br.com.dbserver.desafio.resultado;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/resultados")
public class ResultadoVotacaoResource implements ResultadoVotacaoResourceDocs{

    private final ResultadoVotacaoService service;
    public ResultadoVotacaoResource(ResultadoVotacaoService service) {
        this.service = service;
    }
    @Override
    public ResponseEntity<ResultadoVotacaoDTO> obterResultado(UUID pautaId) {
        return ResponseEntity.ok(this.service.retornaResultado(pautaId));
    }
}
