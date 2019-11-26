package com.progetto.projectmanagement.domain.report;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import com.progetto.projectmanagement.domain.uuidClass;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("report_by_meeting")
public class ProjectReport {

//	@IdColumn(name = "projectId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	@IdColumn(name = "reportId", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID reportId;

	@IdColumn(name = "meetingId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID meetingId;

	private List<uuidClass> attachment;
	private UUID dateReport;
	private String reportDesc;
	private MemberType author;
	private UUID dateMeeting;
	private String meetingTitle;
	private String lang;

	@NotNull
	private UUID modifyTime;

	@NotNull
	private UUID time;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public UUID getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(UUID modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getTime() {
		return time;
	}

	public void setTime(UUID time) {
		this.time = time;
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

	public UUID getReportId() {
		return this.reportId;
	}

	/**
	 * 
	 * @param reportId
	 */
	public void setReportId(UUID reportId) {
		this.reportId = reportId;
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

	public List<uuidClass> getAttachment() {
		return this.attachment;
	}

	/**
	 * 
	 * @param attachment
	 */
	public void setAttachment(List<uuidClass> attachment) {
		this.attachment = attachment;
	}

	public UUID getDateReport() {
		return this.dateReport;
	}

	/**
	 * 
	 * @param dateReport
	 */
	public void setDateReport(UUID dateReport) {
		this.dateReport = dateReport;
	}




	public MemberType getAuthor() {
		return this.author;
	}

	/**
	 * 
	 * @param author
	 */
	public void setAuthor(MemberType author) {
		this.author = author;
	}

	public UUID getDateMeeting() {
		return this.dateMeeting;
	}

	/**
	 * 
	 * @param dateMeeting
	 */
	public void setDateMeeting(UUID dateMeeting) {
		this.dateMeeting = dateMeeting;
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

	public String getReportDesc() {
		return reportDesc;
	}

	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}


}