package com.github.igomarcelino.jwt_nimbus_jose.service.aviso;

import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoRequestDTO;
import com.github.igomarcelino.jwt_nimbus_jose.dto.aviso.AvisoResponseDTO;
import com.github.igomarcelino.jwt_nimbus_jose.mapper.AvisoMapper;
import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Aviso;
import com.github.igomarcelino.jwt_nimbus_jose.repository.AvisoRepository;
import com.github.igomarcelino.jwt_nimbus_jose.service.aviso.exceptions.AvisoNotFoundException;
import com.github.igomarcelino.jwt_nimbus_jose.service.pessoa.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisoService {

    private final AvisoRepository avisoRepository;
    private final AvisoMapper avisoMapper;
    private final PessoaService pessoaService;

    public AvisoService(AvisoRepository avisoRepository, AvisoMapper avisoMapper, PessoaService pessoaService) {
        this.avisoRepository = avisoRepository;
        this.avisoMapper = avisoMapper;
        this.pessoaService = pessoaService;
    }

    public AvisoResponseDTO save(AvisoRequestDTO avisoRequestDTO){
        Aviso aviso =  avisoMapper.toEntity(avisoRequestDTO);
        aviso.setLido(false);
        //var principal = (Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        aviso.setPessoa(pessoaService.getEntityById(1L));
        var avisoCriado = avisoRepository.save(aviso);
        return avisoMapper.toDto(avisoCriado);
    }
    public List<AvisoResponseDTO> getAll(){
        return avisoRepository.findAll()
                .stream()
                .map(avisoMapper::toDto)
                .toList();
    }

    @Transactional
    public AvisoResponseDTO marcarLido(Long idAviso){
        Aviso aviso = getEntityById(idAviso);
        aviso.setLido(true);
        return avisoMapper.toDto(aviso);
    }

    private Aviso getEntityById(Long id){
        return avisoRepository.findById(id)
                .orElseThrow(()-> new AvisoNotFoundException("Aviso nao localizado"));

    }
}
