package controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @author ranjan
 *
 * The SerializeInfo is an object that resides alongside with the 
 * serialized user-nominee hashmap. If the plan is to save 1Lakh user-nominee pair
 * in a folder, then this object contains the currentUserCount, actual set of userId 
 * present in the folder ( which is equal to the keyset of user-nominee hashmap), date created, 
 * date modified etc. 
 * The use of this class is that , we dont need to get the whole hashmap just to check whether 
 * the user's nominee is present. We can just deserialize this object and find out whether user's 
 * nominee is present in the hashmap.
 * 
 * Its advised to update this object atleast once a day. 
 *
 *
 */


public class SerializeInfo implements Serializable  {

	
	private static final long serialVersionUID = 12345L;
	
	private long currentUserIdCount;
	
	private Set<Integer> userIdSet = new LinkedHashSet<>();
	
	private String folderPath;
	
	public Date dateCreated;
	
	public Date dateModified;
	
	
	
	
	/**
	 * The clear function clears the current variable
	 */
	
	public void clear()  {
		
		
		this.currentUserIdCount = 0;
		
		this.userIdSet.clear();
		
		this.folderPath = null;
		
		
	}  // End function
	
	
	
	/**
	 * 
	 * 
	 * @param createdBy
	 * @return
	 */
	
	public boolean contains(int createdBy )  {
		
		if(this.userIdSet.contains(createdBy))  {
			
			return true;
			
		}
		
		return false;
		
	}  // End method
	
	
	// Setters
	
	/**
	 * This method is used to store the folder path of this object.
	 * 
	 */
	
	public void setFolderPath(String absolutePath)  {
		
		if(absolutePath != null)  {
		
			this.folderPath = absolutePath;
			
			
		}
		
		return;
		
		
	}  // End method
	
	
	/**
	 * setCurrentUserCount method is used to update the total userId 
	 * count present in the serialized hashmap.
	 */
	
	public void setCurrentUserCount(long count)  {
		
		
		if(count <= 100000)  {
			
			this.currentUserIdCount = count;
			
		}
		
		return;
		
	}  // End method
	
	/**
	 * Method name: updateAllUserIdSet
	 * 
	 * Once a user is added to the hashmap with his nominees then we need to update the current object's
	 * set as well. 
	 *  
	 * 
	 */
	
    public void addToUserIdSet(int userId)  {
		
		if(userId > 0)  {
			
			
			this.userIdSet.add(userId);
			
		}
		
		return;
		
	}  // End method
	
	
	
	/**
	 * 
	 * The below method is helpful if you are not sure what to add to the set
	 * or if somehow you delete the current object in the folder. 
	 *  
	 * 
	 */
	
    
	
    public void updateAllUserIdSet(Set<Integer> hashMapKeySet)  {
		
		if(hashMapKeySet.size() > 0)  {
			
			
			this.userIdSet.clear();
			
			this.userIdSet.addAll(hashMapKeySet);
			
		}
		
		return;
		
	}  // End method
	
	
	
	// Getter
    
    
    /**
     * @param null
     * 
     * Return this object's currentUserIdCount.
     * UserId count is the count that is same as the serialized hashmap.size().
     * 
     * @return long : The current userId count in hashmap
     * 
     */
    
    public long getCurrentUserIdCount()  {
    	
    	
    	return this.currentUserIdCount;
    	
    }
    
    
    /**
     * @param null
     * 
     * This method returns the current set or the set that is == serialized hashmap's key set<br>
     * @return serialized map's keyset per se.
     */
    
    public Set<Integer> getUserIdSet()  {
    	
    	
    	return this.userIdSet;
    	
    }  // End method
 	
	
    /**
     * 
     * The getFolderPath method returns the current object's folder path.<br>
     * 
     * @return String : Folder path of this object.
     */
    
    public String getFolderPath()  {
    	
    	return this.folderPath;
    	
    }  // End method
    
	
	
}  // End class
