package com.progetto.projectmanagement.controller.report;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.report.ReportByMeeting;
import com.progetto.projectmanagement.domain.report.ReportByTask;
import com.progetto.projectmanagement.domain.report.ReportByUser;
import com.progetto.projectmanagement.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@Service
@RestController
@RequestMapping("/report")
public class ReportController implements IReportController {

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/getReportByTask", method = RequestMethod.POST )
    public ResponseModel getReportByTask(@RequestParam UUID taskId , String lang) {
        makeResponseClear();
        responseModel = reportService.getReportByTask(taskId,lang);
        return responseModel;
    }

    @RequestMapping(value = "/getReportByUser", method = RequestMethod.POST )
    public ResponseModel getReportByUser(UUID userId,String lang) {
        makeResponseClear();
        responseModel = reportService.getReportByUser(userId,lang);
        return responseModel;
    }

    @RequestMapping(value = "/getReportByMeeting", method = RequestMethod.POST )
    public ResponseModel getReportByMeeting(UUID meetingId,String lang) {
        makeResponseClear();
        responseModel = reportService.getReportByMeeting(meetingId,lang);
        return responseModel;
    }

    @RequestMapping(value = "/getReportByProject", method = RequestMethod.POST )
    public ResponseModel getReportByProject(UUID pId,String lang) {
        makeResponseClear();
        responseModel = reportService.getReportByProject(pId,lang);
        return responseModel;
    }

    @RequestMapping(value = "/addReportByTask", method = RequestMethod.POST )
    public ResponseModel addReportByTask(ReportByTask reposrtTask,String lang) {
        makeResponseClear();
        reposrtTask.setLang(lang);
        responseModel = reportService.addReportByTask(reposrtTask,lang);
        return responseModel;
    }

    @RequestMapping(value = "/addReportByUser", method = RequestMethod.POST )
    public ResponseModel addReportByUser(ReportByUser reportByUser,String lang) {
        makeResponseClear();
        reportByUser.setLang(lang);
        responseModel = reportService.addReportByUser(reportByUser,lang);
        return responseModel;
    }

    @RequestMapping(value = "/addReportByMeeting", method = RequestMethod.POST )
    public ResponseModel addReportByMeeting(ReportByMeeting reportByMeeting,String lang) {
        makeResponseClear();
        reportByMeeting.setLang(lang);
        responseModel = reportService.addReportByMeeting(reportByMeeting,lang);
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
