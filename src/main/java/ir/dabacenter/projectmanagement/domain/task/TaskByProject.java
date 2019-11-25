package ir.dabacenter.projectmanagement.domain.task;


import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("task_by_project")
public class TaskByProject {

//	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
//	@CassandraType(type = DataType.Name.TIMEUUID)
//	private UUID id;

	@IdColumn(name = "projectId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;
 
	@IdColumn(name = "taskId", type = PrimaryKeyType.CLUSTERED ,ordering = Ordering.DESCENDING)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskId;

	private String projectName;
	private String taskTitle;
	private MemberType member;
	private String descTask;
	private UUID taskStartDate;
	private UUID taskEndDate;
	private int taskStatus;
	private String taskDuration;
	private int progressRate;
	private String lang;
	private int status;

	@NotNull
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID modifyTime;


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

	public UUID getProjectId() {
		return this.projectId;
	}

	/**
	 * 
	 * @param projectId
	 */
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

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
	public void setprojectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTaskTitle() {
		return this.taskTitle;
	}

	/**
	 * 
	 * @param taskTitle
	 */
	public void settaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
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

	public String getTaskDesc() {
		return this.descTask;
	}



	/**
	 *
	 * @param
	 */

	public void setDescTask(String descTask) {
		this.descTask = descTask;
	}

	public String getDescTask() {
		return descTask;
	}

	/**
	 * 
	 * @param taskStartDate
	 */
	public void setTaskStartDate(UUID taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public UUID getTaskStartDate() {
		return taskStartDate;
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

	public int getTaskStatus() {
		return this.taskStatus;
	}

	/**
	 * 
	 * @param taskStatus
	 */
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}