import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
/**
 * This class tests KML_by_lib class.
 *
 */
public class KML_by_libTest {

	@Test
	public void testFilter_by_ID() {
		//ArrayList <>
	
	}

	@Test
	public void testFilter_by_Time() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFilter_by_Location() {
		//fail("Not yet implemented");
	}
/**
 * This functions tests remove_lows and should_delete_wifi.
 * The function receives a file with two listings of the same wifi with different signals and removes the lowest one
 * (the file has to pass through pass_to_kml function first to be in the right format).
 */
	@Test
	public void testRemove_lows() {
		
	//	fail("Not yet implemented");
	}

/**
 * This functions tests both kml_file and placemarkWithChart functions.
 * It will test the following cases:
 * A table with wrong fields - Should skip the row.
 * 
 */
	@Test
	public void testKml_file() {
		//fail("Not yet implemented");
	}
/**
 * 
 * This function tests pass_to_kml function. It receives paths to folders with the next cases:
 * File columns != 46
 * File with wrong format for fields in row (has to return empty file).
 */
	@Test
	public void testPass_to_kml() {
		//fail("Not yet implemented");
	}

}
