package ir.dabacenter.projectmanagement.domain;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.UUID;


@UserDefinedType("uuidClass")
public class uuidClass {

    /*
    This class create b/c we could not create list of uuid,
    when create list of uuid like List<UUID> in cassandra list of timebase
    UUID wil create so we better create one UUID varible here withe suitable
    annotation the create list of this class
     */

    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
