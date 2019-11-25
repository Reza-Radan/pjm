//package ir.dabacenter.projectmanagement.domain.task;
//
//import com.datastax.driver.core.DataType;
//import ir.dabacenter.projectmanagement.domain.member.MemberType;
//import org.springframework.cassandra.core.Ordering;
//import org.springframework.cassandra.core.PrimaryKeyType;
//import org.springframework.data.cassandra.mapping.CassandraType;
//import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
//import org.springframework.data.cassandra.mapping.Table;
//
//import javax.validation.constraints.NotNull;
//import java.util.UUID;
//
//@Table("task_table")
//public class TaskTable {
//
//	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
//	@CassandraType(type = DataType.Name.TIMEUUID)
//	private UUID id;
//
//	@CassandraType(type = DataType.Name.UUID)
//	private UUID implementorId;
//
//	@CassandraType(type = DataType.Name.UUID)
//	private UUID responsibleId;
//
//	@CassandraType(type = DataType.Name.UUID)
//	private UUID projectId;
//
//	private String projectName;
//
//	private UUID modifyTime;
//
//	@IdColumn(name = "time", ordinal = 1,  type = PrimaryKeyType.CLUSTERED ,ordering = Ordering.DESCENDING)
//	@CassandraType(type = DataType.Name.TIMEUUID)
//	private UUID time;
//
//	@CassandraType(type = DataType.Name.UUID)
//	private UUID subfeatureId;
//
//	private String subfeatureTitle;
//	private String title;
//	private String descTask;
//	private Long startDate;
//	private Long endDate;
//	private boolean isPeriodic;
//	private int status;
//	private String periodicType;
//	private Long periodicTypeStart;
//	private Long periodicTypeStop;
//	private int progressRate;
//	private String taskDuration;
//	private String taskPriority;
//	private MemberType responsible;
//	private MemberType implementor;
//	private String lang;
//
//
//	public TaskTable() {
//	}
//
//	public String getLang() {return lang;}
//
//	public void setLang(String lang) {this.lang = lang;}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public UUID getModifyTime() {
//		return modifyTime;
//	}
//
//	public void setModifyTime(UUID modifyTime) {
//		this.modifyTime = modifyTime;
//	}
//
//	public UUID getId() {
//		return this.id;
//	}
//
//	/**
//	 *
//	 * @param id
//	 */
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return this.title;
//	}
//
//	/**
//	 *
//	 * @param title
//	 */
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDesc() {
//		return this.descTask;
//	}
//
//	/**
//	 *
//	 * @param descTask
//	 */
//	public void setDesc(String descTask) {
//		this.descTask = descTask;
//	}
//
//	public Long getstartDate() {
//		return this.startDate;
//	}
//
//	/**
//	 *
//	 * @param startDate
//	 */
//	public void setstartDate(Long startDate) {
//		this.startDate = startDate;
//	}
//
//	public Long getendDate() {
//		return this.endDate;
//	}
//
//	/**
//	 *
//	 * @param endDate
//	 */
//	public void setendDate(Long endDate) {
//		this.endDate = endDate;
//	}
//
//	public boolean getisPeriodic() {
//		return this.isPeriodic;
//	}
//
//	/**
//	 *
//	 * @param isPeriodic
//	 */
//	public void setisPeriodic(boolean isPeriodic) {
//		this.isPeriodic = isPeriodic;
//	}
//
//	public boolean getIs_run() {
//		// TODO - implement TaskController.getIs_run
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param is_run
//	 */
//	public void setIs_run(boolean is_run) {
//		// TODO - implement TaskController.setIs_run
//		throw new UnsupportedOperationException();
//	}
//
//	public String getperiodicType() {
//		return this.periodicType;
//	}
//
//	/**
//	 *
//	 * @param periodicType
//	 */
//	public void setperiodicType(String periodicType) {
//		this.periodicType = periodicType;
//	}
//
//	public Long getperiodicTypeStart() {
//		return this.periodicTypeStart;
//	}
//
//	/**
//	 *
//	 * @param periodicTypeStart
//	 */
//	public void setperiodicTypeStart(Long periodicTypeStart) {
//		this.periodicTypeStart = periodicTypeStart;
//	}
//
//	public Long getperiodicTypeStop() {
//		return this.periodicTypeStop;
//	}
//
//	/**
//	 *
//	 * @param periodicTypeStop
//	 */
//	public void setperiodicTypeStop(Long periodicTypeStop) {
//		this.periodicTypeStop = periodicTypeStop;
//	}
//
//	public int getprogressRate() {
//		return this.progressRate;
//	}
//
//	/**
//	 *
//	 * @param progressRate
//	 */
//	public void setprogressRate(int progressRate) {
//		this.progressRate = progressRate;
//	}
//
//	public String gettaskDuration() {
//		return this.taskDuration;
//	}
//
//	/**
//	 *
//	 * @param taskDuration
//	 */
//	public void settaskDuration(String taskDuration) {
//		this.taskDuration = taskDuration;
//	}
//
//	public String gettaskPriority() {
//		return this.taskPriority;
//	}
//
//	/**
//	 *
//	 * @param taskPriority
//	 */
//	public void settaskPriority(String taskPriority) {
//		this.taskPriority = taskPriority;
//	}
//
//	public MemberType getResponsible() {
//		return this.responsible;
//	}
//
//	/**
//	 *
//	 * @param responsible
//	 */
//	public void setResponsible(MemberType responsible) {
//		this.responsible = responsible;
//	}
//
//	public MemberType getimplementor() {
//		return this.implementor;
//	}
//
//	/**
//	 *
//	 * @param implementor
//	 */
//	public void setimplementor(MemberType implementor) {
//		this.implementor = implementor;
//	}
//
//	public UUID getsubfeatureId() {
//		return this.subfeatureId;
//	}
//
//	/**
//	 *
//	 * @param subfeatureId
//	 */
//	public void setsubfeatureId(UUID subfeatureId) {
//		this.subfeatureId = subfeatureId;
//	}
//
//	public String getsubfeatureTitle() {
//		return this.subfeatureTitle;
//	}
//
//	/**
//	 *
//	 * @param subfeatureTitle
//	 */
//	public void setsubfeatureTitle(String subfeatureTitle) {
//		this.subfeatureTitle = subfeatureTitle;
//	}
//
//	public UUID getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(UUID projectId) {
//		this.projectId = projectId;
//	}
//
//	public String getProjectName() {
//		return projectName;
//	}
//
//	public void setProjectName(String projectName) {
//		this.projectName = projectName;
//	}
//
//	public UUID getImplementorId() {
//		return implementorId;
//	}
//
//	public void setImplementorId(UUID implementorId) {
//		this.implementorId = implementorId;
//	}
//
//	public UUID getResponsibleId() {
//		return responsibleId;
//	}
//
//	public void setResponsibleId(UUID responsibleId) {
//		this.responsibleId = responsibleId;
//	}
//
//	public UUID getTime() {
//		return time;
//	}
//
//	public void setTime(UUID time) {
//		this.time = time;
//	}
//}