package org.bagnall.nick.fileCRUD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bagnall.nick.ssp.*;


/**
 * Simple File Read and Update implementation
 * File can be read to create a list of presenter objects
 * Create, Update and Delete are performed on the data structure, which is then output to the file
 * 
 * Since the list of Sky Sport News Presenters is relatively small we will just process the entire file
 * This is not thread safe, ignore file locking as this is a single user demo
 * 
 * @author Nick Bagnall
 *
 */
public class FileRW {
	    
	    /**
	     * This method will read the entire presenter data file and return a list of Presenter objects
	     * lines populated entirely with white space are ignored
	     *   
	     * @param filename
	     * @return
	     */
	    public static List<Presenter> readPresenterFile(String filename) {
		    
	    	List<Presenter> presenterList = new ArrayList<Presenter>();
	    	Presenter tempPresenter = null;
		    String presenterData = null;
	    	
	        try {
	            File presenterDataFile = new File(filename);
	            if (presenterDataFile.exists()) {
	            	FileReader presenterFileReader = new FileReader(presenterDataFile);
	            	BufferedReader presenterReader = new BufferedReader(presenterFileReader);

	            	//Read first line
	            	presenterData = presenterReader.readLine();
	            
	            	//Read all lines
	            	while (presenterData != null) {
	            		tempPresenter = new Presenter();
	            		tempPresenter.parsePresenterString(presenterData);
	            		if (tempPresenter.isValid()) {
	            			presenterList.add(tempPresenter);
	            		}
	            		//Read next record
	            		presenterData = presenterReader.readLine();
	            	}
	        
	            	presenterReader.close();
	            	presenterFileReader.close();
	            } else {
	            	System.out.println("File " + filename + " not found returning empty presenter list.");
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        return presenterList;
	    }
	    
	    /**
	     * write the content of the presenter list to a temp file,
	     * make use of the presenter class to format each entry in the file
	     * once complete overwrite the original file with the temp version to complete the update
	     *  
	     * @param filename
	     * @param tempFilename
	     * @param PresenterList
	     * @return
	     */
	    public static int writePresenterFile(String filename, String tempFilename, List<Presenter> PresenterList) {
	    	
	    	File tempPresenterDataFile = null;
	    	FileWriter tempFileWriter = null;
	    	BufferedWriter tempWriter = null;
	    	Presenter tempPresenter = null;
	    	Iterator<Presenter> i = null; 
	    	
	    	int response = 0;
	    	
	    	try {
	    	
		    	tempPresenterDataFile = new File(tempFilename);
		    	tempFileWriter = new FileWriter(tempPresenterDataFile);
		    	tempWriter = new BufferedWriter(tempFileWriter);

		    	i = PresenterList.iterator();
		    	while (i.hasNext()) {
		    		tempPresenter = i.next();
		    		tempWriter.write(tempPresenter.getShortForm());
		    		tempWriter.newLine();
		    	}

		    	tempWriter.flush();
		    	tempFileWriter.flush();
		    	tempWriter.close();
		    	tempFileWriter.close();
		    	
	    	}
	    	catch (Exception e) {
	    		response = -1;
	    		e.printStackTrace();	    		
	    	}
	    	finally {
	            if(tempWriter != null){
	                try {
	    		    	tempWriter.close();
	                } catch (Exception e) {
	                    //do something clever with the exception
	                }
	            }
	            if(tempFileWriter != null){
	                try {
	    		    	tempFileWriter.close();
	                } catch (Exception e) {
	                    //do something clever with the exception
	                }
	            }
	    	}
	    	
	    	//If everything went ok copy the temp file over the standard data file
	    	if (response == 0) {
	    		response = copyOverwrite(filename, tempFilename);
	    	}
	    	            
	    	return response;
	    }
	    
	    /**
	     * overwrite the original file with the temp data file
	     * 
	     * @return
	     * 0 for success
	     * -2 for failure
	     */
	    private static int copyOverwrite(String filename, String tempFilename) {
	    	
	    	int response = -2;

	    	//Copy temp file to normal file - overwrite
	    	try {
	    		Path source = Paths.get(tempFilename);
	    		Path target = Paths.get(filename);
	    		Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
	    		response = 0;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return response;
	    }
	    
}
