package models;

import play.Logger;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.*;
import com.avaje.ebean.Query;

import play.libs.Json;

import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class User extends Model {

    @Id
    @NotNull
    public int id;

    public String code;
    public String registrationKey;
    
    @Required
    public String name;
    public String gender;
    
    @Required
    public String mobileNumber;
    
    @Required
    public String emailId;
    public String deviceID;
    public String profilePictureName;
    public String socialMediaDetails;
    public String bleId;
    public Integer isCloveUser;
    public String cloveDeviceBleId;
    public Integer status;
    public Integer isDeleted;
    public String dateCreated;
    public String dateModified;
    public Integer updateCount;

    public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<PersonalMessageStatus> usermessages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomineeUser")
    List<UserNominee> userNominee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    List<UserNominee> nomineeCreator;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "panicUser")
    List<PanicMaster> panicUser;
    

    public User() { }

    public User(String code, String regKey, String name, String mobileno, String email, String deviceid,
            String socialmediaidetails, String profimagepath, Integer iscloveuser, String clovedeviceid, String bleid, Integer status ) {
        this.code = code;
        this.registrationKey = regKey;
        this.name = name;
        this.mobileNumber = mobileno;
        this.emailId = email;
        this.deviceID = deviceid;
        this.socialMediaDetails = socialmediaidetails;
        this.profilePictureName = profimagepath;
        this.isCloveUser = iscloveuser;
        this.cloveDeviceBleId = clovedeviceid;
        this.bleId = bleid;
        this.status = status;
        this.isDeleted = 0;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-d H:m:s");
        this.dateCreated = new DateTime().toString(fmt);
        this.dateModified = new DateTime().toString(fmt);
        this.updateCount = 0;
    }

    public List<String> getUserRegKeyList(String needyId){
        //Query q = Ebean.find(User.class);
        
        List<User> userData = User.find.where()
                .ne("id", needyId)
                .eq("is_deleted", "0")
                .findList();
        
        List<String> regkeyList = new ArrayList<String>();
        Iterator<User> i = userData.iterator();

        while(i.hasNext()){
            User userDataDetails = i.next();
            if(userDataDetails.registrationKey!=null)
                regkeyList.add(userDataDetails.registrationKey);
        }
        
        return regkeyList;
    }

    public List<SqlRow> getUserByCode(String code){
        String sql = "select u.id, u.code, u.name, u.mobile_number, u.email_id, " +
                "u.device_id, u.profile_picture_name, u.social_media_details, " +
                "u.clove_device_ble_id, u.status, u.is_deleted, u.update_count" +
                " from user u where code = :code";
        SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
        sqlQuery.setParameter("code", code);

        List<SqlRow> userlist = sqlQuery.findList();
        
        return userlist;
    }
    
    
    // Getters 
    
    public Integer getUserId()  {
    	
    	return this.id;
    	
    }
    
    
    // Get userName
    
    public String getUserName()  {
    	
    	return this.name;
    	
    }
    
    
    // Get user's phone number
    
    
    public String getUsersMobileNumber()  {
    	
    	
    	return this.mobileNumber;
    }
    
    
    // Get user's emailId
    
    public String getUserEmail()  {
    	
    	return this.emailId;
    }
    
    
    // Get user's gender
    
    
    public String getUserGender()  {
    	
    	return this.gender;
    	
    }
    
    
    
    
    
    
    
    
}  // End class