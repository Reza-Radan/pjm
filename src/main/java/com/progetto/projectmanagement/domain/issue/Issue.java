package com.progetto.projectmanagement.domain.issue;

import com.progetto.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table
public class Issue {

	@Id
	private UUID id;
	private String title;
	private String issueDesc;
	private Long startDate;
	private String duration;


	@NotNull
	private Long modifyTime;

	@Frozen
	private MemberType issueFrom;

	@Frozen
	private MemberType issueTo;

	private UUID issueFromId;

	private UUID issueToId;

	private UUID releaseId;

	private UUID projectId;

	private UUID taskId;

	private String lang;

	public String getLang() {return lang;}

	public void setLang(String lang) {	this.lang = lang;}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return this.issueDesc;
	}

	/**
	 * 
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.issueDesc = desc;
	}

	public Long getstartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setstartDate(Long startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return this.duration;
	}

	/**
	 * 
	 * @param duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public MemberType getIssue_from() {
		return this.issueFrom;
	}

	/**
	 * 
	 * @param issue_from
	 */
	public void setIssue_from(MemberType issue_from) {
		this.issueFrom = issue_from;
	}

	public MemberType getIssue_to() {
		return this.issueTo;
	}

	/**
	 * 
	 * @param issue_to
	 */
	public void setIssue_to(MemberType issue_to) {
		this.issueTo = issue_to;
	}

	public UUID getRelease_id() {
		return this.releaseId;
	}

	/**
	 * 
	 * @param release_id
	 */
	public void setRelease_id(UUID release_id) {
		this.releaseId = release_id;
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

	public UUID gettaskId() {
		return this.taskId;
	}

	/**
	 * 
	 * @param taskId
	 */
	public void settaskId(UUID taskId) {
		this.taskId = taskId;
	}

	public UUID getIssueFromId() {
		return issueFromId;
	}

	public void setIssueFromId(UUID issueFromId) {
		this.issueFromId = issueFromId;
	}

	public UUID getIssueToId() {
		return issueToId;
	}

	public void setIssueToId(UUID issueToId) {
		this.issueToId = issueToId;
	}
}