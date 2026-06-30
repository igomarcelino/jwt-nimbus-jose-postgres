package com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa;

import java.util.Set;

public record PessoaResponseDTO(
        Long id,
        String nome,
        String email,
        Set<String> roles
) {
}
