package Filter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import General.*;
import Wifi_Data.*;

public class FilterTime {
	
	/**
	 * This function filters the data in data_kml file by the NOT!! time range that the user inputs by format (yyyy-MM-dd hh:mm:ss) ,
	 * and sends it to kml_file for it to be printed in a file.
	 * 
	 * @param data_kml arraylist of rows.
	 * @param filter_begin time wanted to start from.
	 * @param filter_end time wanted to end filter.
	 * @throws FileNotFoundException if file is not found.
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
	 * @param data_kml arraylist of rows.
	 * @param filter_begin time wanted to start from.
	 * @param filter_end time wanted to end filter.
	 * @throws FileNotFoundException if file is not found.
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
