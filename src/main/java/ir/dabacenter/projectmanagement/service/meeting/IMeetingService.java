package ir.dabacenter.projectmanagement.service.meeting;

import ir.dabacenter.projectmanagement.domain.*;
import ir.dabacenter.projectmanagement.domain.meeting.*;

import java.util.UUID;

public interface IMeetingService {

	/**
	 * 
	 * @param meetingByFeature
	 */
	ResponseModel addMeetingByFeature(MeetingByFeature meetingByFeature,String lang);

	/**
	 * 
	 * @param meetingByRelease
	 */
	ResponseModel addMeetingByRelease(MeetingByRelease meetingByRelease,String lang);

	ResponseModel addMeetingByProject(MeetingByProject meetingByProject,String lang);

	/**
	 * 
	 * @param featureId
	 */
	ResponseModel getMeetingByFeature(UUID featureId ,String lang);

	/**
	 * 
	 * @param memberId
	 */
	ResponseModel getMeetingByMember(UUID memberId ,String lang);

	/**
	 * 
	 * @param releaseId
	 */
	ResponseModel getMeetingByRelease(UUID releaseId ,String lang);

	/**
	 *
	 * @param meetingByFeature
	 * @param lang
	 * @return
	 */
	ResponseModel updateMeetingByFeature(MeetingByFeature meetingByFeature, String lang);


	ResponseModel updateMeetingByProject(MeetingByProject meetingByProject, String lang);

	ResponseModel updateMeetingByRelease(MeetingByRelease meetingByRelease, String lang);
}