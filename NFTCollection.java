package com.nftrarity.com.nftrarity.maven.eclipse;

/*
 * 	This class holds the container data of a collection from the cMetadata.json file.
 */
public class NFTCollection {

	private String cContract;
	private String cName;
	private String cStartPoint;
	private String cEndPoint;
	private String cUrlPrefix;
	private String cUrlPostfix;
	private String cTraitCount;
	private String jName;
	private String jId;
	private String jImage;
	private String jDesc;
	private String jExt;
	private String jIHash;
	private String jAName;
	private String jType;
	private String jValue;
	
	NFTCollection( String cContract, String cName, String cStartPoint, String cEndPoint, 
			String cUrlPrefix, String cUrlPostfix, String cTraitCount, String jName,
			String jId, String jImage, String jDesc, String jExt, String jIHash,
			String jAName, String jType, String jValue){
		
		this.cContract = cContract;
		this.cName = cName;
		this.cStartPoint = cStartPoint;
		this.cEndPoint = cEndPoint;
		this.cUrlPrefix = cUrlPrefix;
		this.cUrlPostfix = cUrlPostfix;
		this.cTraitCount = cTraitCount;
		this.jName = jName;
		this.jId = jId;
		this.jImage = jImage;
		this.jDesc = jDesc;
		this.jExt = jExt;
		this.jIHash = jIHash;
		this.jAName = jAName;
		this.jType = jType;
		this.jValue = jValue;
		
	}

	public String printNftCollection() {
		
		String buffer = "";
		
		buffer += "(\n\tcContract:\"" + cContract + "\",\n\tcName:\"" + cName + "\",\n\tcStartPoint:\"" + cStartPoint 
				+ "\",\n\tcEndPoint:\"" + cEndPoint + "\",\n\tcUrlPrefix:\"" + cUrlPrefix 
				+ "\",\n\tcUrlPostfix:\"" + cUrlPostfix + "\",\n\tcTraitCount:\"" + cTraitCount 
				+ "\",\n\tjName:\"" + jName + "\",\n\tjId:" + jId + "\",\n\tjImage:" + jImage 
				+ "\",\n\tjDesc:" + jDesc + "\",\n\tjExt:" + jExt + "\",\n\tjIHash:" + jIHash 
				+ "\",\n\tjAName:" + jAName + "\",\n\tjType:" + jType + "\",\n\tjValue:" + jValue + ")";
		
		return buffer;
	}
	
	public String getcContract() {
		return cContract;
	}

	public void setcContract(String cContract) {
		this.cContract = cContract;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcStartPoint() {
		return cStartPoint;
	}

	public void setcStartPoint(String cStartPoint) {
		this.cStartPoint = cStartPoint;
	}

	public String getcEndPoint() {
		return cEndPoint;
	}

	public void setcEndPoint(String cEndPoint) {
		this.cEndPoint = cEndPoint;
	}
	
	public String getcUrlPrefix() {
		return cUrlPrefix;
	}

	public void setcUrlPrefix(String cUrlPrefix) {
		this.cUrlPrefix = cUrlPrefix;
	}

	public String getcUrlPostfix() {
		return cUrlPostfix;
	}

	public void setcUrlPostfix(String cUrlPostfix) {
		this.cUrlPostfix = cUrlPostfix;
	}

	public String getcTraitCount() {
		return cTraitCount;
	}

	public void setcTraitCount(String cTraitCount) {
		this.cTraitCount = cTraitCount;
	}
	
	public String getjName() {
		return jName;
	}

	public void setjName(String jName) {
		this.jName = jName;
	}

	public String getjId() {
		return jId;
	}

	public void setjId(String jId) {
		this.jId = jId;
	}

	public String getjImage() {
		return jImage;
	}

	public void setjImage(String jImage) {
		this.jImage = jImage;
	}

	public String getjDesc() {
		return jDesc;
	}

	public void setjDesc(String jDesc) {
		this.jDesc = jDesc;
	}

	public String getjExt() {
		return jExt;
	}

	public void setjExt(String jExt) {
		this.jExt = jExt;
	}

	public String getjIHash() {
		return jIHash;
	}

	public void setjIHash(String jIHash) {
		this.jIHash = jIHash;
	}

	public String getjAName() {
		return jAName;
	}

	public void setjAName(String jAName) {
		this.jAName = jAName;
	}

	public String getjType() {
		return jType;
	}

	public void setjType(String jType) {
		this.jType = jType;
	}

	public String getjValue() {
		return jValue;
	}

	public void setjValue(String jValue) {
		this.jValue = jValue;
	}
}
