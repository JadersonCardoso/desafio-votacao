package br.com.dbserver.desafio.associado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    private final AssociadoRepository repository;
    private final AssociadoMapper mapper;
    public AssociadoService(AssociadoRepository repository, AssociadoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<AssociadoDTO> findAll(Pageable pageable) {
        return this.mapper.toPageDTO(this.repository.findAll(pageable));
    }

}
