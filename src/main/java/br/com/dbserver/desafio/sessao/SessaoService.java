package br.com.dbserver.desafio.sessao;

import br.com.dbserver.desafio.exception.BusinessException;
import br.com.dbserver.desafio.exception.FileNotFoundException;
import br.com.dbserver.desafio.pauta.PautaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@Log4j2
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final SessaoMapper mapper;
    public SessaoService(SessaoRepository sessaoRepository, PautaRepository pautaRepository, SessaoMapper mapper) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.mapper = mapper;
    }
    public SessaoDTO executarAberturaSessao(UUID pautaId, Long duracao) {
        log.info("Iniciando a abertura de sessão para a pautaId {}", pautaId);
        var pauta = this.pautaRepository.findById(pautaId).orElseThrow(
                () -> new FileNotFoundException("Nâo existe Pauta com o ID informado"));

        log.info("Verificando se já  existe sessão para a pauta informada");
        if (this.sessaoRepository.existsByPautaId(pautaId)) {
            throw new BusinessException("Já existe sessão aberta/fechada para a pautaID " + pautaId);
        }
        Instant inicio = Instant.now();
        Instant fim = inicio.plus(Duration.ofMinutes(
                duracao != null ? duracao : 1
        ));

        SessaoModel sessao = new SessaoModel();
        sessao.setPauta(pauta);
        sessao.setInicio(inicio);
        sessao.setFim(fim);
        log.info("Salvando a sessão para a pauta informada");
        return this.mapper.toDTO(this.sessaoRepository.save(sessao));
    }
    public Page<SessaoDTO> findAll(Pageable pageable) {
        return this.mapper.toPageDTO(this.sessaoRepository.findAll(pageable));
    }

}
