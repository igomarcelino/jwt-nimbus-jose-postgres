package com.github.igomarcelino.jwt_nimbus_jose.service.pessoa;

import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaRequestRolesDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.pessoa.PessoaResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.mapper.PessoaMapper;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Pessoa;
import com.github.igomarcelino.jwt_nimbus_jose.repository.PessoaRepository;
import com.github.igomarcelino.jwt_nimbus_jose.service.pessoa.exceptions.PessoaNotFoundException;
import com.github.igomarcelino.jwt_nimbus_jose.service.roles.RolesService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolesService rolesService;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper, PasswordEncoder passwordEncoder, RolesService rolesService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.passwordEncoder = passwordEncoder;
        this.rolesService = rolesService;
    }

    public PessoaResponseDTO save(PessoaRequestDTO requestDTO){
        var roleGuest = rolesService.getRoleByName("GUEST");
        Pessoa pessoa = pessoaMapper.toEntity(requestDTO);
        pessoa.setSenha(passwordEncoder.encode(requestDTO.senha()));
        pessoa.setRoles(Set.of(roleGuest));
        var pessoaSalva = pessoaRepository.save(pessoa);
        return pessoaMapper.toDto(pessoaSalva);
    }

    public List<PessoaResponseDTO> getAll(){
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaMapper::toDto)
                .toList();
    }

    @Transactional
    public PessoaResponseDTO setRoles(Long id, PessoaRequestRolesDTO dto){
        Pessoa pessoa = getEntityById(id);
        var roles = dto.roles().stream()
                .map(rolesService::getRoleByName)
                .collect(Collectors.toSet());
        pessoa.setRoles(roles);
        return pessoaMapper.toDto(pessoa);
    }

    public Pessoa getEntityById(Long id){
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa nao localizada!"));
    }

    public Pessoa getByEmail(String email){
        return pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa nao localizada"));
    }
}
