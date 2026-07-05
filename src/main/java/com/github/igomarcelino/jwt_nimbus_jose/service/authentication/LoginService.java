package com.github.igomarcelino.jwt_nimbus_jose.service.authentication;

import com.github.igomarcelino.jwt_nimbus_jose.dto.login.LoginRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.login.LoginResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Pessoa;
import com.github.igomarcelino.jwt_nimbus_jose.repository.PessoaRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PessoaRepository pessoaRepository;

    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService, PessoaRepository pessoaRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.pessoaRepository = pessoaRepository;
    }

    public LoginResponseDTO autenticaUsuario(LoginRequestDTO loginRequestDTO){
        try {
            UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.email(),
                    loginRequestDTO.senha()
            );
            Authentication authentication = authenticationManager.authenticate(userAuthentication);
            System.out.println("Autenticou");
            Pessoa pessoa = (Pessoa) authentication.getPrincipal();
            String token = tokenService.generateToken(authentication);
            return new LoginResponseDTO(token);
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException(e.getMessage());
        }
    }
}
