package com.progetto.projectmanagement.service.task;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.*;
import com.progetto.projectmanagement.domain.task.Task;
import com.progetto.projectmanagement.domain.task.TaskRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TaskService implements ITaskService {
	@Autowired
	TaskRepository taskRepository;

	@Autowired
    ResponseModel responseModel;

	@Autowired
    ResultModel resultModel;

	@Autowired
	TaskValidator taskValidator;

	@Autowired
    RequirementsProperties requirementsProperties;

	org.slf4j.Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Override
	public ResponseModel addTask(Task taskModel) {

		resultModel = taskValidator.taskValidate(taskModel);

		logger.warn("addTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			resultModel = taskRepository.addTask(taskModel);
			if (resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getTaskDetails(UUID taskId) {
		return null;
	}

	/**
	 * 
	 * @param taskId
	 */
	public ResponseModel getTaskDetails(UUID taskId ,String lang) {

		resultModel = taskValidator.getTaskIdValidate(taskId,lang);

		logger.warn("getTaskDetails : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = taskRepository.getTaskDetail(taskId,lang);

		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel ;
	}

	/**
	 * 
	 * @param taskId
	 * @param status 0 todo, 1 progress , 2 done , archive 3 , 4 disable ,5 enable
	 */
	public ResponseModel changeStatus(UUID taskId, int status ,String lang) {
		resultModel = taskValidator.getStatusValidate(taskId ,status ,lang);
		if (resultModel.getError() ==0){
			resultModel = taskRepository.changeStatus(taskId,status,lang);
			if (resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}

		return responseModel;
	}

	public ResponseModel updateTask(Task task) {
		resultModel = taskValidator.taskValidate(task);

		logger.warn("updateTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			resultModel = taskRepository.updateTask(task);
			if (resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getTaskByProject(UUID pId, int page, String lang) {
		resultModel = taskValidator.projectIdValidate(pId,lang);

		logger.warn("updateTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = taskRepository.getTaskByProject(pId,page,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	@Override
	public ResponseModel deleteTask(UUID taskId, String lang) {
		resultModel = taskValidator.getTaskIdValidate(taskId,lang);

		logger.warn("deleteTask : " + resultModel.getError());
		if(resultModel.getError() == 0){
			resultModel = taskRepository.deleteTask(taskId,lang);
			if (resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}

		return responseModel;
	}

	@Override
	public ResponseModel getTaskByUserImpl(UUID taskId,int page, String lang) {
		resultModel = taskValidator.getTaskIdValidate(taskId,lang);

		logger.warn("getTaskByUserImpl : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = taskRepository.getTaskByUserImpl(taskId,page ,lang);
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}

		return responseModel;
	}

	@Override
	public ResponseModel getTaskByUserResp(UUID taskId,int page, String lang) {
		resultModel = taskValidator.getTaskIdValidate(taskId,lang);

		logger.warn("getTaskByUserResp : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = taskRepository.getTaskByUserResp(taskId,page ,lang);

		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}

		return responseModel;
	}

	@Override
	public ResponseModel getTaskBySubFeature(UUID taskId,int page, String lang) {
		resultModel = taskValidator.getTaskIdValidate(taskId,lang);

		logger.warn("getTaskBySubFeature : " + resultModel.getError());
		if(resultModel.getError() == 0){
			responseModel = taskRepository.getTaskBySubFeature(taskId,page ,lang);

		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}

		return responseModel;
	}

	@Override
	public ResponseModel filterTask(String implementorId, String responsibleId,
									String startDate, String endDate, String priority, String status, int page, String lang) {


			responseModel = taskRepository.filterTask( implementorId,  responsibleId,
					 startDate,  endDate,  priority,  status,  page,  lang);

		logger.warn("filterTask : " + responseModel.getResult());
		return responseModel;
	}
}