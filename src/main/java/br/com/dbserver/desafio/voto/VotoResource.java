package br.com.dbserver.desafio.voto;

import br.com.dbserver.desafio.payloads.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/votos")
public class VotoResource implements VotoResourceDocs {

    private final VotoService votoService;
    public VotoResource(VotoService votoService) {
        this.votoService = votoService;
    }
    @Override
    public ResponseEntity<?> votar(VotoRequestDTO request) {
        this.votoService.inserirVoto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi(true,"Voto realizado com sucesso."));
    }
}
