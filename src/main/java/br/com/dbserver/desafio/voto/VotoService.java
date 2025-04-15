package br.com.dbserver.desafio.voto;

import br.com.dbserver.desafio.exception.BusinessException;
import br.com.dbserver.desafio.exception.FileNotFoundException;
import br.com.dbserver.desafio.pauta.PautaModel;
import br.com.dbserver.desafio.pauta.PautaRepository;
import br.com.dbserver.desafio.sessao.SessaoModel;
import br.com.dbserver.desafio.sessao.SessaoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class VotoService {

    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;
    public VotoService(VotoRepository votoRepository, PautaRepository pautaRepository, SessaoRepository sessaoRepository) {
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
    }

    public void inserirVoto(VotoRequest voto) {
        log.info("verificando se existe a pauta infomada.");
        PautaModel pauta = this.pautaRepository.findById(voto.pautaId())
                .orElseThrow(() -> new FileNotFoundException("Não foi encontrada pauta com o ID informado."));

        log.info("Verificando se já existe voto do associado informado.");
        if (this.votoRepository.existsByPautaIdAndAndAssociadoId(voto.pautaId(), voto.associadoId())) {
            throw new BusinessException("Já existe voto do associado pata esta pauta.");
        }
        log.info("Verificando se existe sessão aberta para a pauta informada");
        SessaoModel sessao = this.sessaoRepository.findByPautaId(voto.pautaId())
                .orElseThrow(() -> new FileNotFoundException("Não foi encontrada sessão para esta pauta."));

        log.info("Verificando se sessão pata votação está aberta.");
        Instant agora = Instant.now();
        if (agora.isBefore(sessao.getInicio()) || agora.isAfter(sessao.getFim())) {
            throw new BusinessException("A sessão de votação não está aberta no momento.");
        }

        VotoModel votoModel = new VotoModel();
        votoModel.setAssociadoId(voto.associadoId());
        votoModel.setPauta(pauta);
        votoModel.setValor(VotoValor.valueOf(voto.votoValor()));

        log.info("Salvando voto do associado..");
        this.votoRepository.save(votoModel);

    }
}
