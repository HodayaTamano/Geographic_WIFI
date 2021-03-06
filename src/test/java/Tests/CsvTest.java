package Tests;


import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import General.Csv;

/**
 *  THIS IS A TEST
 * This class tests Csv class.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 21.12.2017
 */
public class CsvTest {
	
/**
 * test_Filenames - This function test filenames function in Csv class.
 * If the number of csv's manually inserted is not the same number returned from the function,
 * there is a problem in the filenames function.
 */
	//change path here <------
	@Test
	public void testFilenames() {
		String path = "C:/Users/Leandrog/git/GW2"; //change path here to a path with NO WIGLE FILES! <--
		ArrayList<String> results = new ArrayList<String>();
		results = Csv.filenames(path);
		assertTrue(results.isEmpty());
	}
	/**
	 * The idea of this test is to create a path with different kinds of wrong csv's and check if in the outcome we will 
	 * receive a file or not (we are not supposed to).
	 * Cases we want to check:
	 * first line != 7 columns
	 * second line & throughout != 11 columns
	 * empty file
	 * file with 'problematic' row (empty field or in wrong format) - has to give an empty file
	 * pass_to_file function is private and will be checked in this test as well.
	 */
	@Test
	public void testPass_to_table() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPass_to_file() {
	//fail("Not yet implemented");
	}

}
