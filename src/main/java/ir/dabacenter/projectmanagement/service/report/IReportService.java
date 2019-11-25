package ir.dabacenter.projectmanagement.service.report;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.report.*;

import java.util.UUID;

public interface IReportService {

	/**
	 * 
	 * @param reposrtTask
	 */
	ResponseModel addReportByTask(ReportByTask reposrtTask,String lang);

	/**
	 * 
	 * @param reportByUser
	 */
	ResponseModel addReportByUser(ReportByUser reportByUser,String lang);

	/**
	 * 
	 * @param projectId
	 */
	ResponseModel getReportByProject(UUID projectId,String lang);

	/**
	 * 
	 * @param taskId
	 */
	ResponseModel getReportByTask(UUID taskId, String lang);

	/**
	 * 
	 * @param userId
	 */
	ResponseModel getReportByUser(UUID userId,String lang);

	/**
	 * 
	 * @param meetingId
	 */
	ResponseModel getReportByMeeting(UUID meetingId,String lang);

	ResponseModel addReportByMeeting(ReportByMeeting reportByMeeting, String lang);
}