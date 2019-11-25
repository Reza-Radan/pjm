package com.progetto.projectmanagement.domain.member;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table
public class Member {

	@Id
	private UUID id;

	private String name;
	private String family;
	private String imagePath;
	private String phoneNumber;
	private String email;
	private String userName;
	private String password= "123456";
	private List<UUID> subMembers;
	private workingType workingtype;
	private String config;



	@NotNull
	private Long modifyTime;

	private UUID roleId;

	private String lang;

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Member() {}

	public workingType getWorkingtype() {return workingtype;}

	public void setWorkingtype(workingType workingtype) {this.workingtype = workingtype;}

	public List<UUID> getSubMembers() {return subMembers;}

	public void setSubMembers(List<UUID> subMembers) {this.subMembers = subMembers;	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getFamily() {
		return this.family;
	}

	/**
	 * 
	 * @param family
	 */
	public void setFamily(String family) {
		this.family = family;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return this.userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UUID getRoleId() {
		return roleId;
	}

	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
}