package ir.dabacenter.projectmanagement.domain.meeting;

import ir.dabacenter.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;
//                    .value(id, uuid = UUIDGen.getTimeUUID())
@Table
public class Meeting {

	@Id
	private UUID id;
	private String title;
	private String meetingDesc;
	private Long startDate;
	private String durationTime;


	@NotNull
	private Long modifyTime;

	@Frozen
	private MemberType createMember;
	private Long startMeeting;
	private MemberType member;
	private String lang;


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
		return this.meetingDesc;
	}

	/**
	 * 
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.meetingDesc = desc;
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

	public MemberType getMember() {
		return this.member;
	}

	/**
	 * 
	 * @param member
	 */
	public void setMember(MemberType member) {
		this.member = member;
	}

}