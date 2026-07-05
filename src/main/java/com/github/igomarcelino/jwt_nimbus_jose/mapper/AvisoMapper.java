package com.github.igomarcelino.jwt_nimbus_jose.mapper;

import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Aviso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PessoaMapper.class})
public interface AvisoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lido", ignore = true)
    @Mapping(target = "pessoa", ignore = true)
    Aviso toEntity(AvisoRequestDTO dto);

    @Mapping(target = "conteudo", source = "conteudo")
    @Mapping(target = "lido", source = "lido")
    @Mapping(target = "criadoPor", source = "pessoa")
    AvisoResponseDTO toDto(Aviso aviso);

}
