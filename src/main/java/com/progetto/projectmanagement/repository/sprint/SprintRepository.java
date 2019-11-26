package com.progetto.projectmanagement.repository.sprint;

import com.progetto.projectmanagement.domain.sprint.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint,Integer> {
}
