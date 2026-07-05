package com.github.igomarcelino.jwt_nimbus_jose.mapper;

import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Aviso;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-05T13:05:54-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class AvisoMapperImpl implements AvisoMapper {

    @Autowired
    private PessoaMapper pessoaMapper;

    @Override
    public Aviso toEntity(AvisoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Aviso aviso = new Aviso();

        aviso.setConteudo( dto.conteudo() );

        return aviso;
    }

    @Override
    public AvisoResponseDTO toDto(Aviso aviso) {
        if ( aviso == null ) {
            return null;
        }

        String conteudo = null;
        Boolean lido = null;
        PessoaResponseDTO criadoPor = null;
        Long id = null;

        conteudo = aviso.getConteudo();
        lido = aviso.getLido();
        criadoPor = pessoaMapper.toDto( aviso.getPessoa() );
        id = aviso.getId();

        AvisoResponseDTO avisoResponseDTO = new AvisoResponseDTO( id, conteudo, lido, criadoPor );

        return avisoResponseDTO;
    }
}
