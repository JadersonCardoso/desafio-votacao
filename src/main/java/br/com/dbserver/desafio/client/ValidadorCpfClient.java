package br.com.dbserver.desafio.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidadorCpfClient {

    private final ValidadorCpfFeignCllent feignCllent;
    public ValidadorCpfClient(ValidadorCpfFeignCllent feignCllent) {
        this.feignCllent = feignCllent;
    }
    public boolean validarCpf(String cpf) {
        try {
            ResponseEntity<Map<String, String>> response = this.feignCllent.validarCpf(cpf);
            String status = response.getBody().get("status");
            return "ABLE_TO_VOTE".equals(status);

        } catch (Exception e) {
            return false;
        }
    }
}
