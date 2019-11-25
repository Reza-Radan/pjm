//package ir.dabacenter.projectmanagement.domain.member;
//
//import com.datastax.driver.core.DataType;
//import org.springframework.data.cassandra.mapping.CassandraType;
//import org.springframework.data.cassandra.mapping.UserDefinedType;
//
//import java.util.List;
//import java.util.UUID;
//
//@UserDefinedType("workingType")
//public class workingType {
//
//    long startTime,endTime;
//    String type;
//    List<Integer> days;
//
//    @CassandraType(type = DataType.Name.UUID)
//    private UUID id;
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public long getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(long startTime) {
//        this.startTime = startTime;
//    }
//
//    public long getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(long endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public List<Integer> getDays() {
//        return days;
//    }
//
//    public void setDays(List<Integer> days) {
//        this.days = days;
//    }
//}
