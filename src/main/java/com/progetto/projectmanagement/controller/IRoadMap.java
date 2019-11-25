package com.progetto.projectmanagement.controller;

import com.progetto.projectmanagement.domain.permission.Permission;
import com.progetto.projectmanagement.domain.project.*;
import com.progetto.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.issue.IssueByRelease;
import ir.dabacenter.projectmanagement.domain.issue.IssueByTask;
import com.progetto.projectmanagement.domain.meeting.MeetingByFeature;
import com.progetto.projectmanagement.domain.meeting.MeetingByRelease;
import com.progetto.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.domain.project.*;
import com.progetto.projectmanagement.domain.report.ReportByMeeting;
import com.progetto.projectmanagement.domain.report.ReportByTask;
import com.progetto.projectmanagement.domain.report.ReportByUser;
import com.progetto.projectmanagement.domain.task.Task;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IRoadMap {


	//-----------------------

		ResponseModel addProject(Project project);
		ResponseModel addProjectMission(MissionByProject mission);
		ResponseModel addProjectVision(VisionByProject vision);
		ResponseModel addProjectRelease(ReleaseByProject release);
		ResponseModel addProjectFeature(FeatureByProject feature);
		ResponseModel addProjectSubfeature(SubFeatureByProject subfeature);
		ResponseModel addProjectAll(ProjectAllDetails projectAll);
		ResponseModel addProjectRole(RoleByProject role);

		ResponseModel getProjectDetails(UUID pId);
		ResponseModel getProjectMission(UUID pId);
		ResponseModel getProjectVision(UUID pId);
		ResponseModel getProjectFeature(UUID pId);
		ResponseModel getProjectSubFeature(UUID pId);
		ResponseModel getProjectRelease(UUID pId);
		ResponseModel getProjectAll(UUID pId);
//----------------------------


	ResponseModel changeStatus(UUID taskId, int status);
	ResponseModel getTaskDetails(UUID taskId);

	ResponseModel addTask(Task taskModel);


	ResponseModel addIssueByTask(IssueByTask issueByTask);
	ResponseModel addIssueByRelease(IssueByRelease issuByRelease);
	ResponseModel getIssueByMember(UUID memberId, UUID issueId);
	ResponseModel getIssueByRelease(UUID issueId, UUID releaseId);
	ResponseModel getIssueByTask(UUID taskId, UUID issueId);

	ResponseModel addReportByTask(ReportByTask reposrtTask);
	ResponseModel addReportByUser(ReportByUser reportByUser);
	ResponseModel addReportByMeeting(ReportByMeeting reportByMeeting);

	ResponseModel getReportByTask(UUID taskId, UUID projectId);
	ResponseModel getReportByUser(UUID userId);
	ResponseModel getReportByMeeting(UUID meetingId);
	ResponseModel getReportByProject(UUID projectId);

	//------------------------------
	ResponseModel addMember(Member member);
	ResponseModel getMember(UUID memberId);
	ResponseModel getMember();
	ResponseModel getMemberByProject(UUID pId);

	//--------------------------------
	ResponseModel addMeetingByFeature(MeetingByFeature meetingByFeature);
	ResponseModel addMeetingByRelease(MeetingByRelease meetingByRelease);
	ResponseModel getMeetingByFeature(UUID featureId);
	ResponseModel getMeetingByRelease(UUID releaseId);
	ResponseModel getMeetingByMember(UUID memberId);
	//---------

	ResponseModel addPermission(Permission permission);
	ResponseModel getUserProjectPermission(UUID projectId, UUID memberId);
	ResponseModel getProjectRole(UUID pId);
	String saveUploadedFile(List<MultipartFile> files);
	ResponseModel login(String userName,String password);

}