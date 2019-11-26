package com.progetto.projectmanagement.domain.activity;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("project_notification")
public class ActivityType {

	@IdColumn(name = "notificationId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID notificationId;

	@IdColumn(name = "projectId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	private String projectName;
	private String notificationTitle;
	private String notificationDesc;
	private MemberType creatorMember;

	@NotNull
	private UUID modifyTime;


	public UUID getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(UUID modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getnotificationId() {
		return this.notificationId;
	}

	/**
	 * 
	 * @param notificationId
	 */
	public void setnotificationId(UUID notificationId) {
		this.notificationId = notificationId;
	}

	public UUID getprojectId() {
		return this.projectId;
	}

	/**
	 * 
	 * @param projectId
	 */
	public void setprojectId(UUID projectId) {
		this.projectId = projectId;
	}

	public String getProject_name() {
		return this.projectName;
	}

	/**
	 * 
	 * @param project_name
	 */
	public void setProject_name(String project_name) {
		this.projectName = project_name;
	}

	public String getNotification_title() {
		return this.notificationTitle;
	}

	/**
	 * 
	 * @param notification_title
	 */
	public void setNotification_title(String notification_title) {
		this.notificationTitle = notification_title;
	}

	public String getNotification_desc() {
		return this.notificationDesc;
	}

	/**
	 * 
	 * @param notification_desc
	 */
	public void setNotification_desc(String notification_desc) {
		this.notificationDesc = notification_desc;
	}

	public MemberType getCreator_member() {
		return this.creatorMember;
	}

	/**
	 * 
	 * @param creator_member
	 */
	public void setCreator_member(MemberType creator_member) {
		this.creatorMember = creator_member;
	}

}