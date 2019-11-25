package com.progetto.projectmanagement.controller.meeting;

import com.progetto.projectmanagement.domain.meeting.MeetingByFeature;
import com.progetto.projectmanagement.domain.meeting.MeetingByProject;
import com.progetto.projectmanagement.domain.meeting.MeetingByRelease;
import com.progetto.projectmanagement.service.meeting.MeetingService;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RestController
@RequestMapping("/meeting")
public class MeetingController implements IMeetingController {

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    private MeetingService meetingService;

    org.slf4j.Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @RequestMapping(value = "/getMeetingByFeature", method = RequestMethod.POST)
    public ResponseModel getMeetingByFeature(@RequestParam UUID featureId , String lang , HttpServletResponse httpServletResponse) {

        logger.error("meeting service getMeetingByFeature featureId: " +featureId);
        makeResponseClear();
        responseModel = meetingService.getMeetingByFeature(featureId ,lang);
        responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }

    @RequestMapping(value = "/getMeetingByRelease", method = RequestMethod.POST)
    public ResponseModel getMeetingByRelease(@RequestParam UUID releaseId,String lang , HttpServletResponse httpServletResponse) {
        return null;
    }


    @RequestMapping(value = "/getMeetingByMember", method = RequestMethod.POST)
    public ResponseModel getMeetingByMember(@RequestParam UUID memberId,String lang, HttpServletResponse httpServletResponse) {
        return null;
    }



    @RequestMapping(value = "/addMeetingByFeature", method = RequestMethod.POST)
    public ResponseModel addMeetingByFeature(@RequestBody MeetingByFeature meetingByFeature, String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        responseModel = meetingService.addMeetingByFeature(meetingByFeature ,lang);
        return responseModel;
    }

    @RequestMapping(value = "/addMeetingByRelease", method = RequestMethod.POST)
    public ResponseModel addMeetingByRelease(@RequestBody MeetingByRelease meetingByRelease, String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        responseModel = meetingService.addMeetingByRelease(meetingByRelease,lang);
        return responseModel;
    }

    @RequestMapping(value = "/addMeetingByProject", method = RequestMethod.POST)
    public ResponseModel addMeetingByProject(@RequestBody MeetingByProject meetingByProject, String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        responseModel = meetingService.addMeetingByProject(meetingByProject,lang);
        return responseModel;
    }

    public void makeResponseClear(){
        responseModel.setContents(null);
        responseModel.setContent(null);
        responseModel.setSystemError("");
        responseModel.setError("");
        responseModel.setRecordCount(0);
    }

    @RequestMapping(value = "/updateMeetingByFeature", method = RequestMethod.POST)
    public ResponseModel updateMeetingByFeature(@RequestBody MeetingByFeature meetingByFeature ,String lang){
        makeResponseClear();
        responseModel = meetingService.updateMeetingByFeature(meetingByFeature,lang);
        return responseModel;
    }

    @RequestMapping(value = "/updateMeetingByProject", method = RequestMethod.POST)
    public ResponseModel updateMeetingByProject(@RequestBody MeetingByProject meetingByProject ,String lang){
        makeResponseClear();
        responseModel = meetingService.updateMeetingByProject(meetingByProject,lang);
        return responseModel;
    }

    @RequestMapping(value = "/updateMeetingByRelease", method = RequestMethod.POST)
    public ResponseModel updateMeetingByRelease(@RequestBody MeetingByRelease meetingByRelease ,String lang){
        makeResponseClear();
        responseModel = meetingService.updateMeetingByRelease(meetingByRelease,lang);
        return responseModel;
    }
}
