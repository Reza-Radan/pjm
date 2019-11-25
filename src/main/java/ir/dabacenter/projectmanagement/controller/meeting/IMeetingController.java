package ir.dabacenter.projectmanagement.controller.meeting;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.meeting.MeetingByFeature;
import ir.dabacenter.projectmanagement.domain.meeting.MeetingByProject;
import ir.dabacenter.projectmanagement.domain.meeting.MeetingByRelease;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface IMeetingController {

    ResponseModel getMeetingByFeature(UUID featureId,String lang, HttpServletResponse httpServletResponse);
    ResponseModel getMeetingByRelease(UUID releaseId,String lang, HttpServletResponse httpServletResponse);
    ResponseModel getMeetingByMember(UUID memberId,String lang, HttpServletResponse httpServletResponse);

    ResponseModel addMeetingByFeature(MeetingByFeature meetingByFeature,String lang, HttpServletResponse httpServletResponse);
    ResponseModel addMeetingByRelease(MeetingByRelease meetingByRelease,String lang, HttpServletResponse httpServletResponse);
    ResponseModel addMeetingByProject(MeetingByProject meetingByProject, String lang, HttpServletResponse httpServletResponse);
}
