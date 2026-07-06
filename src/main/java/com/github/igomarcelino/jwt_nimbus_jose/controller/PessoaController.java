package com.github.igomarcelino.jwt_nimbus_jose.controller;

import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestRolesDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.service.pessoa.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_EDITOR') or hasAuthority('SCOPE_GUEST')")
    public ResponseEntity<PessoaResponseDTO> save(@RequestBody @Validated PessoaRequestDTO requestDTO,
                                                  UriComponentsBuilder builder){
        var pessoa = pessoaService.save(requestDTO);
        URI uri = builder.path("/pessoa/{id}").buildAndExpand(pessoa.id()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_EDITOR') or hasAuthority('SCOPE_GUEST')")
    public ResponseEntity<List<PessoaResponseDTO>> getAll(){
        return ResponseEntity.ok(pessoaService.getAll());
    }

    @PutMapping("/set-roles/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<PessoaResponseDTO> setRole(@PathVariable Long id,@RequestBody PessoaRequestRolesDTO requestDTO){
        return ResponseEntity.ok(pessoaService.setRoles(id,requestDTO));
    }

    @GetMapping("/user-admin")
    public ResponseEntity<Void> criaUsuarioAdmin(){
        pessoaService.criaUsuarioAdmin();
        return ResponseEntity.ok().build();
    }
}
