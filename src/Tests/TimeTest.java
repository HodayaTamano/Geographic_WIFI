package Tests;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import General.Time;

/**
 * This class tests meaningful function in Time class.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 21.12.2017
 */
public class TimeTest {
	 /**
	  * This function tests the original function by sending the String received to the original function through a Time object,
	  * and parsing it manually to the format wanted. If they are not equal - the test will fail.
	  */
	@Test
	public void testTime() {
		Time t = new Time();
		String s = "2017/10/27 16:12:01";
		SimpleDateFormat FormatTime = new SimpleDateFormat();
		t.setDATE(s);
		try {
			if (!(FormatTime.parse(s).equals(t)))
				fail();
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
}
