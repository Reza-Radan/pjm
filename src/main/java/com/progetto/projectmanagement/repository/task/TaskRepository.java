package com.progetto.projectmanagement.repository.task;

import com.progetto.projectmanagement.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
