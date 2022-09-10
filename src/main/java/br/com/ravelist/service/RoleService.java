package br.com.ravelist.service;

import br.com.ravelist.model.ERole;
import br.com.ravelist.model.Role;
import br.com.ravelist.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    final RoleRepository roleRepository;

    public Role findByName(ERole name) throws RuntimeException {
        return roleRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException("Role is not found."));
    }

}
