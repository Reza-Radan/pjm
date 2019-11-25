package com.progetto.projectmanagement.controller.issue;

import com.progetto.projectmanagement.service.issue.IssueService;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.issue.IssueByRelease;
import ir.dabacenter.projectmanagement.domain.issue.IssueByTask;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@Service
@RestController
@RequestMapping("/issue")
public class IssueController implements IIssueController {

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    private IssueService issueService;

    @Autowired
    ResponseModel responseModel;

    org.slf4j.Logger logger = LoggerFactory.getLogger(IssueController.class);

    @RequestMapping(value = "/getIssueByMember", method = RequestMethod.POST)
    public ResponseModel getIssueByMember(@RequestParam Map<String,String> requestParams, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        String memberId=requestParams.get("memberId");
//        String issueId=requestParams.get("issueId");
        String lang=requestParams.get("lang");
        logger.info("memberId: " +memberId  + "lang: " +lang);
        responseModel = issueService.getIssueByMember( memberId,lang);
        return responseModel;
    }

    /**
     * @author masoomeh
     * @param requestParams
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/getIssueByRelease", method = RequestMethod.POST)
    public ResponseModel getIssueByRelease(@RequestParam Map<String,String> requestParams ,HttpServletResponse httpServletResponse) {
        makeResponseClear();
//        String memberId=requestParams.get("memberId");
        String releaseId=requestParams.get("releaseId");
        String lang=requestParams.get("lang");
        logger.info( " releaseId: " +releaseId + "lang: " +lang);
        responseModel = issueService.getIssueByRelease( UUID.fromString(releaseId),lang);
        return responseModel;
    }


    /**
     * @author masoomeh
     * @param taskId
     * @param lang
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/getIssueByTask", method = RequestMethod.POST)
    public ResponseModel getIssueByTask(@RequestParam  UUID taskId, String lang,HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info("getIssueByTask: taskId: "+taskId);
        responseModel = issueService.getIssueByTask(taskId ,lang);
        return responseModel;
    }

    /**
     * @author masoomeh
     * @param issuByRelease
     * @param lang
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/addIssueByRelease", method = RequestMethod.POST)
    public ResponseModel addIssueByRelease(@RequestBody IssueByRelease issuByRelease, String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info("addIssueByRelease: ");
        responseModel = issueService.addIssueByRelease(issuByRelease ,lang);
        return responseModel;
    }

    /**
     * @author masoomeh
     * @param issueByTask
     * @param lang
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/addIssueByTask", method = RequestMethod.POST)
    public ResponseModel addIssueByTask(@RequestBody IssueByTask issueByTask,String lang,HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info("addIssueByTask: ");
        responseModel = issueService.addIssueByTask(issueByTask ,lang);
        return responseModel;
    }

    @RequestMapping(value = "/updateIssueByTask", method = RequestMethod.POST)
    public ResponseModel updateIssueByTask(@RequestBody IssueByTask issueByTask, String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info("updateIssueByTask ");
        responseModel = issueService.updateIssueByTask(issueByTask ,lang);
        return responseModel;
    }

    @RequestMapping(value = "/updateIssueByRelease", method = RequestMethod.POST)
    public ResponseModel updateIssueByRelease(@RequestBody IssueByRelease issuByRelease, String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info("updateIssueByRelease ");
        responseModel = issueService.updateIssueByRelease(issuByRelease ,lang);
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
