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

@Table("project_report")
public class ProjectReport {

	@IdColumn(name = "projectId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	@IdColumn(name = "reportId", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID reportId;

	private String projectTitle;
	private String reportDesc;
	private List<uuidClass> attachment;
	private String lang;

	@NotNull
	private UUID modifyTime;
	private UUID time;


	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
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

	public String getProjectTitle() {
		return this.projectTitle;
	}

	/**
	 * 
	 * @param projectTitle
	 */
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
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

}