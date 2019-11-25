//package ir.dabacenter.projectmanagement.controller;
//
//
//import IAttachmentService;
//import IIssueService;
//import IMeetingService;
//import IMemberService;
//import IPermissionService;
//import ir.dabacenter.projectmanagement.service.project.IProjectController;
//import ir.dabacenter.projectmanagement.service.report.IReportController;
//import SecurityService;
//import ir.dabacenter.projectmanagement.service.task.ITaskController;
//import ResponseModel;
//import ir.dabacenter.projectmanagement.domain.issue.IssueByRelease;
//import ir.dabacenter.projectmanagement.domain.issue.IssueByTask;
//import MeetingByFeature;
//import MeetingByRelease;
//import ir.dabacenter.projectmanagement.domain.member.MemberService;
//import ir.dabacenter.projectmanagement.domain.permission.PermissionService;
//import ir.dabacenter.projectmanagement.domain.project.*;
//import ReportByMeeting;
//import ReportByTask;
//import ReportByUser;
//import ir.dabacenter.projectmanagement.domain.task.TaskController;
//import UserPassSecurityModel;
//import IFileManagerService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/")
//public class RoadMap implements IRoadMap{
//
//	@Inject
//	private IProjectController project;
//
//	@Inject
//	private ITaskController task;
//
//	@Inject
//	private IIssueService issue;
//
//	@Inject
//	private IMeetingService meeting;
//
//	@Inject
//	private IMemberService member;
//
//	@Inject
//	private IReportController report;
//
//	@Inject
//	private IPermissionService permission;
//
//	@Inject
//	private IAttachmentService attachment;
//
//	@Inject
//	private IFileManagerService iFileManagerService;
//
//	@Inject
//	private SecurityService security;
//
//	ResponseModel responseModel;
//
//	private final Logger logger = LoggerFactory.getLogger(RoadMap.class);
//
//
//	/**
//	 *
//	 * @param project
//	 */
//	public ResponseModel addProject(ProjectController project) {
//		// TODO - implement RoadMap.addProject
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param mission
//	 */
//	public ResponseModel addProjectMission(MissionByProject mission) {
//		// TODO - implement RoadMap.addProjectMission
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param vision
//	 */
//	public ResponseModel addProjectVision(VisionByProject vision) {
//		// TODO - implement RoadMap.addProjectVision
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param release
//	 */
//	public ResponseModel addProjectRelease(ReleaseByProject release) {
//		// TODO - implement RoadMap.addProjectRelease
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param feature
//	 */
//	public ResponseModel addProjectFeature(FeatureByProject feature) {
//		// TODO - implement RoadMap.addProjectFeature
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param subfeature
//	 */
//	public ResponseModel addProjectSubfeature(SubFeatureByProject subfeature) {
//		// TODO - implement RoadMap.addProjectSubfeature
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param projectAll
//	 */
//	public ResponseModel addProjectAll(ProjectAllDetails projectAll) {
//		// TODO - implement RoadMap.addProjectAll
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param role
//	 */
//	public ResponseModel addProjectRole(RoleByProject role) {
//		// TODO - implement RoadMap.addProjectRole
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectDetails(UUID pId) {
//		// TODO - implement RoadMap.getProjectDetails
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectMission(UUID pId) {
//		// TODO - implement RoadMap.getProjectMission
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectVision(UUID pId) {
//		// TODO - implement RoadMap.getProjectVision
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectFeature(UUID pId) {
//		// TODO - implement RoadMap.getProjectFeature
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectSubFeature(UUID pId) {
//		// TODO - implement RoadMap.getProjectSubFeature
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectRelease(UUID pId) {
//		// TODO - implement RoadMap.getProjectRelease
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectAll(UUID pId) {
//		// TODO - implement RoadMap.getProjectAll
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param taskModel
//	 */
//	public ResponseModel addTask(TaskController taskModel) {
//		// TODO - implement RoadMap.addTask
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param taskId
//	 * @param status 0 todo, 1 progress , 2 done , archive 3
//	 */
//	public ResponseModel changeStatus(UUID taskId, int status) {
//		// TODO - implement RoadMap.changeStatus
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param taskId
//	 */
//	public ResponseModel getTaskDetails(UUID taskId) {
//		// TODO - implement RoadMap.getTaskDetails
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param issueByTask
//	 */
//	public ResponseModel addIssueByTask(IssueByTask issueByTask) {
//		// TODO - implement RoadMap.addIssueByTask
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param issuByRelease
//	 */
//	public ResponseModel addIssueByRelease(IssueByRelease issuByRelease) {
//		// TODO - implement RoadMap.addIssueByRelease
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param memberId
//	 * @param issueId
//	 */
//	public ResponseModel getIssueByMember(UUID memberId, UUID issueId) {
//		// TODO - implement RoadMap.getIssueByMember
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param issueId
//	 * @param releaseId
//	 */
//	public ResponseModel getIssueByRelease(UUID issueId, UUID releaseId) {
//		// TODO - implement RoadMap.getIssueByRelease
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param taskId
//	 * @param issueId
//	 */
//	public ResponseModel getIssueByTask(UUID taskId, UUID issueId) {
//		// TODO - implement RoadMap.getIssueByTask
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param reposrtTask
//	 */
//	public ResponseModel addReportByTask(ReportByTask reposrtTask) {
//		// TODO - implement RoadMap.addReportByTask
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param reportByUser
//	 */
//	public ResponseModel addReportByUser(ReportByUser reportByUser) {
//		// TODO - implement RoadMap.addReportByUser
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param reportByMeeting
//	 */
//	public ResponseModel addReportByMeeting(ReportByMeeting reportByMeeting) {
//		// TODO - implement RoadMap.addReportByMeeting
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param taskId
//	 * @param projectId
//	 */
//	public ResponseModel getReportByTask(UUID taskId, UUID projectId) {
//		// TODO - implement RoadMap.getReportByTask
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param userId
//	 */
//	public ResponseModel getReportByUser(UUID userId) {
//		// TODO - implement RoadMap.getReportByUser
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param meetingId
//	 */
//	public ResponseModel getReportByMeeting(UUID meetingId) {
//		// TODO - implement RoadMap.getReportByMeeting
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param projectId
//	 */
//	public ResponseModel getReportByProject(UUID projectId) {
//		// TODO - implement RoadMap.getReportByProject
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param member
//	 */
//	public ResponseModel addMember(MemberService member) {
//		// TODO - implement RoadMap.addMember
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param memberId
//	 */
//	public ResponseModel getMember(UUID memberId) {
//		// TODO - implement RoadMap.getMember
//		throw new UnsupportedOperationException();
//	}
//
//	public ResponseModel getMember() {
//		// TODO - implement RoadMap.getMember
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getMemberByProject(UUID pId) {
//		// TODO - implement RoadMap.getMemberByProject
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param meetingByFeature
//	 */
//	public ResponseModel addMeetingByFeature(MeetingByFeature meetingByFeature) {
//		// TODO - implement RoadMap.addMeetingByFeature
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param meetingByRelease
//	 */
//	public ResponseModel addMeetingByRelease(MeetingByRelease meetingByRelease) {
//		// TODO - implement RoadMap.addMeetingByRelease
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param featureId
//	 */
//	public ResponseModel getMeetingByFeature(UUID featureId) {
//		// TODO - implement RoadMap.getMeetingByFeature
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param releaseId
//	 */
//	public ResponseModel getMeetingByRelease(UUID releaseId) {
//		// TODO - implement RoadMap.getMeetingByRelease
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param memberId
//	 */
//	public ResponseModel getMeetingByMember(UUID memberId) {
//		// TODO - implement RoadMap.getMeetingByMember
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param permission
//	 */
//	public ResponseModel addPermission(PermissionService permission) {
//		// TODO - implement RoadMap.addPermission
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param projectId
//	 * @param memberId
//	 */
//	public ResponseModel getUserProjectPermission(UUID projectId, UUID memberId) {
//		// TODO - implement RoadMap.getUserProjectPermission
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param pId
//	 */
//	public ResponseModel getProjectRole(UUID pId) {
//		// TODO - implement RoadMap.getProjectRole
//		throw new UnsupportedOperationException();
//	}
//
//
//	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
//	public String saveUploadedFile(@RequestParam("file") List<MultipartFile> files) {
//		System.out.println("getting file in RoadMap file size "+files.size());
//		iFileManagerService.saveUploadedFile(files);
//		return  "Success";
//	}
//
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String getPost() {
//		return "GET method not supported";
//	}
//
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseModel login(@RequestBody  UserPassSecurityModel userNameModel , HttpServletResponse response) {
//
//		String token = security.createTokenByUserPasswordAuthentication(userNameModel.getUserName(),userNameModel.getPassword());
//		return new ResponseModel(response.getStatus(), userNameModel,"none","none");
//	}
//
//}