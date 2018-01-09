package Filter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import General.*;
import Wifi_Data.*;
import Algorithms.*;
import Tests.*;
import geographic_wifi.*;

public class FilterID {
	
	/**
	 * This function filters the data in data_kml file by the ID String that the user inputs,
	 *  and sends it to kml_file for it to be printed in a file.
	 * @param data_kml
	 * @param filter_ID
	 * @throws FileNotFoundException
	 */
	public static void filterAND_by_ID (ArrayList<Row> data_kml, String filter_ID) throws FileNotFoundException{
		int i=0;
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			if (r.getId()!=filter_ID){
				data_kml.remove(r);
				i--;
			}
			i++;
		}
		KML_by_lib.kml_file(data_kml);
	}
	
	
	/**
	 * This function filters the data in data_kml file by NOT!!! ID String that the user inputs,
	 *  and sends it to kml_file for it to be printed in a file.
	 * @param data_kml
	 * @param filter_ID
	 * @throws FileNotFoundException
	 */
	public static void filterNOT_by_ID (ArrayList<Row> data_kml, String filter_ID) throws FileNotFoundException{
		int i=0;
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			if (r.getId()==filter_ID){
				data_kml.remove(r);
				i--;
			}
			i++;
		}
		KML_by_lib.kml_file(data_kml);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
