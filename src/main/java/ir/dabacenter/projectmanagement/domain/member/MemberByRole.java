package ir.dabacenter.projectmanagement.domain.member;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table
public class MemberByRole {

	@Id
	private UUID memberId;
	private String memberName;
	private String memberFamily;
	private String roleName;
	private String permissionId;
	@NotNull
	private Long modifyTime;

	@Id
	private UUID roleId;



	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getmemberId() {
		return this.memberId;
	}

	/**
	 * 
	 * @param memberId
	 */
	public void setmemberId(UUID memberId) {
		this.memberId = memberId;
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

	public String getMember_family() {
		return this.memberFamily;
	}

	/**
	 * 
	 * @param member_family
	 */
	public void setMember_family(String member_family) {
		this.memberFamily = member_family;
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

	public String getPermissionId() {return permissionId;}

	public void setPermissionId(String permissionId) {this.permissionId = permissionId;	}
}