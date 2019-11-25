package ir.dabacenter.projectmanagement.domain.project;


import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("role_by_project")
public class RoleByProject {

	@IdColumn(name = "roleId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID roleId;

	@IdColumn(name = "projectId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	@IdColumn(name = "memberID", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID memberID;

	private MemberType member;
	private String projectName;
	private String memberName;
	private boolean isStackholder;
	private String roleName;
	private int permissionKey;
	private String permissionName;
	private String lang;


	public UUID getMemberID() {
		return memberID;
	}

	public void setMemberID(UUID memberID) {
		this.memberID = memberID;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@NotNull
	private Long modifyTime;

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getroleId() {
		return this.roleId;
	}

	/**
	 * 
	 * @param roleId
	 */
	public void setroleId(UUID roleId) {
		this.roleId = roleId;
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

	public String getMember_name() {
		return this.memberName;
	}

	/**
	 * 
	 * @param member_name
	 */
	public void setMember_name(String member_name) {
		this.memberName = member_name;
	}

	public boolean getIs_stackholder() {
		return this.isStackholder;
	}

	/**
	 * 
	 * @param is_stackholder
	 */
	public void setIs_stackholder(boolean is_stackholder) {
		this.isStackholder = is_stackholder;
	}

	public String getRole_name() {
		return this.roleName;
	}

	/**
	 * 
	 * @param role_name
	 */
	public void setRole_name(String role_name) {
		this.roleName = role_name;
	}

	public int getPermission_key() {
		return this.permissionKey;
	}

	/**
	 * 
	 * @param permission_key
	 */
	public void setPermission_key(int permission_key) {
		this.permissionKey = permission_key;
	}

	public String getPermission_name() {
		return this.permissionName;
	}

	/**
	 * 
	 * @param permission_name
	 */
	public void setPermission_name(String permission_name) {
		this.permissionName = permission_name;
	}

}