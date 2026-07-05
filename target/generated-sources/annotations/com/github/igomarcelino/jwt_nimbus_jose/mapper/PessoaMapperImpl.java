package com.github.igomarcelino.jwt_nimbus_jose.mapper;

import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Pessoa;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-05T08:54:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class PessoaMapperImpl implements PessoaMapper {

    @Override
    public Pessoa toEntity(PessoaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setNome( dto.nome() );
        pessoa.setCpf( dto.cpf() );
        pessoa.setEmail( dto.email() );

        return pessoa;
    }

    @Override
    public PessoaResponseDTO toDto(Pessoa pessoa) {
        if ( pessoa == null ) {
            return null;
        }

        Long id = null;
        Set<String> roles = null;
        String nome = null;
        String email = null;

        id = pessoa.getId();
        roles = mapRolesToString( pessoa.getRoles() );
        nome = pessoa.getNome();
        email = pessoa.getEmail();

        PessoaResponseDTO pessoaResponseDTO = new PessoaResponseDTO( id, nome, email, roles );

        return pessoaResponseDTO;
    }
}
