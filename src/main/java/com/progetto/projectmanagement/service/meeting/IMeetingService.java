package com.progetto.projectmanagement.service.meeting;

import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.meeting.InviteMeeting;
import com.progetto.projectmanagement.domain.meeting.MeetingByProject;
import com.progetto.projectmanagement.domain.meeting.MeetingByRelease;
import ir.dabacenter.projectmanagement.domain.*;
import ir.dabacenter.projectmanagement.domain.meeting.*;

import java.util.UUID;

public interface IMeetingService {

	/**
	 * 
	 * @param inviteMeeting
	 */
	ResponseModel addMeetingByFeature(InviteMeeting inviteMeeting, String lang);

	/**
	 * 
	 * @param meetingByRelease
	 */
	ResponseModel addMeetingByRelease(MeetingByRelease meetingByRelease, String lang);

	ResponseModel addMeetingByProject(MeetingByProject meetingByProject, String lang);

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
	 * @param inviteMeeting
	 * @param lang
	 * @return
	 */
	ResponseModel updateMeetingByFeature(InviteMeeting inviteMeeting, String lang);


	ResponseModel updateMeetingByProject(MeetingByProject meetingByProject, String lang);

	ResponseModel updateMeetingByRelease(MeetingByRelease meetingByRelease, String lang);
}