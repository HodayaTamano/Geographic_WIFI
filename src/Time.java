
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class defines a Time object, its fields and some functions needed to work properly with this class throughout our project.
 * This is intended to ease on us working with time and parsing it by one unified format.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 1.0.0 - 9.11.17
 */

public class Time {
	private SimpleDateFormat MyFormatTime;
	private Date DATE;
	
	/**
	 *Getters and setters for this class: 
	 * public Date getDateDATE() - This function returns time value in Date format.
	 * public String getStringDATE() - This function returns time value in string.
	 * public boolean setDATE() - This function sets time value in Date format.
	 */
	
	public Time() {
		this.MyFormatTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.DATE = new Date();
	}
	public Date getDateDATE() { //returns time&date at date format
		return DATE;
	}
	public String getStringDATE() {//returns time&date as String
		String str=MyFormatTime.format(DATE);
		return str;
	}
	public boolean setDATE(String str) 
	{
		try{
			this.DATE = this.MyFormatTime.parse(str);			
			return true;
		}catch(ParseException e){
			return false;}
	}

}
