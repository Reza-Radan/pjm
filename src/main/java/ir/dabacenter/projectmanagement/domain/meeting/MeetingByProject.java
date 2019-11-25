package ir.dabacenter.projectmanagement.domain.meeting;

import ir.dabacenter.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Table
public class MeetingByProject {

	@Id
	private UUID meetingId;

	@Id
	private UUID projectId;


	private String projectName;
	private String meetingTitle;
	private String meetingDesc;
	private Long startDate;
	private String durationTime;

	@NotNull
	private Long modifyTime;

	@Frozen
	private MemberType createMember;

	private Long startMeeting;


	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getMeetingId() {
		return this.meetingId;
	}

	/**
	 * 
	 * @param meetingId
	 */
	public void setMeetingId(UUID meetingId) {
		this.meetingId = meetingId;
	}

	public UUID getProjectId() {
		return this.projectId;
	}

	/**
	 * 
	 * @param projectId
	 */
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * 
	 * @param projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMeetingTitle() {
		return this.meetingTitle;
	}

	/**
	 * 
	 * @param meetingTitle
	 */
	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public String getMeetingDesc() {
		return this.meetingDesc;
	}

	/**
	 * 
	 * @param meetingDesc
	 */
	public void setMeetingDesc(String meetingDesc) {
		this.meetingDesc = meetingDesc;
	}

	public Long getStartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public String getDurationTime() {
		return this.durationTime;
	}

	/**
	 * 
	 * @param durationTime
	 */
	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public MemberType getCreateMember() {
		return this.createMember;
	}

	/**
	 * 
	 * @param createMember
	 */
	public void setCreateMember(MemberType createMember) {
		this.createMember = createMember;
	}

	public Long getStartMeeting() {
		return this.startMeeting;
	}

	/**
	 * 
	 * @param startMeeting
	 */
	public void setStartMeeting(Long startMeeting) {
		this.startMeeting = startMeeting;
	}

}