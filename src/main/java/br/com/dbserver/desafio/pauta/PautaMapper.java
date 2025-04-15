package br.com.dbserver.desafio.pauta;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//@Component
@Mapper(componentModel = "spring")
public interface PautaMapper {
    PautaDTO toDto(PautaModel pauta);
    PautaModel toEntity(PautaDTO pautaDTO);
    default Page<PautaDTO> toPageDto(Page<PautaModel> pautas) {
        List<PautaDTO> pautaDTOS = pautas.getContent().stream().map(this::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(pautaDTOS, pautas.getPageable(), pautas.getTotalElements());
    }

}
