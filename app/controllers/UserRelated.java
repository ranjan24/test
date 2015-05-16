package controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;

import models.User;
import models.UserNominee;
import play.db.ebean.Model;

/**
 * 
 * The UserRelated class contains methods relating to users.
 */
public class UserRelated {

    // Fetch all the users present in database
	
	public static List<User> allUsers()  {
		
		//List<User> allUsers = new Model.Finder<>(String.class, User.class).all();
		
		List<User> allUsers = Ebean.find(User.class)
				.select("id")
				.orderBy()
				.asc("id")
				.findList();
		
		return allUsers;
		
		
	}  // End function 
	
	
	
	// The below function fetches all users in nominee table
	// This function is used in views
	
	public static Map<String, String> getAllUsersExceptNominee()  {
		
		int i = 0, size = 0, userId = 0;
		
		List<User> users = new LinkedList<User>();
		
		Map<String, String> userMap = new LinkedHashMap<String, String>();
			
		// Get all nominees in database
		
		List<UserNominee> nominees = Nominee.allNominees();
		
		size = nominees.size();
		
		if(size > 0)  {
		
		    // Get the users using for loop
		
		    for(i = 0; i < size; i++)  {
			
			    try  {
			
			        userId = nominees.get(i).nomineeCloveUserId;
			        
			    
			    }catch(Exception e)  {
				
				    continue;
			    }
			
			    // Add user to users variable
			
			    User user = Ebean.find(User.class, userId);
			    
			    try  {
			
			        userMap.put(String.valueOf(user.id), user.name);
			
			    }catch(Exception e)  {
			    	
			    }
			
		    }  // End for
			
			
		    return userMap;
		    
		}  // End if
		
		else  {
			
			return userMap;
			
		}
					
	}  // End function
	
	
}  // End class
