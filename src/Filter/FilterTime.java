package Filter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import General.*;
import Wifi_Data.*;
import Algorithms.*;
import Tests.*;
import geographic_wifi.*;

public class FilterTime {
	
	/**
	 * This function filters the data in data_kml file by the NOT!! time range that the user inputs by format (yyyy-MM-dd hh:mm:ss) ,
	 * and sends it to kml_file for it to be printed in a file.
	 * 
	 * @param data_kml
	 * @param filter_begin
	 * @param filter_end
	 * @throws FileNotFoundException
	 */
	public static void filterNOT_by_Time (ArrayList<Row> data_kml, Time filter_begin, Time filter_end) throws FileNotFoundException{
		int i=0;
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			Time d = new Time();
			d.setDATE(r.getTimeString());
			if (!(d.getDateDATE().before(filter_begin.getDateDATE()) && d.getDateDATE().after(filter_end.getDateDATE())))
				{
					data_kml.remove(r);
					i--;
				}
			i++;
		}
		KML_by_lib.kml_file(data_kml);
	}
	
	
	/**
	 * This function filters the data in data_kml file by the time range that the user inputs by format (yyyy-MM-dd hh:mm:ss) ,
	 * and sends it to kml_file for it to be printed in a file.
	 * 
	 * @param data_kml
	 * @param filter_begin
	 * @param filter_end
	 * @throws FileNotFoundException
	 */
	public static void filterAND_by_Time (ArrayList<Row> data_kml, Time filter_begin, Time filter_end) throws FileNotFoundException{
		int i=0;
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			Time d = new Time();
			d.setDATE(r.getTimeString());
			if (d.getDateDATE().before(filter_begin.getDateDATE()) || d.getDateDATE().after(filter_end.getDateDATE()))
				{
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
