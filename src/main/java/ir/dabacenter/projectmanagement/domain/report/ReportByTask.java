package ir.dabacenter.projectmanagement.domain.report;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.uuidClass;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("report_by_task")
public class ReportByTask {

	@IdColumn(name = "taskId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID taskId;

//	@IdColumn(name = "projectId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	@IdColumn(name = "reportId", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID reportId;

	private String taskTitle;
	private String reportDesc;
	private List<uuidClass> attachment;
	private String lang;

	@NotNull
	private UUID modifyTime;

	@NotNull
	private UUID time;


	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
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

	public UUID getReportId() {
		return this.reportId;
	}

	/**
	 * 
	 * @param reportId
	 */
	public void setReportId(UUID reportId) {
		this.reportId = reportId;
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

	public String getReportDesc() {
		return this.reportDesc;
	}

	/**
	 * 
	 * @param reportDesc
	 */
	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	public List<uuidClass> getAttachment() {
		return this.attachment;
	}

	/**
	 * 
	 * @param attachment
	 */
	public void setAttachment(List<uuidClass> attachment) {
		this.attachment = attachment;
	}

	public UUID getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(UUID modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getTime() {
		return time;
	}

	public void setTime(UUID time) {
		this.time = time;
	}
}