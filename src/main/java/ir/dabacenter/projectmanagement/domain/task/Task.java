package ir.dabacenter.projectmanagement.domain.task;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table("task")
public class Task {

	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID id;

	@CassandraType(type = DataType.Name.UUID)
	private UUID implementorId;

	@CassandraType(type = DataType.Name.UUID)
	private UUID responsibleId;

	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	private String projectName;

	private UUID modifyTime;

	@IdColumn(name = "time", ordinal = 1,  type = PrimaryKeyType.CLUSTERED ,ordering = Ordering.DESCENDING)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID time;

	@CassandraType(type = DataType.Name.UUID)
	private UUID subfeatureId;

	private String subfeatureTitle;
	private String title;
	private String descTask;
	private UUID startDate;
	private UUID endDate;
	private boolean isPeriodic;
	private int status;
	private String periodicType;
	private Long periodicTypeStart;
	private Long periodicTypeStop;
	private int progressRate;
	private String taskDuration;
	private String taskPriority;
	private MemberType responsible;
	private MemberType implementor;
	private String lang;


	public Task() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public UUID getProjectId() {
		return projectId;
	}

	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public UUID getSubfeatureId() {
		return subfeatureId;
	}

	public void setSubfeatureId(UUID subfeatureId) {
		this.subfeatureId = subfeatureId;
	}

	public String getSubfeatureTitle() {
		return subfeatureTitle;
	}

	public void setSubfeatureTitle(String subfeatureTitle) {
		this.subfeatureTitle = subfeatureTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescTask() {
		return descTask;
	}

	public void setDescTask(String descTask) {
		this.descTask = descTask;
	}

	public UUID getStartDate() {
		return startDate;
	}

	public void setStartDate(UUID startDate) {
		this.startDate = startDate;
	}

	public UUID getEndDate() {
		return endDate;
	}

	public void setEndDate(UUID endDate) {
		this.endDate = endDate;
	}

	public boolean isPeriodic() {
		return isPeriodic;
	}

	public void setPeriodic(boolean periodic) {
		isPeriodic = periodic;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPeriodicType() {
		return periodicType;
	}

	public void setPeriodicType(String periodicType) {
		this.periodicType = periodicType;
	}

	public Long getPeriodicTypeStart() {
		return periodicTypeStart;
	}

	public void setPeriodicTypeStart(Long periodicTypeStart) {
		this.periodicTypeStart = periodicTypeStart;
	}

	public Long getPeriodicTypeStop() {
		return periodicTypeStop;
	}

	public void setPeriodicTypeStop(Long periodicTypeStop) {
		this.periodicTypeStop = periodicTypeStop;
	}

	public int getProgressRate() {
		return progressRate;
	}

	public void setProgressRate(int progressRate) {
		this.progressRate = progressRate;
	}

	public String getTaskDuration() {
		return taskDuration;
	}

	public void setTaskDuration(String taskDuration) {
		this.taskDuration = taskDuration;
	}

	public String getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public MemberType getResponsible() {
		return responsible;
	}

	public void setResponsible(MemberType responsible) {
		this.responsible = responsible;
	}

	public MemberType getImplementor() {
		return implementor;
	}

	public void setImplementor(MemberType implementor) {
		this.implementor = implementor;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}