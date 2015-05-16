package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface SerializeConstants {

	// The parent folder path
	public static final String PARENT_FOLDER_PATH = "/home/ranjan/playWorkspace/endProduct/public/serialized/";
	
	// The name of the Serialize Info file
	
	public static final String nameOfSerializeInfoFile = "SerializeInfo.ser";
	
	// Name of the hashmap stored as serialized object
	
	public static final String nameOfUserNomineeMapFile = "SerializedMap.ser";
	
	// The child folder that stores nominee map
	public static final List<String> FOLDER_LIST = Collections.unmodifiableList(
			
		    new ArrayList<String>() {{
		    	
		    	add(PARENT_FOLDER_PATH+"nominees/NomineeList1");
		    	add(PARENT_FOLDER_PATH+"nominees/NomineeList2");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList3");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList4");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList5");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList6");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList7");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList8");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList9");
		        add(PARENT_FOLDER_PATH+"nominees/NomineeList10");
		        
		    }});
	
	
	
	
	
	
}  // End class
