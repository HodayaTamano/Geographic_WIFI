package Filter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import General.*;
import Wifi_Data.*;

public class FilterLocation {

	
	
	/**
	 * This function filters the data in data_kml file by the location range calculated by a point and area_time  that the user inputs,
	 * and sends it to kml_file for it to be printed in a file.
	 * @param data_kml arraylist of rows.
	 * @param from_lat latitude wanted.
	 * @param from_lon longitude wanted.
	 * @param radius radius wanted.
	 * @throws FileNotFoundException if file is not found.
	 */
	public static void filterAND_by_Location (ArrayList<Row> data_kml, double from_lat, double from_lon, double radius) throws FileNotFoundException{
		int i=0;
		double time_area = 0.0166666667;
		//radius*time area
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			if (r.getLat()>(from_lat+(radius*time_area)) || r.getLat()<(from_lat-(radius*time_area))
					|| r.getLon()>(from_lon+(radius*time_area)) || r.getLon()<(from_lon-(radius*time_area))){
				data_kml.remove(r);
				i--;
			}
			i++;
		}
		KML_by_lib.kml_file(data_kml);
	}
	
	
	
	/**
	 * This function filters the data in data_kml file by the NOT!!! location range calculated by a point and area_time  that the user inputs,
	 * and sends it to kml_file for it to be printed in a file.
	 * @param data_kml arraylist of rows.
	 * @param from_lat latitude wanted.
	 * @param from_lon longitude wanted.
	 * @param radius radius wanted.
	 * @throws FileNotFoundException if file is not found.
	 */
	public static void filterNOT_by_Location (ArrayList<Row> data_kml, double from_lat, double from_lon, double radius) throws FileNotFoundException{
		int i=0;
		double time_area = 0.0166666667;
		//radius*time area
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			if (!(r.getLat()>(from_lat+(radius*time_area)) && r.getLat()<(from_lat-(radius*time_area))
					&& r.getLon()>(from_lon+(radius*time_area)) && r.getLon()<(from_lon-(radius*time_area)))){
				data_kml.remove(r);
				i--;
			}
			i++;
		}
		KML_by_lib.kml_file(data_kml);
	}


}
