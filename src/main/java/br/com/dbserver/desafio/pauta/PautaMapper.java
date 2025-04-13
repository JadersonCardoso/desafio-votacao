package br.com.dbserver.desafio.pauta;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
@Component
@Mapper(componentModel = "spring")
public interface PautaMapper {
    PautaDTO toDto(PautaModel pauta);
    PautaModel toEntity(PautaDTO pautaDTO);

}
