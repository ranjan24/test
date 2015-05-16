package models;

import play.db.ebean.*;
import com.avaje.ebean.*;
import javax.persistence.*;
import com.avaje.ebean.Query;
import java.io.Serializable;

@Entity
public class NomineeViews extends Model implements Serializable {
    
    public String ParentId;
    public String UserId;
    public int IsNominee;

    public NomineeViews(){ };

    public NomineeViews isNomineeByCreator(String creator, String NomineeUserId){
        Query<NomineeViews> querysql = Ebean.find(NomineeViews.class)
                .where()
                .eq("ParentId", creator)
                .eq("UserId", NomineeUserId)
                .query();
        return querysql.findUnique();
    }

}