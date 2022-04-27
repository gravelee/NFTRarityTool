package com.nftrarity.com.nftrarity.maven.eclipse;

/*
 * 				NFT Rarity Tools -- Java Application ToDO List
 * 
 * 
 * 		TODO	Print a menu for the new printed files after Static Analysis.
 * 
 * 		TODO	Trait Array & SetList in Data Structures needs update 
 * 						(new structure parallel with uniqueTraits set).
 * 
 * 		TODO	Add to PrintSetList the total check as the last column.
 * 
 * 		TODO	Make a blacklist ( as hotfix) and if Cyberkongz is the collection 
 * 					then remove birthday trait from calculations (+ when all genesis 
 * 					traits are None rarity.tools does not grade the first none, we do).
 * 
 * 
 * 		*ToDO 	Add descriptions on Data Structure Menu & an extended version of it within the function.
 * 		*ToDO 	When some json files are null, Data Structure prints - NFT throws exception.
 * 		*ToDO	Create 1 new data structures on NFTCTools and write these on Data Structure prints.
 * 					ArrayList<Set<Integer>> list = new ArrayList<Set<Integer>>();
 * 		*ToDO	JArrayData in Data Structures change the numbers with something else.
 * 		*ToDO 	In Rankings array we do not omit nulls & the results are wrong!!
 * 		*ToDO 	Extras array does not work properly.
 * 		*ToDO	Change Print jArrayData to Trait Arrays and print another 
 * 					two arrays ( uniqueTraits, jArrayDataMore + (Counts).
 *		*ToDO	Check Java Asynchronous http requests (baeldung) and update the 
 *					CollectionReader.jsonParser() + NFTCTools.readCollection().
 *		*ToDO	Update getProgress not to check the MOD of the ID but the count ( array length).
 *		*ToDO	Delete the nulls integer variable and use nullsArray.size() instead.
 *		*ToDO	Find the problem with the traitCount (infinity).
 *		*ToDO 	Add http option with https on add url on Add new Collection (MM) & Update Metadata (CM).
 *		*ToDO 	Make empty string "" to return you back when on Add new Collection & Update Metadata.
 * 					This means that the input is used to go back and not to give new value.
 *		*ToDO 	Enter the new collection menu (CM) after Add new Collection.
 *
 * 		dd
 * 		TODO	Add Query functionality to Collection Menu.
 *
 *		TODO 	in query: input # (3, 45, 64, 23,...) then add 1000 more so we have data to get better results
 *					then print only the inputs
 *
 *		TODO 	can we have clickable links? if yes then have one on rarity report to click and go to opensea
 *
 *		TODO	Add to Collection Menu new functionality "Print Summary of", 
 *					Print specific IDs ( integer array as input) and print 
 *					all of their lines from rarityReport.
 *
 *		TODO	Add to Collection Menu new functionality "Print Extended of", 
 *					Print specific IDs and print all of their lines from rarityReport
 *					& scoreReport files + other json data from NFT data stucture.
 *					All of these sorted by rarity
 * 
 * 
 * 
 *		TODO	Add new variable (string array) named "banedTraits" to NFTColleciton class.
 * 					This means that we need to update the cMetadata file and all the functions
 * 					that interpret it. Same goes for all the functions that update the
 * 					nftCollection data structure within NFTCTools. 
 * 
 * 
 * 
 * 		TODO	Change the ghost trait prints (many) to print only the last ghost trait 
 * 					(for every trait).
 * 
 * 		TODO	Create a new data structure in NTCTools class to calculate the 
 * 					Integer traitAndGhostScore[cNum] array that will save the
 * 					original score of an nft plus the ghost score.
 * 
 * 		TODO	After the upper) write code to choose between the score arrays.
 * 					The ghost score + original will replace the original score
 * 					only when the former make an nft enter into the 1% top rare list.
 * 
 * 		
 * 		
 * 		TODO	Colored letters in terminal supported? by tags? <red> </red> </reset>.
 * 
 * 		TODO	Make the program compatible for windows's file system.
 * 
 * 		
 * 
 * 		TODO	Add Create new NFT collection functionality to Main Menu.
 * 
 * 		TODO	Add Dynamic Analysis functionality to Main Menu/ Collection Menu.
 * 
 * 		TODO	Add Change/add/delete j-son file functionality to Collection Menu.
 * 
 * 		
 * 
 * 		TODO 	Print in graph x-y: id:score discrete.
 * 
 * 		
 * 
 *		TODO 	Open sea API read collections buy now list.
 * 
 * 		TODO	Print Buy now list and update it instantly.
 * 
 * 		
 * 
 * 		TODO	Add database technology to the program.
 * 
 * 
 * 
 */
public class App {
   
	@SuppressWarnings("unused")
	public static void main(String[] args) {	
    	
    	MenuHandler mh = new MenuHandler();
    }
}