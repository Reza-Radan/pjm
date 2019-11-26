package com.progetto.projectmanagement.repository.report;

import com.progetto.projectmanagement.domain.report.ProjectReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectReportRepository extends JpaRepository<ProjectReport,Integer> {
}
