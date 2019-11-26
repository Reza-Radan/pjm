package com.progetto.projectmanagement.service.report;

import com.progetto.projectmanagement.domain.report.ProjectReport;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.report.TaskReport;
import com.progetto.projectmanagement.domain.report.UserReport;
import ir.dabacenter.projectmanagement.domain.report.*;

import java.util.UUID;

public interface IReportService {

	/**
	 * 
	 * @param reposrtTask
	 */
	ResponseModel addReportByTask(TaskReport reposrtTask, String lang);

	/**
	 * 
	 * @param userReport
	 */
	ResponseModel addReportByUser(UserReport userReport, String lang);

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

	ResponseModel addReportByMeeting(ProjectReport projectReport, String lang);
}