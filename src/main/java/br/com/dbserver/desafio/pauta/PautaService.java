package br.com.dbserver.desafio.pauta;


import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper mapper;
    public PautaService(PautaRepository pautaRepository, PautaMapper mapper) {
        this.pautaRepository = pautaRepository;
        this.mapper = mapper;
    }
    public PautaDTO create(PautaDTO dto) {
        log.info("Cadastrando nova pauta.");
        var entity = this.mapper.toEntity(dto);
        return this.mapper.toDto(this.pautaRepository.save(entity));
    }

    public Page<PautaDTO> findAll(Pageable pageable) {
        log.info("Listando todas as pautas cadastradas");
        return this.mapper.toPageDto(this.pautaRepository.findAll(pageable));
    }

}
