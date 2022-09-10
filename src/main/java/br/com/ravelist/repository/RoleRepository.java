package br.com.ravelist.repository;

import br.com.ravelist.model.ERole;
import br.com.ravelist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
