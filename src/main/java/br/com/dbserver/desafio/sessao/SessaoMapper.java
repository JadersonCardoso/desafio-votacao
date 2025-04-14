package br.com.dbserver.desafio.sessao;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface SessaoMapper {
    @Mapping(source = "pauta", target = "pauta")
    SessaoDTO toDTO(SessaoModel sessaoModel);
    @Mapping(source = "pauta", target = "pauta")
    SessaoModel toEntity(SessaoDTO sessaoDTO);
    @Mapping(source = "pauta", target = "pauta")
    default Page<SessaoDTO> toPageDTO(Page<SessaoModel> sessoes) {
        List<SessaoDTO> sessaoDTOS = sessoes.getContent().stream().map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(sessaoDTOS, sessoes.getPageable(), sessoes.getTotalElements());
    }
}
