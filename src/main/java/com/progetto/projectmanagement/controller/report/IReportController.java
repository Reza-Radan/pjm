package com.progetto.projectmanagement.controller.report;

import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.report.ProjectReport;
import com.progetto.projectmanagement.domain.report.TaskReport;
import com.progetto.projectmanagement.domain.report.UserReport;

import java.util.UUID;

public interface IReportController {
    ResponseModel getReportByTask(UUID taskId, String lang);
    ResponseModel getReportByUser(UUID userId ,String lang);
    ResponseModel getReportByMeeting(UUID meetingId,String lang);
    ResponseModel getReportByProject(UUID projectId,String lang);

    ResponseModel addReportByTask(TaskReport reposrtTask, String lang);
    ResponseModel addReportByUser(UserReport userReport, String lang);
    ResponseModel addReportByMeeting(ProjectReport projectReport, String lang);
}
