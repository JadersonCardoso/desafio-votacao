package br.com.dbserver.desafio.voto;

import br.com.dbserver.desafio.associado.AssociadoRepository;
import br.com.dbserver.desafio.client.ValidadorCpfClient;
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
    private final AssociadoRepository associadoRepository;
    private final ValidadorCpfClient validadorCpfClient;
    public VotoService(VotoRepository votoRepository, PautaRepository pautaRepository, SessaoRepository sessaoRepository, AssociadoRepository associadoRepository, ValidadorCpfClient validadorCpfClient) {
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
        this.associadoRepository = associadoRepository;
        this.validadorCpfClient = validadorCpfClient;
    }

    public void inserirVoto(VotoRequestDTO voto) {

        log.info("Verificando se associado existe no cadastro");
        var associado = this.associadoRepository.findById(voto.associadoId())
                .orElseThrow(() -> new FileNotFoundException("Não foi encontrado associado com ID informado."));

        log.info("Validando CPF do associado.");
        if (!validadorCpfClient.validarCpf(associado.getCpf())) {
            throw new BusinessException("CPF inválido ou não habilitado para votar");
        }

        log.info("verificando se existe a pautaID {} infomado.", voto.pautaId());
        PautaModel pauta = this.pautaRepository.findById(voto.pautaId())
                .orElseThrow(() -> new FileNotFoundException("Não foi encontrada pauta com o ID informado."));

        log.info("Verificando se já existe voto do associaId {} informado.", voto.associadoId());
        if (this.votoRepository.existsByPautaIdAndAssociadoId(voto.pautaId(), voto.associadoId())) {
            throw new BusinessException("Já existe voto do associado para esta pauta.");
        }
        log.info("Verificando se existe sessão aberta para a pauta informada.");
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

        log.info("Salvando voto do associadoId {}", voto.associadoId());
        this.votoRepository.save(votoModel);
    }
}
