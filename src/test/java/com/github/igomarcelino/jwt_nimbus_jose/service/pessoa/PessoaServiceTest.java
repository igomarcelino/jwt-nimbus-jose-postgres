package com.github.igomarcelino.jwt_nimbus_jose.service.pessoa;

import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestRolesDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.mapper.PessoaMapper;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Pessoa;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Roles;
import com.github.igomarcelino.jwt_nimbus_jose.repository.PessoaRepository;
import com.github.igomarcelino.jwt_nimbus_jose.service.roles.RolesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    PessoaService pessoaService;

    @Mock
    RolesService rolesService;

    @Mock
    PessoaMapper pessoaMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void save() {

        // arrange
        var roleGuest = new Roles(1L, "GUEST");
        var pessoaRequest = new PessoaRequestDTO("Igo","36145216808","teste@email.com","1234");
        var pessoa = new Pessoa();
        var response = new PessoaResponseDTO(1L,"Igo","teste@email.com", Set.of("GUEST"));

        when(rolesService.getRoleByName("GUEST")).thenReturn(roleGuest);
        when(pessoaMapper.toEntity(any(PessoaRequestDTO.class))).thenReturn(pessoa);
        when(passwordEncoder.encode("1234")).thenReturn("hashed_password");
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        when(pessoaMapper.toDto(pessoa)).thenReturn(response);

        // act

        var result = pessoaService.save(pessoaRequest);

        assertNotNull(result);;
        assertEquals("GUEST", result.roles().stream().findFirst().get());
        assertEquals("Igo",result.nome());
        assertEquals("teste@email.com", result.email());

        verify(pessoaRepository).save(any(Pessoa.class));
        verify(pessoaRepository,times(1)).save(any(Pessoa.class));
        verify(pessoaMapper,times(1)).toDto(any(Pessoa.class));
        verify(pessoaMapper,times(1)).toEntity(any(PessoaRequestDTO.class));
        verify(passwordEncoder,times(1)).encode(any());
    }



    @Test
    void setRoles_DeveAtualizarRolesComSucesso() {
        // Arrange
        var roleAdmin = new Roles(2L, "ADMIN");
        var dto = new PessoaRequestRolesDTO(List.of("ADMIN"));
        var pessoa = Pessoa.builder().id(1L).roles(new HashSet<>()).build();
        var response = new PessoaResponseDTO(1L, "Igo", "email@email.com", Set.of("ADMIN"));

        // Mocks
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(rolesService.getRoleByName("ADMIN")).thenReturn(roleAdmin);
        when(pessoaMapper.toDto(any(Pessoa.class))).thenReturn(response);

        // Act
        var result = pessoaService.setRoles(1L, dto);

        // Assert
        assertNotNull(result);
        assertTrue(pessoa.getRoles().contains(roleAdmin));
        verify(pessoaMapper).toDto(pessoa);
    }

    @Test
    void getEntityById() {
    }
}