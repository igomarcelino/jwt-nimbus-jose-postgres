package com.github.igomarcelino.jwt_nimbus_jose;

import com.github.igomarcelino.jwt_nimbus_jose.config.rsa.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(RsaKeyProperties.class)
public class JwtNimbusJoseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtNimbusJoseApplication.class, args);
	}

}
