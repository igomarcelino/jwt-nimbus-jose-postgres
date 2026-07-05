package com.github.igomarcelino.jwt_nimbus_jose.service.pessoa;

import com.github.igomarcelino.jwt_nimbus_jose.BaseIT;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestRolesDTO;
import com.github.igomarcelino.jwt_nimbus_jose.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaServiceIT extends BaseIT {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;


    @Test
    void save_DevePersistirPessoaComRoles_NoBancoReal() {
        // Arrange
        var request = new PessoaRequestDTO("Igo Marcelino", "36145216808", "igo@email.com", "senha123");

        // Act
        var response = pessoaService.save(request);

        // Assert
        assertNotNull(response.id());
        var pessoaNoBanco = pessoaRepository.findById(response.id()).orElseThrow();
        assertEquals("Igo Marcelino", pessoaNoBanco.getNome());
        assertTrue(pessoaNoBanco.getRoles().stream().anyMatch(r -> r.getNome().equals("GUEST")));
    }

    @Test
    void setRoles_DeveAtualizarRoles_NoBancoReal() {
        // Arrange
        var request = new PessoaRequestDTO("Teste", "04923364076", "teste@email.com", "senha");
        var saved = pessoaService.save(request);
        var updateDto = new PessoaRequestRolesDTO(List.of("ADMIN"));

        // Act
        pessoaService.setRoles(saved.id(), updateDto);

        // Assert
        var updatedPessoa = pessoaRepository.findById(saved.id()).orElseThrow();
        assertTrue(updatedPessoa.getRoles().stream().anyMatch(r -> r.getNome().equals("ADMIN")));
        assertEquals(1, updatedPessoa.getRoles().size());
    }
}