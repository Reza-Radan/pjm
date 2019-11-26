package com.progetto.projectmanagement.domain.report;

import com.datastax.driver.core.DataType;
import com.progetto.projectmanagement.domain.uuidClass;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("report_by_user")
public class UserReport {

	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskId;

	@IdColumn(name = "userId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID userId;

	@IdColumn(name = "reportId", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID reportId;
	private String taskTitle;
	private String reportDesc;

	@NotNull
	private UUID modifyTime;

	private UUID time;
	private String lang;

	private List<uuidClass> attachment;

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

	public UUID getTaskId() {
		return this.taskId;
	}

	/**
	 * 
	 * @param taskId
	 */
	public void setTaskId(UUID taskId) {
		this.taskId = taskId;
	}

	public UUID getUserId() {
		return this.userId;
	}

	/**
	 * 
	 * @param userId
	 */
	public void setUserId(UUID userId) {
		this.userId = userId;
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

	public String getTaskTitle() {
		return this.taskTitle;
	}

	/**
	 * 
	 * @param taskTitle
	 */
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getReportDesc() {
		return this.reportDesc;
	}

	/**
	 * 
	 * @param reportDesc
	 */
	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
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

}