package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.*;

import static play.libs.Json.toJson;
import play.cache.Cache;
import play.db.ebean.Model;
import models.User;
import models.UserNominee;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.FutureList;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.RawSql.Sql;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlUpdate;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;

/**
 * 
 * @author ranjan<br>
 * 
 * The Nominee class contains methods related to nominees .<br>
 * 
 * List of instance variables: NIL<br>
 * 
 * List of methods :<br>
 * 
 * 1) List<PathTraversed> generateUserNomineePair(String user, List<String> nominees);<br>
 * 2) List<UserNominee> listOfNomineeObject(int userId);<br>
 * 3) List<String> userNomineeList(String userId ); <br>
 * 4) List<String> userNomineeList(List<String> nominees, Set<String> visited, Queue<String> breadthFirstQueue);<br>
 * 5) void saveFutureNominee(final List<String> futureNomineeList);<br>
 * 6) List<UserNominee> allNominees(); <br>
 * 7) Map<String, String> getAllNomineeExceptUser();<br>
 * 8) String fetchNomineePhone(String nomineeId);<br>
 */

public class Nominee  {
 	
	public static Map<Integer, List<Integer>> nomineeSaved = new HashMap<>();  // Not needed
	
	/**
	 * 
	 * @param user
	 * @param nominees<br><br>
	 * The below function generateUserNomineePair generates
	 *  user-nominee pair (8, 7) (8, 9)
	 * @argument: User, his nominee list
	 * @return
	 */
	
	public static List<PathTraversed> generateUserNomineePair(Integer user, List<Integer> nominees)  {
		
		int i = 0, nomineeSize = 0;
		
		// Create a linked list to add the individual list (8, 7) and (8, 9)
		
		List<PathTraversed> generatedPair = new LinkedList<PathTraversed>();
		
		PathTraversed subPath;
		
		nomineeSize = nominees.size();
		
		if(nomineeSize > 0)  {
		
		    for(i = 0 ; i < nomineeSize; i++)  {
			
			    // create new object of path traversed
			    // If user is 15U then in every iteration subPath will be
			    //Subpath: [15U, 4U]
			    //Subpath: [15U, 6U]
			    //Subpath: [15U, 9U]
			    //Subpath: [15U, 11U]
			    // Where 4 6 9 11 are 15's nominee
			
		        subPath = new PathTraversed();
			
			    subPath.individualPair.add(user);
			
			    subPath.individualPair.add(nominees.get(i));
			
			    generatedPair.add(subPath);
			
		    }  // End for
		
		
		    return generatedPair;
		
		}  // End if  
		
		else  {
			
			return generatedPair;
			
		}
		
	}  // End function
	
	
	
	
	/**
	 * 
	 * @param userId<br><br>
	 * This function returns the list of nominee id's for a specific user id.
	 * @argument: int userId ==> The id of the user
	 * @return: List<Integer> ==> The id's of nominees
	 * @return user's nominees in the form of list
	 */
	
	
	public static List<Integer> userNomineeList(int createdBy )  {
		  
		// The userId will be in the from of 11U and 183N where
		// U is user and N is nominee. This is just to differentiate 
		// between users and nominees.
		
		ExecutionTime.actualCountOfCallingNomineeFunction += 1;
		
		long start = 0;
		
		long end = 0;
		
		boolean isMapLoaded = false;
		
		start = System.currentTimeMillis();
		
	    // Check if the user is presnt in the map. If so then return nominee list
		
		/*isMapLoaded = populateUserNomineeMap(createdBy);
		
		System.out.println("iS MAP LOADED: "+isMapLoaded);
		
		if(isMapLoaded)  {
			
			// The nomineeSave map definitely contains the nominee of 
			// createdby 
			
			return nomineeSaved.get(createdBy);
			
			//return NomineeSaveMap.addNominee.get(createdBy);
			
		}  */
		
		String nomineeId = null;
		
		List<Integer> nomineeList = new LinkedList<Integer>();
		
		//List<String> futureNomineeList = new LinkedList<String>();
		
		int nomineeSize = 0, i = 0, registeredUser = 0;
		
		// Get the end character of userId. Check whether N or U 
		
			
			// The userId is actually registered user.
			
			List<UserNominee> nominees = Ebean.find(UserNominee.class).select("createdBy, nomineeCloveUserId, nomineeId")
					.setUseQueryCache(true)
					.setAutofetch(true)
		 			.fetch("nomineeUser", new FetchConfig().query())
		 			.where()
		 			    .eq("created_by", createdBy)
		 			 .findList(); 
			
			
			nomineeSize = nominees.size();
			
			if(nomineeSize > 0)  {
				
				// The user has nominees
				
				for(i = 0; i < nomineeSize; i++)  {
					
					// For every iteration check whether the nominee is 
					// registered user or not and append the string accordingly
					
					try  {
						
						registeredUser = nominees.get(i).getNomineeCloveUserId();
						
					}catch(NullPointerException e)  {
						
						continue;
					}
					
					// Nominee is a registered user.  
					
					nomineeList.add(registeredUser);
					
					
				}  // End for
				
				end = System.currentTimeMillis();
				
				ExecutionTime.fetchNomineeTime += end - start;
				
				if(nomineeList.size() > 0)  {
				
					// The user contains clove users and optionally
					// non-clove users as nominees. But non clove users
					// are not added to the nomineeList
					
					return nomineeList;
					
				}
				
				else  {
				
					// User has only non-clove users as nominees
					
					nomineeList.add(0, 0);
					
					return nomineeList;
					
				}
				
			}  // End if nominee size > 0
			
			else  {
				
				// User doesnt have nominees
				
                end = System.currentTimeMillis();
				
				ExecutionTime.fetchNomineeTime += end - start;
				
				// In order to differentiate between user who has 
				// non-clove user as nominees and the nominee save map 
				// null confusion, 0 is added to the list to say that
				// the current user has no nominees or has only
				// 
				
				nomineeList.add(0, 0);
				
				return nomineeList;
				
			}  // Nominee size == 0
		  
		 
	  }  // End function	
	

    /**
     * 
     * The ebeanUserNominee function doesn't load any object that 
     * are serialize. This function just queries database and returns
     * the nominees.
     * 	
     */
	
	public static List<Integer> ebeanUserNomineeList(int createdBy)  {
		
		
		int nomineeSize = 0, i = 0, registeredUser = 0;
		
		
		List<Integer> nomineeList = new LinkedList<>();
		
		Map<Integer, List<Integer>> tempMap = new LinkedHashMap<>();
		
		tempMap = (Map) Helper.getSerializedObject("/home/ranjan/playWorkspace/endProduct/public/serialized/SerializedMap.ser");
		

		if(tempMap.containsKey(createdBy) && tempMap.get(createdBy).size() != 0)  {
			
			
			return tempMap.get(createdBy);
			
		}
		
		// The userId is actually registered user.
		
					List<UserNominee> nominees = Ebean.find(UserNominee.class).select("createdBy, nomineeCloveUserId, nomineeId")
							.setUseQueryCache(true)
							.setAutofetch(true)
				 			.fetch("nomineeUser", new FetchConfig().query())
				 			.where()
				 			    .eq("created_by", createdBy)
				 			 .findList(); 
					
					
					
					nomineeSize = nominees.size();
					
					if(nomineeSize > 0)  {
						
						// The user has nominees
						
						for(i = 0; i < nomineeSize; i++)  {
							
							// For every iteration check whether the nominee is 
							// registered user or not and append the string accordingly
							
							try  {
								
								registeredUser = nominees.get(i).getNomineeCloveUserId();
								
							}catch(NullPointerException e)  {
								
								continue;
							}
							
							// Nominee is a registered user.  
							
							nomineeList.add(registeredUser);
							
							
						}  // End for
						
						
						if(nomineeList.size() > 0)  {
						
							// The user contains clove users and optionally
							// non-clove users as nominees. But non clove users
							// are not added to the nomineeList
							
							return nomineeList;
							
						}
						
						else  {
						
							// User has only non-clove users as nominees
							
							nomineeList.add(0, 0);
							
							return nomineeList;
							
						}
						
					}  // End if nominee size > 0
					
					else  {
						
						// User doesnt have nominees
						
						// In order to differentiate between user who has 
						// non-clove user as nominees and the nominee save map 
						// null confusion, 0 is added to the list to say that
						// the current user has no nominees or has only
						// 
						
						nomineeList.add(0, 0);
						
						return nomineeList;
						
					}  // Nominee size == 0
				  
				 
			  }  // End function		
	
	
	
	
	/**
	 * Function: populateUserNomineeMap.
	 * @param void
	 * 
	 * @serialData 
	 * Get the serialized object in assets folder 
	 * and assign it to the NomineeMapSave class that
	 * contains a map. This map is static , so it holds 
	 * the user nominee pair throughout the requests.
	 * 
	 * @return void
	 */
	
	public static Map<Integer, List<Integer>> populateUserNomineeMap(int createdBy)  {
		
        boolean mapContainsKey = false;
        
        String nameOfSerializeInfoFile = SerializeConstants.nameOfSerializeInfoFile,
        		nameOfUserNomineeMapFile = SerializeConstants.nameOfUserNomineeMapFile;
    	
    	SerializeInfo serializeInfoObject = null;
    	
    	Map<Integer, List<Integer>> nomineeHashMap = new LinkedHashMap<>();
    	
    	Map<Integer, List<Integer>> blank = new LinkedHashMap<>();
    	
    	List<Integer> blankList = new LinkedList<>();
    	
    	
    	
    	int folderListSize = SerializeConstants.FOLDER_LIST.size(), i = 0;
        
    	
    	
		// Note: The nomineeListGet may also contain just 0 as a list item. In that case
		// just continue executing in Relation class
		
			
			// If the control comes here then the only explanation is that
			// the createdBy is not found in the map or the correct map is
			// not yet deserialized
			
			// Now get the correct serialized object and populate the hashmap
			
			// Now get the NomineeInfo type object present in one of the folders and check 
    		// whether the createdBy is present in the objects list.
    		
    		 
    		for(i = 0; i < folderListSize; i++)  {
    			
    			// Get individual folder and check where createdBy is present
    			// in individual object
    			
    			String serializeInfoAbsPath = SerializeConstants.FOLDER_LIST.get(i)+"/"+nameOfSerializeInfoFile;
    			
    			String serializedMapAbsPath = SerializeConstants.FOLDER_LIST.get(i)+"/"+nameOfUserNomineeMapFile;
    			
    			boolean serializeInfoFileExist = Helper.doesThisFileExist(serializeInfoAbsPath);
    			
    			boolean serializedMapFileExist = Helper.doesThisFileExist(serializedMapAbsPath);
    			
    			
    			// Now we need to consider one thing, if two mentioned file exist then only we need to
    			// continue the execution
    			
    			if(serializeInfoFileExist && serializedMapFileExist)  {
    				
    				// Now both the files exist. Deserialize the info object
    				
    				try  {
    					
    					serializeInfoObject = (SerializeInfo) Helper.getSerializedObject(serializeInfoAbsPath);
    					
                        // Check if createdBy exists in serializeInfoObject's set
    					
    					if(serializeInfoObject.contains(createdBy))  {
    						
    						// If you are here then the current folder for current iteration count
    						// contains the createdBy id. Hence get the serializedMap and assign it 
    						// to the static nomineeMap and return success
    						
    						
    						
    						try  {
    							
    							nomineeHashMap = (Map<Integer, List<Integer>>) Helper.getSerializedObject(serializedMapAbsPath);
    							
    							serializeInfoObject = null;
    							
    							return nomineeHashMap;
    							
    							//nomineeSaved = nomineeHashMap;
    							
    							
    							
    						}catch(Exception e)  {
    							
    							e.printStackTrace();
    							
    							continue;
    							
    						}  // End catch
    						
    						
    					}  // End if serializeInfoObject.contains(createdBy)
    					
    					else  {
    						
    						continue;
    					}
    					
    					
    				}catch(Exception e)  {
    					
    					e.printStackTrace();
    					
    					// If for some reason the file exist but still return null then
    					
    					continue;
    					
    				}  // End catch
    								
    				
    			}  // End if serializeInfo.exists() && serializedMap.exists()
    			
    			else  {
    				
    				blankList.add(0);
    	        	
    	        	blank.put(0, blankList);
    	        	
    	        	return blank;
    				
    			}
    			
    			
    			
    		}  // End for loop
			
    		
    		blankList.add(0);
        	
        	blank.put(0, blankList);
    		
			return blank;
		
		
	}  // End function	
	
	
	
	
	
	/**
	 * 
	 * @param userId <br><br>
	 * The below function returns the nominees of a user in the 
	 * form of objects. 
	 * This function is used by listNominees.
	 * @return List of type UserNominee i.e nominees of a user as an object
	 */
	
	public static List<UserNominee> listOfNomineeObject(int userId)  {
		
		int nomineeSize = 0;
		
		List<UserNominee> nominees = Ebean.find(UserNominee.class).select("created_by")
	 			 .fetch("nomineeUser")
	 			 .where()
	 			     .eq("created_by", userId)
	 			 .findList();
		  
		  nomineeSize = nominees.size();
		  
		  if(nomineeSize > 0)  {
			  
			  return nominees;
			  
		  }
		  
		  else  {
			
			  return null;
			  
		  }
		
		
		
		
	}  // End function
	
	

	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param nominees
	 * @param visited
	 * @param breadthFirstQueue 
	 * @Description
	 * The userNomineeList (3 args) creates new list of nominees that are not 
	 * present or not in visited and breadthFirstQueue
	 * this ensures that the nodes are not repeated while traversing.
	 * @return List of nominees that are not in given arguments except nominees.
	 */
	
	public static List<Integer> userNomineeList(List<Integer> nominees,
			Set<Integer> visited, Queue<Integer> breadthFirstQueue)  {
		
		int i = 0, nomineeListSize = 0;
		
		nomineeListSize = nominees.size();
		
		List<Integer> newNomineeList = new LinkedList<Integer>();
		
		int nominee = 0;
		
		for(i = 0; i < nomineeListSize; i++)  {
			
			nominee = nominees.get(i);
			
			if(!visited.contains(nominee) && 
					!breadthFirstQueue.contains(nominee))  {
				
				newNomineeList.add(nominee);
				
				
			}  // End if
			
			continue;
			
		}
		
		
		return newNomineeList;  
		
	}  // End function
	
	
	/**
	 * 
	 * @param parentNomineeList
	 * 
	 * Description:<br>
	 * 
	 * If a user has 4 nominees, then the function saveFutureNominee<br>
	 * saves the nominees of the 4 nominees provided by the argument
	 * @return void
	 */
	
	
	/*
	public static void saveFutureNominee(final List<String> parentNomineeList)  {
		
		//NomineeSaveMap.addNominee.clear();  ///////////////////////////////////////  REMOVE LATER
		// Variable declarations
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
		
		
		long start = 0, end = 0;
		
		start = System.currentTimeMillis();
		
		int futureNomineeListSize = 0, i = 0, createdByPresent, createdByPrevious, user = 0;
		
		List<UserNominee> futureNomineeList = null;
		
		//List<UserNominee> futureNomineeList2 = null;
		
		String nomineeContactNumber = null;
		
		UserNominee futureNomineeObject = null;
		
		//Map<String, String> test = new LinkedHashMap<String, String>();
		
		//List<UserNominee> futureNomineeList = new ArrayList<UserNominee>();
		
		
	    // The below query takes approx 8ms for any number of parentNomineeList
		
		end = System.currentTimeMillis();
		
		SaveFutureNomineeTime.variableDeclaration += end - start; 
		
		start = System.currentTimeMillis();
		
		
		futureNomineeList = Ebean.find(UserNominee.class).select("createdBy, nomineeCloveUserId, nomineeContactNumber")
	               .setUseQueryCache(true)  // Remove if not needed
	               //.setAutofetch(true)
		           .where()
		           .in("createdBy", parentNomineeList).orderBy().asc("createdBy").findList(); 
		
		
		end = System.currentTimeMillis();
		
		SaveFutureNomineeTime.ebeanQuery += end - start;
		
		start = System.currentTimeMillis();
		
		futureNomineeListSize = futureNomineeList.size();
		
		int j = 0;
		
		
		for(i = 0; i < futureNomineeListSize; i++)  {
			
			//System.out.println("i: "+i+", j: "+j);
			
			futureNomineeObject = futureNomineeList.get(i);
			
			// Get the created by
			
			createdByPresent = futureNomineeObject.createdBy;
			
			createdByPrevious = futureNomineeList.get(j).createdBy;
			
			// As long as ceatedByPresent == createdByPrevious remains same, populate the nominee
			
			if(createdByPresent == createdByPrevious)  {
				
				// If you are here then populate the nominees for a user to new map or a list
				
				try  {
					
					user = futureNomineeObject.nomineeCloveUserId;
					
				}catch(Exception e)  {
					
					// Get nominee contact number
					
					nomineeContactNumber = futureNomineeObject.nomineeContactNumber;
					
					// Save the nominee contact number in a map.
					
					//test.put(String.valueOf(createdByPresent)+"U", String.valueOf(nomineeContactNumber)+"N");
					
					if(!NomineeSaveMap.addNominee.containsKey(String.valueOf(createdByPresent)+"U"))  {
					
						// The map is not yet created, hence create
						
						List<String> tempNomContactNumber = new LinkedList<>();
						
						tempNomContactNumber.add(String.valueOf(nomineeContactNumber)+"N");
						
						NomineeSaveMap.addNominee.put(String.valueOf(createdByPresent)+"U", tempNomContactNumber);
						
					
						
					}
					
					else  {
						
						// The key is already present . So append to list
						
						NomineeSaveMap.addNominee.get(String.valueOf(createdByPresent)+"U").add(String.valueOf(nomineeContactNumber)+"N");
						
					}  // End else
					
					continue;
					
				}  // End catch
				
				// Similarly, if the key is not present then create new map...
				
				if(!NomineeSaveMap.addNominee.containsKey(String.valueOf(createdByPresent)+"U"))  {
					
					
					// The map's key not created, hence create
					
					List<String> tempNomCloveUserId = new LinkedList<>();
					
					tempNomCloveUserId.add(String.valueOf(user)+"U");
					
					NomineeSaveMap.addNominee.put(String.valueOf(createdByPresent)+"U", tempNomCloveUserId);
					
					
				}
				
				else  {
					
				    //
					
					NomineeSaveMap.addNominee.get(String.valueOf(createdByPresent)+"U").add(String.valueOf(user)+"U");
					
					
				}  // End else 
				 
				
			} // End if createdByPresent == createdByPrevious
			
			else  {
				
				//break;
				
				j = i;
				
				i--;
				
				
			}  
			
			
			//System.out.println("CREATEDBY PRESENT: "+createdByPresent);
			
			//System.out.println("CREATEDBY PREVIOUS: "+createdByPrevious);
			
			//System.out.println("------------------------------------------------");
			
			
			
		}  // End for
		
		
		//System.out.println("Nominee map: "+NomineeSaveMap.addNominee);
		
		end = System.currentTimeMillis();
		
		SaveFutureNomineeTime.forLoop += end - start;
				
		
        }});  // End thread runnable
		
		
		thread.start();
		
		
	}  // End method newSaveFutureNominee  */
	
	
	/**
	 * @param void
	 * List all nominees in userNominee table, send it as a list of userNominee
	 * @return
	 */
	
	public static List<UserNominee> allNominees()  {
		
		//List<UserNominee> nomineeListAll = new Model.Finder<>(String.class, UserNominee.class).all();
		
		List<UserNominee> nomineeListAll = Ebean.find(UserNominee.class)
				.select("*")
				.orderBy()
				.asc("nomineeId")
				.findList();
		
		
		List<UserNominee> allUserNominee = new LinkedList<UserNominee>();
		
		List<UserNominee> returnVal = new LinkedList<UserNominee>();
		
		allUserNominee.addAll(nomineeListAll);
		
		returnVal = allUserNominee.size() == 0 ? returnVal : allUserNominee;
		
		return returnVal;
		
	}  // End function
	

	/*
	 * The below function fetches all nominees except users in userNOminee table.
	 * @return  Map<String, String> of nominees except user
	 */
	
	public static Map<String, String> getAllNomineeExceptUser()  {
		
		int i = 0, size = 0, userId = 0;
		
		// Fetch everything from user_nominee table.
		
		List<UserNominee> allNominees  = allNominees();
		
		//List<UserNominee> nominees = new LinkedList<UserNominee>();
		
		Map<String, String> nominees = new HashMap<String, String>();
		
		// If there is no exception, then continue 
		
		size = allNominees.size();
		
		for(i = 0; i < size; i++)  {
			
			
			try  {
				
				userId = allNominees.get(i).nomineeCloveUserId;
				
			}catch(Exception e)  {
				
				nominees.put(String.valueOf(allNominees.get(i).nomineeId),
						allNominees.get(i).nomineeName);
				
			}
			
			continue;
			
		}  // ENd for
		
		return nominees;
		
		
	}  // End function

	
	

	/**
	 * The below function returns a list of users or a nominee so that 
	 * it contains only registered users as well as the destination.
	 * 
	 * 
	 */
	@Deprecated
	public static List<String> fetchOnlyRegisteredUsersFrom(List<String> nomineeList, String destination)  {
		
		int i = 0, nomineeSize = 0;
		
		nomineeSize = nomineeList.size();
		
		String individualNominee = null;
		
		List<String> registeredNomineeList = new LinkedList<String>();
		
		
		for(i = 0; i < nomineeSize; i++)  {
			
			
			individualNominee = nomineeList.get(i);
			
			// Get the flag of individualNominee. i.e U for user and N for nominee
			// Usually for clarity, users = 1122U and nominee is 3222N where numbers are their table id's.
			
			if(Relation.returnOnlyChar(individualNominee).equals("U") ||
					individualNominee.equals(destination))  {
				
				registeredNomineeList.add(individualNominee);
				
				
			}  // End if
			
		}  // End for
		
		
		return registeredNomineeList;
		
	}  // End method
	
	
	/**
	 * 
	 * The getAllNomineeId returns all nominee id's but as 1N, 2N etc
	 * 
	 * @param void
	 * 
	 * @return List<String> nomineeId ( eg [1N, 2N, 3N etc])
	 * 
	 */
	
	public static List<Integer> getAllNomineeId()  {
		
		// Variable declarations
		
		List<UserNominee> allNominees = Nominee.allNominees();
		
		List<Integer> allNomineeIdList = new LinkedList<>();
		
		int i = 0, allNomineeSize = allNominees.size(), user = 0;
		
		for(i = 0; i < allNomineeSize; i++)  {
			
			
			try  {
				
				user = allNominees.get(i).nomineeCloveUserId;
				
				
			}catch(Exception e)  {
				
				allNomineeIdList.add(allNominees.get(i).getNomineeId());
				
			}
			
		}   // End for
		
		
		if(allNomineeIdList.size() != 0)  {
			
			return allNomineeIdList;
			
			
		}
		
		return allNomineeIdList;
		
		
	}  // End method

	
	
	/**
	 * @param url of the directory
	 * 
	 * <b>This is just for testing</b>
	 * 
	 * Serialize all user's nominee in a map.
	 * Get all users from the database, get his nominee in a
	 * for loop and save the map using serialization.
	 * 
	 * 
	 */
	@Deprecated
	public static void serializeNominees()  {
		
		
		Map<Integer, List<Integer>> nominees = new LinkedHashMap<Integer, List<Integer>>();
		
		int userSize = 0, i = 0;
		
		List<Integer> tempNominee;
		
		SerializeInfo serializedInfoObject = new SerializeInfo();
		
		String serializeInfoPath, serializeMapPath;
		
		String nameOfSerializeInfoFile = SerializeConstants.nameOfSerializeInfoFile,
        		nameOfUserNomineeMapFile = SerializeConstants.nameOfUserNomineeMapFile;
		
		int userId, j = 0;
		
        Map<Integer, List<Integer>> tempMap = new LinkedHashMap<>();
		
		tempMap = (Map) Helper.getSerializedObject("/home/ranjan/playWorkspace/endProduct/public/serialized/SerializedMap.ser");
		

        System.out.println("DONE LOADING");		
		
		
		
		// Get all users
		
		//List<User> users = Ebean.find(User.class).orderBy().asc("name").findList();
		
		List<User> users = new Model.Finder<>(String.class, User.class)
				                     .all();
		
		userSize = users.size();
		
		System.out.println("User size: "+userSize);
		
		for(i = 0; i < userSize; i++)  {
			
			tempNominee = new LinkedList<>();
			
			userId = users.get(i).id;
			
			serializedInfoObject.addToUserIdSet(userId);
			
			// Get his nominees and save it to the map
			
			
			if(tempMap.containsKey(userId))  {
				
				
				tempNominee = tempMap.get(userId);
				
			}
			
			else  {
			
			    tempNominee = ebeanUserNomineeList(userId);
				
			}
			
			
			// There is no need to check whether tempNominee is empty or not
			// because, if user has no nominee, [0] is returned or if a user
			// has only non-clove users as nominees then also [0] is returned
			// This avoids confusion regarding the deserialization
			
			nominees.put(userId, tempNominee);
			
			if(i % 5000 == 0 && i != 0 )  {
				
			
			
		     
				System.out.println("Now i is : "+i);
				
				serializeInfoPath = SerializeConstants.FOLDER_LIST.get(j)+"/"+nameOfSerializeInfoFile;
				
    			
    			serializeMapPath = SerializeConstants.FOLDER_LIST.get(j)+"/"+nameOfUserNomineeMapFile;
						
						
				
				try
			      {
			         FileOutputStream fileOut =
			         new FileOutputStream(serializeInfoPath);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(serializedInfoObject);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized info data is saved in ");
			      }catch(IOException e)
			      {
			          e.printStackTrace();
			      }
				
				
				
				try
			      {
			         FileOutputStream fileOut =
			         new FileOutputStream(serializeMapPath);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(nominees);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized map data is saved in ");
			      }catch(IOException e)
			      {
			          e.printStackTrace();
			      }
				
				System.out.println("Done");
				
				// Clear nominees
				
				nominees.clear();
		
				serializedInfoObject.clear();
				
				j++;
				
			}  // End if
			
			else if(i == 49999)  {
				
                System.out.println("Now i is : "+i);
				
				serializeInfoPath = SerializeConstants.FOLDER_LIST.get(j)+"/"+nameOfSerializeInfoFile;
				
    			
    			serializeMapPath = SerializeConstants.FOLDER_LIST.get(j)+"/"+nameOfUserNomineeMapFile;
						
						
				
				try
			      {
			         FileOutputStream fileOut =
			         new FileOutputStream(serializeInfoPath);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(serializedInfoObject);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized info data is saved in ");
			      }catch(IOException e)
			      {
			          e.printStackTrace();
			      }
				
				
				
				try
			      {
			         FileOutputStream fileOut =
			         new FileOutputStream(serializeMapPath);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(nominees);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized map data is saved in ");
			      }catch(IOException e)
			      {
			          e.printStackTrace();
			      }
				
				System.out.println("Done");
				
				// Clear nominees
				
				nominees.clear();
		
				serializedInfoObject.clear();
				
				
				
				
			}  // End else if
				
			
		}  // End for
		 
		
		System.out.println("FINISH");
		
		//nomineeSavedTemp.clear();
		
		
		
		
	}  // End function
	
	
	
	// Test function 
		@Deprecated
		public static void testNominee(int createdBy)  {
			
			
			
	        int futureNomineeListSize = 0, i = 0, createdByPresent, createdByPrevious, user = 0;
			
			List<UserNominee> futureNomineeList = null;
			
			List<Integer> nomineeList = new LinkedList<>();
			
			UserNominee futureNomineeObject = null;
			
			//Map<String, String> test = new LinkedHashMap<String, String>();
			
			//List<UserNominee> futureNomineeList = new ArrayList<UserNominee>();
			
			
			futureNomineeList = Ebean.find(UserNominee.class).select("createdBy, nomineeCloveUserId, nomineeContactNumber")
		               .setUseQueryCache(true)  // Remove if not needed
		               //.setAutofetch(true)
			           .where()
			           .in("createdBy", createdBy).orderBy().asc("createdBy").findList(); 
			
					
			futureNomineeListSize = futureNomineeList.size();
			
			int j = 0;
			
			
			for(i = 0; i < futureNomineeListSize; i++)  {
				
				//System.out.println("i: "+i+", j: "+j);
				
				futureNomineeObject = futureNomineeList.get(i);
				
				// Get the created by
				
				createdByPresent = futureNomineeObject.createdBy;
				
				createdByPrevious = futureNomineeList.get(j).createdBy;
				
				// As long as ceatedByPresent == createdByPrevious remains same, populate the nominee
				
				if(createdByPresent == createdByPrevious)  {
					
					// If you are here then populate the nominees for a user to new map or a list
					
					try  {
						
						user = futureNomineeObject.getNomineeCloveUserId();
						
					}catch(Exception e)  {
						
						continue;
						
					}  // End catch
					
					// Similarly, if the key is not present then create new map...
					
					nomineeList.add(user);
					 
					
				} // End if createdByPresent == createdByPrevious
				
				else  {
					
					//break;
					
					j = i;
					
					i--;
					
					
				}  
						
				
			}  // End for
			
			
			System.out.println("Nom: "+nomineeList);
			
		} // ENd method
		
		
	
	
}  // ENd class
