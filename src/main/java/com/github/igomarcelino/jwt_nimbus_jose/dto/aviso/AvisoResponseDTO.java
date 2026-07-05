package com.github.igomarcelino.jwt_nimbus_jose.dto.aviso;

import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaResponseDTO;

public record AvisoResponseDTO(
        Long id,
        String conteudo,
        Boolean lido,
        PessoaResponseDTO criadoPor
) {
}
