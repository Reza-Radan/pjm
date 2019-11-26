package com.progetto.projectmanagement.domain.meeting;

import com.progetto.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table
public class InviteMeeting {

	@Id
	private UUID meetingId;

	@Id
	private UUID featureId;

	private String featureName;
	private String meetingTitle;
	private String meetingDesc;
	private Long startDate;
	private String durationTime;
	private String lang;


	@NotNull
	private Long modifyTime;

	@Frozen
	private MemberType createMember;
	private Long startMeeting;

	private UUID projectId;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getMeeting_id() {
		return this.meetingId;
	}

	/**
	 * 
	 * @param meeting_id
	 */
	public void setMeeting_id(UUID meeting_id) {
		this.meetingId = meeting_id;
	}

	public UUID getfeatureId() {
		return this.featureId;
	}

	/**
	 * 
	 * @param featureId
	 */
	public void setfeatureId(UUID featureId) {
		this.featureId = featureId;
	}

	public String getFeature_name() {
		return this.featureName;
	}

	/**
	 * 
	 * @param feature_name
	 */
	public void setFeature_name(String feature_name) {
		this.featureName = feature_name;
	}

	public String getMeeting_title() {
		return this.meetingTitle;
	}

	/**
	 * 
	 * @param meeting_title
	 */
	public void setMeeting_title(String meeting_title) {
		this.meetingTitle = meeting_title;
	}

	public String getMeeting_desc() {
		return this.meetingDesc;
	}

	/**
	 * 
	 * @param meeting_desc
	 */
	public void setMeeting_desc(String meeting_desc) {
		this.meetingDesc = meeting_desc;
	}

	public Long getStart_date() {
		return this.startDate;
	}

	/**
	 * 
	 * @param start_date
	 */
	public void setStart_date(Long start_date) {
		this.startDate = start_date;
	}

	public String getDuration_time() {
		return this.durationTime;
	}

	/**
	 * 
	 * @param duration_time
	 */
	public void setDuration_time(String duration_time) {
		this.durationTime = duration_time;
	}

	public MemberType getCreate_member() {
		return this.createMember;
	}

	/**
	 * 
	 * @param create_member
	 */
	public void setCreate_member(MemberType create_member) {
		this.createMember = create_member;
	}

	public Long getStart_meeting() {
		return this.startMeeting;
	}

	/**
	 * 
	 * @param start_meeting
	 */
	public void setStart_meeting(Long start_meeting) {
		this.startMeeting = start_meeting;
	}

	public UUID getProject_id() {
		return this.projectId;
	}

	/**
	 * 
	 * @param project_id
	 */
	public void setProject_id(UUID project_id) {
		this.projectId = project_id;
	}

}