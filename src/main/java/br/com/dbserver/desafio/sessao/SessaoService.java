package br.com.dbserver.desafio.sessao;

import br.com.dbserver.desafio.exception.FileNotFoundException;
import br.com.dbserver.desafio.pauta.PautaRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    public SessaoService(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public SessaoDTO executar(UUID pautaId, Long duracao) {
        var pauta = this.pautaRepository.findById(pautaId).orElseThrow(
                () -> new FileNotFoundException("Nâo existe Pauta com o ID informado"));
        Instant inicio = Instant.now();
        Instant fim = inicio.plus(Duration.ofSeconds(
                duracao != null ? duracao : 60
        ));
        SessaoModel sessao = new SessaoModel();
        sessao.setPauta(pauta);
        sessao.setInicio(inicio);
        sessao.setFim(fim);

        return null;

    }

}
