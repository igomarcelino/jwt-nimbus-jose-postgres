package com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa;

public record PessoaRequestDTO(
        String nome,
        String cpf,
        String email,
        String senha
) {
}
