package com.github.igomarcelino.jwt_nimbus_jose.controller;

import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.service.aviso.AvisoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/aviso")
public class AvisoController {

    private final AvisoService avisoService;

    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_GUEST')")
    public ResponseEntity<AvisoResponseDTO> save(@RequestBody AvisoRequestDTO avisoRequestDTO,
                                                 UriComponentsBuilder builder){
        var aviso = avisoService.save(avisoRequestDTO);
        URI uri = builder.path("/aviso/{id}").buildAndExpand(aviso.id()).toUri();
        return ResponseEntity.created(uri).body(aviso);
    }

    @GetMapping
    public ResponseEntity<List<AvisoResponseDTO>> getAll(){
        return ResponseEntity.ok(avisoService.getAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AvisoResponseDTO> setLido(@PathVariable Long id){
        return ResponseEntity.ok(avisoService.marcarLido(id));
    }
}
