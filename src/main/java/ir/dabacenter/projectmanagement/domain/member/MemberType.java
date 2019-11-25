//package ir.dabacenter.projectmanagement.domain.member;
//
//
//import com.datastax.driver.core.DataType;
//import org.springframework.data.cassandra.mapping.CassandraType;
//import org.springframework.data.cassandra.mapping.UserDefinedType;
//
//import java.util.UUID;
//
//@UserDefinedType("memberType")
//public class MemberType {
//
//    @CassandraType(type = DataType.Name.UUID)
//    private UUID id;
//
//    private String name;
//    private String family;
//    private String imagePath;
//    private String phoneNumber;
//    private String email;
//    private String userName;
//
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getFamily() {
//        return family;
//    }
//
//    public void setFamily(String family) {
//        this.family = family;
//    }
//
//    public String getImagePath() {
//        return imagePath;
//    }
//
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//}
