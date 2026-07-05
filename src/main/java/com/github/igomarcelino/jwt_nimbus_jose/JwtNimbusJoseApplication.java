package com.github.igomarcelino.jwt_nimbus_jose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JwtNimbusJoseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtNimbusJoseApplication.class, args);
	}

}
