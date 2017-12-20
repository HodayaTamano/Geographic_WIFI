package Tests;
import Tests.*;

import Algorithms.*;
import General.*;
import static org.junit.Assert.*;

import org.junit.Test;

import Wifi_Data.Wifi;

/**
 * test_wifi - This class tests the meaningful function in Wifi object class.
 * If any of them does not work properly, a fail notice will be thrown.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 */
public class WifiTest {

/**
 * Test_equalsTo - This function tests the equals_to function in Wifi object class.
 * It checks if the function returns true for two different Wifi objects.
 */
	@Test
	public void testEqualsTo() {
		Wifi w = new Wifi();
		w.setSsid("a");
		Wifi j = new Wifi();
		j.setSsid("c");
		if (j.equalsTo(w))
			fail();
	}

}
