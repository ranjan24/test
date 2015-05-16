package models;

import play.Logger;

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
public class PanicMaster extends Model implements Serializable{
    @Id
    public int Id;

    public String PanicCode;
    public String UserId;
    public String Latitude;
    public String Longitude;
    public int IsActive;
    public String DateCreated;
    public String DateModified;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    public User panicUser;

    public static Finder<String, PanicMaster> find = new Finder<String, PanicMaster>(String.class, PanicMaster.class);

    public PanicMaster() {}

    public PanicMaster(String panicCode, String userId,
            String latitude, String longitude){
        this.PanicCode = panicCode;
        this.UserId = userId;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.IsActive = 1;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-d H:m:s");
        this.DateCreated = new DateTime().toString(fmt);
        this.DateModified = new DateTime().toString(fmt);
    }

    public PanicMaster panicExists(String userId, String panicCode){
        Query query = Ebean.find(PanicMaster.class);
        ExpressionList<PanicMaster> queryExpression = query.where();

        if(userId != null)
            queryExpression.add(
                query
                .getExpressionFactory()
                .eq("UserId", userId)
           );

        if(panicCode != null)
           queryExpression.add(
                query
                .getExpressionFactory()
                .eq("PanicCode", panicCode)
            );

        return queryExpression
                .add(
                    query
                    .fetch("panicUser")
                    .getExpressionFactory()
                    .eq("is_active", 1)
                ).findUnique();
    }
}