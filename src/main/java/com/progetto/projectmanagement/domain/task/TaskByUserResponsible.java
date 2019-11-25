package com.progetto.projectmanagement.domain.task;


import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("task_by_user_responsible")
public class TaskByUserResponsible {

//	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
//	@CassandraType(type = DataType.Name.TIMEUUID)
//	private UUID id;

//	@IdColumn(name = "userId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
//	@CassandraType(type = DataType.Name.UUID)
//	private UUID userId;
 
	@IdColumn(name = "taskId", type = PrimaryKeyType.CLUSTERED ,ordering = Ordering.DESCENDING)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskId;


//	@IdColumn(name = "implementorId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID implementorId;

	@IdColumn(name = "responsibleId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID responsibleId;

	private MemberType implementor;
	private MemberType responsible;

	private String projectName;
	private String taskTitle;
//	private MemberType member;
	private String descTask;
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskStartDate;
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskEndDate;
	private int status;
	private String taskDuration;
	private int progressRate;
	private String lang;


	@NotNull
	private UUID modifyTime;

	@NotNull
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID time;


	public String getLang() {return lang;}

	public void setLang(String lang) {	this.lang = lang;}

	public UUID getModifyTime() { return modifyTime;}

	public void setModifyTime(UUID modifyTime) {this.modifyTime = modifyTime;}

//	public UUID getId() {
//		return this.id;
//	}

//	/**
//	 *
//	 * @param id
//	 */
//	public void setId(UUID id) {
//		this.id = id;
//	}

//	public UUID getprojectId() {
//		return this.userId;
//	}
//
//	/**
//	 *
//	 * @param userId
//	 */
//	public void setprojectId(UUID userId) {
//		this.userId = userId;
//	}
//
	public UUID getTaskId() {
		return this.taskId;
	}

	/**
	 * 
	 * @param taskId
	 */
	public void setTaskId(UUID taskId) {
		this.taskId = taskId;
	}

	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * 
	 * @param projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTaskTitle() {
		return this.taskTitle;
	}

	/**
	 * 
	 * @param taskTitle
	 */
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

//	public MemberType getMember() {
//		return this.member;
//	}
//
//	/**
//	 *
//	 * @param member
//	 */
//	public void setMember(MemberType member) {
//		this.member = member;
//	}


	public String getDescTask() {
		return descTask;
	}

	public void setDescTask(String descTask) {
		this.descTask = descTask;
	}

	/**
	 * 
	 * @param taskStartDate
	 */
	public void setTaskStartDate(UUID taskStartDate) {
		this.taskStartDate = taskStartDate;
	}
	public UUID getTaskStartDate() {
		return this.taskStartDate;
	}

	public UUID getTaskEndDate() {
		return this.taskEndDate;
	}

	/**
	 * 
	 * @param taskEndDate
	 */
	public void setTaskEndDate(UUID taskEndDate) {
		this.taskEndDate = taskEndDate;
	}



	public String getTaskDuration() {
		return this.taskDuration;
	}

	/**
	 * 
	 * @param taskDuration
	 */
	public void setTaskDuration(String taskDuration) {
		this.taskDuration = taskDuration;
	}

	public int getProgressRate() {
		return this.progressRate;
	}

	/**
	 * 
	 * @param progressRate
	 */
	public void setProgressRate(int progressRate) {
		this.progressRate = progressRate;
	}


	public UUID getImplementorId() {
		return implementorId;
	}

	public void setImplementorId(UUID implementorId) {
		this.implementorId = implementorId;
	}

	public UUID getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(UUID responsibleId) {
		this.responsibleId = responsibleId;
	}

	public MemberType getImplementor() {
		return implementor;
	}

	public void setImplementor(MemberType implementor) {
		this.implementor = implementor;
	}

	public MemberType getResponsible() {
		return responsible;
	}

	public void setResponsible(MemberType responsible) {
		this.responsible = responsible;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UUID getTime() {
		return time;
	}

	public void setTime(UUID time) {
		this.time = time;
	}
}