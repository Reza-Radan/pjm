package ir.dabacenter.projectmanagement.domain.permission;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Table
public class Permission {

	@Id
	private UUID id;
	private String name;
	private String description;
	private int permissionKey;
	private permissionType taskComponent,projectComponent,
			               issueComponent,reportComponent,
			               memberComponent ;
	private permissionMeetingType meetingComponent;
	private Long modifyTime;
	private String lang;

	public String getLang() {return lang;}

	public void setLang(String lang) {	this.lang = lang;}

	public permissionType getTaskComponent() {
		return taskComponent;
	}

	public void setTaskComponent(permissionType taskComponent) {
		this.taskComponent = taskComponent;
	}

	public permissionType getProjectComponent() {
		return projectComponent;
	}

	public void setProjectComponent(permissionType projectComponent) {
		this.projectComponent = projectComponent;
	}

	public permissionType getIssueComponent() {
		return issueComponent;
	}

	public void setIssueComponent(permissionType issueComponent) {
		this.issueComponent = issueComponent;
	}

	public permissionType getReportComponent() {
		return reportComponent;
	}

	public void setReportComponent(permissionType reportComponent) {
		this.reportComponent = reportComponent;
	}

	public permissionType getMemberComponent() {
		return memberComponent;
	}

	public void setMemberComponent(permissionType memberComponent) {
		this.memberComponent = memberComponent;
	}

	public permissionMeetingType getMeetingComponent() {return meetingComponent;}

	public void setMeetingComponent(permissionMeetingType meetingComponent) {this.meetingComponent = meetingComponent;}

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

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int getPermission_key() {
		return this.permissionKey;
	}

	/**
	 * 
	 * @param permission_key
	 */
	public void setPermission_key(int permission_key) {
		this.permissionKey = permissionKey;
	}

}