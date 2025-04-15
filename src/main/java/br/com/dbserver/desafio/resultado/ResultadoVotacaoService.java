package br.com.dbserver.desafio.resultado;

import br.com.dbserver.desafio.exception.BusinessException;
import br.com.dbserver.desafio.exception.FileNotFoundException;
import br.com.dbserver.desafio.sessao.SessaoModel;
import br.com.dbserver.desafio.sessao.SessaoRepository;
import br.com.dbserver.desafio.voto.VotoRepository;
import br.com.dbserver.desafio.voto.VotoValor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Log4j2
public class ResultadoVotacaoService {

    private final VotoRepository votoRepository;
    private final SessaoRepository sessaoRepository;
    public ResultadoVotacaoService(VotoRepository votoRepository, SessaoRepository sessaoRepository) {
        this.votoRepository = votoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    public ResultadoVotacaoDTO retornaResultado(UUID pautaId) {
        log.info("Verificando se existe sessão de votação para a pautaId {}", pautaId);
        SessaoModel sessao = this.sessaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new FileNotFoundException("Sessão não encontrada para a pauta informada."));

        if (Instant.now().isBefore(sessao.getFim())) {
            throw new BusinessException("Sessão de votação não encerrada");
        }

        log.info("Verificando a quantidade de votos SIM");
        long totalSim = this.votoRepository.countByPautaIdAndValor(pautaId, VotoValor.SIM);
        log.info("Verificando a quantidade de votos NAO");
        long toalNao = this.votoRepository.countByPautaIdAndValor(pautaId, VotoValor.NAO);

        return new ResultadoVotacaoDTO(totalSim, toalNao);

    }

}
