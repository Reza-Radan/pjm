package com.progetto.projectmanagement.repository.report;

import com.progetto.projectmanagement.domain.report.TaskReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskReportRepository extends JpaRepository<TaskReport,Integer> {
}
