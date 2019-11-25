package ir.dabacenter.projectmanagement.controller.task;

import ir.dabacenter.projectmanagement.controller.meeting.MeetingController;
import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.domain.task.SubfeatureByTask;
import ir.dabacenter.projectmanagement.domain.task.Task;
import ir.dabacenter.projectmanagement.domain.task.TaskByProject;
import ir.dabacenter.projectmanagement.service.meeting.MeetingService;
import ir.dabacenter.projectmanagement.service.task.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RestController
@RequestMapping("/task")
public class TaskController implements ITaskController {

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    private TaskService taskService;

    org.slf4j.Logger logger = LoggerFactory.getLogger(TaskController.class);

    //test by Masoomeh
    @Override
    @RequestMapping(value = "/addTask", method = RequestMethod.POST )
    public ResponseModel addTask(@RequestBody Task task, HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = taskService.addTask(task);
        return responseModel;
    }

    //test by Masoomeh
    @RequestMapping(value = "/updateTask", method = RequestMethod.POST)
    public ResponseModel updateTask(@RequestBody Task task) {
        makeResponseClear();
        responseModel = taskService.updateTask(task);
        return responseModel;
    }


    /**
     * @author Masoomeh
     * @param taskId
     * @param status
     * @param lang
     * @return
     */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public ResponseModel changeStatus(@RequestParam UUID taskId,@RequestParam int status,String lang) {

        logger.info("changeStatus");
        makeResponseClear();
        responseModel = taskService.changeStatus(taskId,status,lang);
        return responseModel;
    }

    @RequestMapping(value = "/getTaskDetails", method = RequestMethod.POST)
    public ResponseModel getTaskDetails(@RequestParam UUID taskId,String lang) {
        makeResponseClear();
        responseModel = taskService.getTaskDetails(taskId ,lang);
        return responseModel;
    }


    @RequestMapping(value = "/getAllTask", method = RequestMethod.POST)
    public ResponseModel getAllTask(Task task, int page) {
        return null;
    }

    @RequestMapping(value = "/getTaskByProject", method = RequestMethod.POST)
    public ResponseModel getTaskByProject(@RequestParam UUID pId, @RequestParam int page ,String lang) {
        logger.info("getTaskByProject");
        makeResponseClear();
        responseModel = taskService.getTaskByProject(pId,page,lang);
        return responseModel;
    }

    /**
     * @author Masoomeh
     * @param taskId
     * @param lang
     * @return
     */
    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    public ResponseModel deleteTask(@RequestParam UUID taskId, String lang) {

        logger.info("deleteTask");
        makeResponseClear();
        responseModel = taskService.deleteTask(taskId,lang);
        return responseModel;
    }

    @RequestMapping(value = "/filterTask", method = RequestMethod.POST)
    public ResponseModel filterTask(@RequestParam Map<String, String> map,int page , String lang) {
        logger.info("filterTask + map: " +map);
        makeResponseClear();
        String implementorId =map.get("implementorId");
        String responsibleId =map.get("responsibleId");
        String startDate =map.get("startDate");
        String endDate =map.get("endDate");
        String priority =map.get("priority");
        String status =map.get("status");

        responseModel = taskService.filterTask(implementorId,responsibleId,startDate,
                endDate,priority,status,page,lang);
        logger.info("response: " + responseModel.getResult());
        return responseModel;

    }


    @RequestMapping(value = "/getTaskByUserImplementor", method = RequestMethod.POST)
    public ResponseModel getTaskByUserImplementor(@RequestParam UUID implementorId, @RequestParam int page,String lang) {

        logger.info("getTaskByUserImplementor");
        makeResponseClear();
        responseModel = taskService.getTaskByUserImpl(implementorId,page,lang);
        logger.info("response: " + responseModel.getResult());
        return responseModel;
    }

    @RequestMapping(value = "/getTaskByUserResp", method = RequestMethod.POST)
    public ResponseModel getTaskByUserResp(@RequestParam UUID responsibleId,@RequestParam int page, String lang) {

        logger.info("getTaskByUserResp");
        makeResponseClear();
        responseModel = taskService.getTaskByUserResp(responsibleId,page,lang);
        return responseModel;
    }

    @RequestMapping(value = "/getTaskBySubFeature", method = RequestMethod.POST)
    public ResponseModel getTaskBySubFeature(@RequestParam UUID subfeatureId, @RequestParam int page, String lang) {

        logger.info("getTaskBySubFeature");
        makeResponseClear();
        responseModel = taskService.getTaskBySubFeature(subfeatureId,page,lang);
        return responseModel;
    }

    public void makeResponseClear(){
        responseModel.setContents(null);
        responseModel.setContent(null);
        responseModel.setSystemError("");
        responseModel.setError("");
        responseModel.setRecordCount(0);
    }
}
