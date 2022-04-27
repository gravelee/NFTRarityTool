package com.nftrarity.com.nftrarity.maven.eclipse;

import java.util.Comparator;
import java.util.Map;

class ValueComparator implements Comparator<Integer> {
	
    Map<Integer, Double> base = null;

    public ValueComparator( Map<Integer, Double> base) {
    	
        this.base = base;
    }
    
	@Override
	public int compare(Integer a, Integer b) {
		
		if (base.get(a) >= base.get(b)) {
			
            return -1;
            
        } else {
        	
            return 1;
        }
	}
}

/*
Boolean [] isProcessed = new Boolean[cNum];

for( int i=0; i<cNum; i++)
	isProcessed[i] = false;

Integer counter = 0;

while( counter != cNum) {
	
	try {
		
		Object a = new Object();
		a.wait(2);
		
	}catch(Exception e) {}
	
	for( int i=0; i<cNum; i++) {
		
		if( !isProcessed[i] && tempRes[i].isDone()) {
			
			Object obj = tempRes[i].get();
			
			if( obj != null)
				jStrings[i] = (String) obj;
			else {
				
				jStrings[i] = null;
			}
			
			NFTCTools.getProgress(i,start,cNum,counter,"Reading.. ");
			isProcessed[i] = true;
			counter++;
		}
	}
}*/