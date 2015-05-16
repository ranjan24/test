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
public class UserLocation extends Model implements Serializable{
    @Id
    public int Id;

    public String UserId;
    public String PanicCode;
    public String Latitude;
    public String Longitude;
    public String LocatedOn;

    public static Finder<String, UserLocation> find = new Finder<String, UserLocation>(String.class, UserLocation.class);

    public UserLocation() { }

    public UserLocation(String userId, String panicCode, String latitude, String longitude){
        this.UserId = userId;
        this.PanicCode = panicCode;
        this.Latitude = latitude;
        this.Longitude = longitude;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-d H:m:s");
        this.LocatedOn = new DateTime().toString(fmt);
    }

    public List getAllUserLocationByPanicId(String panicCode, String userId){

         String sql = "SELECT `t1`.`id` AS id, `t1`.`user_id` AS userid," +
                 " `t1`.`panic_code` AS paniccode, `t1`.`latitude` AS latitude," +
                 " `t1`.`longitude` AS longitude, `t2`.`parent_id` AS needyid," +
                 " DATE_FORMAT(`t1`.`located_on`, '%Y-%m-%d %T') AS locatedon, "+
" CASE"+
"	 WHEN `t2`.`is_nominee` = '1' THEN 'NM'"+
"	 WHEN `t2`.`is_nominee` = '0' THEN 'TN'"+
"	 ELSE 'NR'"+
" END AS `relationstatus`"+
" FROM `user_location` AS `t1`"+
" LEFT OUTER JOIN `nominee_views` AS `t2`"+
" ON `t1`.`user_id` = `t2`.`user_id`"+
" WHERE `t1`.`panic_code` = :paniccode"+
" AND `t2`.`parent_id` is null"+
" OR `t2`.`parent_id` = :userid";

         SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		sqlQuery.setParameter("paniccode", panicCode);
		sqlQuery.setParameter("userid", userId);
		List<SqlRow> list = sqlQuery.findList();               
		return list;
    }

    public void deleteByPanicCode(String panicCode){      
        SqlUpdate down = Ebean.createSqlUpdate("DELETE FROM user_location WHERE panic_code = '"+panicCode+"'");
        down.execute();
    }
    
}