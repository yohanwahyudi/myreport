package test.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class Test {
	
	public static void main(String args[]) {
		System.out.println("current week: "+getCurrentWeek());		
		System.out.println("current month: "+getCurrentMonthString());		
		System.out.println("current week of year: "+getCurrentWeekYear());
		System.out.println();
		
		LocalDate today = LocalDate.now();

	    LocalDate monday = today.with(previousOrSame(MONDAY));
	    LocalDate sunday = today.with(nextOrSame(SUNDAY));

	    System.out.println("Today: " + today);
	    System.out.println("Monday of the Week: " + monday);
	    System.out.println("Sunday of the Week: " + sunday);
	    
	    LocalDate twelves = LocalDate.of( 2018 , 6 , 21 );
	    int week = twelves.get ( IsoFields.WEEK_OF_WEEK_BASED_YEAR );
	    int weekYear = twelves.get ( IsoFields.WEEK_BASED_YEAR );
	    System.out.println();
	    System.out.println(week);
	    System.out.println(weekYear);
		
	}
	
	private static int getCurrentWeek() {
	    LocalDate date = LocalDate.now();
	    WeekFields weekFields = WeekFields.of(Locale.getDefault());
	    return date.get(weekFields.weekOfMonth());
	}
	
	private static int getCurrentMonth() {
		 int month;
	        GregorianCalendar date = new GregorianCalendar();      
	        month = date.get(Calendar.MONTH);
	        month = month+1;
	        
	        return month;
	}
	
	private static String getCurrentMonthString() {
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
		 
		 return sdf.format(date);
	}
	
	private static int getCurrentWeekYear() {
	    LocalDate date = LocalDate.now();
	    WeekFields weekFields = WeekFields.of(Locale.getDefault());
//	    return date.get(weekFields.weekOfYear());
	    
	    Calendar cal = Calendar.getInstance();	    
	    System.out.println(cal.getMinimalDaysInFirstWeek());
	    
	    return cal.get(Calendar.WEEK_OF_YEAR);
	    
	}

}
