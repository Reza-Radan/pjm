package ir.dabacenter.projectmanagement.domain.notification;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("notification_by_user")
public class NotificationByUser {

	@IdColumn(name = "notificationId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID notificationId;

	private String notificationTitle;
	private String notificationDesc;

	@IdColumn(name = "notificationTypeId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID notificationTypeId;

	private String notificationType;
	private MemberType creatorMember;
	private List<MemberType> members;

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

	public UUID getnotificationTypeId() {
		return this.notificationTypeId;
	}

	/**
	 * 
	 * @param notificationTypeId
	 */
	public void setnotificationTypeId(UUID notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public String getNotification_type() {
		return this.notificationType;
	}

	/**
	 * 
	 * @param notification_type
	 */
	public void setNotification_type(String notification_type) {
		this.notificationType = notification_type;
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

	public List<MemberType> getMembers() {
		return this.members;
	}

	/**
	 * 
	 * @param members
	 */
	public void setMembers(List<MemberType> members) {
		this.members = members;
	}

}