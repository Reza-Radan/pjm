package ir.dabacenter.projectmanagement.controller.report;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.report.ReportByMeeting;
import ir.dabacenter.projectmanagement.domain.report.ReportByTask;
import ir.dabacenter.projectmanagement.domain.report.ReportByUser;

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
