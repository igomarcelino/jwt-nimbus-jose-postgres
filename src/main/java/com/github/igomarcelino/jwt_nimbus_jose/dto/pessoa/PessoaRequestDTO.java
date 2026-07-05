package com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDTO(
        @NotBlank(message = "Nome Obrigatorio")
        String nome,
        @NotBlank(message = "Cpf obrigatorio")
        String cpf,
        @NotBlank(message = "e-mail obrigatorio")
        String email,
        @NotBlank(message = "senha obrigatorio")
        String senha
) {
}
