package com.progetto.projectmanagement.repository.report;

import com.progetto.projectmanagement.domain.report.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReportRepository extends JpaRepository<UserReport,Integer> {
}
