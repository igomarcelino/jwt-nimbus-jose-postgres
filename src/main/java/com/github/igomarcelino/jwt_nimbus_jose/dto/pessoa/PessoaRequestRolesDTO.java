package com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PessoaRequestRolesDTO(
        @NotNull(message = "informar as roles")
        List<String> roles
) {
}
