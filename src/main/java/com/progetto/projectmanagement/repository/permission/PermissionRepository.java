package com.progetto.projectmanagement.repository.permission;

import com.progetto.projectmanagement.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Project,Integer> {
}
