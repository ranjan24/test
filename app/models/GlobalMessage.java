package models;

import play.db.ebean.*;
import javax.persistence.*;
import java.io.Serializable;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class GlobalMessage extends Model implements Serializable {
    @Id
    public Integer Id;

    public String MessageCode;
    public int SendTo;
    public String SendOn;

    public static Finder<String, GlobalMessage> find = new Finder<String, GlobalMessage>(String.class, GlobalMessage.class);

    public GlobalMessage() { }

    public GlobalMessage(String messageCode, int sendTo, String createdBy) {
        this.MessageCode = messageCode;
        this.SendTo = sendTo;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-d H:m:s");
        this.SendOn = new DateTime().toString(fmt);
    }
}