package com.github.igomarcelino.jwt_nimbus_jose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.igomarcelino.jwt_nimbus_jose.config.rsa.RsaKeyProperties;
import com.github.igomarcelino.jwt_nimbus_jose.config.security.SecurityConfig;
import com.github.igomarcelino.jwt_nimbus_jose.dto.login.LoginRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.login.LoginResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.service.authentication.LoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@Import(SecurityConfig.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private LoginService loginService;

    @MockitoBean
    private JwtEncoder jwtEncoder;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @MockitoBean
    private RsaKeyProperties rsaKeyProperties;


    @Test
    @DisplayName("Deve retornar 200 OK e o Token JWT ao enviar credenciais válidas")
    void deveRetornarToken_QuandoLoginComSucesso() throws Exception {
        LoginRequestDTO request = new LoginRequestDTO("admin@teste.com", "senha123");
        String jsonPayload = objectMapper.writeValueAsString(request);

        String tokenSimulado = "eyJhbGciOiJSUzI1NiJ9.simulacao.token_seguro";
        LoginResponseDTO responseDTO = new LoginResponseDTO(tokenSimulado);

        Mockito.when(loginService.autenticaUsuario(any(LoginRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(tokenSimulado));
    }

}