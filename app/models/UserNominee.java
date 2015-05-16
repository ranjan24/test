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
public class UserNominee extends Model implements Serializable {
    @Id
    @Column(name="id")
    public int nomineeId;
    
    @Column(name="user_id")
    public Integer nomineeCloveUserId;
    public String nomineeContactNumber;
    public String nomineeName;
    public Integer acceptStatus;
    public Integer createdBy;
    public Integer status;    
    public String dateCreated;
    public String dateModified;
    
    @Transient
    public String userNomineeTable;
    
    @Transient
    public Integer addNomineeUserSelect;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    public User nomineeUser;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="created_by", insertable = false, updatable = false)
    public User creator;

    //public static Finder<String, UserNominee> find
            //= new Finder<String, UserNominee>(String.class, UserNominee.class);

    public UserNominee() { }

    public UserNominee(Integer UserId, String contactNo, String nomineeName, int creator, int acceptStatus) {
        this.nomineeCloveUserId = UserId;
        this.nomineeContactNumber = contactNo;
        this.nomineeName = nomineeName;
        this.acceptStatus = acceptStatus;
        this.createdBy = creator;
        this.status = 1;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-d H:m:s");
        this.dateCreated = new DateTime().toString(fmt);
        this.dateModified = new DateTime().toString(fmt);
    }

    public static Finder<String, UserNominee> find = new Finder<String, UserNominee>(String.class, UserNominee.class);

    public int updateNomineeDetailsById(Integer userid, String mobileno){
        String sql = "UPDATE user_nominee un " +
                "SET un.user_id = "+userid+", un.accept_status = 1 " +
                "WHERE un.nominee_contact_number = "+mobileno;

        SqlUpdate sqlUp = Ebean.createSqlUpdate(sql);
        
        return sqlUp.execute();
    }

    public List<String> getAllUserExceptNominee(List<Integer> nomineeList){
        Query q = Ebean.find(UserNominee.class);
        Expression exp = q.getExpressionFactory().idIn(nomineeList);
        List<User> userData = q.where().not(exp).findList();

        List<String> regkeyList = new ArrayList<String>();
        Iterator<User> i = userData.iterator();

        while(i.hasNext()){
            String registrationKey = i.next().registrationKey;
            if(registrationKey!=null)
                regkeyList.add(registrationKey);
        }
        return regkeyList;
    }

    public UserNominee isNomineeByCreator(String creator, String NomineeUserId){
        Query<UserNominee> querysql = Ebean.find(UserNominee.class)
                .fetch("nomineeUser")
                .where()
                .eq("CreatedBy", creator)
                .eq("t0.user_id", NomineeUserId)
                .eq("t0.status", "1")
                .eq("t0.accept_status", "1")
                .query();
        return querysql.findUnique();
    }

    public List<UserNominee> getUserNomineeByCreator(String creator){
        Query<UserNominee> querysql = Ebean.find(UserNominee.class)
                            .fetch("nomineeUser")
                            .fetch("creator")
                            .where()
                            .eq("created_by", creator)
                            .eq("t0.status", "1")
                            .query();
        List<UserNominee> nomineeList = querysql.findList();
        //Logger.debug(querysql.getGeneratedSql());
        return nomineeList;
    }

    
    
    // GETTERS 
    
    // Get nomineeId
    
    public Integer getNomineeId()  {
    	
    	
    	return this.nomineeId;
    	
    }
    
    
    public Integer getNomineeCloveUserId()  {
    	
    	
    	return this.nomineeCloveUserId;
    	
    	
    }  // End function
    
    public String getNomineeContactNumber()  {
    	
    	return this.nomineeContactNumber;
    	
    }
    
    
    
    
    // Get nomineeName
    
    public String getNomineeName()  {
    	
    	return this.nomineeName;
    	
    }
    
    
    // Get createdBy
    
    public Integer getNomineeCreatedBy()  {
    	
    	
    	return this.createdBy;
    	
    }
    
    
    
    
    
}  // ENd class