package com.github.igomarcelino.jwt_nimbus_jose.controller;

import com.github.igomarcelino.jwt_nimbus_jose.dto.login.LoginRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.login.LoginResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.service.authentication.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        var usuario = loginService.autenticaUsuario(request);
        return ResponseEntity.ok(usuario);
    }
}
