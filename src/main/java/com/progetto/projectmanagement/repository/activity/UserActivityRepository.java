package com.progetto.projectmanagement.repository.activity;

import com.progetto.projectmanagement.domain.activity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity,Integer> {
}
