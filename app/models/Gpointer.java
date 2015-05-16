package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.*;
import com.avaje.ebean.*;

@Entity
public class Gpointer extends Model {

	@Id
	public String b_id;

	public String name;
	public Float lati;
	public Float lngi;
	
	 public List getDescription(String nme) {  
        
		String sql = "select * from gpointer g where name like :name";
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		sqlQuery.setParameter("name", "%"+nme);	
		List<SqlRow> list = sqlQuery.findList();
		
		return list;	
    }  
}