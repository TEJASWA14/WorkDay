package wrkday_pom;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility {
	
	public String toCamelCase1(String word)
	{
		String firstLetter	= word.substring(0, 1).toUpperCase();
		String restLetters	= word.substring(1, word.length()).toLowerCase();
		System.out.println(firstLetter+restLetters);
		return(firstLetter+restLetters);
	}
	
	 public String getDaysBetweenDates(int noOfDays)
	 { 
	     Calendar cal = Calendar.getInstance();	
	     cal.add(Calendar.DATE, noOfDays);	    
	     SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy"); 	    
	     String dates = formatter1.format(cal.getTime());
	     return dates;   
	 }
	 
	 public String[] splitString(String mgr_Name, String regExp){
	    	
	    	String[] a;
	    	a = mgr_Name.split(regExp);
	    	return a;
	  }
}
