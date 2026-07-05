package com.github.igomarcelino.jwt_nimbus_jose.repository;

import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByNome(String nome);
}
