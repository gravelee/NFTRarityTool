package com.nftrarity.com.nftrarity.maven.eclipse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/*
 * 	NFTCTools
 */
class NFTCTools {
	
	//	These are the target values
	public static String contract;
	public static String collectionName;
	public static String startingPoint;
	public static String endingPoint;
	public static String urlPrefix;
	public static String urlPostfix;
	public static String traitCount; 				//	initMetadata()
	public static String copyTraitCount;			//	copyState()
	public static String jName;
	public static String jId;
	public static String jImage;
	public static String jDesc;
	public static String jExt;
	public static String jIHash;
	public static String jAName;
	public static String jType;
	public static String jValue;
	
	//	These are calculated for every collection. Variables & data structures.
	
	
	public static String [] jStrings;					// 	readColleciton()
	public static String [] copyJStrings;				//	copyState()
	public static ArrayList<Integer> nullsArray;		//	readColleciton()
	public static ArrayList<Integer> copyNullsArray;	//	copyState()
	public static ArrayList<Integer> inputIdsArray;		//	readColleciton()
	public static ArrayList<Integer> copyInputIdsArray;	//	copyState()
	public static String [] jArrayData;					//	initMetadata()
	public static String [] copyJArrayData;				//	copyState()
	public static Set<String> uniqueTraits;				//	initMetadata()
	public static Set<String> copyUniqueTraits;			//	copyState()
	public static Pair [] jArrayDataMore;				//	nftParse()
	public static Pair [] copyJArrayDataMore;			//	copyState()
	public static Integer [] jArrayDataMoreCount;		//	nftParse()
	public static Integer [] copyJArrayDataMoreCount;	//	copyState()
	public static ArrayList<Set<Integer>> setList;		//	nftParse()
	public static ArrayList<Set<Integer>> copySetList;	//	copyState()
	public static ArrayList<Integer> traitCounts;		//	nftParse()
	public static ArrayList<Integer> copyTraitCounts;	//	copyParse()
	public static NFT [] nft;							//	nftParse()
	public static NFT [] copyNft;						//	copyState()
	public static Integer [] extras;					//	nftParse()
	public static Integer [] copyExtras;				//	copyState()
	public static Integer [] frequencies;				//	nftParse()
	public static Integer [] copyFrequencies;			//	copyState()
	public static Map<Pair, Integer> valueFreq;			//	nftParse()
	public static Map<Pair, Integer> copyValueFreq;		//	copyState()
	public static Map<Integer, Double> rankings;		//	rarityCalculation()
	public static Map<Integer, Double> copyRankings;	//	copyState()
	public static ArrayList<Pair> pairArray;			//	cRarityScore()
	public static ArrayList<Pair> copyPairArray;		//	copyState()
	
	
	//	These are standard metadata names for the cMetadata.json.
	public static String cMFileName = "cMetadata.json";
	public static String cAMeta = "cMetadata";
	
	public static String cContract = "cContract";
	public static String cName = "cName";
	public static String cStartPoint = "cStartPoint";
	public static String cEndPoint = "cEndPoint";
	public static String cUrlPrefix = "cUrlPrefix";
	public static String cUrlPostfix = "cUrlPostfix";
	public static String cTraitCount = "cTraitCount"; 
	public static String jNameName = "name";
	public static String jTokenId = "tokenId";
	public static String jImageName = "image";
	public static String jDescName = "description";
	public static String jExternal = "external_url";
	public static String jImageHash = "imageHash";
	public static String jArrayName = "attributes";
	public static String jTraitType = "trait_type";
	public static String jTraitValue = "value";
	
	//	These are standard metadata names for queries json files.
	public static String minId = "minId";
	public static String maxId = "maxId";
	public static String minTNum = "minTNum";
	public static String maxTNum = "maxTNum";
	public static String attrExist = "attrExist";
	public static String attrNotExist = "attrNotExist";
	public static String specificId = "specificId";
	public static String specificNotId = "specificNotId";
	public static String specificTNum = "specificTNum";
	public static String specificNotTNum = "specificNotTNum";
	
	//	This is standard metadata value for queries json files.	
	public static String minTNumValue = "0";
	
	//	This is based on the pseudo + logical cores of the system.
	public static Integer cores = Runtime.getRuntime().availableProcessors();
	
	//	This generates the list of all written collection of cMetadata.json file.
	public static NFTCollection [] nftCollection = null;//	addNftCollection()
	
	//	This is a value set for the max number of traits. If not enough it doubles.
	public static Integer jArrayMaxLength = 20;
	
	//	This is a variable created to support printing on the command line
	public static volatile Boolean writer = false;
	
	//	These are the folders that this program uses.
	public static String cFolderName = "collections";
	public static String rFolderName = "results";
	public static String qFolderName = "queries";
	
	
	//	This method returns an Integer from a String
	public static Integer toInt(String string) {
		
		return Integer.parseInt(string);
	}
	
	
	
	/*
	 * 	This method sets the state (target collection) of the NFTCTools class.
	 * 	When takes <0 as input it sets the default values.
	 */
	public static void setState(Integer index, Boolean deleteStructures) {
		
		if( index >= 0) {
			
			contract = nftCollection[index].getcContract();
			collectionName = nftCollection[index].getcName();
			startingPoint = nftCollection[index].getcStartPoint();
			endingPoint = nftCollection[index].getcEndPoint();
			urlPrefix = nftCollection[index].getcUrlPrefix();
			urlPostfix = nftCollection[index].getcUrlPostfix();
			traitCount =  nftCollection[index].getcTraitCount();
			jName = nftCollection[index].getjName();
			jId = nftCollection[index].getjId();
			jImage = nftCollection[index].getjImage();
			jDesc = nftCollection[index].getjDesc();
			jExt = nftCollection[index].getjExt();
			jIHash = nftCollection[index].getjIHash();
			jAName = nftCollection[index].getjAName();
			jType = nftCollection[index].getjType();
			jValue = nftCollection[index].getjValue();
			
		}
		else {
			
			contract = "None";
			collectionName = "None";
			startingPoint = "None";
			endingPoint = "None";
			urlPrefix = "None";
			urlPostfix = "";
			traitCount = null;
			copyTraitCount = null;
			jName = "name";
			jId = "tokenId";
			jImage = "image";
			jDesc = "description";
			jExt = "external_url";
			jIHash = "imageHash";
			jAName = "attributes";
			jType = "trait_type";
			jValue = "value";
		}
		
		if(deleteStructures) {
			
			jStrings = null;
			copyJStrings = null;
			nullsArray = null;
			copyNullsArray = null;
			inputIdsArray = null;
			copyInputIdsArray = null;
			jArrayData = null;
			copyJStrings = null;
			uniqueTraits = null;
			copyUniqueTraits = null;
			jArrayDataMore = null;
			copyJArrayDataMore = null;
			jArrayDataMoreCount = null;
			copyJArrayDataMoreCount = null;
			setList = null;
			copySetList = null;
			traitCounts = null;
			copyTraitCounts = null;
			nft = null;
			copyNft = null;
			extras = null;
			copyExtras = null;
			frequencies = null;
			copyFrequencies = null;
			valueFreq = null;
			copyValueFreq = null;
			rankings = null;
			copyRankings = null;
			pairArray = null;
			copyPairArray = null;
		}
	}
	
	
	
	/*
	 * 	This method copies the data of the NFTCTools data structures. 
	 */
	public static void copyState( Boolean back) {
		
		if( back) {
			
			traitCount = copyTraitCount;
			
			if( copyJStrings != null && copyJStrings.length != 0) {
				
				jStrings = new String [copyJStrings.length];
				
				for( int i=0; i<copyJStrings.length; i++)
					jStrings[i] = copyJStrings[i];
			}
			else
				jStrings = null;
			
			if( copyNullsArray != null)
				nullsArray = new ArrayList<Integer>(copyNullsArray);
			else
				nullsArray = null;
			
			if( copyInputIdsArray != null)
				inputIdsArray = new ArrayList<Integer>(copyInputIdsArray);
			else
				inputIdsArray = null;
			
			if( copyJArrayData != null && copyJArrayData.length != 0) {
			
				jArrayData = new String [copyJArrayData.length];

				for( int i=0; i<copyJArrayData.length; i++)
					jArrayData[i] = copyJArrayData[i];
			}
			else
				jArrayData = null;
			
			if( copyUniqueTraits != null)
				uniqueTraits = new HashSet<String>(copyUniqueTraits);
			else
				uniqueTraits = null;
			
			if( copyJArrayDataMore != null && copyJArrayDataMore.length != 0) {
				
				jArrayDataMore = new Pair [copyJArrayDataMore.length];

				for( int i=0; i<copyJArrayDataMore.length; i++)
					jArrayDataMore[i] = copyJArrayDataMore[i];
			}
			else
				jArrayDataMore = null;
			
			if( copyJArrayDataMoreCount != null && copyJArrayDataMoreCount.length != 0) {
				
				jArrayDataMoreCount = new Integer [copyJArrayDataMoreCount.length];

				for( int i=0; i<copyJArrayDataMoreCount.length; i++)
					jArrayDataMoreCount[i] = copyJArrayDataMoreCount[i];
			}
			else
				jArrayDataMoreCount = null;
			
			if( copySetList != null)
				setList = new ArrayList<Set<Integer>>(copySetList);
			else
				setList = null;
			
			if( copyTraitCounts != null)
				traitCounts = new ArrayList<Integer>(copyTraitCounts);
			else
				traitCounts = null;
				
			if( copyNft != null && copyNft.length != 0) {
			
				nft = new NFT[copyNft.length];
				for( int i=0; i<copyNft.length; i++)
					nft[i] = new NFT(copyNft[i]);
			}
			else
				nft = null;
			
			if( copyExtras != null && copyExtras.length != 0) {
				
				extras = new Integer [copyExtras.length];

				for( int i=0; i<copyExtras.length; i++)
					extras[i] = copyExtras[i];
			}
			else
				extras = null;
			
			if( copyFrequencies != null && copyFrequencies.length != 0) {
				
				frequencies = new Integer [copyFrequencies.length];

				for( int i=0; i<copyFrequencies.length; i++)
					frequencies[i] = copyFrequencies[i];
			}
			else
				frequencies = null;
			
			if( copyValueFreq != null)
				valueFreq = new HashMap<Pair,Integer>(copyValueFreq);
			else
				valueFreq = null;
			
			if( copyRankings != null)
				rankings = new HashMap<Integer,Double>(copyRankings);
			else
				rankings = null;
			
			if( copyPairArray != null)
				pairArray = new ArrayList<Pair>(copyPairArray);
			else
				pairArray = null;
		}
		else {
			
			copyTraitCount = traitCount;
			
			if( jStrings != null && jStrings.length != 0) {
				
				copyJStrings = new String [jStrings.length];
				
				for( int i=0; i<jStrings.length; i++)
					copyJStrings[i] = jStrings[i];
			}
			else
				jStrings = null;
			
			if( nullsArray != null)
				copyNullsArray = new ArrayList<Integer>(nullsArray);
			else
				copyNullsArray = null;
			
			if( inputIdsArray != null)
				copyInputIdsArray = new ArrayList<Integer>(inputIdsArray);
			else
				copyInputIdsArray = null;
			
			if( jArrayData != null && jArrayData.length != 0) {
				
				copyJArrayData = new String [jArrayData.length];

				for( int i=0; i<jArrayData.length; i++)
					copyJArrayData[i] = jArrayData[i];
			}
			else
				copyJArrayData = null;
				
			if( uniqueTraits != null)
				copyUniqueTraits = new HashSet<String>(uniqueTraits);
			else
				copyUniqueTraits = null;
			
			if( jArrayDataMore != null && jArrayDataMore.length != 0) {
				
				copyJArrayDataMore= new Pair [jArrayDataMore.length];

				for( int i=0; i<jArrayDataMore.length; i++)
					copyJArrayDataMore[i] = jArrayDataMore[i];
			}
			else
				copyJArrayDataMore = null;
			
			if( jArrayDataMoreCount != null && jArrayDataMoreCount.length != 0) {
				
				copyJArrayDataMoreCount = new Integer [jArrayDataMoreCount.length];

				for( int i=0; i<jArrayDataMoreCount.length; i++)
					copyJArrayDataMoreCount[i] = jArrayDataMoreCount[i];
			}
			else
				copyJArrayDataMoreCount = null;
			
			if( setList != null)
				copySetList = new ArrayList<Set<Integer>>(setList);
			else
				copySetList = null;
			
			if( traitCounts != null)
				copyTraitCounts = new ArrayList<Integer>(traitCounts);
			else
				copyTraitCounts = null;
			
			if( nft != null && nft.length != 0) {
			
				copyNft = new NFT[nft.length];
				for( int i=0; i<nft.length; i++)
					copyNft[i] = new NFT(nft[i]);
			}
			else
				copyNft = null;
			
			if( extras != null && extras.length != 0) {
				
				copyExtras = new Integer [extras.length];

				for( int i=0; i<extras.length; i++)
					copyExtras[i] = extras[i];
			}
			else
				copyExtras = null;
			
			if( frequencies != null && frequencies.length != 0) {
				
				copyFrequencies = new Integer [frequencies.length];

				for( int i=0; i<frequencies.length; i++)
					copyFrequencies[i] = frequencies[i];
			}
			else
				copyFrequencies = null;
			
			if( valueFreq != null)
				copyValueFreq = new HashMap<Pair,Integer>(valueFreq);
			else
				copyValueFreq = null;
			
			if( rankings != null)
				copyRankings = new HashMap<Integer,Double>(rankings);
			else
				copyRankings = null;
			
			if( pairArray != null)
				copyPairArray = new ArrayList<Pair>(pairArray);
			else
				copyPairArray = null;
		}
	}
	
	
	
	/*
	 * 	This method returns all the results of a specific results file.
	 */
	public static String [] getResults(String fileName) {
		
		Integer length = 100;
		String [] results = new String[length];
		
		try {
			
			String projectPath = new java.io.File(".").getCanonicalPath();
			String fullPath = projectPath + "/" + rFolderName + "/" + collectionName;
			
			String currentLine;
			Integer index = 0;
			File file = new File(fullPath + "/" + fileName);
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
	        while ((currentLine = reader.readLine()) != null) {
	        	
	        	results[index] = currentLine;
	        	index++;
	        	
	        	if(length.toString().equals(index.toString())) {
	        		
	        		String [] temp = new String[length];
	        		
	        		for(int i=0; i<length; i++)
	        			temp[i] = results[i];
	        		
	        		length *=2;
	        		results = new String[length];
	        		
	        		for(int i=0; i<length/2; i++)
	        			 results[i] = temp[i];
	        	}
	        }
		    
	        reader.close();
	        
	        if( index > 0) {
	        	
		        String [] temp = new String[index];
	    		
	    		for(int i=0; i<index; i++)
	    			temp[i] = results[i];
	    		
	    		results = new String[index];
	    		
	    		for(int i=0; i<index; i++)
	    			 results[i] = temp[i];
	        }
	        
		}catch(Exception e) {
			
			System.out.println("Error, couldn't read from the result file!");
		}
		
		return results;
	}
	
	
	
	/*
	 * 	This method is returning all the names of the results/collection
	 */
	public static String [] readResultNames() {
		
		String [] names = null;
		
		try {
			
			String projectPath = new java.io.File(".").getCanonicalPath();
			String fullPath = projectPath + "/" + rFolderName + "/" + collectionName;
			
			File resFile = new File(fullPath);
			
			if( resFile.exists()) {
				
				File [] contents = resFile.listFiles();
				names = new String[contents.length];
				
				if (contents != null) {
					
					Integer counter = 0;
					
					for (File file : contents){
						
						Path path = file.toPath();
						
						if( Files.isRegularFile(path) && 
								!Files.isSymbolicLink(path) && 
								!Files.isHidden(path)) {
							
							names[counter] = file.toPath().getFileName().toString();
							counter++;
						}
					}
					
					if( counter != contents.length) {
						
						String [] temp = new String[counter];
						
						for( int i=0; i<counter; i++)
							temp[i] = names[i];
						
						names = new String[counter];
						
						for( int i=0; i<counter; i++)
							names[i] = temp[i];
					}
				}
			}
		}
		catch(Exception e) {
			
			System.out.println("Error, couldn't read the result files!");
		}
		
		return names;
	}
	
	
	
	/*
	 * 	This method is returning all the names of the added collections.
	 */
	public static String [] readNames() {
		
		if( nftCollection == null)
			return null;
		
		Integer length = nftCollection.length;
		String [] names = new String[length];
		
		for( int i=0; i<length; i++)
			names[i] = nftCollection[length-i-1].getcName();
		
		return names;
	}
	
	
	
	/*
	 * 	This method is for adding a new collection to the nftCollection array.
	 */
	public static void addNftCollection(Integer index) {
		
		if( index < 0) {
			
			Integer length = 0;
			if( nftCollection != null)
				length = nftCollection.length;
			
			NFTCollection [] newNftCollection = new NFTCollection[length+1];
			
			newNftCollection[0] = new NFTCollection(contract,collectionName,startingPoint,endingPoint,
					urlPrefix,urlPostfix,traitCount,jName,jId,jImage,jDesc,jExt,jIHash,jAName,jType,jValue);
			
			for( int i=0; i<length; i++)
				newNftCollection[i+1] = nftCollection[i];
			
			nftCollection = new NFTCollection[length+1];
			
			for( int i=0; i<length+1; i++)
				nftCollection[i] = newNftCollection[i];
		}
		else {
			
			nftCollection[index] = new NFTCollection(contract,collectionName,startingPoint,endingPoint,
					urlPrefix,urlPostfix,traitCount,jName,jId,jImage,jDesc,jExt,jIHash,jAName,jType,jValue);
		}
	}
	
	
	
	/*
	 * 	This method is for deleting a collection from the nftCollection array.
	 */
	public static void deleteNftCollection(Integer index) {
			
		
		Integer length = nftCollection.length;
		
		NFTCollection [] newNftCollection = new NFTCollection[length-1];
		
		int k = 0;
		
		for( int i=0; i<length; i++)
			if( i != index) {
				
				newNftCollection[k] = nftCollection[i];
				k++;
			}
		
		nftCollection = new NFTCollection[length-1];
		
		for( int i=0; i<length-1; i++)
			nftCollection[i] = newNftCollection[i];
		
	}
	
	
	
	/*
	 * 	This method adds all the NFTCollections from nftCollection data structure
	 * 	to the cMetadata.json file.
	 */
	public static void updateCMetadata() {
		
		deleteFolder("",cMFileName);
		
		String content = "";
		
		try {
			
			String projectPath = new java.io.File(".").getCanonicalPath();
			String filePath = "/" + cMFileName;
			
			FileOutputStream fos = new FileOutputStream(projectPath + filePath);
	        PrintStream ps = new PrintStream(fos);
	        
	        content += "{\n\t\"" + cAMeta + "\":["; 
			
	        if(nftCollection != null) {
	        
				for( int i=0; i<nftCollection.length; i++) {
					
					content += "\n\n{\n" 
							+ "\t\"" + cContract + "\":\"" + nftCollection[i].getcContract() + "\",\n"
							+ "\t\"" + cName + "\":\"" + nftCollection[i].getcName() + "\",\n" 
							+ "\t\"" + cStartPoint + "\":\"" + nftCollection[i].getcStartPoint() + "\",\n" 
							+ "\t\"" + cEndPoint + "\":\"" + nftCollection[i].getcEndPoint() + "\",\n"
							+ "\t\"" + cUrlPrefix + "\":\"" + nftCollection[i].getcUrlPrefix() + "\",\n"
							+ "\t\"" + cUrlPostfix + "\":\"" + nftCollection[i].getcUrlPostfix() + "\",\n"
							+ "\t\"" + cTraitCount + "\":\"" + nftCollection[i].getcTraitCount() + "\",\n"
							+ "\t\"" + jNameName + "\":\"" + nftCollection[i].getjName() + "\",\n"
							+ "\t\"" + jTokenId + "\":\"" + nftCollection[i].getjId() + "\",\n"
							+ "\t\"" + jImageName + "\":\"" + nftCollection[i].getjImage() + "\",\n"
							+ "\t\"" + jDescName + "\":\"" + nftCollection[i].getjDesc() + "\",\n"
							+ "\t\"" + jExternal + "\":\"" + nftCollection[i].getjExt() + "\",\n"
							+ "\t\"" + jImageHash + "\":\"" + nftCollection[i].getjIHash() + "\",\n"
							+ "\t\"" + jArrayName + "\":\"" + nftCollection[i].getjAName() + "\",\n"
							+ "\t\"" + jTraitType + "\":\"" + nftCollection[i].getjType() + "\",\n"
							+ "\t\"" + jTraitValue + "\":\"" + nftCollection[i].getjValue() + "\"\n}";
					
					if( i != nftCollection.length-1)
						content += ",";
					
				}
	        }
			
			content += "]}";
			
			ps.println(content);
			
			fos.close();
	        ps.close();
		}
		catch(Exception e) {
			
			System.out.println("Error, couldn't update the cMetadata.json file!");
		}
	}
	
	
	
	/*
     * 	This method is called to read the contents of cMetadata.json file.
     * 	If cMetadata.json file doesn't exist it initiates one.
     */
    public static String readCMetadata() {
    	
    	Path path = null;
		String content = null;
		String filePath = null;
		String projectPath = null;
		
		try {
			
			projectPath = new java.io.File(".").getCanonicalPath();
			filePath = "/" + cMFileName;
			
			path = Path.of(projectPath + filePath);
			content = Files.readString(path,StandardCharsets.US_ASCII);
			
	    }catch (IOException e) {
        	
	    	content = null;
	    	//System.out.print("Error, couldn't read the cMetadata.json file!"); 
        }
		
		if ( content == null) {
	        
	        try {
	        	
	            FileOutputStream fos = new FileOutputStream(projectPath + filePath);
	            PrintStream ps = new PrintStream(fos);
	            
	            content = "{\n\t\"" + cAMeta + "\":[]}";
	            ps.println(content);
	            
	            fos.close();
	            ps.close();
	            
	        } catch (IOException ex) {
	        	
	        	System.out.println("\n ..Error, create failed!\n"); 
	        	return null;
	        }
		}
		
        return content;
    }
	
    
    
	/*
	 * 	This method is getting from cMetadata.json to nftCollection data structure.
	 * 	This is called in the beginning of the program or when we have new collections
	 * 	added, deleted or updated.
	 */
	public static void updateNftCollection() {
		
		String cMFile = readCMetadata();
		JSONTokener tokener = new JSONTokener(cMFile);
		JSONObject jo = new JSONObject(tokener);
		JSONArray ja = (JSONArray) jo.get(cAMeta);
		
		if( ja.length() != 0) {
			
			nftCollection = new NFTCollection[ja.length()];
			
			for ( int j=0; j < ja.length(); j++) {
				
				JSONObject obj = ja.getJSONObject(j);
				
				String xcContract =  obj.get(cContract).toString();
				String xcName =  obj.get(cName).toString();
				String xcStartPoint =  obj.get(cStartPoint).toString();
				String xcEndPoint =  obj.get(cEndPoint).toString();
				String xcUrlPrefix =  obj.get(cUrlPrefix).toString();
				String xcUrlPostfix =  obj.get(cUrlPostfix).toString();
				String xcTraitCount =  obj.get(cTraitCount).toString();
				String xjName =  obj.get(jNameName).toString();
				String xjId =  obj.get(jTokenId).toString();
				String xjImage =  obj.get(jImageName).toString();
				String xjDesc =  obj.get(jDescName).toString();
				String xjExt =  obj.get(jExternal).toString();
				String xjIHash =  obj.get(jImageHash).toString();
				String xjAName =  obj.get(jArrayName).toString();
				String xjTrait =  obj.get(jTraitType).toString();
				String xjValue =  obj.get(jTraitValue).toString();
				
				nftCollection[j] = new NFTCollection(xcContract,xcName,xcStartPoint,xcEndPoint,
					xcUrlPrefix,xcUrlPostfix,xcTraitCount,xjName,xjId,xjImage,xjDesc,xjExt,
					xjIHash,xjAName,xjTrait,xjValue);
			}
		}
	}
	
	
	
	/*
	 * 	This function calculates the rarity score of the collection.
	 * 	If write true then prints the results to ScoreReport file.
	 */
	public static void rarityCalculation(Boolean write, String queryName) {
		
		rankings = new HashMap<Integer, Double>();
		
		Integer cNum = inputIdsArray.size() + nullsArray.size();
		Integer start = toInt(startingPoint);
		Integer cCount = toInt(traitCount);
		
		String [] ghostTraits = new String[cNum];
		String [] outputs = new String[cNum];
		String [] strs1 = new String[cCount];
		String [] strs2 = new String[cCount];
		String timeStamp = null;
		PrintStream out = null;
		String path = null;
		File file = null;
	
		try {
			
			timeStamp = new SimpleDateFormat("dd-MM-y_HH:mm:ss").format(Calendar.getInstance().getTime());
			path = new java.io.File(".").getCanonicalPath() + "/" + rFolderName + "/" + collectionName;
			file = new File(path + "/" + collectionName + "_ScoreReport_" 
					+ (queryName != null ? queryName + "_" : "") + timeStamp + ".txt");
			if( write)
				out  = new PrintStream(new FileOutputStream(file));
		
		} catch(IOException e){
			
			System.out.println(" ..Error, it wasn't possible to write the results.");
		}
    		
    	for ( int i=0; i<inputIdsArray.size(); i++) {
    		
    		Integer id = inputIdsArray.get(i)-start;
    		
    		if( nft[id] != null) {
    		
	    		double freq = 0.0;
	    		double score;
	    		
	    		outputs[id] = "NTF Index : " + (id+1) + " \n";
	    		ghostTraits[id] = "";
	    		
	    		for ( int j=0; j<cCount; j++ ) {
	    			
	    			String string1 = nft[id].getAtt(j).getKey();
	    			String string2 = nft[id].getAtt(j).getValue();
					Boolean fNNsN = false;
					Boolean bNN = false;
					Boolean bN = false;
					Boolean nF = true;
					strs1[j] = string1;
					strs2[j] = string2;
					int k;
					
					for( k=j-1; k>=0; k--) {
						
						if( strs1[k].equals(string1) && (string2.equals("None"))) {
							
							if( !strs2[k].equals("None"))
								fNNsN = true;
							else
								bN = true;
							nF = false;
							break;
						}
						
						if(strs1[k].equals(string1)) {
							
							bNN = true;
							nF = false;
							k = j;
							break;
						}
					}
					
					Integer integer = valueFreqReturns( new Pair(string1, string2));
					
					if( !nF) {
						
						if( fNNsN) {
							
							//	Rarity.tools does not count that even thought  it is the RIGHT WAY!
							score = ((double) inputIdsArray.size()) / ((double) extras[k] );
	    					ghostTraits[id] += String.format("%-35s= %s" , "Ghost : \"" + string1 
	    							+ "\" ", String.format("%-35s= %s", "Value : \"" + extras[k] 
	    									+ "\", ", "Score : (" + score + ")\n") );
	    					//freq += score;
	    					score = 0;
						}
						else if( bNN) {
							
							//	Rarity.tools does not count that even thought  it is the RIGHT WAY!
							score = ((double) inputIdsArray.size()) / ((double) extras[j] );
	    					ghostTraits[id] += String.format("%-35s= %s" , "Ghost : \"" + string1 
	    							+ "\" ", String.format("%-35s= %s", "Value : \"" + extras[j] 
	    									+ "\", ", "Score : (" + score + ")\n") );
	    					
	    					
	    					//freq += score;
	    					score = 0;
						}
						else if( bN) {
							
							
						}
						else {
							
							System.out.println("Error, there is no way!");
						}
					}
					
					if( bN || fNNsN)
						score = 0;
					else
						score = ((double) inputIdsArray.size()) / ((double) integer );	
					// (inputIdsArray.size()+extras[j]*(n-1))/integer, where n is the nth extra trait. RIGHT WAY!
	    			
	    			outputs[id] += String.format("%-35s= %s" , "Trait : \"" + string1 + "\" "
	    							, String.format("%-35s= %s", "Value : \"" + string2 
	    									+ "\", ", "Score : \"" + score + "\"\n") );
	    			
	    			freq += score;
	    		}
	    			
	    		score = ((double) inputIdsArray.size()) / ((double) frequencies[nft[id].getTraitNum()]);
	    		
	    		outputs[id] += String.format("%-35s= %s" , "Ghost : \"" + "Trait Count\" "
	    							, String.format("%-35s= %s", "Value : \"" + nft[id].getTraitNum() 
	    									+ "\", ", "Score : \"" + score + "\"\n") );
	    		freq += score;
	    		
	    		outputs[id] += ghostTraits[id];
	    		outputs[id] += "Total score : "+ freq + "\n\n";
	    		
	    		rankings.put(id+start,freq);
    		}
    		else {
    			
    			outputs[id] = "NTF Index : " + (id+1) + " \n NULL \n\n";
    			rankings.put(id+start,0.0);
    		}
    	}
		
    	if( write) {
    		
    		for( int i=0; i<cNum; i++)
    			out.println(outputs[i]);
    		
    		out.close();
    	}
	}
	
	
	
	/*
	 * 	This function takes as input the rankings of the collection sorted by ID
	 * 	and sorts them based on rarity write the results to pairArray.  
	 */
	public static void cRarityScore() {
		
		ValueComparator bvc = new ValueComparator(rankings);
    	TreeMap< Integer, Double> sortedRanks = new TreeMap< Integer, Double>(bvc);
    	sortedRanks.putAll(rankings);
		
		int i = 0;
		
		pairArray = new ArrayList<>();
		
    	for( Map.Entry< Integer, Double> itr : sortedRanks.entrySet()) {
    		
    		int temp = (int)(itr.getValue()*100.0);
    	    double rank = ((double)temp)/100.0;
    		Integer id = itr.getKey();
    		
    		pairArray.add(new Pair(null, null));
    		pairArray.get(i).setKey(String.valueOf(rank));
    		pairArray.get(i).setValue(String.valueOf(id));
    		i++;
    	}
    	
	}
	
	
	
	/*
	 * 	This function prints all the result pairs ( sorted by rarity) to a file.
	 */
	public static void printRarityReport( String queryName) {
		
		try {
			
			String output = String.format("\n%-6s %s", "Rank", 
							String.format("%-6s %s", "ID", 
							String.format("%-7s %s", "Rarity","Score\n\n")));
			
			String timeStamp = new SimpleDateFormat("dd-MM-y_HH:mm:ss").format(Calendar.getInstance().getTime());
			String path = new java.io.File(".").getCanonicalPath() + "/" + rFolderName + "/" + collectionName;
			File file = new File(path + "/" + collectionName + "_RarityReport_" 
					+ (queryName != null ? queryName + "_" : "") + timeStamp + ".txt");
			PrintStream out = new PrintStream(new FileOutputStream(file));
			
			for( int i=0; i<pairArray.size(); i++) {
				
				Double score  = Double.parseDouble(pairArray.get(i).getKey());
				String rarity = String.format("%s%s", ((((Double)(double)((int)((score / 
					Double.parseDouble(pairArray.get(0).getKey()))*100))).toString())).toString(),"");
				
				rarity = rarity.substring(0,rarity.length()-2) + "%";
				
				output += String.format("%-6s %s", (i+1), 
						  String.format("%-6s %s", pairArray.get(i).getValue(), 
						  String.format("%-7s %s", rarity, score + "\n")));
				
			}
			
			System.out.println("\nResults has been written to file!");
			out.print(output);
			out.close();
			
		} catch(IOException e){
			
			System.out.println(" ..Error, it wasn't possible to write the results.");
		}
	}
	
	
	
	/*
	 *	This function prints only the specified pair that the user gave.
	 *	They are printed ranked by rarity.
	 */
	/*
	public static void printSpecificPairs(ArrayList<Pair> pair, Integer [] snipes) {
		
		Integer counter = 0;
		Integer start = toInt(startingPoint);
		Integer end = toInt(endingPoint);
		Integer cNum = end - start + 1;
		
		System.out.println("\nHighest Rarity Score : " + Double.parseDouble(pair.get(0).getKey()));
		System.out.println("Lowest  Rarity Score : " + Double.parseDouble(pair.get(cNum-1).getKey()) + "\n");
		
		
		for(i=0; i<cNum; i++) {
			
			for(int j=0; j<snipes.length; j++) {
				
				Integer id = Integer.parseInt(pair.get(i).getValue());
				Double score = Double.parseDouble(pair.get(i).getKey());
				
				if( id.equals(snipes[j])) {
					
					counter+=1;
					System.out.println("Rank : " + (i+1) + ",\t ID : #" 
									+ id + ",\t Rarity Score : " + score);
				}
			}
			
			if( counter == snipes.length)
				break;
		}
	}*/
	
	
	
	/*
	 *  	This function gets a row of integers as input from the user and returns them in an array.
	 */
	/*
	public static Integer [] specifiedIDs() {
		
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		System.out.println("Input : Enter the IDs that you are interested in (Ex. \"4,34,674,1038,9043,895\"). ");
		
		String str= sc.nextLine();
		String[] result = str.split(",");
		Integer [] numbers = new Integer[result.length];
		
		for( int i=0; i<result.length; i++)
			numbers[i] = Integer.parseInt(result[i]);
		
		return numbers;
	}*/
	
	
	
	/*
	 * 	This function checks if all the proper files exist .. if not it creates them.
	 */
	public static void checkFiles() {
		
		createFolder("",cFolderName);
    	createFolder("",rFolderName);
    	createFolder("",qFolderName);
    	createFolder("/" + cFolderName ,collectionName);
    	createFolder("/" + rFolderName ,collectionName);
    	createFolder("/" + qFolderName ,collectionName);
	}
	
	
	
	/*
	 * 	This method checks if the specified folder exists within the specified path.
	 * 	If not then create the folder.
	 */
	public static void createFolder(String innerPath, String folderName) {
		
		try {
			
			String currentPath = new java.io.File(".").getCanonicalPath();
			File file = new File( currentPath + innerPath + "/" + folderName);
			
			if( !file.exists()) {
				
				file.mkdirs();
				System.out.println(folderName + " folder has been created!!");
			}
			
		}catch(IOException e){
			
			System.out.println("Error, there was a problem in creating the folder!");
		}
	}
	
	
	
	/*
	 * 	This method renames the specific folder.
	 */
	public static void renameFolder(String innerPath, String folderName, String newName) {
		
		try {
			
			String currentPath = new java.io.File(".").getCanonicalPath();
			String path = currentPath + innerPath + "/";
			String fullPath = path + "/" + folderName + "/";
			
			File oldNameFile = new File( path + folderName);
			File newNameFile = new File( path + newName);
			
			if( oldNameFile.exists()) {
				
				File [] contents = oldNameFile.listFiles();
				
				if (contents != null) {
					
					for (File file : contents){
						
						Path filePath = file.toPath();
						
						if( Files.isRegularFile(filePath) && 
								!Files.isSymbolicLink(filePath) && 
								!Files.isHidden(filePath)) {
							
							Integer length = folderName.length();
							String temp = file.toPath().getFileName().toString();
							String name = newName + temp.substring(length);
							
					    	file.renameTo(new File(fullPath + name));
						}
					}
				}
				
				oldNameFile.renameTo(newNameFile);
				
			}
			
		}catch(IOException e){
			
			System.out.println("Error, there was a problem in deleting the folder!");
		}
	}
	
	
	
	/*
	 * 	This method deletes the specific folder if exists.
	 */
	public static void deleteFolder(String innerPath, String folderName) {
		
		try {
			
			String currentPath = new java.io.File(".").getCanonicalPath();
			File file = new File( currentPath + innerPath + "/" + folderName);
			
			if( file.exists()) {
				
				deleteDirs(file);
				//System.out.println( "\n" + folderName + " folder has been deleted!!");
			}
			
		}catch(IOException e){
			
			System.out.println("Error, there was a problem in deleting the folder!");
		}
	}
	
	
	
	/*
	 * 	This method deletes all the sub directories of the specific file.
	 */
	private static void deleteDirs(File file) {
		
	    File[] contents = file.listFiles();
	    
	    if (contents != null)
	        for (File f : contents)
	            if (! Files.isSymbolicLink(f.toPath()))
	                deleteDirs(f);
	    
	    file.delete();
	}
	
	
	
	/*
	 *  This method by giving as input the index of an NFT it returns the Internet path of the json file.
	 */
	public static String getPathOf( Integer index) {
		
		return urlPrefix + index.toString() + urlPostfix;
	}
	
	
	
	/*
	 * 	This method prints the progress percentage of the collections process.
	 */
	public static void getProgress(Integer index, Integer start, 
				Integer cNum, Integer counter, String string) {
		
		Integer printBlock = cNum / 100;
		
		if( counter<0)
			counter = index + start;
		
		double rate = ((double) counter / (double) cNum)*100;
		int intRate = (int) rate;
		rate = (double) intRate;
		
    	if( counter % printBlock == 0 || !writer) {
    		
    		System.out.print("\n" + string + rate + "%, " + (index+start));
    		writer = true;
    		
    	}
    	else
    		System.out.print(", " + (index+start));
    	
	}

	
	
	/*
	 * 	If the input pair is been found in the valueFreq Map it returns the frequency count of that pair.
	 * 	If not found it returns 0
	 */
	public static Integer valueFreqReturns(Pair pair) {
		
		for( Map.Entry<Pair, Integer> i : valueFreq.entrySet())
			if ( i.getKey().equals(pair))
				return i.getValue();
		
		return 0;
	}
	
	
	
	/*
	 * 	Returns true if the pair has been found and removed successfully. Returns false otherwise.
	 */
	public static Boolean valueFreqRemove(Pair pair) {
	
		Pair removalKey = null;
		
		for ( Map.Entry<Pair, Integer> entry : valueFreq.entrySet()) {
			
			if ( pair.equals(entry.getKey())) {
				
				removalKey = entry.getKey();
		        break;
			}
		}

	    if (removalKey != null) {
	    	
	        valueFreq.remove(removalKey);
	        return true;
	    }
	    
	    return false;
	}
	
	
	
	/*
	 * 	This method prints the report of the readColleciton function.
	 */
	public static String report() {
		
		String buffer = "";
		
		if( nullsArray != null && nullsArray.size() != 0) {
			
			buffer += "Report :  nulls = " + nullsArray.size() + ", IDs = {";
			
			for( int i=0; i<nullsArray.size(); i++) {
				
				buffer += nullsArray.get(i);
				
				if(i == nullsArray.size()-1)
					buffer += "}.\n";
				else
					buffer += ", ";
			}
		}
		else
			buffer += "\nReport :  nulls = 0, IDs = {}.\n";
		
		return buffer;
	}

	
	
	/*
	 * 	This method downloads the collection and writes it to the SSD and RAM.
	 * 	jStrings is the container for the Ram.
	 */
	@SuppressWarnings("rawtypes")
	public static void readCollection(){
		
		Integer start = toInt(startingPoint);
		Integer end = toInt(endingPoint);
		Integer cNum = end - start + 1;
		
		ExecutorService es = Executors.newFixedThreadPool(cores);
		
		jStrings = new String[cNum];
		
		if( inputIdsArray == null || inputIdsArray.size() == 0) {
		
			nullsArray = new ArrayList<Integer>();
			inputIdsArray = new ArrayList<Integer>();
			
			try {
			
				Future [] tempRes = new Future[cNum];
				
				for( int i=0; i<cNum; i++)
					tempRes[i] = es.submit( new CollectionReader(i,start,true));
				
				for( int i=0; i<cNum; i++) {
					
					Object obj = tempRes[i].get();
					
					if( obj != null)
						jStrings[i] = (String) obj;
					else
						jStrings[i] = null;
					
					getProgress(i,start,cNum,-1,"Reading.. ");
				}
			}catch(Exception e){
				
	            System.out.println("Error, There was a problem getting the data!\n");
	            
	        }
		}
		else {
			
			try {
				
				Future [] tempRes = new Future[inputIdsArray.size()];
				
				int c=0;
				for( Integer element : inputIdsArray) {
					
					tempRes[c] = es.submit( new CollectionReader(element-start,start,false));
					c++;
				}
				
				for( int i=0; i< cNum; i++)
					jStrings[i] = null;
				
				for( int i=0; i<inputIdsArray.size(); i++) {
					
					Object obj = tempRes[i].get();
					
					if( obj != null)
						jStrings[inputIdsArray.get(i)-start] = (String) obj;
					else 
						jStrings[i] = null;
					
					getProgress(i,start,inputIdsArray.size(),-1,"Reading.. ");
				}
			}catch(Exception e){
				
	            System.out.println("Error, There was a problem getting the data!\n");
	        }
		}
		
		es.shutdown();

		if( nullsArray != null && nullsArray.size() > 0)
			Collections.sort(nullsArray);
		
		if( inputIdsArray != null && inputIdsArray.size() > 0)
			Collections.sort(inputIdsArray);
		
		System.out.println();
		
		writer = false;
	}
	
	
	
	/*
	 * 	This method runs through the collection and calculates the traitCount and the jArrayData.
	 */
	public static Integer initMetadata() {
		
		Boolean error;
		Integer tCount;
		Integer start = toInt(startingPoint);
		Integer cNum = inputIdsArray.size() + nullsArray.size();
		
		do {
			
			error = false;
			Integer index = 0;
			
			jArrayData = new String[jArrayMaxLength];
			
			for( int i = 0; i<jArrayMaxLength; i++)
				jArrayData[i] = null;
			
			for( int i = 0; i<inputIdsArray.size(); i++){
				
				Integer id = inputIdsArray.get(i) - start;
				
				try {
					
		    		JSONTokener tokener = new JSONTokener(jStrings[id]);
		    		JSONObject jo = new JSONObject(tokener);
		    		JSONArray ja = (JSONArray) jo.get(jAName);
		    		
		    		String [] strings = new String[ja.length()];
		    		
		    		for ( int j=0; j < ja.length(); j++) {
		    			
		    			JSONObject obj = ja.getJSONObject(j);
		    			String traitType =  obj.get(jType).toString();
		    			strings[j] = traitType;
		    			
		    			int c1 = 0;	// number of specific trait_type within jArrayData array.
		    			int c2 = 1;	// number of specific trait_type within this nft file before.
		    			int k = 0;
		    			
						while( jArrayData[k] != null) {
							
							if( jArrayData[k].equals(traitType))
								c1++;
							k +=1;
						}
						
						for( int z=0; z<j; z++)
							if( traitType.equals(strings[z])) {
								
								c2++;
							}
						
			    		for( int z=0; z<c2-c1; z++) {
						
			    			jArrayData[index] = traitType;
							index +=1;
						}
			    		
		    		}
				}catch ( Exception e) {
		    		//*
					
					System.out.println("jStrings[" + id + "] = error, " + "id = " + (id + start));
					
		    		if(jArrayMaxLength<=640) {
		    			
		    			System.out.println("\nError, number of traits exceded "
		    					+ "the default max : \"" + jArrayMaxLength + "\"\n"
		    					+ "Doubleing the max number... Success!\n");
		    			jArrayMaxLength = jArrayMaxLength*2;
		    			error = true;
		    			break;
		    		}
		    	}//*/
				
				getProgress(id,start,cNum,-1,"Extracting... ");
			}
			
			tCount = index;
			
		}while(error);
		
		//	Here we change the size of the array jArrayData
		String [] temp = new String[tCount];
		
		for( int j=0;j<tCount;j++)
			temp[j] = jArrayData[j];
		
		jArrayData = new String[tCount];
		
		for( int j=0;j<tCount;j++)
			jArrayData[j] = temp[j];
		
		traitCount = tCount.toString();
		jArrayMaxLength = 20;
		writer = false;
		
		//	Here we initiate other data structures
		uniqueTraits = new HashSet<String>();
		Collections.addAll(uniqueTraits, jArrayData);
		
		System.out.println();
		return tCount;
	}

	
	
	/*
	 * 	Here we create the structures that we need to do the rarity calculations afterwards
	 */
	public static void nftParse(){
		
		Integer start = toInt(startingPoint);
		Integer cNum = inputIdsArray.size() + nullsArray.size();
		Integer tCount = toInt(traitCount);
		
		jArrayDataMore = new Pair[uniqueTraits.size()];
		jArrayDataMoreCount = new Integer[uniqueTraits.size()];
		setList = new ArrayList<Set<Integer>>();
		traitCounts = new ArrayList<Integer>();
		nft = new NFT[cNum];
		extras = new Integer[tCount];
		frequencies = new Integer[tCount+1];
		valueFreq = new HashMap<Pair,Integer>();
		
		
		
		@SuppressWarnings("rawtypes")
		Iterator itr;
		Integer moreIndex = 0;
		for( itr = uniqueTraits.iterator(); itr.hasNext();) {
			
			jArrayDataMore[moreIndex] = new Pair( itr.next() 
					+ "_AllNone", "" + ((moreIndex+1)*(-1)));
			jArrayDataMoreCount[moreIndex] = 0;
			moreIndex++;
		}
		
		frequencies[tCount] = 0;
		
		for( int i=0; i<tCount; i++) {
    		
			frequencies[i] = 0;
			extras[i] = 0;
		}
		
		for( int i = 0; i<inputIdsArray.size(); i++){
			
			Integer id = inputIdsArray.get(i) - start;
			
    		JSONTokener tokener = new JSONTokener(jStrings[id]);
    		JSONObject jo = new JSONObject(tokener);
    		
    		String name;
    		String tokenId;
    		String image;
    		String description;
    		String external_url;
    		String imageHash;
    		
    		try {
    		
    			name = (String) jo.get(jName);
    			
    		}catch(Exception e) {
    			
    			name = "";
    		};
    		
    		try {
    		
    			tokenId = (String) jo.get(jId);
    			
    		}catch(Exception e) {
    			
    			tokenId = "";
    		};
    		
    		try {
    		
    			image = (String) jo.get(jImage);
    			
    		}catch(Exception e) {
    			
    			image = "";
    		};
    		
    		try {
    		
    			description = (String) jo.get(jDesc);
    			
    		}catch(Exception e) {
    			
    			description = "";
    		};
    		
    		try {
    		
    			external_url = (String) jo.get(jExt);
    			
    		}catch(Exception e) {
    			
    			external_url = "";
    		};
    		
    		try {
    		
    			imageHash = (String) jo.get(jIHash);
    			
    		}catch(Exception e) {
    			
    			imageHash = "";
    		};
    		
    		JSONArray ja = (JSONArray) jo.get(jAName);
    		String [] strings = new String[tCount];
    		Pair [] pair = new Pair[tCount];
    		
    		for ( int j=0; j < tCount; j++) 
    			pair[j] = new Pair(jArrayData[j],"None");
    		
    		for ( int j=0; j < ja.length(); j++) {
    			
    			JSONObject obj = ja.getJSONObject(j);
    			String traitType =  obj.get(jType).toString();
    			strings[j] = traitType;
    			String traitValue;
    			
    			try {
    			
    				traitValue =  obj.get(jValue).toString();
    			}catch(Exception e) {
    				
    				traitValue = "None";
    			};
    			
				if(traitValue.equals("None"))
					continue;
				
    			Integer count1 = 0;
				for( int z=0; z<j; z++)
					if( strings[z].equals(traitType))
						count1++;
				
				Integer count2 = 0;
				Integer index = -1;
				for( int k=0; k<tCount; k++) 
					if( jArrayData[k].equals(traitType)) {
						
						count2++;
						if( count2 > count1) {
							
							index = k;
							break;
						}
					}
				
				pair[index].setValue(traitValue);
    		}
    		
    		for ( int j=0; j < tCount; j++) {
    			
				Pair thisPair = new Pair(pair[j].getKey(),pair[j].getValue());
				Integer f = valueFreqReturns(thisPair);
				
				if( f == 0)
					valueFreq.put(thisPair,1);
				else {
					
					valueFreqRemove(thisPair);
					valueFreq.put(thisPair,f+1);
				}
    		}
    		
    		ArrayList<String> traitsProcessed = new ArrayList<String>();
    		
    		for ( int j=0; j < tCount; j++) {
    			
    			Set<Integer> set = new HashSet<Integer>();
    			String traitType =  pair[j].getKey();
    			
    			Boolean next = false;
    			for( String string : traitsProcessed)
    				if( string.equals(traitType))
    					next = true;
    			
    			if( next)
    				continue;
    			
    			Integer count = 0;
    			Integer noneCount = 0;
    			for( int k=0; k<tCount; k++)
    				if( jArrayData[k].equals(traitType)) {
    					
    					count++;
    					if( !pair[k].getValue().equals("None")) {
    						
    						set.add(k);
    					}
    					else {
    						
    						noneCount++;
    					}
    				}
    			
    			if( noneCount == count) {
    				
    				Integer index;
    				for( int z=0; z<uniqueTraits.size(); z++) {
    					
    					index = ((z+1)*(-1));
    					
    					if( jArrayDataMore[z].getKey().equals(traitType + "_AllNone")) {
    						
    						jArrayDataMore[z].setValue( index.toString());
    						jArrayDataMoreCount[z]++;
    						set.add( index);
    						break;
    					}
    				}
    			}
    			
    			
    			
    			if( set.isEmpty())
    				continue;
    			
    			Integer index = 0;
    			for( Set<Integer> checkSet : setList) {
    				
    				if( checkSet.equals(set)) {
    					
    					Integer integer = traitCounts.get(index);
    					traitCounts.set(index,integer+1);
    					traitsProcessed.add(traitType);
    					break;
    				}
    				index++;
    			}
    			
    			if( index == setList.size()) {
    				
    				traitsProcessed.add(traitType);
    				traitCounts.add(1);
    				setList.add(set);
    			}
    		}
    		
    		Integer counter = 0;
    		for( int j=0; j<tCount; j++)
    			if(!pair[j].getValue().equals("None"))
    				counter++;
    		
    		frequencies[counter] += 1;
    		
    		nft[id] = new NFT( id+start, counter, name, tokenId, image, 
    					description, external_url, imageHash, pair);
    		
    		getProgress(id,start,cNum,-1,"Analyzing ... ");
		}
		
		for( int i = 0; i<nullsArray.size(); i++){
			
			Integer id = nullsArray.get(i) - start;
			nft[id] = null;
		}
		
		System.out.println();
		writer = false;
	}
	
	
	
	/*
	 * 	This method prints a portion of the output and
	 * 	lets the user read more while the user gives inputs.
	 */
	public static void printHandler(Object [] obj) {
		
		Integer counter = 0;
    	String buffer = null;
    	String inputName = null;
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		for( int i=0; i<obj.length; i++) {
			
			System.out.println(obj[i]);
			counter++;
			
			if(counter == 100) {
				
				counter = 0;
				
				buffer = "\nTo print more records press ENTER\n";
				buffer +="To go back give a randon input.";
				
				System.out.println(buffer);
				
				inputName = scanner.nextLine();
				
				System.out.println();
				
				if(!inputName.equals(""))
					break;
			}
		}
	}
	
	
	
	/*
	 * 	This method prints the jStrings array.
	 */
	public static void printJStrings() {
		
		System.out.println();
    	System.out.println("\t\t JStrings [String]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis array stores every single nfts' metadata in json format.");
    	System.out.println();
    	
    	if(jStrings != null)
    		printHandler(jStrings);
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the nullsArray array.
	 */
	public static void printNullsArray() {
		
		System.out.println();
    	System.out.println("\t\t NullsArray ArrayList<Integer>");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis array stores all the null IDs. Null IDs are"
    			+ "\nnonexistent IDs between startingPoint and endingPoint.");
    	System.out.println();
    	
    	if(nullsArray != null && nullsArray.size() != 0) {
	    	
	    	String output = nullsArray.toString();
	    	
			System.out.println("IDs : " + output);
			System.out.println();
			
			System.out.println("nullsArray.size() = " + nullsArray.size());
    		System.out.println();
		}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the inputIdsArray array.
	 */
	public static void printInputIdsArray() {
		
		System.out.println();
    	System.out.println("\t\t InputIdsArray ArrayList<Integer>");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis array stores the input Ids to be processed from"
    			+ "\nour procedures. Prints the size of the array too.");
    	System.out.println();
    	
    	if(inputIdsArray != null && inputIdsArray.size() != 0) {
	    	
	    	String output = inputIdsArray.toString();
			
			System.out.println("IDs : " + output);
			System.out.println();
			
			System.out.println("inputIdsArray.size() = " + inputIdsArray.size());
    		System.out.println();
		}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the jArrayData array.
	 */
	public static void printTraitArrays() {
		
		String output = "";
		
    	output += "\nTrait Arrays : UniqueTraits, JArrayData, JArrayDataMore + count.\n";
    	output += "\n\t UniqueTraits Set<String>\n\n";
    	output += "Description :\nThis array stores all the unique trait_types of the collection."
    			+ "\nDoes not include the extra traits that have the same name with other basic traits.\n\n";
    	output += String.format("%-6s %s", "Index", "Value");
    	output += "\n\n";
    	
    	@SuppressWarnings("rawtypes")
		Iterator itr = uniqueTraits.iterator();
    	if( uniqueTraits != null)
    		for( int i=0; i<uniqueTraits.size(); i++)
    			output += String.format("%-6s %s", i, itr.next() + "\n");
    	else
    		output += "null\n";
    	
    	output += "\n\n\t JArrayData [String]\n\n";
    	output += "Description :\nThis array stores all the trait_types of the collection."
    			+ "\nIncludes the extra traits that have the same name with other basic traits."
    			+ "\nFor example if there are three same name trait_type that means that the"
    			+ "\nfirst one is the basic trait and all the others are the extra ones."
    			+ "\nBy having three same name traits in a collection it means that there "
    			+ "\nis at least one nft with three same name traits with value (not \"None\").\n\n";
    	output += String.format("%-6s %s", "Index", "Value");
    	output +="\n\n";
    	
    	for( int i=0; i<jArrayData.length; i++)
    		output += String.format("%-6s %s", i, jArrayData[i] + "\n");
    		
    	output += "\n\t JArrayDataMore Pair, JArrayDataMoreCount [Integer]\n\n";
    	output += "Description :\nPairs' first string stores all the unique \"None\" trait_types "
    			+ "\n( within one nft all the same name traits are \"None\"s) & the second string"
    			+ "\nstores the set identifier (this represents specific trait and its position within"
    			+ "\nthe json file). The position is significant cause it informs you what it counts (see "
    			+ "\njArrayData description for more). If the trait does not have extras it represents "
    			+ "\nthat the unique trait is \"None\" itself. The data structure is an array that counts "
    			+ "\nthe different cases of jArrayDataMore structure.\n\n";
    	output += String.format("%-6s %s", "Index", String.format("%-25s %s",
    			"Value", String.format("%-5s %s", "Idnt", "Count")));
    	output += "\n\n";
    	
    	for( int i=0; i<jArrayDataMore.length; i++)
    		output += String.format("%-6s %s", i, String.format("%-25s %s", 
    				jArrayDataMore[i].getKey(), String.format("%-5s %s", 
    				jArrayDataMore[i].getValue(), jArrayDataMoreCount[i] + "\n")));
    	
    	System.out.println(output);
	}
	
	
	
	/*
	 * 	This method prints the setList array.
	 */
	public static void printSetList() {
		
		System.out.println();
    	System.out.println("\t\t SetList ArrayList<Set<Integer>>");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis array contains all different combinations of same "
    			+ "\ntrait_type (duplicates: original + extras) sets of not "
    			+ "\n\"None\" values plus their sum of appearences.");
    	
    	int c = 0;
		Integer [] arr;
		for( Set<Integer> set : setList) {
			
			if( !set.isEmpty()) {
				
				arr = new Integer[set.size()];
				arr = set.toArray(new Integer[set.size()]);
				Integer equalTraits = arr.length;
				
				if( arr[0] >= 0) {
					
					extras[arr[equalTraits-1]] = traitCounts.get(c);
					
					System.out.println();
					System.out.println(jArrayData[arr[equalTraits-1]]);
					System.out.println(set + " : " + extras[arr[equalTraits-1]]);
					
				}
				else {
					
					System.out.println();
					System.out.println(jArrayDataMore[(arr[0]+1)*(-1)].getKey());
					System.out.println(set + " : " + traitCounts.get(c));
				}
				
				c++;
			}
		}

    	System.out.println("\n");
    	
		//	Another representation, the whole picture
    	if( setList != null) {
    		
    		Integer length = jArrayData.length 
    					+ jArrayDataMore.length;
    		
    		Integer [] counts = new Integer[length];
	    	String [] outputs = new String[length];
	    	String [] sets = new String[length];
	    	
	    	for( int i=0; i<length; i++) {
	    		
	    		sets[i] = "{";
	    		counts[i] = 0;
	    	}
	    	
    		for( int i=0; i<setList.size(); i++) {
    			
    			ArrayList<Integer> array = new ArrayList<Integer>(setList.get(i));
    			
    			String name;
    			Integer counter = 0;
    			Integer size = array.size();
    			Integer index = array.get(0);
    			
    			if( index >= 0 ) {
    				
    				index = array.get(0);
    				name = jArrayData[index];
        			
    				for( int j=0; j<length; j++) {
        				
        				if( jArrayData[j].equals(name)) {
        					
        					counter++;
        					if( counter == size) {
        						
        						index = j;
        						break;
        					}
        				}
        			}
    			}
    			else {
    			
    				index = (index+1)*(-1);
    				name = jArrayDataMore[index].getKey();
    				index = jArrayData.length + index;
    			}
    			
    			if( sets[index].equals("{")) {
    				
    				sets[index] += array.toString();
    				
    			}
    			else {
    				
    				sets[index] += "," + array.toString();
    				
    			}
    			
    			sets[index] += ":" + traitCounts.get(i);
    			counts[index] += traitCounts.get(i);
    		}
    		
    		for( int i=0; i<length; i++) {
    			
    			sets[i] += "}";
    			if( i < jArrayData.length)
    				outputs[i] = String.format("%25s %s", jArrayData[i], 
    					String.format("%-5s %s", " " + counts[i], " = " + sets[i]));
    			else
    				outputs[i] = String.format("%25s %s", 
    					jArrayDataMore[i-jArrayData.length].getKey(), 
    					String.format("%-5s %s", " " + counts[i], " = " + sets[i]));
    		}
    		
    		System.out.println( String.format("%25s %s", "jArrayData(More) Names"
    				, String.format("%5s %7s", "Sums", "Sets")));
    		System.out.println("--------------------------------------------");
    		printHandler(outputs);
    	}
    	else
    		System.out.println("null\n");
    	
    	System.out.println();
	}
	
	
	
	/*
	 * 	This method prints the nft array.
	 */
	public static void printNft() {
		
		System.out.println();
    	System.out.println("\t\t Nft [NFT]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis Class sums up all the metadata needed to form an nft item"
    			+ "\nand the nft array is the set of all the nfts within the collection.");
    	System.out.println();
    	
    	if(nft != null) {
    		
	    	Integer length = nft.length;
	    	String [] outputs = new String[length];
	    	
	    	for ( int i=0; i<length; i++)
	    		if(nft[i] != null)
	    			outputs[i] = nft[i].printNft() + "\n";
	    		else
	    			outputs[i] = "\n{null}\n";
	    	
	    	printHandler(outputs);
			System.out.println();
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the extras array.
	 */
	public static void printExtras() {
		
		System.out.println();
    	System.out.println("\t\t Extras [Integer]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis array represents all the counts of not \"None\" "
    			+ "\n(type,value) pairs. When there are more same named types"
    			+ "\nthis means that the second in the list counts all the"
    			+ "\nnfts that have exactly two same type not \"None\" pairs, etc.");
    	System.out.println();
    	
    	if(extras != null) {
    		
    		Integer length = extras.length;
	    	String [] outputs = new String[length];
	    	
			for( int i=0; i<length; i++)
				outputs[i] = "( " + jArrayData[i] + ", " + extras[i] + ")";
	    	
    		printHandler(outputs);
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the frequencies array.
	 */
	public static void printFrequencies() {
		
		System.out.println();
    	System.out.println("\t\t Frequencies [Integer]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis array represents how many nfts exist with zero traits,"
    			+ "\none trait, two traits etc.. within the collection.");
    	System.out.println();
    	
    	if(frequencies != null) {
	    	
    		Integer length = frequencies.length;
	    	String [] outputs = new String[length];
	    	
			for( int i=0; i<length; i++)
				outputs[i] = "( #trait: " + i + ", " + frequencies[i] + ")";
	    	
	    	printHandler(outputs);
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the counts of the different trait_types.
	 */
	public static void printValueCounts() {
			
		System.out.println();
    	System.out.println("\t\t ValueCounts [String]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis method prints the counts of the different trait_values.");
    	System.out.println();
		
		if(valueFreq == null) {
			
			System.out.println("null\n");
			return;
		}
		
		Set<String> traitSet = new HashSet<String>();
		
		Collections.addAll(traitSet, jArrayData);
		String [] originalTraits = traitSet.toArray(new String[traitSet.size()]);
		Integer differentTraits = originalTraits.length;
		Integer [] countDiffTraits = new Integer[differentTraits];
		
		for( int i=0; i<differentTraits; i++)
			countDiffTraits[i] = 0;
		
		for ( Pair pair : valueFreq.keySet()) {
			
			String trait = pair.getKey();
			
			for( int i=0; i<differentTraits; i++)
				if( originalTraits[i].equals(trait))
					countDiffTraits[i]++;
		}
		
		String [] outputs = null;
		
    	if(differentTraits != 0) {
	    	
    		outputs = new String[differentTraits];
    		
			for ( int i=0; i<differentTraits; i++)
				outputs[i] = "( " + originalTraits[i] + ", " + countDiffTraits[i] + ")";
			
			Arrays.sort(outputs);
			printHandler(outputs);
			System.out.println();
    	}
    	else
    		System.out.println("null\n");
    	
	}
	
	
	
	/*
	 * 	This method prints the counts of the different pairs
	 * 	and the counts of the different pairs with the same key.
	 */
	public static void printPairCounts() {
		
		System.out.println();
    	System.out.println("\t\t PairCounts [Pair]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis method prints the different pairs and the counts of the "
    			+ "\ndifferent pairs with the same key ( with and without \"None\"s)."
    			+ "\nThe former always add up to the collection number unless"
    			+ "\nthere are traits with multiple appearances within an nft.");
    	System.out.println();
		
		if(valueFreq == null) {
			
			System.out.println("null\n");
			return;
		}
		
		Set<String> traitSet = new HashSet<String>();
		Collections.addAll(traitSet, jArrayData);
		String [] originalTraits = traitSet.toArray(new String[traitSet.size()]);
		Integer differentTraits = originalTraits.length;
		Integer [] countDiffTraits = new Integer[differentTraits];
		Integer [] countDiffTraitsWithoutNone = new Integer[differentTraits];
		
		for( int i=0; i<differentTraits; i++) {
			
			countDiffTraitsWithoutNone[i] = 0;
			countDiffTraits[i] = 0;
		}
		
		for ( Pair pair : valueFreq.keySet()) {
			
			String key = pair.getKey();
			String value = pair.getValue();
			
			for(int i=0; i<differentTraits; i++) {
				
				if( originalTraits[i].equals(key)) {
					
					countDiffTraits[i] += valueFreqReturns(pair);
					if( !value.equals("None"))
						countDiffTraitsWithoutNone[i] += valueFreqReturns(pair);
					break;
				}
			}
				
		}
		
    	if(differentTraits != 0) {
	    	
    		String [] outputs = new String[differentTraits];
    		
    		System.out.println( String.format("%-20s %s", "Trait_type      Sum : ", 
    				String.format("%-4s %s", "All", "Sans None\n"
    						+ "------------------------------------")));
    		
			for ( int i=0; i<differentTraits; i++)
				
				outputs[i] = String.format("%-20s %s", "( " + originalTraits[i] 
						+ ", ", String.format("%-6s %s", countDiffTraits[i] 
						+ ", ", countDiffTraitsWithoutNone[i] + ")"));
				
			Arrays.sort(outputs);
			printHandler(outputs);
			System.out.println();
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the valueFreq array.
	 */
	public static void printValueFreq() {
		
		System.out.println();
    	System.out.println("\t\t ValueFreq Map<Pair, Integer>");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis data structure stores all the (trait_type,value)"
    			+ "\npairs and their total sum of their total appearences.");
    	System.out.println();
    	
    	if(valueFreq != null) {
	    	
    		Integer length = valueFreq.keySet().size();
	    	String [] outputs = new String[length];
	    	
	    	int i = 0;
	    	
			for ( Pair pair : valueFreq.keySet()) {
				
				outputs[i] = "( " + pair.printPair() + ", " + valueFreqReturns( pair) + ")";
				i++;
			}
			
			Arrays.sort(outputs);
			printHandler(outputs);
			System.out.println();
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the valueFreq array.
	 */
	public static void printRankings() {
		
		System.out.println();
    	System.out.println("\t\t Rankings Map<Integer, Double>");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis tuple represents the Ids one the left and their score"
    			+ "\non the right. All the tuples represent the whole collection."
    			+ "\n`They are sorted based on the Ids number.");
    	System.out.println();
    	
    	if(rankings != null) {
	    	
    		Integer length = rankings.keySet().size();
	    	String [] outputs = new String[length];
	    	
	    	int i = 0;
	    	
			for ( Integer integer : rankings.keySet()) {
				
				outputs[i] = "( " + integer + ", " + rankings.get(integer) + ")";
				i++;
			}
			
			printHandler(outputs);
			System.out.println();
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the pairArray array.
	 */
	public static void printPairArray() {
		
		System.out.println();
    	System.out.println("\t\t PairArray [Pair]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis tuple represents the Ids one the right and their score"
    			+ "\non the left. All the tuples represent the whole collection."
    			+ "\n`They are sorted based on the Score value.");
    	System.out.println();
    	
    	if(pairArray != null) {
	    	
    		Integer length = pairArray.size();
	    	String [] outputs = new String[length];
	    	
	    	for ( int i=0; i<length; i++)
				outputs[i] = pairArray.get(i).printPair();
	    	
	    	printHandler(outputs);
			System.out.println();
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints the nftCollection array.
	 */
	public static void printNftColleciton() {
		
		System.out.println();
    	System.out.println("\t\t NftCollection [NFTCollection]");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThis data structure stores all Collections metadata. The"
    			+ "\nNFTCollection array represents the whole cMetadata file.");
    	System.out.println();
    	
    	if(nftCollection != null) {
	    	
    		Integer length = nftCollection.length;
	    	String [] outputs = new String[length];
	    	
	    	for ( int i=0; i<length; i++)
				outputs[i] = nftCollection[i].printNftCollection();
	    	
	    	printHandler(outputs);
			System.out.println();
    	}
    	else
    		System.out.println("null\n");
	}
	
	
	
	/*
	 * 	This method prints all the NFTCTools variables.
	 */
	public static void printVariables() {
		
		System.out.println();
    	System.out.println("\t\t NFTCTools All Variables");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThese data are all the variables that NFTCTools Class has."
    			+ "\nCurrent values are written too.");
    	System.out.println();
    	
    	String output = "(\n\tcontract:\"" + contract + "\",\n\tcollectionName:\"" + collectionName 
    			+ "\",\n\tstartingPoint:\"" + startingPoint + "\",\n\tendingPoint:\"" + endingPoint
    			+ "\",\n\turlPrefix:\"" + urlPrefix + "\",\n\turlPostfix:\"" + urlPostfix 
    			+ "\",\n\ttraitCount:\"" + traitCount + "\",\n\tjName:\"" + jName + "\",\n\tjId:\"" 
    			+ jId + "\",\n\tjImage:\"" + jImage + "\",\n\tjDesc:\"" + jDesc 
    			+ "\",\n\tjExt:\"" + jExt + "\",\n\tjIHash:\"" + jIHash + "\",\n\tjAName:\"" 
    			+ jAName + "\",\n\tjType:\"" + jType + "\",\n\tjValue:\"" + jValue 
    			+ "\",\n\tcores:\"" + cores + "\",\n\tjArrayMaxLength:\"" + jArrayMaxLength 
    			+ "\",\n\twriter:\"" + writer + "\")";
    	
    	System.out.println(output + "\n");
	}
	
	
	
	/*
	 * 	This method prints all the NFTCTools constants.
	 */
	public static void printConstants() {
		
		System.out.println();
    	System.out.println("\t\t NFTCTools All Constants");
    	System.out.println();
    	System.out.println("Description :"
    			+ "\nThese data are all the constants that NFTCTools Class has."
    			+ "\nAll values are written too.");
    	System.out.println();
    	
    	String output = "(\n\tcMFileName:\"" + cMFileName + "\",\n\tcAMeta:\"" + cAMeta 
    		    + "\",\n\tcContract:\"" + cContract + "\",\n\tcName:\"" + cName 
    			+ "\",\n\tcStartPoint:\"" + cStartPoint + "\",\n\tcEndPoint:\"" + cEndPoint 
    			+ "\",\n\tcUrlPrefix:\"" + cUrlPrefix + "\",\n\tcUrlPostfix:\"" + cUrlPostfix 
    			+ "\",\n\tcTraitCount:\"" + cTraitCount + "\",\n\tjNameName:\"" + jNameName 
    			+ "\",\n\tjTokenId:\"" + jTokenId + "\",\n\tjImageName:\"" + jImageName 
    			+ "\",\n\tjDescName:\"" + jDescName + "\",\n\tjExternal:\"" + jExternal 
    			+ "\",\n\tjImageHash:\"" + jImageHash + "\",\n\tjArrayName:\"" + jArrayName 
    			+ "\",\n\tjTraitType:\"" + jTraitType + "\",\n\tjTraitValue:\"" + jTraitValue 
    			+ "\",\n\tcFolderName:\"" + cFolderName + "\",\n\trFolderName:\"" + rFolderName 
    			+ "\",\n\tqFolderName:\"" + qFolderName + "\")";
    	
    	System.out.println(output + "\n");
	}



	/*
	 *	This method returns a String of an ArrayList.
	 *
	public static String arrayListToString( ArrayList<Integer> list) {
		
		return list.stream().map(Object::toString).collect(Collectors.joining(", "));
	}
	//*/
	
	
	
}