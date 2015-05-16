package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static play.libs.Json.toJson;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.User;

import com.avaje.ebean.Ebean;
import com.avaje.ebeaninternal.server.lib.util.NotFoundException;

import play.cache.Cache;

/**
 * 
 * @author ranjan
 * Relation class contains methods that calculates the relationship
 * between source and destines.
 * 
 * List of instance variables: NIL
 * 
 * List of methods: 
 * 1) List<String> shortestPath(String source, String destination);
 * 2) List<String> generateActualShortPath(List<PathTraversed> pathTraversed, String source, String destination);
 * 3) String returnOnlyChar(String string);
 * 4) String returnOnlyNumbers(String string);
 */

public class Relation {


	public static Map<Integer, List<Integer>> nomineeListMap = new LinkedHashMap<>();
	
/**
 * 
 * @param source
 * @param destination
 * @description 
 * Method alias: Modified breadth first search
 * The below function calculates the shortest path between two nodes.
 * The source will be in the form of 11U where U is the user,
 * and destination will be 14N or 14U where N is nominees
 * 
 * @return Shortest path between source and destination
 */
	
	
@SuppressWarnings({ "unchecked", "unused" })
public static List<Integer> shortestPath(int source, int destination)  {
		
	System.out.println("Source: "+source+" Destination: "+destination);
	
	long startTime = System.currentTimeMillis();
	
	List<Integer> shortestPathMap = new LinkedList<Integer>();
	
	Map<Integer, List<Integer>> nomineeDeserialized = new LinkedHashMap<>();
	
	List<Integer> nomineeList = new LinkedList<Integer>();
	
	Queue<Integer> breadthFirstQueue = new LinkedList<Integer>();
	
	List<PathTraversed> pathTraversed = new LinkedList<PathTraversed>();
	
	List<Integer> newNomineeList = new LinkedList<Integer>();
	
	byte flag = 0;
	
	boolean isNomineeMapLoaded = false;
	
	// Below var is user to store user nominee pair in a map. See below code
	
	List<Integer> nomineeMapGet = new LinkedList<>();
	
	int nomineeSize = 0, nomineeMapGetSize  = 0;
	
	Set<Integer> visited = new LinkedHashSet<Integer>();
	
	// Get the object and populate the map
	
	long start = System.currentTimeMillis();
	
	//isNomineeMapLoaded = Nominee.populateUserNomineeMap();
	
	long end = System.currentTimeMillis();
	
	System.out.printf("Deserialization took: %d\n", end - start);
	
	//List<String> nomineeCache = new LinkedList<String>();
		//Object nomineeCache = null;
		
	
	// If you are here then source is a registered user
		
	if(source == destination)  {
			
		System.out.println("Cant compare with same users.");
			
		return  shortestPathMap; 
			
	}
		
	// Add the source's Id to visited list
		
	visited.add(source);
		
	ExecutionTime.numberOfNodesVisited = 1;
		
	// Check if source has nominees
		
	//nomineeList = Nominee.userNomineeList(source);
		
	// Load the nomineeMap that is serialized
	
	
	// Check whether the nominee is already saved in the map
		
	//nomineeDeserialized
	
	if(nomineeDeserialized.size() == 0)  {
		
		System.out.println("SIZE IS 0 HENCE POPULATING USING FUNCTION");
		
		// Deserialize the map that contains source
		
		nomineeDeserialized = Nominee.populateUserNomineeMap(source);
		
		if(nomineeDeserialized != null)  {
			
			System.out.println("POPULATE FUNCTION NOT NULL FOR SOURCE: "+source);
			
			
			nomineeList = nomineeDeserialized.get(source);
			
			nomineeDeserialized.remove(source);
			
			System.out.println("AFTER GETTING THE VALUE: "+source+" "+nomineeList);
			
		}
		
		else  {
			
			System.out.println("YOU ARE NOT SUPPOSED TO SEE THIS");
			
			nomineeList = Nominee.userNomineeList(source);
			
		}
		
		
	}
	
	else  {
		
		
		if(nomineeDeserialized.containsKey(source))  {
			
			nomineeList = nomineeDeserialized.get(source);
			
		}
		
		else  {
			
			nomineeList = Nominee.userNomineeList(source);
		}
		
	}
		
		
	//System.out.println("Outside for loop first nominees of "+source+" "+nomineeList );
		
	// Generate userNominee pair (8,7) (8, 9) for short path calculation
		
	pathTraversed.addAll(Nominee.generateUserNomineePair(source, nomineeList));
		
	// At the beginning the pathTraversed contains ,
	//PathTraversed.individualPath[0] =  [15U, 4U]
	//PathTraversed.individualPath[1] =  [15U, 6U]
	//PathTraversed.individualPath[2] =  [15U, 9U]
	//PathTraversed.individualPath[3] =  [15U, 11U]
	// IF 15 is the source and 4, 6, 9, 11 are his nominees 
		
	// Get the lenght of nominees
		
	nomineeSize = nomineeList.size();
		
	if(nomineeSize > 0)  {
			
		// Source has nominees and source is a registered user
		
		// Check whether his nominees contains destination
			
		if(nomineeList.contains(destination))  {
				
			System.out.println("Source's nominee is destination.");
				
			// Short path is source -> destination
				
			shortestPathMap.add(source);
				
			shortestPathMap.add(destination);
				
			return shortestPathMap;
				
		}
			
		// Enqueue the Nominee list.
		// Now enqueue list where it contains only registered user and 
		// the destination ( if destination is a non registered user) 
			
		breadthFirstQueue.addAll(nomineeList);
			
		long endTime = System.currentTimeMillis();
			
		ExecutionTime.beforeForLoop = (endTime - startTime);
			
		//System.out.println("Outside for loop: queue: "+breadthFirstQueue);
			
		int i = 0;
			
		while(breadthFirstQueue.size() != 0)  {
				
			/*if(i == 5)  {
					
				break;
					
			}
				
			i++; */   
				
			startTime = System.currentTimeMillis();
				
			nomineeList.clear();
				
			endTime = System.currentTimeMillis();
				
			ExecutionTime.nomineeListClear += endTime - startTime;
				
			// Dequeue the breadthFirstQueue
				
			startTime = System.currentTimeMillis();
				
			source = breadthFirstQueue.poll();
			
			// If source is a nominee and not equal to destination then continue
				
			endTime = System.currentTimeMillis();
				
			ExecutionTime.breadthFirstQueuePoll += endTime - startTime;
				
			// Mark the source as visited.
				
			visited.add(source);
			
			//System.out.println("Source: "+source);
			
			ExecutionTime.numberOfNodesVisited += 1;
				
			startTime = System.currentTimeMillis();
				
			//System.out.println("Cache: "+Cache.get(source));
				
			// Get the nominee list of source
			//nomineeCache =  Cache.get(source);
				
			endTime = System.currentTimeMillis();

			ExecutionTime.nomineeCacheGet += endTime - startTime;
				
			startTime = System.currentTimeMillis();
				
			//nomineeMapGet = NomineeSaveMap.addNominee.get(source);
			
			
			if(nomineeDeserialized.containsKey(source))  {
				
				
				nomineeList = nomineeDeserialized.get(source);
				
				nomineeDeserialized.remove(source);
				
				//System.out.println("Nominee is present: "+nomineeList);
				
				
			}
			
			else  {
				
				//System.out.println("key not found for "+source+" hence deserializing");
				//System.out.println("Size of map: "+nomineeDeserialized.size());
				
				nomineeDeserialized.putAll(Nominee.populateUserNomineeMap(source));
				
				if(nomineeDeserialized.containsKey(source))  {
					
					
					nomineeList = nomineeDeserialized.get(source);
					
				//	System.out.println("Now key found for "+source+" noms: "+nomineeList);
					
				}
				
				else  {
					
					nomineeList = Nominee.userNomineeList(source);
					
				}
				
			}  // End else
			
				
		    //System.out.println(source+" nominees are "+nomineeList);
				
			startTime = System.currentTimeMillis();
				
			nomineeSize = nomineeList.size();
				
			endTime = System.currentTimeMillis();

			ExecutionTime.nomineeListSize += endTime - startTime;
				
			if(nomineeSize > 0)  {
					
				//ExecutionTime.cacheSetCount += 1;
					
				startTime = System.currentTimeMillis();
				
				// Generate new Nominee list that is not in visited node as well as 
				// breadthFirst queue.
				
				newNomineeList = Nominee.userNomineeList(nomineeList, visited, breadthFirstQueue);
					
				endTime = System.currentTimeMillis();

				ExecutionTime.newUserNomineeList += endTime - startTime;
					
				//System.out.println("nom list thats not in visited and in bfs queue: "+newNomineeList);
					
				if(newNomineeList.size() > 0)  {
						
				    // Enqueue newNominee list
					
					startTime = System.currentTimeMillis();
						 
					breadthFirstQueue.addAll(newNomineeList);
					
					endTime = System.currentTimeMillis();

					ExecutionTime.breadthFirstQueueAddAll += endTime - startTime;
					    
					//System.out.println("Queue: "+breadthFirstQueue);
					
					// Generate user nominee pair
					startTime = System.currentTimeMillis();
					    
					pathTraversed.addAll(Nominee.generateUserNomineePair(source,
					    		newNomineeList));
					    
					endTime = System.currentTimeMillis();

					ExecutionTime.pathTraversedAddAll += endTime - startTime;
						
					if(nomineeList.contains(destination))  {
					    	
					    flag = 1;
					    	
					    //System.out.println("Found destination "+destination);
					    	
					    break;
					    	
					}
					
				}  // End if newNomineeList.size() > 0
					
				else  {
						
					// The new nomineeList length == 0 hence no need to process
						
					continue;
						
				}
					
					
			}  // End  user has nominees
				
			else  {
					
					// User does not have nominees and probably queue is not empty
					
				continue;
					
			}
				
				
		}  // End while breadthFirstQueue.size() != 0
			
		// Now calculate the actual shortest path using the pathTraversed.
		// If the mesh traversal comes across the destination then a flag is set.
			
		if(flag == 1)  {
			
			ExecutionTime.cacheSetCount = nomineeDeserialized.size();
					
			return generateActualShortPath(pathTraversed ,
					source, destination);		
	
				
		}
			
		else  {
				
			// Even though the pathTraversed may contain the list of generated path
			// we return an empty list because the mesh traversal have not come across 
			// the destination.
				
			return shortestPathMap;
				
				
		}
			
		}  // End if nomineeSize > 0
		
		else  {
			
			// Source has no nominees
			
			return shortestPathMap;
			
			
		}  // End else Source has no nominees
	
		
}  // End shortestPath


/**
 * 
 * @param pathTraversed
 * @param source
 * @param destination
 * @description
 * Write a subroutine that calculates the actual shortest path from the pathTraversed object
 * @return Shortest path between source and destination
 */

public static List<Integer> generateActualShortPath(List<PathTraversed> pathTraversed,
		int source, int destination)  {
	
	long startTime = System.currentTimeMillis();
	
	int i = 0, pathTracersedSize = pathTraversed.size(), firstIndexSize = 0;
	
	List<Integer> lastIndexList = new LinkedList<Integer>();
	
	Set<Integer> firstIndexSet = new LinkedHashSet<Integer>();
	
	List<Integer> firstIndexList = new LinkedList<Integer>();
	
	List<Integer> shortPathList = new LinkedList<Integer>();
	
	List<Integer> blank = new LinkedList<Integer>();
	
	// Get the last items of the individualPath thats in pathTraversed
	// PathTraversed looks like the below list
	//[18U, 19U]
	//[18U, 20U]
	//[18U, 21U]
	//[18U, 22U]
	//[19U, 591N]
	//[20U, 23U]
	//[20U, 24U]
	
	// At the beginning , store the 2nd index row of pathTraversed.individual list
	// i.e [19U, 20U, 21U, 22U, 591N, 23U, 24U] this is lastIndexList
	// Similarly store the first index as a unique list 
	// i.e [18U, 19U, 20U ]etc. This is firstIndexSet
	
	for(i = 0; i < pathTracersedSize; i++)  {
		
		firstIndexSet.add(pathTraversed.get(i).individualPair.get(0));
		
		lastIndexList.add(pathTraversed.get(i).individualPair.get(1));
		
		
	}  // End for 
	
	// Convert set to list for get operations
	
	firstIndexList.addAll(firstIndexSet);
	
	firstIndexSize = firstIndexList.size();
	
	int firstIndexSelect;
	
	int index = 0;
	
	// Before for loop, get the last item of firstIndexList
	
	shortPathList.add(firstIndexList.get(firstIndexSize - 1));
	
	
	for(i = firstIndexSize; i != 0 ; i--)  {
		
		firstIndexSelect = shortPathList.get(0);
		
		//System.out.println("Selected: "+firstIndexSelect);
		
		// Now check whether the above variable is present in the lastIndexList
		
		index = lastIndexList.indexOf(firstIndexSelect);
		
		if(index != -1)  {
			
			// Found the value.
			// Now insert the appropriate value to the front of the shotrtestPathList
			
			shortPathList.add(0, pathTraversed.get(index).individualPair.get(0));
			
		}
		
		else  {
			
			break;
			
		}
		
	}  // End for
	
	shortPathList.add(destination);
	
	long endTime = System.currentTimeMillis();

	ExecutionTime.generateActualShortPath += endTime - startTime;
	
	/*if(shortPathList.size() == 2)  {
		
		return blank;
		
	} */
	
	shortPathList = shortPathList.size() == 2 ? blank : shortPathList;
	
	return shortPathList;
	
}  // End function



public static List<ReturnUserList> relationshipToUserList(List<Integer> relationshipList)  {
	
	
	int i = 0, relationshipListSize = relationshipList.size();
	
	int userId;
	
	User user = null;
	
	ReturnUserList actualUser = null;
	
	List<ReturnUserList> returnList = new LinkedList<>();
	
	for(i = 0; i < relationshipListSize; i++)  {
		
		
		userId = relationshipList.get(i);
		
		user = Ebean.find(User.class, userId);
		
		actualUser = new ReturnUserList();
		
		actualUser.name = user.name;
		
		actualUser.gender = user.gender;
		
		actualUser.phone = user.mobileNumber;
		
		returnList.add(actualUser);
		
	}
	
	return returnList;
	
	
	
	
}  // End method



/**
 * 
 * @param string (eg. 121U or 132423423N ) Where U is userFlag and N is Nominee flag 
 * @return
 */

public static String returnOnlyChar(String string)  {
	
	Pattern p = Pattern.compile("[A-Za-z]");
	
	Matcher m = p.matcher(string);
	
	List<String> check = new LinkedList<>();
	
	String returnVal = null;
	
	while(m.find())  {
		
		check.add(m.group());
		
		returnVal =  m.group();
		
	}
	
	if(check.size() != 1)  {
		
		// Check if first character is U or N. 
		
		switch(check.get(0))  {
		
		    case "U": return "U";
		
		    case "N": return "N";
		
		    default: throw new NotFoundException("String other that U or N");
		
		
		}  // End switch
		
		
	}
	
	else  {
		
	
		return returnVal;
		
	}
	
	
	
	
}  // End function


// The below function returns only the numbers in a string

public static String returnOnlyNumbers(String string)  {
	
    Pattern p = Pattern.compile("\\d+");
	
	Matcher m = p.matcher(string);
	
	String returnVal = null;
	
	while(m.find())  {
		
		returnVal =  m.group();
		
	}
	
	return returnVal;
	
	
	
}  // End function

/**
 * 
 * @param source
 * @param destination
 * Check whether the source and destination provided by clent is valid.
 * @return boolean .
 */


public static boolean isUserNomineeGenuine(String source, String destination)  {
	
	// Get 
	
    Pattern p = Pattern.compile("[A-Za-z]");
	
	Matcher sourceMatch = p.matcher(source);
	
	Matcher destinationMatch = p.matcher(destination);
	
	List<String> sourceCheck = new LinkedList<>();
	
	List<String> destinationCheck = new LinkedList<>();
	
	boolean returnVal;
	
	while(sourceMatch.find())  {
		
		sourceCheck.add(sourceMatch.group());
		
		//returnVal =  sourceMatch.group();
		
	}
	
    while(destinationMatch.find())  {
		
		destinationCheck.add(destinationMatch.group());
		
		//returnVal =  sourceMatch.group();
		
	}
	
	
	// If the charater is only U or N then check 
	
	if(sourceCheck.size() == 1)  {
			
			
		returnVal = sourceCheck.get(0).equals("U") || sourceCheck.get(0).equals("N") ? true : false;
		
		return returnVal;
		
		
	}  // End outer if
	
	return false;
	
	
}  // End function


	
}  // End class
