package models;

import java.util.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import javax.persistence.*;
import com.avaje.ebean.Query;
import org.joda.time.DateTime;
import com.avaje.ebean.SqlUpdate;
import java.io.Serializable;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class UserTempNominee extends Model implements Serializable {
    @Id
    public int Id;

    public String UserId;
    public String PanicCode;
    public String ForwardedBy;
    public String NeedyId;
    public String ForwardedOn;

    public UserTempNominee() { }

    public UserTempNominee(String userid, String panicCode,
            String forwardedBy, String needyId) {

        this.UserId = userid;
        this.PanicCode = panicCode;
        this.ForwardedBy = forwardedBy;
        this.NeedyId = needyId;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-d H:m:s");
        this.ForwardedOn = new DateTime().toString(fmt);

    }

    public UserTempNominee isTempNomineeExists(String userId, String panicCode){
        Query<UserTempNominee> querySql =
                Ebean.find(UserTempNominee.class)
                .where()
                .eq("UserId", userId)
                .eq("PanicCode", panicCode)
                .query();

        return querySql.findUnique();
    }
}