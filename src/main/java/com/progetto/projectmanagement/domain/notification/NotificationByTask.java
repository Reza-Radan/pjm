package com.progetto.projectmanagement.domain.notification;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("notification_by_task")
public class NotificationByTask {

//	@IdColumn(name = "notificationId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
//	@CassandraType(type = DataType.Name.UUID)
//	private UUID notificationId;

	@IdColumn(name = "taskId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskId;

	private String taskName;
	private String notificationTitle;
	private String notificationDesc;
	private MemberType creatorMember;

	@NotNull
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID modifyTime;

	public UUID getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(UUID modifyTime) {
		this.modifyTime = modifyTime;
	}

//	public UUID getnotificationId() {
//		return this.notificationId;
//	}

//	/**
//	 *
//	 * @param notificationId
//	 */
//	public void setnotificationId(UUID notificationId) {
//		this.notificationId = notificationId;
//	}

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

	public String getTask_name() {
		return this.taskName;
	}

	/**
	 * 
	 * @param task_name
	 */
	public void setTask_name(String task_name) {
		this.taskName = task_name;
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