package com.github.igomarcelino.jwt_nimbus_jose.repository;

import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    Optional<Pessoa> findByEmail(String email);
    boolean existsByEmail(String email);
}
