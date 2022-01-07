package com.gasstation.api.repositories.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasstation.api.model.auth.ERole;
import com.gasstation.api.model.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}
