package com.progetto.projectmanagement.service.report;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.report.ReportByMeeting;
import com.progetto.projectmanagement.domain.report.ReportByTask;
import com.progetto.projectmanagement.domain.report.ReportByUser;
import com.progetto.projectmanagement.domain.report.ReportRepository;
import ir.dabacenter.projectmanagement.domain.*;
import ir.dabacenter.projectmanagement.domain.report.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ReportService implements IReportService {

	@Autowired
	private ReportService reportService;

	@Autowired
    ReportRepository reportRepository;

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
	public ResponseModel addReportByTask(ReportByTask reportTask, String lang) {
		reportTask.setLang(lang);
		resultModel = reportValidator.reportTaskValidate(reportTask);

		logger.warn("addReportByTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = reportRepository.addReportByTask(reportTask ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}


	@Override
	public ResponseModel addReportByMeeting(ReportByMeeting reportByMeeting, String lang) {
		resultModel = reportValidator.reportMeetingValidate(reportByMeeting );

		logger.warn("addTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			//responseModel = reportRepository.addReportByMeeting(reportByMeeting ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	/**
	 *
	 * @param reportByUser
	 */
	public ResponseModel addReportByUser(ReportByUser reportByUser, String lang) {
		reportByUser.setLang(lang);
		resultModel = reportValidator.reportUserValidate(reportByUser);

		logger.warn("addReportByTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = reportRepository.addReportByUser(reportByUser ,lang);
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
			responseModel = reportRepository.getReportByProject(projectId ,lang);
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
			responseModel = reportRepository.getReportByTask(taskId ,lang);
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
			responseModel = reportRepository.getReportByUser(userId ,lang);
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
			responseModel = reportRepository.getReportByMeeting(meetingId ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

}