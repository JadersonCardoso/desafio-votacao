package br.com.dbserver.desafio.associado;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface AssociadoMapper {
    AssociadoDTO toDTO(AssociadoModel associado);

    default Page<AssociadoDTO> toPageDTO(Page<AssociadoModel> associados) {
        List<AssociadoDTO> associadoDTOS = associados.getContent().stream().map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(associadoDTOS, associados.getPageable(), associados.getTotalElements());
    }
}
