package com.progetto.projectmanagement.service.report;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.report.ProjectReport;
import com.progetto.projectmanagement.domain.report.SprintReport;
import com.progetto.projectmanagement.domain.report.TaskReport;
import com.progetto.projectmanagement.domain.report.UserReport;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ReportService implements IReportService {

	@Autowired
	private ReportService reportService;

	@Autowired
	SprintReport sprintReport;

	@Autowired
    ResponseModel responseModel;

	@Autowired
    ResultModel resultModel;

	@Autowired
	ReportValidator reportValidator;

	@Autowired
    RequirementsProperties requirementsProperties;

	org.slf4j.Logger logger = LoggerFactory.getLogger(ReportService.class);

	/**
	 *
	 * @param reportTask
	 * @param lang
	 * @return
	 */
	public ResponseModel addReportByTask(TaskReport reportTask, String lang) {
		reportTask.setLang(lang);
		resultModel = reportValidator.reportTaskValidate(reportTask);

		logger.warn("addReportByTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = sprintReport.addReportByTask(reportTask ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}


	@Override
	public ResponseModel addReportByMeeting(ProjectReport projectReport, String lang) {
		resultModel = reportValidator.reportMeetingValidate(projectReport);

		logger.warn("addTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			//responseModel = sprintReport.addReportByMeeting(projectReport ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	/**
	 *
	 * @param userReport
	 */
	public ResponseModel addReportByUser(UserReport userReport, String lang) {
		userReport.setLang(lang);
		resultModel = reportValidator.reportUserValidate(userReport);

		logger.warn("addReportByTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = sprintReport.addReportByUser(userReport,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	/**
	 *
	 * @param projectId
	 */
	public ResponseModel getReportByProject(UUID projectId,String lang) {
		resultModel = reportValidator.idProjectValidate(projectId ,lang);

		logger.warn("getReportByProject : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = sprintReport.getReportByProject(projectId ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	/**
	 *
	 * @param taskId
	 */
	public ResponseModel getReportByTask(UUID taskId,String lang) {
		resultModel = reportValidator.idTaskValidate(taskId ,lang);

		logger.warn("addTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = sprintReport.getReportByTask(taskId ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	/**
	 *
	 * @param userId
	 */
	public ResponseModel getReportByUser(UUID userId,String lang) {
		resultModel = reportValidator.idUserValidate(userId ,lang);

		logger.warn("addTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = sprintReport.getReportByUser(userId ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	/**
	 *
	 * @param meetingId
	 */
	public ResponseModel getReportByMeeting(UUID meetingId,String lang) {
		resultModel = reportValidator.idUserValidate(meetingId ,lang);

		logger.warn("addTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = sprintReport.getReportByMeeting(meetingId ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

}