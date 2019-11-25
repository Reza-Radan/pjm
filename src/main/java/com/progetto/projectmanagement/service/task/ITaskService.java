package com.progetto.projectmanagement.service.task;

import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.task.Task;
import ir.dabacenter.projectmanagement.domain.*;

import java.util.UUID;

public interface ITaskService {

	/**
	 * 
	 * @param taskModel
	 */
	ResponseModel addTask(Task taskModel);

	/**
	 * 
	 * @param taskId
	 */
	ResponseModel getTaskDetails(UUID taskId);

	/**
	 * 
	 * @param taskId
	 * @param status 0 todo, 1 progress , 2 done , archive 3
	 */
	ResponseModel changeStatus(UUID taskId, int status ,String lang);

	ResponseModel updateTask(Task task);

    ResponseModel getTaskByProject(UUID pId, int page, String lang);

	ResponseModel deleteTask(UUID taskId, String lang);

	ResponseModel getTaskByUserImpl(UUID taskId, int page, String lang);

	ResponseModel getTaskByUserResp(UUID taskId, int page, String lang);

	ResponseModel getTaskBySubFeature(UUID taskId,int page, String lang);

	ResponseModel filterTask(String implementorId, String responsibleId, String startDate, String endDate, String priority, String status, int page, String lang);
}