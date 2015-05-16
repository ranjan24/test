package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ranjan
 *
 * The helper class contains method that are not related to 
 * the project but still necessary
 *
 */


public class Helper {

	
	/**
	 * @param url
	 * 
	 * The getSerializedObject method returns the deserialized object from
	 * the specidied url.
	 * 
	 * @return Object: The deserialized object
	 */
	
	public static Object getSerializedObject(String url)  {
		
		
		Object returnObject = null;
		
		try  {
    		
    		FileInputStream fileIn = new FileInputStream(url);
    		
    		ObjectInputStream in = new ObjectInputStream(fileIn);
    		
    		returnObject =  in.readObject();
    		
    		in.close();
    		
    		fileIn.close();
    		
    	}catch(IOException i)  {
    		
    		i.printStackTrace();
    		
    		return null;


    	}catch(ClassNotFoundException c)  {

    		c.printStackTrace();
    		
    		return null;

    	}
		
		
	    returnObject = returnObject == null ? null : returnObject;
		
		return returnObject;
	    
	    
	}  // End method 
	
	
	
	/**
	 * @param url, object
	 * The serializeObject takes url and object as a parameter and 
	 * saves the object to the specified argument(url)
	 * 
	 * @return boolean: success or failure
	 */
	
	public static boolean serializeObject(String url, Object object)  {
		
		if(object != null && url != null && !url.isEmpty())  {
			
			try  {
				
		        FileOutputStream fileOut =
		           new FileOutputStream(url);
		        
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        
		        out.writeObject(object);
		        
		        out.close();
		        
		        fileOut.close();
		        
		      }catch(Exception e)  {
		    	  
		          e.printStackTrace();
		          
		          return false;
		          
		      }
			
			return true;
			
			
		}  // End if
		
		else  {
			
			return false;
			
			
		}  // End else 
		
		
	}  // End method
	
	
	
	/**
	 * 
	 * @param url
	 * 
	 * The doesThisFileExist function checks whether a file 
	 * exist or not
	 * 
	 * @return boolean: true if file exist else false
	 */
	
	public static boolean doesThisFileExist(String url)  {
		
		long start = System.currentTimeMillis();
		long end;
		
		boolean itDoes = false;
		
		
		try  {
		
		    File filePointer = new File(url);

		    itDoes = filePointer.exists() && filePointer.isFile() ? true : false;
		    
		}catch(Exception e)  {
			
			end = System.currentTimeMillis();
			
			e.printStackTrace();
			
			return false;
			
		}
		
		end = System.currentTimeMillis();
		
		return itDoes;
		
		
	}  // End method
	
	
	
}  // End class
