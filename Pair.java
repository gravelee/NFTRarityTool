package com.nftrarity.com.nftrarity.maven.eclipse;

/*
 * 	This is a helping class that helps to represent pairs of strings.
 */
class Pair {
	
	private String key = null;
	private String value = null;
	
	Pair( String key, String value) {
		
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		
		return key;
	}
	
	public String getValue() {
		
		return value;
	}
	
	public void setKey( String key) {
		
		this.key = key;
	}
	
	public void setValue( String value) {
		
		this.value = value;
	}

	@Override
    public boolean equals( Object obj) {
    	
		Pair pair = (Pair) obj;
		
    	if ( key.equals(pair.getKey()) && 
    		value.equals(pair.getValue())) {
    		
			return true;
    	}
    	
		return false;
    }

	public String printPair() {
		
		return "Pair(" + key + "," + value + ")";
	}
}
