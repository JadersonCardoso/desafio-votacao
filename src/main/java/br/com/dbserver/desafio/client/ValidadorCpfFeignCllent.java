package br.com.dbserver.desafio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "validadorCpf", url = "http://localhost:8080")
public interface ValidadorCpfFeignCllent {
    @GetMapping("/validador-cpf/{cpf}")
    ResponseEntity<Map<String, String>> validarCpf(@PathVariable("cpf") String cpf);
}
