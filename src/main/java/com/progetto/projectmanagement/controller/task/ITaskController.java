package com.progetto.projectmanagement.controller.task;

import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.task.Task;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

public interface ITaskController {

    ResponseModel addTask(Task task , HttpServletResponse httpServletResponse);
    ResponseModel getTaskDetails(UUID taskId ,String lang);
    ResponseModel getAllTask(Task task,int page);
    ResponseModel getTaskByProject(UUID pId, int page ,String lang);
    ResponseModel getTaskByUserImplementor(UUID taskId ,int page,String lang);
    ResponseModel getTaskByUserResp(UUID taskId ,int page,String lang);
    ResponseModel getTaskBySubFeature(UUID taskId ,int page,String lang);

    ResponseModel updateTask(Task task);

    ResponseModel deleteTask(UUID taskId ,String lang);

    ResponseModel changeStatus(UUID taskId, int status,String lang);

    ResponseModel filterTask(Map<String ,String> map ,int page,String lang);



}
