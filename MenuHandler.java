package com.nftrarity.com.nftrarity.maven.eclipse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * 	In this Class we handle all Menus of our program.
 */
public class MenuHandler {
	
	private Time getTime;
	private Time startTime;
	private Scanner scanner;
	private String printBuffer;
	private String errorBuffer;
	private Integer input;
	private Integer index;
	private String inputName;
	
	private Boolean mainMenu;
	private Boolean mainMenuAdd;
	private Boolean mainMenuCheck;
	private Boolean mainMenuCreate;
	private Boolean mainMenuAnalysis;
	private Boolean collectionMenu;
	private Boolean exit;
	
	
	MenuHandler(){
		
		startTime = new Time();
		getTime = null;
		scanner = new Scanner(System.in);
		printBuffer = null;
		errorBuffer = null;
		input = null;
		index = null;
		inputName = null;
		
		mainMenu = true;
		mainMenuAdd = false;
		mainMenuCheck = false;
		mainMenuCreate = false;
		mainMenuAnalysis = false;
		collectionMenu = false;
		exit = false;
		
		handler();
	}
	
	
	private boolean isInteger(String str) {
		
        return str.matches("-?\\d+");
    }
	
	
	private void println(String message) {
		
		System.out.println(message);
	}
	
	
	private void print(String message) {
		
		System.out.print(message);
	}
	
	
	private void printTime(Time time) {
		
		Time now = new Time();
    	print("Process completed in : ");
    	Time.pInterval(Time.interval(time.getTime(),now.getTime()));
	}
	
	
	private void menu(Integer choices) {
		
		println(printBuffer);
		
		do {
	    	
	    	inputName = scanner.nextLine();
	    	
	    	try {
	    		
	    		input = Integer.parseInt(inputName);
	    		
	    	}catch(Exception e) {
	    		
				println(printBuffer);
				println(errorBuffer);
	    	}
			 
    	}while( !isInteger(inputName) || input < 0 || input > choices);
		
	}
	
	
	private void checkFiles(Integer index) {
		
		try {
			
			Boolean change = false;
			ArrayList<Integer> oldNullsArray;
			
			if( NFTCTools.nullsArray != null)
				oldNullsArray = new ArrayList<Integer>(NFTCTools.nullsArray);
			else
				oldNullsArray = null;
			
			System.out.println("\n" + NFTCTools.collectionName);
			
			NFTCTools.checkFiles();
			NFTCTools.readCollection();
			NFTCTools.initMetadata();
			
			int i=0;
			if( oldNullsArray != null)
				for( Integer integer : oldNullsArray) {
					
					if( NFTCTools.nullsArray.get(i) != integer) {
						
						change = true;
						break;
					}
						
					i++;
				}
			
			if(change) {
				
				NFTCTools.nftParse();
		    	NFTCTools.rarityCalculation(false,null);
		    	NFTCTools.cRarityScore();
		    	println("");
			}
			
			NFTCTools.addNftCollection(index);
			NFTCTools.updateCMetadata();
			
			printBuffer += "\n" + NFTCTools.collectionName + " : " + NFTCTools.report();
			
		} catch (Exception e) {
			
			System.out.println(" Error, couldn't find the  \"" 
					+ NFTCTools.collectionName + "\" collection!");
			
		}
	}
	
	/*
	private Integer pairSetCount( Pair [] pairArray, Integer i) {
		
		for( int k=0; k<pairArray.length; k++) {
			
			for( int j=0; j<NFTCTools.nft[i].getAtt().length; j++) {
				
				if( pairArray[k] != null && NFTCTools.nft[i] != null ) {
					
					if( !pairArray[k].getKey().equals("") && !pairArray[k].getValue().equals(""))
						if( pairArray[k].getKey().equals(NFTCTools.nft[i].getAtt(j).getKey())
							&& pairArray[k].getValue().equals(NFTCTools.nft[i].getAtt(j).getValue())) 
							return i;
					else if( !pairArray[k].getKey().equals(""))
						if( pairArray[k].getKey().equals(NFTCTools.nft[i].getAtt(j).getKey())) 
							return i;
					else 
						if( pairArray[k].getValue().equals(NFTCTools.nft[i].getAtt(j).getValue()))
							return i;
				}
			}
		}
		
		return null;
	}*/
	
	
	private Integer intTNumSetCount( ArrayList<Integer> intArray, Integer i){
		
		for( int k=0; k<intArray.size(); k++) {
			
			if( intArray.get(k) == NFTCTools.nft[i].getTraitNum()) {
				
				return i;
			}
		}
			
		return null;
	}
	
	
	public static String stringToTitleCase(String text) {
	    if (text == null || text.isEmpty()) {
	        return text;
	    }

	    StringBuilder converted = new StringBuilder();

	    boolean convertNext = true;
	    for (char ch : text.toCharArray()) {
	        if (Character.isSpaceChar(ch)) {
	            convertNext = true;
	        } else if (convertNext) {
	            ch = Character.toTitleCase(ch);
	            convertNext = false;
	        } else {
	            ch = Character.toLowerCase(ch);
	        }
	        converted.append(ch);
	    }

	    return converted.toString();
	}
	
	
	@SuppressWarnings("unused")
	public void handler() {
		
		do {
			
			NFTCTools.setState(-1,true);
			NFTCTools.updateNftCollection();
			
			if(mainMenu) {
				
				Integer length;
		    	String [] names;
		    	Integer counter = 1;
		    	
				printBuffer = "\n";
				printBuffer += String.format("\n%-2s %s", "","NFT Rarity Tools\n\n");
				printBuffer += String.format("%-4s %s", "","MAIN MENU\n\n");
		    	
				if( NFTCTools.nftCollection != null){
		    		
		    		length = NFTCTools.nftCollection.length;
		    		names = new String[length];
		    		names = NFTCTools.readNames();
		    		
		    		for( int i=0; i<length; i++) {
			    		
		    			printBuffer += String.format("%-3s %s", counter + ")", "-> " + names[i] + "\n");
			    		counter++;
			    	}
		    	}
				else
		    		length = 0;
		    	
				if( length != 0) {
					
					printBuffer += String.format("%-3s %s", counter + ")", " " + "Check Files\n");
					counter++;
				}
				
		    	printBuffer += String.format("%-3s %s", counter + ")", " " + "Add New Collection\n");
		    	counter++;
		    	printBuffer += String.format("%-3s %s", counter + ")", " " + "Create New Collection\n");
		    	counter++;
		    	printBuffer += String.format("%-3s %s", counter + ")", " " + "Dynamic Analysis\n");
		    	printBuffer += String.format("%-3s %s", "0)", " " + "Exit\n");
		    	
		    	errorBuffer = "Error, your input is not valid!!\n";
			    errorBuffer += "Please enter a value between [0-" + counter + "]!\n";
			    		
				menu(counter);
		    	mainMenu = false;
				
		    	if(input == 0)
					exit = true;
		    	else if( input < counter-3) {
					
					index = length - input;
					collectionMenu = true;
				}
				else if( input == counter-3)
					mainMenuCheck = true;
				else if( input == counter-2) {
					
					index = 0;
					mainMenuAdd = true;
				}
				else if( input == counter-1)
					mainMenuCreate = true;
				else
					mainMenuAnalysis = true;
				
			}
			else if(mainMenuCheck) {
				
				getTime = new Time();
				
				Integer length;
				printBuffer = "";
				
				if( NFTCTools.nftCollection != null){
					
					length = NFTCTools.nftCollection.length;
					for( int i=length-1; i>=0; i--) {
						
						NFTCTools.setState(i,true);
						checkFiles(i);
					}
					
					println(printBuffer);
					printTime(getTime);
					println("\"Checking files\" has been completed successfully!\n");
					
				}
				else
					println("There are no collecitons to check!");
				
				mainMenuCheck = false;
				mainMenu = true;
				
			}
			else if(mainMenuAdd) {
				
				do {
					
					printBuffer = "\n";
					printBuffer += String.format("%-4s %s", "","NEW COLLECTION MENU\n\n");
					printBuffer += String.format("%-3s %s", 1 + ")", String.format("%-40s %s"," " 
							+ "Add contract address \t \"" + NFTCTools.cContract + "\"", " (" + NFTCTools.contract + ")\n"));
					printBuffer += String.format("%-3s %s", 2 + ")", String.format("%-40s %s"," " 
							+ "Add collections' name \t \"" + NFTCTools.cName + "\"", "  (" + NFTCTools.collectionName + ")\n"));
					printBuffer += String.format("%-3s %s", 3 + ")", String.format("%-40s %s"," " 
							+ "Add ID starting point \t \"" + NFTCTools.cStartPoint + "\"", "  (" + NFTCTools.startingPoint + ")\n"));
					printBuffer += String.format("%-3s %s", 4 + ")", String.format("%-40s %s"," " 
							+ "Add ID ending point \t \"" + NFTCTools.cEndPoint + "\"", "(" + NFTCTools.endingPoint + ")\n"));
					printBuffer += String.format("%-3s %s", 5 + ")", String.format("%-40s %s"," " 
							+ "Add URI page prefixlink \t \"" + NFTCTools.cUrlPrefix + "\"", "    (" + NFTCTools.urlPrefix + ")\n"));
					printBuffer += String.format("%-3s %s", 6 + ")", String.format("%-40s %s"," " 
							+ "Change URI page postfixlink \"" + NFTCTools.cUrlPostfix + "\"", "    (" + NFTCTools.urlPostfix + ")\n"));
					printBuffer += String.format("%-3s %s", 7 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jNameName + "\" ", "(" + NFTCTools.jName + ")\n"));
					printBuffer += String.format("%-3s %s", 8 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jTokenId + "\" ", "(" + NFTCTools.jId + ")\n"));
					printBuffer += String.format("%-3s %s", 9 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jImageName + "\" ", "(" + NFTCTools.jImage + ")\n"));
					printBuffer += String.format("%-3s %s", 10 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jDescName + "\" ", "(" + NFTCTools.jDesc + ")\n"));
					printBuffer += String.format("%-3s %s", 11 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jExternal + "\" ", "(" + NFTCTools.jExt + ")\n"));
					printBuffer += String.format("%-3s %s", 12 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jImageHash + "\" ", "(" + NFTCTools.jIHash + ")\n"));
					printBuffer += String.format("%-3s %s", 13 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jArrayName + "\" ", "(" + NFTCTools.jAName + ")\n"));
					printBuffer += String.format("%-3s %s", 14 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jTraitType + "\" ", "(" + NFTCTools.jType + ")\n"));
					printBuffer += String.format("%-3s %s", 15 + ")", String.format("%-40s %s"," " 
							+ "Change meta-tag     \t \"" + NFTCTools.jTraitValue + "\" ", "(" + NFTCTools.jValue + ")\n"));
					printBuffer += "----------------------------------------------------------------------\n";
					printBuffer += String.format("%-3s %s", 16 + ")", " " + "Complete!\n");
					printBuffer += String.format("%-3s %s", 0 + ")", " " + "back\n");
					
					errorBuffer = "Error, your input is not valid!!\n";
				    errorBuffer += "Please enter a value between [0-16]!\n";
				    			
					menu(16);
				    
					if( input == 1) {
						
						print("Write the contract address : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.length() > 1 && (!(inputName.length() == 42) 
											|| !inputName.substring(0,2).equals("0x"))) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please enter the correct address (42 char long & starting with \"0x\").\n");
							print("Please re-write the contract address : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.contract;
								break;
							}
						}
						
						NFTCTools.contract = inputName;
					}
					else if( input == 2) {
						
						print("Write the collection name : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.matches("\\d+")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please enter a correct name (no number & at least 1 char long).\n");
							print("Please re-write the collection name : ");
							inputName = scanner.nextLine();
							
							if( inputName.equals("")) {
								
								inputName = NFTCTools.collectionName;
								break;
							}
						}
					
						NFTCTools.collectionName = inputName;
					}
					else if( input == 3) {
						
						print("Write the collection starting ID number : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( !inputName.matches("\\d+")){
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please enter an integer number.\n");
							print("Please re-write the collection starting ID number : ");
							inputName = scanner.nextLine();
							
							if( inputName.equals("")) {
								
								inputName = NFTCTools.startingPoint;
								break;
							}
						}
							
						NFTCTools.startingPoint = inputName;
					}
					else if( input == 4) {
						
						print("Write the collection ending ID number : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( !inputName.matches("\\d+") || !(Integer.parseInt(inputName)>0)){
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please enter a positive number.\n");
							print("Please re-write the collection ending ID number : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.endingPoint;
								break;
							}
						}
							
						NFTCTools.endingPoint = inputName;
					}
					else if( input == 5) {
						
						print("Write the collection prefix link : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( !(inputName.length() >= 14 && (inputName.substring(0,8).equals("https://")
								|| inputName.substring(0,7).equals("http://")))) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please enter the correct prefix link (\"http(s)://\"..).\n");
							print("Please re-write the collection prefix link : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.urlPrefix;
								break;
							}
						}
						
						NFTCTools.urlPrefix = inputName;
					}
					else if( input == 6) {
						
						print("Write the collection postfix link : ");
						inputName = scanner.nextLine();
						
						while( inputName.length() > 0 && !inputName.substring(0,1).equals(".")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please enter the correct postfix link (empty or starts with \".\").\n");
							print("Please re-write the collection postfix link : ");
							inputName = scanner.nextLine();
						}
						
						NFTCTools.urlPostfix = inputName;
					}
					else if( input == 7) {
						
						print("Write the collection metadata \"name\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"name\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jName;
								break;
							}
						}
						
						NFTCTools.jName = inputName;
					}
					else if( input == 8) {
						
						print("Write the collection metadata \"tokenId\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"tokenId\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jId;
								break;
							}
						}
						
						NFTCTools.jId = inputName;
					}
					else if( input == 9) {
						
						print("Write the collection metadata \"image\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"image\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jImage;
								break;
							}
						}
						
						NFTCTools.jImage = inputName;
					}
					else if( input == 10) {
						
						print("Write the collection metadata \"description\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"description\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jDesc;
								break;
							}
						}
						
						NFTCTools.jDesc = inputName;
					}
					else if( input == 11) {
						
						print("Write the collection metadata \"external_url\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"external_url\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jExt;
								break;
							}
						}
						
						NFTCTools.jExt = inputName;
					}
					else if( input == 12) {
						
						print("Write the collection metadata \"imageHash\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"imageHash\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jIHash;
								break;
							}
						}
						
						NFTCTools.jIHash = inputName;
					}
					else if( input == 13) {
						
						print("Write the collection metadata \"attributes\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"attributes\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jAName;
								break;
							}
						}
						
						NFTCTools.jAName = inputName;
					}
					else if( input == 14) {
						
						print("Write the collection metadata \"trait_type\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"trait_type\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jType;
								break;
							}
						}
						
						NFTCTools.jType = inputName;
					}
					else if( input == 15) {
						
						print("Write the collection metadata \"value\" tag : ");
						inputName = scanner.nextLine();

						if( inputName.equals(""))
							continue;
						
						while( inputName.substring(0,1).equals(" ")) {
							
							println("\nError, input \"" + inputName + "\" is not valid!!");
							println("Please do not start with spaces.\n");
							print("Please re-write the collection metadata \"value\" tag : ");
							inputName = scanner.nextLine();

							if( inputName.equals("")) {
								
								inputName = NFTCTools.jValue;
								break;
							}
						}
						
						NFTCTools.jValue = inputName;
					}
					else if( input == 16) {
						
						if( NFTCTools.contract.equals("None") || NFTCTools.collectionName.equals("None") 
								|| NFTCTools.startingPoint.equals("None") || NFTCTools.endingPoint.equals("None") 
								|| NFTCTools.urlPrefix.equals("None")) {
							
							println("Error, the new collection is not ready to be written!");
							println("The \"Add\" variables from the menu are requiered information!!");
							println("Collection contract address, name, starting/ending id & URI prefix link needs to filled out!!!");
						}
						else {
							
							getTime = new Time();
							NFTCTools.checkFiles();
							
							printBuffer = String.format("%-4s %s", "", "\nDo you want to download the colleciton now?\n");
							printBuffer += String.format("%-3s %s", "1)", " Yes");
							printBuffer += String.format("%-3s %s", "\t0)", " No\n");
					    	
							errorBuffer = "";
							
							menu(1);
							
							if( input == 1) {
								
								NFTCTools.readCollection();
								NFTCTools.initMetadata();
							}
							
							NFTCTools.addNftCollection(-1);
							NFTCTools.updateCMetadata();
							printBuffer = "\n" + NFTCTools.collectionName + " : " + NFTCTools.report();
							println("\n" + printBuffer);
							printTime(getTime);
							input = -1;
							break;
						}
					}
			    	
				}while( input != 0);	//never happens
				
				mainMenuAdd = false;
				
				if( input == 0)
					mainMenu = true;
				else
					collectionMenu = true;
			}
			else if(mainMenuCreate) {
				
				System.out.println("This function has not been programmed yet!");
				
				mainMenuCreate = false;
				mainMenu = true;
			}
			else if(mainMenuAnalysis) {
				
				System.out.println("This function has not been programmed yet!");
				
				mainMenuAnalysis = false;
				mainMenu = true;
			}
			else if(collectionMenu) {
				
				NFTCTools.setState(index,false);
				
				do {
					
					printBuffer = "\n";
					printBuffer += String.format("%-4s %s", "", "\"" + NFTCTools.collectionName + "\" MENU\n\n");
					printBuffer += String.format("%-3s %s", 1 + ")", "Check   Files \n");
					printBuffer += String.format("%-3s %s", 2 + ")", "Update  Files \n");
					printBuffer += String.format("%-3s %s", 3 + ")", "Update  Metadata \n");
					printBuffer += String.format("%-3s %s", 4 + ")", "Change  File \n");
					printBuffer += String.format("%-3s %s", 5 + ")", "Add js  File \n");
					printBuffer += String.format("%-3s %s", 6 + ")", "Delete  File \n");
					printBuffer += String.format("%-3s %s", 7 + ")", "Static  Analysis \n");
					printBuffer += String.format("%-3s %s", 8 + ")", "Dynamic Simulation \n");
					printBuffer += String.format("%-3s %s", 9 + ")", "Query   Collection \n");
					printBuffer += String.format("%-3s %s", 10 + ")", "Data    Structures \n");
					printBuffer += String.format("%-3s %s", 11 + ")", "Print   Results \n");
					printBuffer += String.format("%-3s %s", 12 + ")", "Delete  Results \n");
					printBuffer += String.format("%-3s %s", 13 + ")", "Delete  Collection \n");
					printBuffer += String.format("%-3s %s", 0 + ")", "back \n");
					
					errorBuffer = "Error, your input is not valid!!\n";
					errorBuffer += "Please enter a value between [0-13]!\n";
					
					menu(13);
					
					if( input == 2 || input == 6 || input == 8 || input == 13) {
						
						Integer temp = input;
						
						printBuffer = String.format("%-4s %s", "", "\nAre you sure of your choice (" + input + ")?\n");
						printBuffer += String.format("%-3s %s", "1)", " Yes");
						printBuffer += String.format("%-3s %s", "\t0)", " No\n");
				    	
						errorBuffer = "";
						
						menu(1);
						
						if(input == 0) {
							
							input = -1;
							continue;
						}
						
						input = temp;
					}
					
					if( input == 1 || input == 2) {
						
						getTime = new Time();
						printBuffer = "";
						
						if( input == 2)
							NFTCTools.deleteFolder("/collections",NFTCTools.collectionName);
						
						checkFiles(index);
						println(printBuffer);
						printTime(getTime);
						println("\"Checking files\" has been completed successfully!\n");
					}
					else if( input == 3) {
						
						String [] memory = new String[15];
						
						memory[0] = NFTCTools.contract;
						memory[1] = NFTCTools.collectionName;
						memory[2] = NFTCTools.startingPoint;
						memory[3] = NFTCTools.endingPoint;
						memory[4] = NFTCTools.urlPrefix;
						memory[5] = NFTCTools.urlPostfix;
						memory[6] = NFTCTools.jName;
						memory[7] = NFTCTools.jId;
						memory[8] = NFTCTools.jImage;
						memory[9] = NFTCTools.jDesc;
						memory[10] = NFTCTools.jExt;
						memory[11] = NFTCTools.jIHash;
						memory[12] = NFTCTools.jAName;
						memory[13] = NFTCTools.jType;
						memory[14] = NFTCTools.jValue;
						
						do {
							
							printBuffer = "\n";
							printBuffer += String.format("%-4s %s", "","NEW COLLECTION MENU\n\n");
							printBuffer += String.format("%-3s %s", 1 + ")", String.format("%-40s %s"," " 
									+ "Add contract address \t \"" + NFTCTools.cContract + "\"", " (" + NFTCTools.contract + ")\n"));
							printBuffer += String.format("%-3s %s", 2 + ")", String.format("%-40s %s"," " 
									+ "Add collections' name \t \"" + NFTCTools.cName + "\"", "  (" + NFTCTools.collectionName + ")\n"));
							printBuffer += String.format("%-3s %s", 3 + ")", String.format("%-40s %s"," " 
									+ "Add ID starting point \t \"" + NFTCTools.cStartPoint + "\"", "  (" + NFTCTools.startingPoint + ")\n"));
							printBuffer += String.format("%-3s %s", 4 + ")", String.format("%-40s %s"," " 
									+ "Add ID ending point \t \"" + NFTCTools.cEndPoint + "\"", "(" + NFTCTools.endingPoint + ")\n"));
							printBuffer += String.format("%-3s %s", 5 + ")", String.format("%-40s %s"," " 
									+ "Add URI page prefixlink \t \"" + NFTCTools.cUrlPrefix + "\"", "    (" + NFTCTools.urlPrefix + ")\n"));
							printBuffer += String.format("%-3s %s", 6 + ")", String.format("%-40s %s"," " 
									+ "Change URI page postfixlink \"" + NFTCTools.cUrlPostfix + "\"", "    (" + NFTCTools.urlPostfix + ")\n"));
							printBuffer += String.format("%-3s %s", 7 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jNameName + "\" ", "(" + NFTCTools.jName + ")\n"));
							printBuffer += String.format("%-3s %s", 8 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jTokenId + "\" ", "(" + NFTCTools.jId + ")\n"));
							printBuffer += String.format("%-3s %s", 9 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jImageName + "\" ", "(" + NFTCTools.jImage + ")\n"));
							printBuffer += String.format("%-3s %s", 10 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jDescName + "\" ", "(" + NFTCTools.jDesc + ")\n"));
							printBuffer += String.format("%-3s %s", 11 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jExternal + "\" ", "(" + NFTCTools.jExt + ")\n"));
							printBuffer += String.format("%-3s %s", 12 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jImageHash + "\" ", "(" + NFTCTools.jIHash + ")\n"));
							printBuffer += String.format("%-3s %s", 13 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jArrayName + "\" ", "(" + NFTCTools.jAName + ")\n"));
							printBuffer += String.format("%-3s %s", 14 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jTraitType + "\" ", "(" + NFTCTools.jType + ")\n"));
							printBuffer += String.format("%-3s %s", 15 + ")", String.format("%-40s %s"," " 
									+ "Change meta-tag     \t \"" + NFTCTools.jTraitValue + "\" ", "(" + NFTCTools.jValue + ")\n"));
							printBuffer += "----------------------------------------------------------------------\n";
							printBuffer += String.format("%-3s %s", 16 + ")", " " + "Complete!\n");
							printBuffer += String.format("%-3s %s", 0 + ")", " " + "back\n");
							
					    	errorBuffer = "Error, your input is not valid!!\n";
							errorBuffer += "Please enter a value between [0-16]!\n";
						
							menu(16);
							
							if( input == 1) {
								
								print("Write the contract address : ");
								inputName = scanner.nextLine();
								
								if( inputName.equals(""))
									continue;
								
								while( inputName.length() >= 2 && (!(inputName.length() == 42) 
													|| !inputName.substring(0,2).equals("0x"))) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please enter the correct address (42 char long & starting with \"0x\").\n");
									print("Please re-write the contract address : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.contract;
										break;
									}
								}
								
								NFTCTools.contract = inputName;
							}
							else if( input == 2) {
								
								print("Write the collection name : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.matches("\\d+")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please enter a correct name (no number and at least 1 char long).\n");
									print("Please re-write the collection name : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.collectionName;
										break;
									}
								}
								
								NFTCTools.collectionName = inputName;
							}
							else if( input == 3) {
								
								print("Write the collection starting ID number : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( !inputName.matches("\\d+")){
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please enter a number.\n");
									print("Please re-write the collection starting ID number : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.startingPoint;
										break;
									}
								}
									
								NFTCTools.startingPoint = inputName;
							}
							else if( input == 4) {
								
								print("Write the collection ending ID number : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( !inputName.matches("\\d+") || !(Integer.parseInt(inputName)>0)){
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please enter a positive number.\n");
									print("Please re-write the collection ending ID number : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.endingPoint;
										break;
									}
								}
									
								NFTCTools.endingPoint = inputName;
							}
							else if( input == 5) {
								
								print("Write the collection prefix link : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( !(inputName.length() >= 14 && (inputName.substring(0,8).equals("https://")
										|| inputName.substring(0,7).equals("http://")))) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please enter the correct prefix link (\"http(s)://\"..).\n");
									print("Please re-write the collection prefix link : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.urlPrefix;
										break;
									}
								}
								
								NFTCTools.urlPrefix = inputName;
							}
							else if( input == 6) {
								
								print("Write the collection postfix link : ");
								inputName = scanner.nextLine();
								
								while( inputName.length() > 0 && !inputName.substring(0,1).equals(".")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please enter the correct postfix link (empty or starts with \".\").\n");
									print("Please re-write the collection postfix link : ");
									inputName = scanner.nextLine();
								}
							
								NFTCTools.urlPostfix = inputName;
							}
							else if( input == 7) {
								
								print("Write the collection metadata \"name\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"name\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jName;
										break;
									}
								}
								
								NFTCTools.jName = inputName;
							}
							else if( input == 8) {
								
								print("Write the collection metadata \"tokenId\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"tokenId\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jId;
										break;
									}
								}
								
								NFTCTools.jId = inputName;
							}
							else if( input == 9) {
								
								print("Write the collection metadata \"image\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"image\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jImage;
										break;
									}
								}
								
								NFTCTools.jImage = inputName;
							}
							else if( input == 10) {
								
								print("Write the collection metadata \"description\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"description\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jDesc;
										break;
									}
								}
								
								NFTCTools.jDesc = inputName;
							}
							else if( input == 11) {
								
								print("Write the collection metadata \"external_url\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"external_url\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jExt;
										break;
									}
								}
								
								NFTCTools.jExt = inputName;
							}
							else if( input == 12) {
								
								print("Write the collection metadata \"imageHash\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"imageHash\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jIHash;
										break;
									}
								}
								
								NFTCTools.jIHash = inputName;
							}
							else if( input == 13) {
								
								print("Write the collection metadata \"attributes\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"attributes\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jAName;
										break;
									}
								}
								
								NFTCTools.jAName = inputName;
							}
							else if( input == 14) {
								
								print("Write the collection metadata \"trait_type\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"trait_type\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jType;
										break;
									}
								}
								
								NFTCTools.jType = inputName;
							}
							else if( input == 15) {
								
								print("Write the collection metadata \"value\" tag : ");
								inputName = scanner.nextLine();

								if( inputName.equals(""))
									continue;
								
								while( inputName.substring(0,1).equals(" ")) {
									
									println("\nError, input \"" + inputName + "\" is not valid!!");
									println("Please do not start with spaces.\n");
									print("Please re-write the collection metadata \"value\" tag : ");
									inputName = scanner.nextLine();

									if( inputName.equals("")) {
										
										inputName = NFTCTools.jValue;
										break;
									}
								}
								
								NFTCTools.jValue = inputName;
							}
							else if( input == 16) {
								
								if( NFTCTools.contract.equals("None") || NFTCTools.collectionName.equals("None") 
										|| NFTCTools.startingPoint.equals("None") || NFTCTools.endingPoint.equals("None") 
										|| NFTCTools.urlPrefix.equals("None")) {
									
									println("Error, the new collection is not ready to be written!");
									println("The \"Add\" variables from the menu are requiered information!!");
									println("Collection contract address, name, starting/ending id & URI prefix link needs to filled out!!!");
								}
								else {
									
									getTime = new Time();
									
									Integer check = 0;
									
									if( !(memory[0].equals(NFTCTools.contract) && memory[1].equals(NFTCTools.collectionName))) {
										
										if( !memory[1].equals(NFTCTools.collectionName)) {
										
											NFTCTools.renameFolder("/results",memory[1],NFTCTools.collectionName);
											NFTCTools.renameFolder("/collections",memory[1],NFTCTools.collectionName);
											
											memory[1] = NFTCTools.collectionName;
										}
										
										NFTCTools.addNftCollection(index);
										NFTCTools.updateCMetadata();
										
										memory[0] = NFTCTools.contract;
										
										check++;
									}
									
									if( !(memory[6].equals(NFTCTools.jName) && memory[7].equals(NFTCTools.jId) && 
											memory[8].equals(NFTCTools.jImage) && memory[9].equals(NFTCTools.jDesc) && 
											memory[10].equals(NFTCTools.jExt) && memory[11].equals(NFTCTools.jIHash) && 
											memory[12].equals(NFTCTools.jAName) && memory[13].equals(NFTCTools.jType) && 
											memory[14].equals(NFTCTools.jValue))) {
										
										NFTCTools.deleteFolder("/collections",memory[1]);
									}
									
									if( !memory[2].equals(NFTCTools.startingPoint)) {
										
										Integer oldStartingPoint = Integer.parseInt(memory[2]);
										Integer newStartingPoint = Integer.parseInt(NFTCTools.startingPoint);
										
										if( newStartingPoint > oldStartingPoint)
											for( int i = oldStartingPoint; i < newStartingPoint; i++)
												NFTCTools.deleteFolder("/collections/" + NFTCTools.collectionName, 
														NFTCTools.collectionName + i + ".json");
									}
									
									if( !memory[3].equals(NFTCTools.endingPoint)) {
										
										Integer oldEndingPoint = Integer.parseInt(memory[3]);
										Integer newEndingPoint = Integer.parseInt(NFTCTools.endingPoint);
										
										if( newEndingPoint < oldEndingPoint)
											for( int i = newEndingPoint+1; i <= oldEndingPoint; i++)
												NFTCTools.deleteFolder("/collections/" + NFTCTools.collectionName, 
														NFTCTools.collectionName + i + ".json");
									}
									
									if( !(memory[2].equals(NFTCTools.startingPoint) && memory[3].equals(NFTCTools.endingPoint) &&
											memory[4].equals(NFTCTools.urlPrefix) && memory[5].equals(NFTCTools.urlPostfix) &&
											memory[6].equals(NFTCTools.jName) && memory[7].equals(NFTCTools.jId) && 
											memory[8].equals(NFTCTools.jImage) && memory[9].equals(NFTCTools.jDesc) && 
											memory[10].equals(NFTCTools.jExt) && memory[11].equals(NFTCTools.jIHash) && 
											memory[12].equals(NFTCTools.jAName) && memory[13].equals(NFTCTools.jType) && 
											memory[14].equals(NFTCTools.jValue))) {
										
										printBuffer = "";
										checkFiles(index);
										println(printBuffer);
										
										memory[2] = NFTCTools.startingPoint;
										memory[3] = NFTCTools.endingPoint;
										memory[4] = NFTCTools.urlPrefix;
										memory[5] = NFTCTools.urlPostfix;
										memory[6] = NFTCTools.jName;
										memory[7] = NFTCTools.jId;
										memory[8] = NFTCTools.jImage;
										memory[9] = NFTCTools.jDesc;
										memory[10] = NFTCTools.jExt;
										memory[11] = NFTCTools.jIHash;
										memory[12] = NFTCTools.jAName;
										memory[13] = NFTCTools.jType;
										memory[14] = NFTCTools.jValue;
										
										check++;
									}
									
									if( check != 0) {
										
										printTime(getTime);
									    input = -1;
									    break;
									}
									else
										println("There were no changes to be processed!");
								}
							}
					    	
							if( input == 0) {
									
								NFTCTools.setState(index,false);
								input = -1;
								break;
							}
							
						}while( input != 0);	//never happens
						
						mainMenuAdd = false;
						mainMenu = true;
					}
					else if( input == 4) {
						
						//	change json
						System.out.println("This function has not been programmed yet!");
					}
					else if( input == 5) {
						
						//	add json
						System.out.println("This function has not been programmed yet!");
					}
					else if( input == 6) {
						
						//	delete json
						System.out.println("This function has not been programmed yet!");
					}
					else if( input == 7) {
						
						getTime = new Time();
						
						NFTCTools.checkFiles();
						NFTCTools.readCollection();
						NFTCTools.initMetadata();
						NFTCTools.nftParse();
				    	NFTCTools.rarityCalculation(true,null);
				    	NFTCTools.cRarityScore();
				    	NFTCTools.printRarityReport(null);
						printBuffer = NFTCTools.collectionName + " : " + NFTCTools.report();
						println("\n" + printBuffer);
				    	printTime(getTime);
				    	
				    	//	print a menu with the written results to be audited.
				    	
				    	
					}
					else if( input == 8) {
						
						//	Dynamic Simulation
						System.out.println("This function has not been programmed yet!");
					}
					else if( input == 9) {
						
						do {
							
							printBuffer = "\n";
							printBuffer += String.format("%-4s %s", "", "QUERY MENU\n\n");
							printBuffer += String.format("%-3s %s", 1 + ")", "Create query\n");
							printBuffer += String.format("%-3s %s", 2 + ")", "Analyze query\n");
							printBuffer += String.format("%-3s %s", 3 + ")", "Delete query\n");
							printBuffer += String.format("%-3s %s", 0 + ")", "back \n");
							
							errorBuffer = "Error, your input is not valid!!\n";
							errorBuffer += "Please enter a value between [0-3]!\n";
								
							menu(3);
							
							if(input == 1){
								/*
								if( NFTCTools.nft == null) {
									
									NFTCTools.checkFiles();
									NFTCTools.readCollection();
									NFTCTools.initMetadata();
									NFTCTools.nftParse();
							    	NFTCTools.rarityCalculation(false,null);
							    	NFTCTools.cRarityScore();
								}*/
								
								String [] memory = new String[4];
								
								memory[0] = NFTCTools.startingPoint;
								memory[1] = NFTCTools.endingPoint;
								memory[2] = NFTCTools.minTNumValue;
								memory[3] = NFTCTools.traitCount;
								
								Pair [] attrExist = null;
								Pair [] attrNotExist = null;
								ArrayList<Integer> specificId = null;
								ArrayList<Integer> specificNotId = null;
								ArrayList<Integer> specificTNum = null;
								ArrayList<Integer> specificNotTNum = null;
								
								do {
									
									printBuffer = "\n";
									printBuffer += String.format("%-4s %s", "","NEW QUERY MENU\n\n");
									
									printBuffer += String.format("%-3s %s", 1 + ")", String.format("%-40s %s", " " 
											+ "Change the minimum Id number", String.format("%-17s %s", "\"" 
													+ NFTCTools.minId + "\"" , "(" + NFTCTools.startingPoint + ")\n")));
									
									printBuffer += String.format("%-3s %s", 2 + ")", String.format("%-40s %s"," " 
											+ "Change the maximum Id number", String.format("%-17s %s", "\"" 
													+ NFTCTools.maxId + "\"", "(" + NFTCTools.endingPoint + ") \n")));
									
									printBuffer += String.format("%-3s %s", 3 + ")", String.format("%-40s %s"," " 
											+ "Change the minimum trait count", String.format("%-17s %s", "\"" 
													+ NFTCTools.minTNum + "\"","(" + NFTCTools.minTNumValue + ")\n")));
									
									printBuffer += String.format("%-3s %s", 4 + ")", String.format("%-40s %s"," " 
											+ "Change the maximum trait count", String.format("%-17s %s", "\"" 
													+ NFTCTools.maxTNum + "\"", "(" + NFTCTools.traitCount + ")\n")));
									
									printBuffer += String.format("%-3s %s", 5 + ")", String.format("%-40s %s"," " 
											+ "Add attributes to be included", String.format("%-17s %s", "\"" + NFTCTools.attrExist 
											+ "\"", "(" + (attrExist != null ? attrExist.toString() : "null") + ")\n")));
									
									printBuffer += String.format("%-3s %s", 6 + ")", String.format("%-40s %s"," " 
											+ "Add attributes NOT to be included", String.format("%-17s %s", "\"" + NFTCTools.attrNotExist 
											+ "\"", "(" + (attrNotExist != null ? attrNotExist.toString() : "null") + ")\n")));
									
									printBuffer += String.format("%-3s %s", 7 + ")", String.format("%-40s %s"," " 
											+ "Add Ids to be included", String.format("%-17s %s", "\"" + NFTCTools.specificId 
											+ "\"", "(" + (specificId != null ? Arrays.toString(specificId.toArray()) : "null") + ")\n")));
									
									printBuffer += String.format("%-3s %s", 8 + ")", String.format("%-40s %s"," " 
											+ "Add Ids NOT to be included", String.format("%-17s %s", "\"" + NFTCTools.specificNotId 
											+ "\"", "(" + (specificNotId != null ? Arrays.toString(specificNotId.toArray()) : "null") + ")\n")));
									
									printBuffer += String.format("%-3s %s", 9 + ")", String.format("%-40s %s"," " 
											+ "Add trait count to be included", String.format("%-17s %s", "\"" + NFTCTools.specificTNum 
											+ "\"", "(" + (specificTNum != null ? Arrays.toString(specificTNum.toArray()) : "null") + ")\n")));
									
									printBuffer += String.format("%-3s %s", 10 + ")", String.format("%-40s %s"," " 
											+ "Add trait count NOT to be included", String.format("%-17s %s", "\"" + NFTCTools.specificNotTNum 
													+ "\"", "(" + (specificNotTNum != null ? Arrays.toString(specificNotTNum.toArray()) : "null") + ")\n")));
									
									printBuffer += "----------------------------------------------------------------------\n";
									printBuffer += String.format("%-3s %s", 11 + ")", " " + "Complete!\n");
									printBuffer += String.format("%-3s %s", 0 + ")", " " + "back\n");
									
							    	errorBuffer = "Error, your input is not valid!!\n";
									errorBuffer += "Please enter a value between [0-11]!\n";
								
									menu(11);
									
									if( input == 1 || input == 2) {
										
										String string;
										
										if( input == 1)
											string = "minimum";
										else
											string = "maximum";
										
										print("Write the " + string + " Id number : ");
										inputName = scanner.nextLine();
										
										if( inputName.equals(""))
											continue;
										
										while( !(inputName.matches("\\d+") 
												&& NFTCTools.toInt(inputName) >= NFTCTools.toInt(memory[0])
												&& NFTCTools.toInt(inputName) <= NFTCTools.toInt(memory[1]))) {
											
											println("\nError, input \"" + inputName + "\" is not valid!!");
											println("Please enter a correct Id number (between \"" 
																	+ memory[0] + "-" + memory[1] + "\").\n");
											print("Please re-write the " + string + " Id number : ");
											inputName = scanner.nextLine();
											
											if( inputName.equals("")) {
												
												if( input == 1)
													inputName = NFTCTools.startingPoint;
												else
													inputName = NFTCTools.endingPoint;
												break;
											}
										}
										
										if( input == 1)
											NFTCTools.startingPoint = inputName;
										else
											NFTCTools.endingPoint = inputName;
									}
									else if( input == 3 || input == 4) {
										
										String string;
										
										if( input == 3)
											string = "minimum";
										else
											string = "maximum";
										
										print("Write the " + string + " trait count : ");
										inputName = scanner.nextLine();
										
										if( inputName.equals(""))
											continue;
										
										while( !(inputName.matches("\\d+") 
												&& NFTCTools.toInt(inputName) >= NFTCTools.toInt(memory[2])
												&& NFTCTools.toInt(inputName) <= NFTCTools.toInt(memory[3]))) {
											
											println("\nError, input \"" + inputName + "\" is not valid!!");
											println("Please enter a correct trait count (between \"" 
																	+ memory[2] + "-" + memory[3] + "\").\n");
											print("Please re-write the " + string + " trait count : ");
											inputName = scanner.nextLine();
											
											if( inputName.equals("")) {
												
												if( input == 3)
													inputName = NFTCTools.minTNumValue;
												else
													inputName = NFTCTools.traitCount;
												break;
											}
										}
										
										if( input == 3)
											NFTCTools.minTNumValue = inputName;
										else
											NFTCTools.traitCount = inputName;
									}
									else if( input == 5 || input == 6) {
										
										System.out.println("This functionality is under construction!");
										/*
										print("Write attributes ((\"type1\",\"value1\"), ... ) : ");
										
										String [] attBuffer;
										Boolean formation = false;
										
										while(!formation){
											
											inputName = scanner.nextLine();
											
											if( inputName.equals("")) {
												
												formation = true;
												
												if( input == 5 && attrExist != null) {
													
													for( int i=0; i<attrExist.length; i++)
														attrExist[i] = null;
												}
												else if( attrNotExist != null){
													
													for( int i=0; i<attrNotExist.length; i++)
														attrNotExist[i] = null;
												}
											}
											else if( inputName.length() >= 3) {
												
												inputName = stringToTitleCase(inputName);
												
												if( inputName.substring(inputName.length()-1).equals(","))
													inputName = inputName.substring(0,inputName.length()-2);
												
												attBuffer = inputName.split(",");
												
												if( input == 5)
													attrExist = new Pair[attBuffer.length];
												else
													attrNotExist = new Pair[attBuffer.length];
												
												for( int i=0; i<attBuffer.length; i++) {
													
													if( attBuffer[i].substring(0,0).equals("(") && 
														attBuffer[i].substring(attBuffer[i].length()-1).equals(")")) {
														
														attBuffer[i] = attBuffer[i].substring(1,attBuffer[i].length()-2);
														
														String [] data = new String[2];
														data = attBuffer[i].split(",");
														
														if( input == 5)
															attrExist[i] = new Pair(data[0],data[1]);
														else
															attrExist[i] = new Pair(data[0],data[1]);
														
														formation = true;
													}
												}
											}
											
											if(!formation) {
												
												println("\nError, input \"" + inputName + "\" is not valid!!");
												println("Please enter a correct input like "
																+ "((\"type1\",\"value1\"),.. ) or empty.\n");
												print("Please re-write the attributes : ");
											}
											
											attrExist = null;
											attrNotExist = null;
										}
										//*/
									}
									else if( input == 7 || input == 8 || input == 9 || input == 10 ) {
										
										String string1,string2;
										
										if( input == 7 || input == 8) {
											
											string1 = "(\"Id1,Id2,Id3 ..\") or empty.\n";
											string2 = "specific Ids";
											print("Write specific Ids (\"Id1,Id2,Id3 ..\") : ");
										}
										else {
											
											string1 = "(\"TC1,TC2,TC3 ..\") or empty.\n";
											string2 = "trait counts";
											print("Write trait counts (\"TC1,TC2,TC3 ..\") : ");
										}
										
										String [] attBuffer;
										Boolean formation = false;
										
										while(!formation){
											
											inputName = scanner.nextLine();
											formation = true;
											
											if( inputName.equals("")) {
												
												if( input == 7)
													specificId = null;
												else if ( input == 8)
													specificNotId = null;
												else if( input == 9)
													specificTNum = null;
												else
													specificNotTNum = null;
											}
											else {
												//TODO
												//if( inputName.length() > 1 && inputName.substring(0,1).equals(","))
												//	inputName = inputName.substring(1,inputName.length());
												
												attBuffer = inputName.split(" ");
												
												if( input == 7)
													specificId = new ArrayList<Integer>();
												else if ( input == 8)
													specificNotId = new ArrayList<Integer>();
												else if( input == 9)
													specificTNum = new ArrayList<Integer>();
												else
													specificNotTNum = new ArrayList<Integer>();
												
												for( int i=0; i<attBuffer.length; i++) {
													
													if( attBuffer[i].matches("\\d+")) {
														
														Boolean found = false;
														
														if( input == 7) {
															
															for( Integer element : specificId)
																if(element == NFTCTools.toInt(attBuffer[i])) {
																	
																	found = true;
																	break;
																}
															
															if( !found)
																specificId.add(NFTCTools.toInt(attBuffer[i]));
														}
														else if( input == 8) {
															
															for( Integer element : specificNotId)
																if(element == NFTCTools.toInt(attBuffer[i])) {
																	
																	found = true;
																	break;
																}
															
															if( !found)
																specificNotId.add(NFTCTools.toInt(attBuffer[i]));
														}
														else if( input == 9) {
															
															for( Integer element : specificTNum)
																if(element == NFTCTools.toInt(attBuffer[i])) {
																	
																	found = true;
																	break;
																}
															
															if( !found)
																specificTNum.add(NFTCTools.toInt(attBuffer[i]));
														}
														else {
															
															for( Integer element : specificNotTNum)
																if(element == NFTCTools.toInt(attBuffer[i])) {
																	
																	found = true;
																	break;
																}
															
															if( !found)
																specificNotTNum.add(NFTCTools.toInt(attBuffer[i]));
														}
													}
													else {
														
														formation = false;
														break;
													}
												}
											}
											
											if(!formation) {
												
												println("\nError, input \"" + inputName + "\" is not valid!!");
												println("Please enter a correct " + string2 + " like " + string1);
												print("Please re-write the " + string2 + " : ");
											}
										}
									}
									else if( input == 11) {
										
										Boolean empty1 = true;
										Boolean empty2 = true;
										
										if( attrExist != null)
											for( int i=0; i<attrExist.length; i++) {
													
													empty1 = false;
													break;
												}
										
										if( attrNotExist != null)
											for( int i=0; i<attrNotExist.length; i++) {
													
													empty2 = false;
													break;
												}
											//
										if( memory[0].equals(NFTCTools.startingPoint) && memory[1].equals(NFTCTools.endingPoint)
											&& memory[2].equals(NFTCTools.minTNumValue) && memory[3].equals(NFTCTools.traitCount)
											&& empty1 && empty2 && specificId == null && specificNotId == null 
											&& specificTNum == null && specificNotTNum == null) {
											
											println("There are no changes from the original collection to be processed!");
										}
										else {
											
											System.out.print("Give a name to your query : ");
											inputName = scanner.nextLine();
											
											getTime = new Time();
											
//											write the query to file.
											
											Integer start = NFTCTools.toInt(NFTCTools.startingPoint);
											Integer end = NFTCTools.toInt(NFTCTools.endingPoint);
											Integer minTCount = NFTCTools.toInt(NFTCTools.minTNumValue);
											Integer tCount = NFTCTools.toInt(NFTCTools.traitCount);
											
											NFTCTools.startingPoint = memory[0];
											NFTCTools.endingPoint = memory[1];
											NFTCTools.minTNumValue = memory[2];
											NFTCTools.traitCount = memory[3];
											
											Set<Integer> set = new HashSet<Integer>();
											Set<Integer> subsetA = new HashSet<Integer>();
											Set<Integer> subsetB = new HashSet<Integer>();
											Set<Integer> subsetC = null;
											Set<Integer> subsetD = null;
											Set<Integer> subsetE = new HashSet<Integer>();
											Set<Integer> subsetF = new HashSet<Integer>();
											
											if( specificId != null)
												subsetC = specificId.stream().collect(Collectors.toSet());			
											
											if( specificNotId != null)
												subsetC = specificNotId.stream().collect(Collectors.toSet());	
											
											for( int i=start-NFTCTools.toInt(memory[0]); i<end; i++)
												//if( NFTCTools.nft[i].getTraitNum() >= minTCount 
												//		&& NFTCTools.nft[i].getTraitNum() <= tCount)
													set.add(i+NFTCTools.toInt(memory[0]));
											
											for( int i=start-NFTCTools.toInt(memory[0]); i<end; i++) {
												
												if( specificTNum != null)
													if( intTNumSetCount(specificTNum,i) != null)
														subsetE.add(i+NFTCTools.toInt(memory[0]));
												
												if( specificNotTNum != null)
													if( intTNumSetCount(specificNotTNum,i) != null)
														subsetF.add(i+NFTCTools.toInt(memory[0]));
											}
											
											System.out.println("\t ALL FILTERS\n");
											System.out.println("\nID  Range     filter : " 
													+ "[" + start + "-" + end + "]");
											System.out.println("Trait Range     filter : " 
													+ "[" + minTCount + "-" + tCount + "]");
											System.out.println("Att       Exist filter : " + subsetA);
											System.out.println("Att   Not Exist filter : " + subsetB);
											System.out.println("ID        Exist filter : " + subsetC);
											System.out.println("ID    Not Exist filter : " + subsetD);
											System.out.println("Trait     Exist filter : " + subsetE);
											System.out.println("Trait Not Exist filter : " + subsetF);
											
											if( subsetA != null && subsetA.size() != 0)
												set.retainAll(subsetA);
											
											if( subsetB != null && subsetB.size() != 0)
												set.removeAll(subsetB);
											
											if( subsetC != null && subsetC.size() != 0)
												set.retainAll(subsetC);
											
											if( subsetD != null && subsetD.size() != 0)
												set.removeAll(subsetD);
											
											if( subsetE != null && subsetE.size() != 0)
												set.retainAll(subsetE);
											
											if( subsetF != null && subsetF.size() != 0)
												set.removeAll(subsetF);
											
											if( set.isEmpty()) {
												
												System.out.println("The results of the query is empty.\n"
														+ "There are no nfts that fulfil the requirements of this query!");
												continue;
											}
											
											NFTCTools.copyState(false);
											
											NFTCTools.inputIdsArray = new ArrayList<Integer>(set);
											Collections.sort(NFTCTools.inputIdsArray);
											
											NFTCTools.readCollection();
											
											Integer counter = 0;
											NFTCTools.nullsArray = new ArrayList<Integer>();
											for( int i = 0; i < NFTCTools.toInt(memory[1]) - NFTCTools.toInt(memory[0]) + 1 ; i++) {
												
												if( counter >= NFTCTools.inputIdsArray.size() 
														|| NFTCTools.inputIdsArray.get(counter) != i + NFTCTools.toInt(memory[0])) {
													
													NFTCTools.nullsArray.add(i+NFTCTools.toInt(memory[0]));
													NFTCTools.jStrings[i] = null;
												}
												else 
													counter++;
											}
											
											System.out.println("\n\t IDs TO BE PROCESSED\n");
											System.out.println("Analysis input set : " + set + "\n");
											
											NFTCTools.initMetadata();
											NFTCTools.nftParse();
									    	NFTCTools.rarityCalculation(true,inputName);
									    	NFTCTools.cRarityScore();
									    	NFTCTools.printRarityReport(inputName);
											printBuffer = "\n" + NFTCTools.collectionName + " : " + NFTCTools.report();
											println(printBuffer);
											
											//	then print the new results with different name.
											
											NFTCTools.setState(index,false);
											NFTCTools.copyState(true);
											
											printTime(getTime);
											input = -1;
											break;
										}
									}
							    	
									if( input == 0) {
											
										NFTCTools.setState(index,false);
										input = -1;
										break;
									}
									
								}while( input != 0);	//never happens
								
								mainMenuAdd = false;
								mainMenu = true;
							}
							else if(input == 2) {
								
								System.out.println("This function has not been programmed yet!");
							}
							else if(input == 3) {
								
								System.out.println("This function has not been programmed yet!");
							}
							else {
								
								input = -1;
								break;
							}
							
						}while(input != 0);	// never happens
					}
					else if( input == 10) {
						
						do {
							
							printBuffer = "\n";
							printBuffer += String.format("%-4s %s", "", "DATA STRUCTURES MENU\n\n");
							printBuffer += String.format("%-3s %s", 1 + ")", 
									String.format("%-20s %s", "Print JStrings ", "( Every single nfts' metadata in json format.)\n"));
							printBuffer += String.format("%-3s %s", 2 + ")", 
									String.format("%-20s %s", "Print NullsArray ", "( All null IDs of the collection + array size.)\n"));
							printBuffer += String.format("%-3s %s", 3 + ")", 
									String.format("%-20s %s", "Print InputIdsArray ", "( Input Ids to be processed + array size.)\n"));
							printBuffer += String.format("%-3s %s", 4 + ")", 
									String.format("%-20s %s", "Print TraitArrays ", "( All different arrays that store trait_types.)\n"));
							printBuffer += String.format("%-3s %s", 5 + ")", 
									String.format("%-20s %s", "Print SetList ", "( All different valid combinations of same trait_type.)\n"));
							printBuffer += String.format("%-3s %s", 6 + ")", 
									String.format("%-20s %s", "Print Nft ", "( All nft metadata after processing of the collection.)\n"));
							printBuffer += String.format("%-3s %s", 7 + ")", 
									String.format("%-20s %s", "Print Extras ", "( Total sum of not \"None\" pairs of the collection.)\n"));
							printBuffer += String.format("%-3s %s", 8 + ")", 
									String.format("%-20s %s", "Print Frequencies ", "( Nft totals by number of traits.)\n"));
							printBuffer += String.format("%-3s %s", 9 + ")", 
									String.format("%-20s %s", "Print ValueCounts ", "( Total number of different trait_type values.)\n"));
							printBuffer += String.format("%-3s %s", 10 + ")", 
									String.format("%-20s %s", "Print PairCounts ", "( Total sums by trait_type ±\"None\" values.)\n"));
							printBuffer += String.format("%-3s %s", 11 + ")", 
									String.format("%-20s %s", "Print ValueFreq ", "( All unique pairs <trait_type,value> -> #appearences.)\n"));
							printBuffer += String.format("%-3s %s", 12 + ")", 
									String.format("%-20s %s", "Print Rankings ", "( All <Id,Score> pairs sorted by Id.)\n"));
							printBuffer += String.format("%-3s %s", 13 + ")", 
									String.format("%-20s %s", "Print PairArray ", "( All <Score,Id> pairs sorted by Score.)\n"));
							printBuffer += String.format("%-3s %s", 14 + ")", 
									String.format("%-20s %s", "Print NftCollection ", "( (c)Metadata of all the collections stored locally.)\n"));
							printBuffer += String.format("%-3s %s", 15 + ")", 
									String.format("%-20s %s", "Print Variables ", "( All the variables that the NFTCTools Class has.)\n"));
							printBuffer += String.format("%-3s %s", 16 + ")", 
									String.format("%-20s %s", "Print Constants ", "( All the constants that the NFTCTools Class has.)\n"));
							printBuffer += String.format("%-3s %s", 0 + ")", "back\n");
							
							errorBuffer = "Error, your input is not valid!!\n";
							errorBuffer += "Please enter a value between [0-16]!\n";
							
							menu(16);
									
							if(input == 1)
								NFTCTools.printJStrings();
							else if(input == 2)
								NFTCTools.printNullsArray();
							else if(input == 3)
								NFTCTools.printInputIdsArray();
							else if(input == 4)
								NFTCTools.printTraitArrays();
							else if(input == 5)
								NFTCTools.printSetList();
							else if(input == 6)
								NFTCTools.printNft();
							else if(input == 7)
								NFTCTools.printExtras();			
							else if(input == 8)
								NFTCTools.printFrequencies();				
							else if(input == 9)
								NFTCTools.printValueCounts();
							else if(input == 10)
								NFTCTools.printPairCounts();				
							else if(input == 11)
								NFTCTools.printValueFreq();					
							else if(input == 12)
								NFTCTools.printRankings();							
							else if(input == 13)
								NFTCTools.printPairArray();										
							else if(input == 14)
								NFTCTools.printNftColleciton();	
							else if(input == 15)
								NFTCTools.printVariables();									
							else if(input == 16)
								NFTCTools.printConstants();
							else {
								
								input = -1;
								break;
							}
							
						}while(input != 0);	//never happens
					}
					else if( input == 11 || input == 12) {
						
						Integer length;
						String [] names;
						String menuName;
						Integer temp = input;
						
						if( temp == 11)
							menuName = "PRINT";
						else
							menuName = "DELETE";
						
						do {
							
							names = NFTCTools.readResultNames();
							Arrays.sort(names);
							
							if( names != null)
								length = names.length;
							else
								length = 0;
							
							printBuffer = "\n";
							printBuffer += String.format("%-4s %s", "", menuName + " RESULTS MENU\n\n");
							
							for(int i=0; i<length; i++)
								printBuffer += String.format("%-3s %s", (i+1) + ")", names[i] + "\n");
							
							printBuffer += String.format("%-3s %s", 0 + ")", "back\n");
								
							errorBuffer = "Error, your input is not valid!!\n";
							errorBuffer += "Please enter a value between [0-" + length + "]!\n";
									
							menu(length);
							
							if( input != 0) {
								
								if( temp == 11) {
									
									String [] results = NFTCTools.getResults(names[input-1]);
									NFTCTools.printHandler(results);
								}
								else if( temp == 12){
									
									NFTCTools.deleteFolder("/" + NFTCTools.rFolderName 
											+ "/" + NFTCTools.collectionName, names[input-1]);
								}
							}
							else {
								
								input = -1;
								break;
							}
						}while( input != 0);	//never happens
					}
					else if( input == 13) {
						
						getTime = new Time();
						
						NFTCTools.deleteFolder("/" + NFTCTools.cFolderName, NFTCTools.collectionName);
						NFTCTools.deleteNftCollection(index);
						NFTCTools.updateCMetadata();
						printTime(getTime);
						input = -1;
						break;
					}
				}while( input != 0);	//never happens
				
				collectionMenu = false;
				mainMenu = true;
			}
			
		}while( !exit);
		
		printTime(startTime);
		print("The program has been terminated successfully!\n\n");
	}
}
