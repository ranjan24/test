package controllers;


import play.Logger;
import play.cache.Cache;
import play.db.ebean.Model;
import play.data.Form;
import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

import models.Relationship;
import models.User;
import models.UserNominee;
//import com.avaje.ebean.Ebean;
//import com.avaje.ebean.FetchConfig;
//import com.avaje.ebean.FutureList;
//import com.avaje.ebean.Query;
//import com.fasterxml.jackson.databind.JsonNode;

import static play.libs.Json.toJson;
import play.data.Form;
import scala.concurrent.duration.Duration;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FutureList;
import com.avaje.ebean.Query;

import exceptions.MyCustomException;


public class Application extends Controller {
	
	
    public static Result index() {
    	
    	String mapUrl = SerializeConstants.FOLDER_LIST.get(0)+"/"+SerializeConstants.nameOfUserNomineeMapFile;
    	
    	String infoUrl = SerializeConstants.FOLDER_LIST.get(0)+"/"+SerializeConstants.nameOfSerializeInfoFile;
    	
    	response().setContentType("text/html");
    	response().setHeader(CACHE_CONTROL, "max-age=3600");
    	response().setHeader(ETAG, "xxx");
    	
    	String apiValue = request().getHeader("ETAG");
    	
    	System.out.println("Req header: "+apiValue);
    	
    	 /*Thread t = new Thread(new Runnable() { public void run() { 
    	   // your code goes here... 
    		
    	 }}); */


    	//Nominee.serializeNominees();
    	
    	
    	//Connection.generateConnection();                                 // CAREFUL
    	
	    Form<User> userForm = Form.form(User.class);
	        
	    //session().clear();
	    
        return ok(index.render("Hello World!", userForm));
        
    }
    
    
    // Post controller method coming from index page
    
    public static Result signUp()  {
    	
        User user = null;
    	
    	Form<User> userForm = Form.form(User.class);
    	
    	 // Bind the data
    	
    	try  {
    	
    	    user = userForm.bindFromRequest().get();
    	    
    	    
    	    //user = Form.form(User.class).bindFromRequest().get();

    	    
    	}catch(Exception e) {
    		
    	    return unauthorized(e.getMessage());
    		
    	}
    	
    	if(userForm.hasErrors()) {
    		
    		String errorMsg = "";
            java.util.Map<String, List<play.data.validation.ValidationError>> errorsAll = userForm.errors();
            for (String field : errorsAll.keySet()) {
                errorMsg += field + " ";
                for (play.data.validation.ValidationError error : errorsAll.get(field)) {
                    errorMsg += error.message() + ", ";
                }
            }
            flash("error", "Please correct the following errors: " + errorMsg);
    		
            return badRequest(errorMsg);
            
        } else {
        	
        	// Save the user in session and save to database.
        	
            user.save();
            
            //session("userId", Integer.toString(user.id));
            
            flash("success", "Successful sign up");
            
        }
    	
    	// After signing up, redirect to add nominee page  
    	
    	return redirect("/addNominee");
    	
    }  // ENd function
    
	
    
    // Add nominee method.
    // Controller name: addNominee()
    // View name: add_nominee
    
    public static Result addNominee()  {
    	
    	// Get the list of users present in database
        
        List<User> allUsers = UserRelated.allUsers();
        
        // Use the form helper to get the data coming from form
        
        Form<UserNominee> nomineeForm = Form.form(UserNominee.class);
        
        return ok(addNominee.render("HI", allUsers, nomineeForm));
    	
    	
    }  // End addnominee
    
    
    // add nominee post
    
    public static Result addNomineePost()  {
    	
        UserNominee nominee = null;
    	
    	int status = 0, acceptStatus = 0, createdBy = 0,
    			i, flag = 0, commaSeperatedLength = 0;
    	
    	String[] commaSeperated = null;
    	
    	String nomineeContactNumber = null, nomineeName = null;
    	
    	// Get the list of users present in database
		
        //List<User> allUsers = new Model.Finder<>(String.class, User.class).all();
        
        // Use the form helper to get the data coming from form
        
        Form<UserNominee> nomineeForm = Form.form(UserNominee.class);
        
        
        // If the request is post, then save the nominees .
        
        
        try  {
        	
    	    nominee = nomineeForm.bindFromRequest().get();
    	   
    	    //user = Form.form(UserNominee.class).bindFromRequest().get();
    	    
    	    
    	}catch(Exception e) {
    		
    	    return unauthorized(e.getMessage());
    		
    	}   
        
        try{
        	acceptStatus = nominee.acceptStatus;
        }catch(Exception e)  {};
        
        try  {
            //createdBy = Integer.valueOf(session("userId"));
            createdBy = nominee.addNomineeUserSelect;
        }catch(Exception e)  {};
        
        try  {
            nomineeContactNumber = nominee.nomineeContactNumber;
        }catch(Exception e)  {};
        
        try  {
            nomineeName = nominee.nomineeName;
        }catch(Exception e)  {};
        
        try  {
            status = nominee.status;
        }catch(Exception e)  {};
        
        // After signup the page is redirected here hence,
        // Get the createdBy id using session
        
        //nominee.createdBy = Integer.valueOf(session("userId"));
        
        nominee.createdBy = nominee.addNomineeUserSelect;
        
        // Now get the individual values of comma seperated values.
        
        try  {
        
            commaSeperated = nominee.userNomineeTable.split(",");
        
        }catch(Exception e)  {
        	
        	// Set falg to 1
        	flag += 1;
        	
        }
        
        
        if(flag > 0)  {
        
        	commaSeperatedLength = 0;
        }
        
        else  {
        	
            commaSeperatedLength = commaSeperated.length;
        }
            
        
        UserNominee newNominee;
        
        // The commaSeperated array contents says that the individual value are the 
        // users selected as nominees.
        
        if(commaSeperatedLength > 0)  {
        
            for(i = 0; i < commaSeperatedLength; i++)  {
        	
        	    newNominee = new UserNominee();
        	
        	    try  {
        	        newNominee.acceptStatus = acceptStatus;
        	     }catch(Exception e)  {};
        	
        	    try  {
        		    newNominee.createdBy = createdBy;    
        	    }catch(Exception e)  {};
        	
        	    try  {
        		    newNominee.nomineeCloveUserId = Integer.valueOf(commaSeperated[i]);	
        	    }catch(Exception e) {};
        	
        	    try  {
        		    newNominee.status = status;	
        	    }catch(Exception e)  {};
        	
        	    try  {
        	        newNominee.nomineeContactNumber = nomineeContactNumber;
        	    }catch(Exception e)  {};
        	
        	    try {
        	        newNominee.nomineeName = nomineeName;
        	    }catch(Exception e)  {};
        	
        	    // Save the nominee to database.
        	
        	    newNominee.save();
        	
        	
            }  // End for
        
        }  // End if
        
        else  {
        	
        	//length == 0 
        	
        	nominee.save();
        	
        }  // End else  
   
        return redirect("/listNominees/0");
    	
    	
    }  // End function
    
    
    /**
     * At the time of deploying, use this method instead of addNomineePost method 
     * @return
     */
    
    public static void saveUserNominee()  {
    	
    	
        Thread thr = new Thread()  {
        	
        	@SuppressWarnings("unchecked")
			public void run()  {
        	
        
    	// Get the form data from the client. 
    	
    	int i, createdBy = 0, nomineeCloveUserId = 0, acceptStatus, status;
    	
    	String nomineeName = null, dateModified, nomineeContactNumber = null;
        
    	// This is the actual name of the file that contains the information
    	// of individual hashmap and the nameOfUserNomineeMapFile is self explanatory
    	// Both the files are present in /path/to/project/public/serialized/nominees
    	// The name of the files remains constant as the folders are different.
    	
    	final String nameOfSerializeInfoFile = "SerializeInfo.ser", nameOfUserNomineeMapFile = "SerializedMap";
    	
    	UserNominee nominee = null;
    	
    	SerializeInfo serializeInfoObject = null;
    	
    	Map<Integer, List<Integer>> nomineeHashMap = null;
    	
    	
    	
    	// Now assign the above identifiers accordingly and carefully.
    	
    	nomineeContactNumber = "123";              //remove
    	nomineeName = "asd";						//remove
    	
       // if nomineeContact number and name are not null then the person is 
    	// not yet a clove user
    	
    	if(nomineeContactNumber != null && nomineeName != null)  {
    		
    		// Non clove user info is added.
    		
    		nominee.nomineeContactNumber = nomineeContactNumber;
    		
    		nominee.nomineeName = nomineeName;
    		
    		nominee.createdBy = createdBy;
    		
    		nominee.save();
    		
    		return;
    		
    		// ADD date modified here.
    		
    		
    		
    	}  // ENd if
    	
    	else if(nomineeCloveUserId != 0 )  {                       // Doubt line
    		
    		// Registered clove info is the form data.
    		
    		nominee.nomineeCloveUserId = nomineeCloveUserId;
    		
    		nominee.createdBy = createdBy;
    		
    		// Add the rest below
    		
    		
    	}  // End else if
        
    	
    		
    	if(nominee != null)  {
    		
    		// Now get the NomineeInfo type object present in one of the folders and check 
    		// whether the createdBy is present in the objects list.
    		
    		int folderListSize = SerializeConstants.FOLDER_LIST.size(); 
    		
    		for(i = 0; i < folderListSize; i++)  {
    			
    			// Get individual folder and check where createdBy is present
    			// in individual object
    			
    			File serializeInfo = new File(SerializeConstants.FOLDER_LIST.get(i)+"/"+nameOfSerializeInfoFile);
    			
    			File serializedMap = new File(SerializeConstants.FOLDER_LIST.get(i)+"/"+nameOfUserNomineeMapFile);
    			
    	
    			/////////////////////////////////////////////////////////////////////////////////////////////////
    			
    			// Now we need to work on four cases. They are 
    	    	// 1) If both files are present
    			// 2) Only serializeInfo is present
    			// 3) Only serialize map is present
    			// 4) Both files are absent
    			
    			if(serializeInfo.exists() && serializedMap.exists())  {
    				
    				// If you are here then both the files are present
    				
    				// Get the object that shows the info of the hashmap, 
    				// for that current folder
    				
    				serializeInfoObject = (SerializeInfo) Helper.getSerializedObject(serializeInfo.getPath());
    				
    				if(serializeInfoObject == null)  {
    					
    					// If for some reason the file is present but returns null
    					
    					break;
    					
    				}
    				
    				// Check if the above object contains 'createdBy' 
    				
                    if(serializeInfoObject.contains(nominee.createdBy)) {
    					
    					// If you are here then serializeInfoObject (Object serialized before)
    					// contains the createdBy element you asked for.
    					// Now we need to get the hashmap that is in the current directory.
    					
    					nomineeHashMap = (Map<Integer, List<Integer>>) Helper.getSerializedObject(serializedMap.getPath());
    					
    					if(nomineeHashMap == null)  {
    						
    						// If for some reason the file is present but returns null
    						
    						break;
    						
    					}
    					
    					// Now if the nomineeHashMap.list contains the user_id then dont do anything
    					
    					if(!nomineeHashMap.get(nominee.createdBy).contains(nominee.nomineeCloveUserId))  {
    						
    						nomineeHashMap.get(nominee.createdBy).add(nominee.createdBy);
    						
    					}
    					
    					// Now serialize the above object.
    					
    					Helper.serializeObject(serializedMap.getPath(), nomineeHashMap);
    					
    					
    				}  // End if serializeInfoObject.contains(nominee.createdBy)
    				
                    else  {
                    	
                    	// If you are here then createdBy is not present in the object.
                    	// It may be present in other folder
                    	
                    	continue;
                    }
    				
    				
    			}  // End if both files are present
    			
    			
    			// Now continue for other cases
    			
    			
    			
    			
    		}  // End for
    		
    		
    		
    		
    		
    	}  // End if nominee != null
    	
    	
    	
    	
         }}; // End thread
    	
         thr.setPriority(7);
         
         thr.start();
        
         
    }  // End method
    
    
    
    
  public static Result listNominees(int id)  {
    	
    	
      List<User> allUsers = UserRelated.allUsers();
      
	  //'List<User> allUsers = new Model.Finder<>(String.class, User.class).all();
      
      List<UserNominee> nominees = null;
      
     
      if(id != 0)  {
      
          // Get the list of nominees by providing id
      
          nominees = Nominee.listOfNomineeObject(id);
      
      }
      
      else  {
    	  
    	  nominees = null;
    	  
      }
        
      return ok(listNominee.render("List Nominees", allUsers, nominees));
    	
  }  // End function  
    
  
  /**
   * @param nill
   * Method: GET
   * Description: This method renders the view for relationship page.
   */
  
  public static Result relationship()  {
	  
	  // Get the list of users present in database
	  
	  List<User> allUsers = UserRelated.allUsers();
	  
	  List<Object> blank = new LinkedList<Object>();
	 
	  // Get the id and name of user except nominees in nominee table
	 
	  Map<String, String> userExceptNominee = UserRelated.getAllUsersExceptNominee();
	  
	  // Get all nominees except users.
	  
	  Map<String, String> nomineeExceptUsers = Nominee.getAllNomineeExceptUser();
	  
	  return ok(relationship.render("Relationship", allUsers,
			  userExceptNominee, nomineeExceptUsers, blank));
	  
	  
  }  // End function
  
  // Get the post data
  
  /**
   * @deprecated
   * @param user1
   * @param user2
   * @return
   */
  public static Result relationshipPost(String user1, String user2)  {
	
	  
	  Form<Relationship> relationshipForm = Form.form(Relationship.class);
	 
	  Relationship relation = null;
	  
	  try  {
		  
		   relation = relationshipForm.bindFromRequest().get();
		  
	  }catch(Exception e)  {}
	  
	  // GET THE TWO STRINGS
	  
	  System.out.println("USER1: "+relation.source);
	  
	  System.out.println("USER1: "+relation.destination);
	  
	  
	  return ok("");
	  
	  
  }  // End function
  
  
  /**
   * @param nil
   * @return Promise of a result( List of shortestPath)
   *
   * The asyncRelationship function is invoked by POST /asyncRelationship written in routes file. This function fetches the source and 
   * destination using REST and the shortest path between source and destination is calculated asynchronously.
   * This sub routine is executed in different context hence the end users/ clients are blocked till the shortest path
   * is calculated but server's scalability is unchanged. 
   * 
   */
  
  public static Promise<Result> asyncRelationship() {

	  Form<Relationship> relationshipForm = Form.form(Relationship.class);
	  
      final List<User> allUsers = UserRelated.allUsers();
	  
	  final List<Integer> blank = new LinkedList<Integer>();
	 
	  // Get the id and name of user except nominees in nominee table
	 
	  final Map<String, String> userExceptNominee = UserRelated.getAllUsersExceptNominee();
	  
	  // Get all nominees except users.
	  
	  final Map<String, String> nomineeExceptUsers = Nominee.getAllNomineeExceptUser();
	  
	  List<String> sourceList = new LinkedList<>();
	  
	  Relationship relation = null;
	  
	  try  {
		  
		   // Bind form data
		  
		   relation = relationshipForm.bindFromRequest().get();
		  
	  }catch(Exception e)  {
		  
		  // Return promise of result or empty list
	  }
	  
	  // Assign source and destination to strings source and destination
	  
	  final String sourceString = relation.source;
	  
	  final String destinationString = relation.destination;
	  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  ExecutionTime.clearVariables(); // Remove when deploying
	  
	  SaveFutureNomineeTime.clear();
	  
	  
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	  // Promise 
	  
	  Promise<List<Integer>> promiseOfShortPath = Promise.promise(
		      new Function0<List<Integer>>() {
		    	  
		        public List<Integer> apply() {
		        	
		        	// Convert source and destination to integers
		        	
		        	int source = Integer.valueOf(Relation.returnOnlyNumbers(sourceString));
		        	
		        	int destination = Integer.valueOf(Relation.returnOnlyNumbers(destinationString));
		        	
		            return Relation.shortestPath(source, destination);
		          
		        }});
		        
		        return promiseOfShortPath.map(
		        		
		        	      new Function<List<Integer>, Result>() {
		        	    	  
		        	        public Result apply(List<Integer> shortPath) {
		        	        	
		        	        	//////////////////////////////////////////////////////////// Remove
		        	        	
		        	        	List<Object> shortPaths = new LinkedList<>();
		        	        	
		        	        	shortPaths.addAll(shortPath);
		        	        	
		        	        	/*for(int i=0; i < shortPath.size(); i++)  {
		        	        		
		        	        		shortPaths.add(String.valueOf(shortPath.get(i)));
		        	        		
		        	        		
		        	        	}  */
		        	        	
		        	        	
		        	        		
		        	            //return ok(relationship.render("Relationship", allUsers,
				        	      //			  userExceptNominee, nomineeExceptUsers, blank));
		        	        		
		        	        	
		        	        	
		        	        	System.out.println("Short path LENGTH: "+shortPath.size());
		        	        	
		        	        	System.out.println("Before for loop took: "+ExecutionTime.beforeForLoop);
		        	        	
		        	        	System.out.println("Nominee function totally took: "+ExecutionTime.fetchNomineeTime+" ms");
		        	    		
		        	        	System.out.println(" nomineeListClear totally took: "+ExecutionTime.nomineeListClear+" ms");
		        	    		
		        	        	System.out.println(" breadthFirstQueuePtotally took: "+ExecutionTime.breadthFirstQueuePoll+" ms");
		        	    		
		        	        	System.out.println(" generateActualShortPath took: "+ExecutionTime.generateActualShortPath+" ms");
		        	    		
		        	        	//System.out.println(" nomineeCacheGet took: "+ExecutionTime.nomineeCacheGet+" ms");
		        	    		
		        	        	System.out.println(" nomineeListSize took: "+ExecutionTime.nomineeListSize+" ms");
		        	    		
		        	        	System.out.println(" Line 316 newUserNomineeList took: "+ExecutionTime.newUserNomineeList+" ms");
		        	        	
		        	        	System.out.println(" cacheSet took: "+ExecutionTime.cacheSet+" ms");
		        	    		
		        	        	System.out.println(" breadthFirstQueueAddAll took: "+ExecutionTime.breadthFirstQueueAddAll+" ms");
		        	    		
		        	        	System.out.println(" Line 339 pathTraversedAddAll took: "+ExecutionTime.pathTraversedAddAll+" ms");
		        	    	
		        	        	System.out.println(" Nominees fetched from cache count: "+ExecutionTime.cacheNomineeListFetch);
		        	        	
		        	        	System.out.println(" total NumberOfNodesVisited: "+ExecutionTime.numberOfNodesVisited);
		        	        	
		        	        	System.out.println(" total cache set count nominees : "+ExecutionTime.cacheSetCount);
		        	        	
		        	        	System.out.println(" Total number of times nominees fetched from map: "+ExecutionTime.nomineeFetchMapCount);
		        	        	
		        	        	System.out.println("Contents count of NomineeSaveMap.map"+NomineeSaveMap.addNominee.size());
		        	        	
		        	        	//System.out.printf(" futureNomineeTimeTaken: %d\n",Nominee.futureNomineeTimeTaken);
		        	        	
		        	        	System.out.println("Number of times nominee function called: "+ExecutionTime.actualCountOfCallingNomineeFunction);
		        	        	
		        	        	//System.out.println("saveFutureNominee totally took: "+ExecutionTime.saveFutureNominee+" ms");
		        	        	
		        	        	System.out.println("Save future nominee's variableDeclaration took: "+SaveFutureNomineeTime.variableDeclaration+" ms");
		        	        	
		        	        	System.out.println("Save future nominee's ebean took: "+SaveFutureNomineeTime.ebeanQuery+" ms");
		        	        	
		        	        	System.out.println("Save future nominee's for loop totally took: "+SaveFutureNomineeTime.forLoop+" ms");
		        	        	
		        	        	System.out.println("SHORTEST PATH TOOK: ");
		        	        	
		        	        	System.out.println(ExecutionTime.beforeForLoop + ExecutionTime.fetchNomineeTime +
		        	        			ExecutionTime.nomineeListClear + ExecutionTime.breadthFirstQueuePoll + 
		        	        			ExecutionTime.generateActualShortPath + ExecutionTime.nomineeCacheGet + 
		        	        			ExecutionTime.nomineeListSize + ExecutionTime.cacheSet + ExecutionTime.newUserNomineeList+ 
		        	        			ExecutionTime.breadthFirstQueueAddAll + ExecutionTime.pathTraversedAddAll + 
		        	        			ExecutionTime.cacheNomineeListFetch );
		        	        	
		        	        	//System.out.println("FINAL MAP OF SAVED NOMS : "+NomineeSaveMap.addNominee);
		        	        	
		        	        	//NomineeSaveMap.addNominee.clear();
		        	        	
		        	        	return ok(relationship.render("Relationship", allUsers,
		        	      			  userExceptNominee, nomineeExceptUsers, shortPaths));
		        	        	
		        	        } 
		        	        
		        	      }
		        	      
		        	    );
	 
  }  // End asyncRelationship

    
  
  
  
  public static Promise<Result> asyncRelationships(final String sourceString, final String destinationString) {
	    
	  
	  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  ExecutionTime.clearVariables(); // Remove when deploying
	  
	  SaveFutureNomineeTime.clear();
	  
	  
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	  // Promise 
	  
	  Promise<List<Integer>> promiseOfShortPath = Promise.promise(
		      new Function0<List<Integer>>() {
		    	  
		        public List<Integer> apply() {
		        	
		        	// Convert source and destination to integers
		        	
		        	int source = Integer.valueOf(Relation.returnOnlyNumbers(sourceString));
		        	
		        	int destination = Integer.valueOf(Relation.returnOnlyNumbers(destinationString));
		        	
		            return Relation.shortestPath(source, destination);
		            
		          
		        }});
		        
		        return promiseOfShortPath.map(
		        		
		        	      new Function<List<Integer>, Result>() {
		        	    	  
		        	        public Result apply(List<Integer> shortPath) {
		        	        	
		        	        	//////////////////////////////////////////////////////////// Remove
		        	        	
		        	        	//List<Object> shortPaths = new LinkedList<>();
		        	        	
		        	        	//shortPaths.addAll(shortPath);
		        	        	
		        	        	List<ReturnUserList> userList = new LinkedList<>();
		        	        	
		        	        	userList = Relation.relationshipToUserList(shortPath);
		        	        	
		        	        	/*for(int i=0; i < shortPath.size(); i++)  {
		        	        		
		        	        		shortPaths.add(String.valueOf(shortPath.get(i)));
		        	        		
		        	        		
		        	        	}  */
		        	        	
		        	        	
		        	        		
		        	            //return ok(relationship.render("Relationship", allUsers,
				        	      //			  userExceptNominee, nomineeExceptUsers, blank));
		        	        		
		        	        	
		        	        	
		        	        	System.out.println("Short path LENGTH: "+shortPath.size());
		        	        	
		        	        	System.out.println("Before for loop took: "+ExecutionTime.beforeForLoop);
		        	        	
		        	        	System.out.println("Nominee function totally took: "+ExecutionTime.fetchNomineeTime+" ms");
		        	    		
		        	        	System.out.println(" nomineeListClear totally took: "+ExecutionTime.nomineeListClear+" ms");
		        	    		
		        	        	System.out.println(" breadthFirstQueuePtotally took: "+ExecutionTime.breadthFirstQueuePoll+" ms");
		        	    		
		        	        	System.out.println(" generateActualShortPath took: "+ExecutionTime.generateActualShortPath+" ms");
		        	    		
		        	        	//System.out.println(" nomineeCacheGet took: "+ExecutionTime.nomineeCacheGet+" ms");
		        	    		
		        	        	System.out.println(" nomineeListSize took: "+ExecutionTime.nomineeListSize+" ms");
		        	    		
		        	        	System.out.println(" Line 316 newUserNomineeList took: "+ExecutionTime.newUserNomineeList+" ms");
		        	        	
		        	        	System.out.println(" cacheSet took: "+ExecutionTime.cacheSet+" ms");
		        	    		
		        	        	System.out.println(" breadthFirstQueueAddAll took: "+ExecutionTime.breadthFirstQueueAddAll+" ms");
		        	    		
		        	        	System.out.println(" Line 339 pathTraversedAddAll took: "+ExecutionTime.pathTraversedAddAll+" ms");
		        	    	
		        	        	System.out.println(" Nominees fetched from cache count: "+ExecutionTime.cacheNomineeListFetch);
		        	        	
		        	        	System.out.println(" total NumberOfNodesVisited: "+ExecutionTime.numberOfNodesVisited);
		        	        	
		        	       
		        	        	
		        	        	System.out.println(" Total number of times nominees fetched from map: "+ExecutionTime.nomineeFetchMapCount);
		        	        	
		        	        	System.out.println("Contents OF MAP "+ExecutionTime.cacheSetCount);
		        	        	
		        	        	//System.out.printf(" futureNomineeTimeTaken: %d\n",Nominee.futureNomineeTimeTaken);
		        	        	
		        	        	System.out.println("Number of times nominee function called: "+ExecutionTime.actualCountOfCallingNomineeFunction);
		        	        	
		        	        	//System.out.println("saveFutureNominee totally took: "+ExecutionTime.saveFutureNominee+" ms");
		        	        	
		        	        	System.out.println("Save future nominee's variableDeclaration took: "+SaveFutureNomineeTime.variableDeclaration+" ms");
		        	        	
		        	        	System.out.println("Save future nominee's ebean took: "+SaveFutureNomineeTime.ebeanQuery+" ms");
		        	        	
		        	        	System.out.println("Save future nominee's for loop totally took: "+SaveFutureNomineeTime.forLoop+" ms");
		        	        	
		        	        	System.out.println("SHORTEST PATH TOOK: ");
		        	        	
		        	        	System.out.println(ExecutionTime.beforeForLoop + ExecutionTime.fetchNomineeTime +
		        	        			ExecutionTime.nomineeListClear + ExecutionTime.breadthFirstQueuePoll + 
		        	        			ExecutionTime.generateActualShortPath + ExecutionTime.nomineeCacheGet + 
		        	        			ExecutionTime.nomineeListSize + ExecutionTime.cacheSet + ExecutionTime.newUserNomineeList+ 
		        	        			ExecutionTime.breadthFirstQueueAddAll + ExecutionTime.pathTraversedAddAll + 
		        	        			ExecutionTime.cacheNomineeListFetch );
		        	        	
		        				
		        	        	
		        	        	//System.out.println("FINAL MAP OF SAVED NOMS : "+NomineeSaveMap.addNominee);
		        	        	
		        	        	//NomineeSaveMap.addNominee.clear();
		        	        	
		        	        	return ok(toJson(userList));
		        	        	
		        	        } 
		        	        
		        	      }
		        	      
		        	    );
	 
  }  // End asyncRelationship
  
  
  
  
    
}  // End class
