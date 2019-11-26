package com.progetto.projectmanagement.repository.role;

import com.progetto.projectmanagement.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
