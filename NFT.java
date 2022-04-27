package com.nftrarity.com.nftrarity.maven.eclipse;

/*
 * 				NFT Class
 * 
 * 		With this class we represent the general idea of a Non-Fungible-Token.
 * 
 */
class NFT {
	
	/*
	 * 	Here we initiate the objects properties in default values.
	 * 	Afterwards you start adding the data one by one calling the setter methods bellow.
	 */
	private Integer id = 0;
	private Integer traitNum = 0;
	private String name = new String();
	private String tokenId = new String();
	private String image = new String();
	private String description = new String();
	private String externalUrl = new String();
	private String imageHash = new String();
	private Pair [] attribute = null;
	
	/*
	 * 	This method initiates the NFT object with its properties on creation.
	 */
	NFT( Integer id, Integer traitNum, String name, String tokenId, String image, String description, 
								String externalUrl, String imageHash, Pair [] attribute) {
		
		Integer tCount = NFTCTools.toInt(NFTCTools.traitCount);
		
		this.id = id;
		this.traitNum = traitNum;
		this.name = name;
		this.tokenId = tokenId;
		this.image = image;
		this.description = description;
		this.externalUrl = externalUrl;
		this.imageHash = imageHash;
		
		this.attribute = new Pair[tCount];
		
		for( int i=0; i<tCount; i++)
			this.attribute[i] = attribute[i];
	}
	
	/*
	 * 	This method is a copy constructor
	 */
	NFT( NFT nft) {
	
		if( nft != null) {
		
			Integer tCount = nft.getAtt().length;
	
			this.id = nft.getID();
			this.traitNum = nft.getTraitNum();
			this.name = nft.getName();
			this.tokenId = nft.getTokenId();
			this.image = nft.getImg();
			this.description = nft.getDesc();
			this.externalUrl = nft.getExternalUrl();
			this.imageHash = nft.getImageHash();
			
			this.attribute = new Pair[tCount];
			
			for( int i=0; i<tCount; i++)
				this.attribute[i] = nft.getAtt(i);
		}
		else {
			
			this.id = null;
			this.traitNum = null;
			this.name = null;
			this.tokenId = null;
			this.image = null;
			this.description = null;
			this.externalUrl = null;
			this.imageHash = null;
			
			this.attribute = null;
		}
		
	}
	
	/*
	 * 	This method prints the NFT object.
	 */
	public String printNft() {
		
		String buffer = "";
		
		buffer += "(\n\tid:\"" + id + "\",\n\ttraitNum:\"" + traitNum + "\",\n\tname:\"" + name 
				+ "\",\n\ttokenId:\"" + tokenId + "\",\n\timage:\"" + image 
				+ "\",\n\tdescription:\"" + description + "\",\n\texternalUrl:\"" + externalUrl 
				+ "\",\n\timageHash:\"" + imageHash + "\",\n\tattribute:[\n\t\t";
		
		for( int i=0; i<attribute.length-1; i++) 
				buffer += attribute[i].printPair() + ",\n\t\t";
		
		buffer += attribute[attribute.length-1].printPair() + "])";
		
		return buffer;
	}
	
	//	A getter method for id variable.
	public Integer getID() {
		
		return id;
	}
	
	//	A getter method for traitNum variable.
	public Integer getTraitNum() {
		
		return traitNum;
	}

	//	A getter method for name variable.
	public String getName() {
		
		return name;
	}
	
	//	A getter method for tokenId variable.
	public String getTokenId() {
		return tokenId;
	}

	//	A getter method for image variable.
	public String getImg() {
		
		return image;
	}

	//	A getter method for description variable.
	public String getDesc() {
		
		return description;
	}
	
	//	A getter method for externalUrl variable.
	public String getExternalUrl() {
		return externalUrl;
	}
	
	//	A getter method for imageHash variable.
	public String getImageHash() {
		return imageHash;
	}
	
	//	A getter for a specific pair within the value array.
	public Pair getAtt(Integer index) {
		
		return attribute[index];
	}
	
	//	A getter for a specific pair within the value array.
	public Pair [] getAtt() {
		
		return attribute;
	}
	
	//	A setter method for id variable.
	public void setID( Integer id) {

		this.id = id;
	}
	
	//	A setter method for traitNum variable.
	public void setTraitNum( Integer traitNum) {

		this.traitNum = traitNum;
	}
	
	//	A setter method for name variable.
	public void setName( String name) {
		
		this.name = name;
	}
	
	//	A setter method for tokenId variable.
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	//	A setter method for image variable.
	public void setImg( String image) {
		
		this.image = image;
	}
	
	//	A setter method for description variable.	
	public void setDesc(String description) {
		
		this.description = description;
	}
	
	//	A setter method for externalUrl variable.
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}
	
	//	A setter method for imageHash variable.
	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}
	
	//	A setter method for the whole value pair array.
	public void setAtt( Pair [] value) {
		
		for( int i=0; i<value.length; i++) {
			
			this.attribute[i] = value[i];
		}
	}
	
	//	A setter method for a specific pair within the value array.
	public void setAtt( Pair value, Integer index) {
		
		this.attribute[index] = value;
	}
}
