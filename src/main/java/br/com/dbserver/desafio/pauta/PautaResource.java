package br.com.dbserver.desafio.pauta;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pautas")
public class PautaResource implements PautaResourceDocs{

    private final PautaService service;
    public PautaResource(PautaService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<PautaDTO> create(@Valid PautaDTO pauta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(pauta));
    }

    @Override
    public ResponseEntity<Page<PautaDTO>> findAll(Pageable pageable) {
        if (this.service.findAll(pageable).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(this.service.findAll(pageable));
    }
}
