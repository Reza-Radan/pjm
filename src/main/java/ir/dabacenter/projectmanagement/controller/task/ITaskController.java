package ir.dabacenter.projectmanagement.controller.task;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.domain.task.Task;
import ir.dabacenter.projectmanagement.domain.task.Task;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
