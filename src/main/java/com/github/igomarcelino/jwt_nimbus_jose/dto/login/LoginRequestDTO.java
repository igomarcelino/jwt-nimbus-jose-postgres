package com.github.igomarcelino.jwt_nimbus_jose.dto.login;

public record LoginRequestDTO(
        String email,
        String senha
) {
}
