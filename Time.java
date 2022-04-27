package com.nftrarity.com.nftrarity.maven.eclipse;

import java.time.Duration;
import java.time.Instant;

public class Time {

	private Instant time;

	Time( ){
		
		this.time = Instant.now();
	}
	
	public Instant getTime() {
		
		return time;
	}
	
	public static Duration interval( Instant time1, Instant time2) {
		
		Duration interval = Duration.between(time1,time2);
		return interval;
	}
	
	public static void pInterval( Duration interval) {
		
		Integer hours = (int) interval.toHours();
    	Integer minutes = (int) interval.toMinutes() - hours*60;
    	Integer seconds = (int) interval.toSeconds() - hours*3600 - minutes*60;
    	
    	System.out.println( hours + ":" + minutes + ":" + seconds + ", (H:M:S).");
	}
}
