package com.progetto.projectmanagement.domain.notification;

import com.datastax.driver.core.DataType;
import com.progetto.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("notification_by_user")
public class NotificationByIssue {

	@IdColumn(name = "notificationId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID notificationId;

	@IdColumn(name = "issueId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID issueId;

	private String notificationTitle;
	private String issue_title;
	private String notificationDesc;

	@NotNull
	private Long modifyTime;

	@Frozen
	private MemberType creator_member;

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
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

	public UUID getissueId() {
		return this.issueId;
	}

	/**
	 * 
	 * @param issueId
	 */
	public void setissueId(UUID issueId) {
		this.issueId = issueId;
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

	public String getIssue_title() {
		return this.issue_title;
	}

	/**
	 * 
	 * @param issue_title
	 */
	public void setIssue_title(String issue_title) {
		this.issue_title = issue_title;
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
		return this.creator_member;
	}

	/**
	 * 
	 * @param creator_member
	 */
	public void setCreator_member(MemberType creator_member) {
		this.creator_member = creator_member;
	}

}