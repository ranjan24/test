package models;

import play.db.ebean.*;

import javax.persistence.*;
import com.avaje.ebean.*;

@Entity
public class Person extends Model {

    @Id
    public String id;

    public String name;
    public String utype;

    //public static Finder<String, Person> find = new Finder<String, Person>(String.class, Person.class);

    public Person() {
        
    }

    public Person(String name, String type) {
        this.name = name;
        this.utype = type;
    }
}