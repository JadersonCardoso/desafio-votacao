package br.com.dbserver.desafio.associado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/associados")
public class AssociadoResource implements AssociadoResourceDocs{

    private final AssociadoService service;
    public AssociadoResource(AssociadoService service) {
        this.service = service;
    }
    @Override
    public ResponseEntity<Page<AssociadoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }
}
