package br.com.dbserver.desafio.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/validador-cpf")
public class FakeValidaCpfResource {

    private final Random random = new Random();

    @GetMapping("/{cpf}")
    public ResponseEntity<Map<String, String>> validar(@PathVariable String cpf) {
        int change = random.nextInt(100);

        if (change < 10) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        boolean podeVotar = change% 2 == 0;
        Map<String, String> response = Map.of("status", podeVotar ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE");

        return ResponseEntity.ok(response);
    }
}
