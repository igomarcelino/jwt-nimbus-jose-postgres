package com.github.igomarcelino.jwt_nimbus_jose.repository;

import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
