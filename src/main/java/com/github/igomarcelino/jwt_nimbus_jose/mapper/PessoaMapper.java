package com.github.igomarcelino.jwt_nimbus_jose.mapper;

import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Pessoa;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Pessoa toEntity(PessoaRequestDTO dto);

    @Mapping(target = "roles", source = "roles", qualifiedByName="rolesName")
    PessoaResponseDTO toDto(Pessoa pessoa);

    @Named("rolesName")
    default Set<String> mapRolesToString(Set<Roles> roles){
        if (roles == null) return Set.of();
        return roles
                .stream()
                .map(Roles::getNome)
                .collect(Collectors.toSet());
    }
}
