package com.progetto.projectmanagement.domain.attachment;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table
public class Attachment {

	@Id
	private UUID id;
	private String name;
	private String path;
	private String extention;
	private double size;

	@NotNull
	private Long modifyTime;


	private UUID project_id;
	private String lang;


	public String getLang() {return lang;	}

	public void setLang(String lang) {	this.lang = lang;	}

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

	public String getPath() {
		return this.path;
	}

	/**
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public String getExtention() {
		return this.extention;
	}

	/**
	 * 
	 * @param extention
	 */
	public void setExtention(String extention) {
		this.extention = extention;
	}

	public double getSize() {
		return this.size;
	}

	/**
	 * 
	 * @param size
	 */
	public void setSize(double size) {
		this.size = size;
	}

	public UUID getProject_id() {
		return this.project_id;
	}

	/**
	 * 
	 * @param project_id
	 */
	public void setProject_id(UUID project_id) {
		this.project_id = project_id;
	}

}