package com.github.igomarcelino.jwt_nimbus_jose.service.roles;

import com.github.igomarcelino.jwt_nimbus_jose.BaseIT;
import com.github.igomarcelino.jwt_nimbus_jose.repository.RolesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RolesServiceIT extends BaseIT {

    @Autowired
    RolesRepository rolesRepository;

    @Test
    void deveBuscarRolePorNome(){
        var role = rolesRepository.findByNome("ADMIN");

        assertTrue(role.isPresent());
        assertEquals("ADMIN", role.get().getNome());
    }
}
