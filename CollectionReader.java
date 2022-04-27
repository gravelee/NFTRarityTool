package com.nftrarity.com.nftrarity.maven.eclipse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class CollectionReader implements Callable<String> {
	
    private Integer index;
    private Integer start;
    private Boolean createArrays;
    
    
    public CollectionReader(Integer index, Integer start, Boolean createArrays){ 
    	
    	this.index = index;
    	this.start = start;
    	this.createArrays = createArrays;
    }
    
    
    /*
     * 	This method is called when we initiate threads to be calculated.
     */
    public String call(){
        return jsonParser();
    }
    
    
    
    /*
     * 	this method add an element to the nullsArray arraylist.
     */
    private static synchronized void addElementToArrayList(ArrayList<Integer> arrayList, Integer id) {
    	
    	arrayList.add(id);
    }
    
    
    /*
     * 	This method read all the json nft files from a collection (online or locally).
     */
    private String jsonParser(){
        
		Path path = null;
		String content = null;
		String filePath = null;
		String projectPath = null;
		
		try {
			
			projectPath = new java.io.File(".").getCanonicalPath();
			filePath = "/" + NFTCTools.cFolderName + "/" + NFTCTools.collectionName + "/" 
					+ NFTCTools.collectionName + (index+start) + ".json";
			
			path = Path.of(projectPath + filePath);
			content = Files.readString(path,StandardCharsets.US_ASCII);
			
			//System.out.println("File found locally!"); 
	        
	    }catch (IOException ex) {
        	
	    	content = null;
	    	//System.out.print("File downloading.."); 
        }
		
		if ( content == null) {
	        
			Boolean downloaded = false;
			Integer seconds = 1;
			
			do {
			
		        try {
		        	
		            URL u = new URL(NFTCTools.getPathOf(index+start));
		            ReadableByteChannel rbc = Channels.newChannel(u.openStream());
		            
		            FileOutputStream fos = new FileOutputStream(projectPath + filePath);
		            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		            
		            path = Path.of(projectPath + filePath);
					content = Files.readString(path,StandardCharsets.US_ASCII);
					content = specificCollectionChecks(content);
					
					if( content == null) {
						
	        			fos.close();
	        			rbc.close();
	        			
	        			File file = new File(projectPath + filePath);
	        			file.delete();
	        			
	        			if( createArrays)
	        				addElementToArrayList(NFTCTools.nullsArray,index+start);
	        			break;
					}
					else
						if( createArrays)
							addElementToArrayList(NFTCTools.inputIdsArray,index+start);
					
					downloaded = true;
		            fos.close();
		            rbc.close();
		            
		        } catch (IOException ex) {
		        	
		        	try {
		        		
		        		TimeUnit.SECONDS.sleep(seconds);
		        		seconds+=19;
		        		
		        		if( seconds >= NFTCTools.jArrayMaxLength) {
		        			
		        			System.out.print(",failed(" + (index+start) + ")");
		        			
		        			if( createArrays)
		        				addElementToArrayList(NFTCTools.nullsArray,index+start);
		        			break;
		        		}
		        		
		        	}catch(Exception e) {
		        		
		        		System.out.println("Error, failed to try not to fail!");
		        	}
		        }
		        
			}while(!downloaded);
		}
		else
			if( createArrays)
				addElementToArrayList(NFTCTools.inputIdsArray,index+start);
		
        return content;
    }
    
    
    /*
     * 	This method check the content for specific changes from collection to collection.
     */
    private String specificCollectionChecks(String content) {
    	
    	//	Null check
    	if( content == null)
    		return null;
    	
    	//	CyberKongs check
    	if( content.equals("{\"message\":\"Kong not found\"}"))
    		return null;
    	
    	//	Doge Pound check 
    	if( content.equals("{\"status\": false,\"message\": \"Cannot read property 'Fur' of null\"}"))
    		return null;
    	
    	//  Doge Pound check 
    	if( content.equals("{\"status\":false,\"message\":\"ERC721Metadata: URI query for nonexistent token\"}"))
    		return null;
    	
    	
    		
    	return content;
    }
}
