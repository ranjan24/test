package controllers;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * 
 * @author ranjan
 *
 * When an object is created using this class, the object will 
 * have an identifier equal to the id of the registered user.
 * The object also contains a list of nodes connected to it.
 * 
 *
 */
@Deprecated
public class Destination implements Serializable{

	
	private long id;
	
	private Set<Integer> connections;
	
	private long pheromones;
	
	
	
	//         Behaviours
	
	
	/**
	 * 
	 * @param id
	 * 
	 * The method setId ,sets the id of this object.
	 * 
	 * @return void
	 */
	
	public void setId(int id)  {
		
		this.id = id;
		
	}  // End setId
	
	
	/**
	 * 
	 * The getId method returns the id of the current object.
	 * 
	 * @return id of the current object
	 */
	
	public long getId()  {
		
		return this.id;
		
	}  // End getId
	
	
	/**
	 * 
	 * Return the list of nodes that are actually connected to 
	 * this ( object's ) id.
	 * 
	 * @return List of connected nodes for this.id
	 */
	
	public Set<Integer> getConnection()  {
		
		
	    if(this.connections != null)  {
	    	
	    	return this.connections;
	    	
	    }
	    
	    else  {
	    	
	    	Set<Integer> blank = new LinkedHashSet<>();
	    	
	    	return blank;
	    	
	    	
	    }
		
		
	}  // End getConnectedNodes
	
	
	/**
	 *
	 * @param connection
	 * 
	 * The setConnection method sets this objects connection i.e 
	 * the list of nodes that are connected to this object's id.
	 * The connection contains the list of all node's id connected to
	 * the destination.
	 */
	
	public void setConnection(List<Integer> connection)  {
		
		
		if(connection != null && connection.size() > 0)  {
		
			this.connections.addAll(connection);
			
		}
		
		else  {
			
			return;
			
		}
		
		
		
	}  // End setConnectedNodes
	
	
	
	/**
	 * 
	 * @param id
	 * 
	 * The is NodePresent method checks whether a node is present in
	 * the connections set. 
	 * This is useful when updating the object
	 * 
	 * @return
	 */
	
	public boolean isNodePresent(long id)  {
		
	
		if(id > 0)  {
			
			
			if(this.connections.contains(id))  {
				
				
				return true;
				
			}
			
			else  {
				
				return false;
				
			}
			
			
		}  // End if
		
		else  {
			
			return false;
			
		}
		
		
	}  // End 
	
	
	/**
	 * The destination is the current object's id.<br>
	 * The updateAllConnections method is used to update the connections if 
	 * two or more nominees are changed.
	 * This method is used only when you are not sure which user changed his nominee,
	 * or if the serialized data is lost
	 * 
	 */
	
	public void updateAllConnections()  {
		
		// First clear the current connection
		
		this.connections.clear();
		
		// For all the user except this user as source, 
		// check whether there exists a path or not.
		// If path exists, then 
		
		
		
		
		
	}  // End updateAllConnection
	
	
	
	
	
   /**
    * 
    * Later write a method for updating the connection
    * 
    */
	
	
	
	
}  // End class
