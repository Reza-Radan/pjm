package com.progetto.projectmanagement.domain.task;

import com.datastax.driver.core.DataType;
import org.apache.cassandra.utils.UUIDGen;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table("subfeature_by_task")
public class SubfeatureByTask {

	@IdColumn(name = "subfeatureId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID subfeatureId;

	@IdColumn(name = "taskId", type = PrimaryKeyType.CLUSTERED ,ordering = Ordering.DESCENDING)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskId;

	private String taskTitle;
	private String subFeatureTitle;
	private String descTask;
	private String lang;
	private int status;

	@NotNull
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID modifyTime;

	public String getLang() {return lang;}

	public void setLang(String lang) {	this.lang = lang;}

	public UUID getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(UUID modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getsubfeatureId() {
		return this.subfeatureId;
	}

	/**
	 * 
	 * @param subfeatureId
	 */
	public void setsubfeatureId(UUID subfeatureId) {
		this.subfeatureId = subfeatureId;
	}

	public UUID gettaskId() {
		return this.taskId;
	}

	/**
	 * 
	 * @param taskId
	 */
	public void settaskId(UUID taskId) {
		this.taskId = taskId;
	}

	public String gettaskTitle() {
		return this.taskTitle;
	}

	/**
	 * 
	 * @param taskTitle
	 */
	public void settaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getsubFeatureTitle() {
		return this.subFeatureTitle;
	}

	/**
	 * 
	 * @param subFeatureTitle
	 */
	public void setsubFeatureTitle(String subFeatureTitle) {
		this.subFeatureTitle = subFeatureTitle;
	}

	public String gettaskDesc() {
		return this.descTask;
	}

	/**
	 * 
	 * @param descTask
	 */
	public void settaskDesc(String descTask) {
		this.descTask = descTask;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}