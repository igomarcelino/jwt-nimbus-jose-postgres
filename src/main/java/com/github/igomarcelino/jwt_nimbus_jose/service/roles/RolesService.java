package com.github.igomarcelino.jwt_nimbus_jose.service.roles;

import com.github.igomarcelino.jwt_nimbus_jose.model.entity.Roles;
import com.github.igomarcelino.jwt_nimbus_jose.repository.RolesRepository;
import com.github.igomarcelino.jwt_nimbus_jose.service.roles.exception.RolesNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private  final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }



    public Roles getRoleByName(String role){
        return getAllRolesCached()
                .stream()
                .filter(r -> r.getNome().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(()-> new RolesNotFoundException("Role nao localizada"));
    }

    @Cacheable("rolesList")
    public List<Roles> getAllRolesCached(){
        return rolesRepository.findAll();
    }
}
