package com.github.igomarcelino.jwt_nimbus_jose.service.roles;

import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Roles;
import com.github.igomarcelino.jwt_nimbus_jose.repository.RolesRepository;
import com.github.igomarcelino.jwt_nimbus_jose.service.roles.exception.RolesNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RolesServiceTest {

    @Mock
    RolesRepository rolesRepository;

    @InjectMocks
    RolesService rolesService;


    @Test
    void getRoleByName_Deve_Retornar_Se_Existente() {
        //ARRANGE
        Roles roleMock = new Roles(1L, "ADMIN");
        when(rolesService.getAllRolesCached()).thenReturn(List.of(roleMock));
        //ACT
        var result = rolesService.getRoleByName("ADMIN");
        //ASSERT
        assertNotNull(result);
        assertEquals("ADMIN", result.getNome());
        verify(rolesRepository,times(0)).findAll();
    }

    @Test
    void getRoleBYName_Deve_Lancar_Exception_Se_Nao_Localizar(){
        //arrange
        when(rolesService.getAllRolesCached()).thenReturn(List.of());
        // act & assert
        assertThrows(RolesNotFoundException.class, () -> {rolesService.getRoleByName("VISITANTE");});
    }
}