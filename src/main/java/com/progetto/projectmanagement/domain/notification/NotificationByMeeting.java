package com.progetto.projectmanagement.domain.notification;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("notification_by_meeting")
public class NotificationByMeeting {


	@IdColumn(name = "notificationId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID notificationId;

	@IdColumn(name = "meetingId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID meetingId;

	private String meetingTitle;
	private Long meetingTime;
	private String notificationTitle;
	private String notificationDesc;
	private MemberType creatorMember;

	@NotNull
	private Long modifyTime;

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

	public UUID getmeetingId() {
		return this.meetingId;
	}

	/**
	 * 
	 * @param meetingId
	 */
	public void setmeetingId(UUID meetingId) {
		this.meetingId = meetingId;
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

	public Long getMeeting_time() {
		return this.meetingTime;
	}

	/**
	 * 
	 * @param meeting_time
	 */
	public void setMeeting_time(Long meeting_time) {
		this.meetingTime = meeting_time;
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