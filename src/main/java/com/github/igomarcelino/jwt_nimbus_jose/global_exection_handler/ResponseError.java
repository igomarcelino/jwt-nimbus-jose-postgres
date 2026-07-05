package com.github.igomarcelino.jwt_nimbus_jose.global_exection_handler;


import java.sql.Timestamp;

public record ResponseError(
        String cause,
        String message,
        int statusCode,
        Timestamp date
) {
}
