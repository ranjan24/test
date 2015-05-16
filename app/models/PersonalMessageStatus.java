package models;

import play.Logger;
import play.db.ebean.*;
import javax.persistence.*;

import com.avaje.ebean.*;
import com.avaje.ebean.Query;


import com.fasterxml.jackson.databind.node.*;
import play.libs.Json;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class PersonalMessageStatus extends Model {
    @Id
    public Integer Id;
    
    public String Message;
    public int SenderId;
    public int ReceiverId;
    public int IsRead = 0;
    public int IsDeleted = 0;
    public String DateSend;
    public String DateRead;
    public String DateDelete;

    @OneToOne
    @JoinColumn(name="sender_id", insertable = false, updatable = false)
    User user;

   
    public static Finder<String, PersonalMessageStatus> find = new Finder<String, PersonalMessageStatus>(String.class, PersonalMessageStatus.class);

    public PersonalMessageStatus() { }

    public PersonalMessageStatus(String messageText, int senderid, int receiverid) {
        this.Message = messageText;
        this.SenderId = senderid;
        this.ReceiverId = receiverid;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-d H:m:s");
        this.DateSend = new DateTime().toString(fmt);
    }

    public List<PersonalMessageStatus> getAllMessageByUserId(int userid){
        
        Query<PersonalMessageStatus> querysql = Ebean.find(PersonalMessageStatus.class)                
                .fetch("user")
                .where().eq("ReceiverId", userid).eq("t0.is_deleted", "0")
                .query();
       
        List<PersonalMessageStatus> messageList = querysql.findList();
        return messageList;
    }
}