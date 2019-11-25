package com.progetto.projectmanagement.controller.report;

import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.report.ReportByMeeting;
import com.progetto.projectmanagement.domain.report.ReportByTask;
import com.progetto.projectmanagement.domain.report.ReportByUser;

import java.util.UUID;

public interface IReportController {
    ResponseModel getReportByTask(UUID taskId, String lang);
    ResponseModel getReportByUser(UUID userId ,String lang);
    ResponseModel getReportByMeeting(UUID meetingId,String lang);
    ResponseModel getReportByProject(UUID projectId,String lang);

    ResponseModel addReportByTask(ReportByTask reposrtTask,String lang);
    ResponseModel addReportByUser(ReportByUser reportByUser,String lang);
    ResponseModel addReportByMeeting(ReportByMeeting reportByMeeting,String lang);
}
