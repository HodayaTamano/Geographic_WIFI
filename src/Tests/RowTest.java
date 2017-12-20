package Tests;
import Wifi_Data.*;
import Algorithms.*;
import General.*;
import static org.junit.Assert.*;

import org.junit.Test;

import Wifi_Data.Row;
/**
 * test_row - This class tests the meaningful function in Row object class.
 * If any of them does not work properly, a fail notice will be thrown.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 */
public class RowTest {
/**
 * This function test the constructor of the Row class.
 * It checks to see if all fields were initialized as they are supposed to be. If not - the test fails.	
 */
	@Test
	public void testRow() {
		Row r= new Row();
		if (r.getId()!="" || r.getLat()!=0 || r.getLon()!=0 || r.getAlt()!=0 || r.getWifi_count()!=0 || r.getWifi().size()!=0)
			fail();
	}	
/**
 * Test_setLat - This function tests the setLat function of the Row object class.
 * It sets the value of the lat field only if the value given is between -90 to 90.
 */
	@Test
	public void testSetLat() {
		Row r = new Row();
		double lat_trial = 108.92;
		r.setLat(lat_trial);
		if (r.getLat()==lat_trial)
			fail("Receives wrong input");
	}
/**
 * Test_setLon - This function tests the setLonfunction of the Row object class.
 * It sets the value of the lon field only if the value given is between -180 to 180. 
 */
	@Test
	public void testSetLon() {
		Row r = new Row();
		double lon_trial = -200.5156;
		r.setLon(lon_trial);
		if (r.getLon()==lon_trial)
			fail("Receives wrong input");
	}
}
