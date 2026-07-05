package com.github.igomarcelino.jwt_nimbus_jose.service.authentication;

import com.github.igomarcelino.jwt_nimbus_jose.repository.PessoaRepository;
import com.github.igomarcelino.jwt_nimbus_jose.service.pessoa.exceptions.PessoaNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final PessoaRepository pessoaRepository;

    public AuthenticationService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pessoaRepository.findByEmail(username)
                .orElseThrow(()-> new PessoaNotFoundException("Usuario nao localizado"));
    }
}
