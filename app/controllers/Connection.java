package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static play.libs.Json.toJson;
import models.User;
import models.UserNominee;

/**
 * 
 * @author ranjan
 *
 * This class holds few methods that are responsible for 
 * determining whether two nodes are connected or not
 *
 */


public class Connection {

	
	private static List<List<Integer>> serializedConnectionList = new ArrayList<List<Integer>>();
	
	private static Hashtable<String, Integer> hash = new Hashtable<>();
	
	
	/**
	 * Generate the connection between each and every node between each and every node. 
	 * The below method is suitable only if measurable users and nominees are present in database.
	 * IMPORTANT: Call this function whenever necessary 
	 */
	
	/*
	
	public static void generateConnection()  {
		
		// Variable declarations
		
		// Hashing is used to store userId corresponding to array index
		Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
		
		// This is the actual list that stores the connection as a 2 D array
		List<List<Integer>> connectionList = new ArrayList<List<Integer>>();
		
		// Get all users and get their id's.
		
		List<User> allUsers = UserRelated.allUsers();
		
		List<Integer> allUserIdList =  new LinkedList<>();
		
		List<String> allNomineeIdList = new LinkedList<>();
		
		List<Integer> combined = new LinkedList<>();
		
		List<String> shortestPath = new LinkedList<>();
		
		List<UserNominee> allNominee = Nominee.allNominees();
		
		List<Integer> connectionColumnFill = new LinkedList<>();

		int userId;
		
		
		int i = 0, j = 0, user, allUserSize = allUsers.size(),
				allNomineeSize = allNominee.size(), combinedSize = 0;
		
	    // Get the user's id as id+"U"
		
		for(i = 0; i < allUserSize; i++)  {
			
			// After this operation allUserIdList will be 1U, 2U, 3U etc
			
			allUserIdList.add(allUsers.get(i).getUserId());
			
			
		}  // End for
		
		// Now get all nominee's id present in table as 1N, 2N etc
		
		allNomineeIdList.addAll(Nominee.getAllNomineeId());
		
		// Now combine both allNomineeIdList and allUserId list
		
		combined.addAll(allUserIdList);
		
		combined.addAll(allNomineeIdList);
		
		combinedSize = combined.size();
		
		// Now for all the users present in the database check the conenction and 
		
		for(i = 0; i < allUserSize; i++)  {
			
			connectionColumnFill = new LinkedList<>();
			
			// Get the first userId
			
			userId = allUserIdList.get(i);
			
			hash.put(userId, i);
			
			for(j = 0; j < combinedSize; j++)  {
				
				// Now source is the userId, destination is all combined List
				
				shortestPath = Relation.shortestPath(userId, combined.get(j)); 
				
				if(shortestPath.size() == 0)  {
					
					connectionColumnFill.add(0);
					
				}
				
				else  {
					
					connectionColumnFill.add(1);
					
				}
				
				
			}  // End inner for
			
			connectionList.add(i, connectionColumnFill);
			
			shortestPath.clear();  
			
			
		}  // End outer for 
		
		// Now add the remaining nomineeId to the hash
		
		if(i == combinedSize)  {
			
		
			for(j = i; j < i + allNomineeIdList.size(); j++)  {
				
				
				hash.put(allNomineeIdList.get(i), j);
				
				
				
			}  // End for
			
		} // End if
		
		
		
		
		//System.out.println("Size: "+toJson(connectionList));
		
		//////////////////// Serializing the array list
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("/home/ranjan/playWorkspace/endProduct/public/serialized/connection.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(connectionList);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved ");
	      }catch(IOException e)
	      {
	          e.printStackTrace();
	      }
		
		// Serializing the hashtable
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("/home/ranjan/playWorkspace/endProduct/public/serialized/hashtable.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(hash);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved ");
	      }catch(IOException e)
	      {
	          e.printStackTrace();
	      }
		
		
	}  // End method  */
	
	
	
	
	
	public static boolean areConnected(String source, String destination)  {
		
		
		int sourceIndex ;
		int destinationIndex;
		
		// If the object is not deserialize , serialize
		
	
		if(serializedConnectionList.size() == 0)  {
		
		    try  {
		    	
	            FileInputStream fileIn = new FileInputStream("/home/ranjan/playWorkspace/endProduct/public/serialized/connection.ser");
	            ObjectInputStream in = new ObjectInputStream(fileIn);
	            serializedConnectionList =   (List<List<Integer>>) in.readObject();
	            in.close();
	            fileIn.close();
	        }catch(IOException i)  {
	        	
	            i.printStackTrace();
	      
	        }catch(ClassNotFoundException c)  {
	    	
	             c.printStackTrace();
	       
	        }
		
		    System.out.println("Connection loaded");
		
	    }  // End if
		
		if(hash.size() == 0)  {
			
			
            try  {
		    	
	            FileInputStream fileIn = new FileInputStream("/home/ranjan/playWorkspace/endProduct/public/serialized/hashtable.ser");
	            ObjectInputStream in = new ObjectInputStream(fileIn);
	            hash = (Hashtable<String, Integer>) in.readObject();
	            in.close();
	            fileIn.close();
	        }catch(IOException i)  {
	        	
	            i.printStackTrace();
	      
	        }catch(ClassNotFoundException c)  {
	    	
	             c.printStackTrace();
	       
	        }
		
		    System.out.println("HashTable loaded");
			
			
		}  // End another if
		
		
		long start = System.currentTimeMillis();
		long end ;
		
		// Get the value of source and destination in hashtable
		
		sourceIndex = Integer.valueOf(hash.get(source));
		
		destinationIndex = Integer.valueOf(hash.get(destination));
		
		
		if(serializedConnectionList.get(sourceIndex).get(destinationIndex) == 1)  {
			
			
			 end = System.currentTimeMillis();
			
			 System.out.printf("Took: %d\n",end - start);
			 
			return true;
			
			
		}
		
		else  {
			
			end = System.currentTimeMillis();
			
			System.out.printf("Took: %d\n",end - start);
			
			return false;
			
		}
	
		
		
	} // End function
	
	
	
}  // End class
