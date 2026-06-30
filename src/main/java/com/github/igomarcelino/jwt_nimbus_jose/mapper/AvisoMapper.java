package com.github.igomarcelino.jwt_nimbus_jose.mapper;

import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Aviso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AvisoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lido", ignore = true)
    Aviso toEntity(AvisoRequestDTO dto);

    AvisoResponseDTO toDto(Aviso aviso);

}
