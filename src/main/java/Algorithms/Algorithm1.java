package Algorithms;

import java.util.*;
import Wifi_Data.*;

/** This class is responsible for calculating the access point approximated location, given an arraylist of scans of the access point.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 27.12.2017
 */

public class Algorithm1 {

	final static int numOfScans = 4; //global variable to define number of scans wanted to calculate
	
	
	/**
	 * This function receives an arraylist of scans of a specific access point and calculates its approximated location.
	 * @param scansByNum The list of scans.
	 * @return array of doubles with lat, lon, alt (coordinates)
	 */
	public static double [] ap_location (ArrayList<Wifi_Scan> scansByNum){
		
		double [] w_sum = new double [3];
		double sumofweight=0;
		for (int i=0; i<scansByNum.size(); i++){
			double signal = scansByNum.get(i).getSignal();
			double weight = 1 /Math.pow(signal,2);
			w_sum[0] += scansByNum.get(i).getLat()*weight;
			w_sum[1] += scansByNum.get(i).getLon()*weight;
			w_sum[2] += scansByNum.get(i).getAlt()*weight;
			sumofweight += weight;
		}
		w_sum[0] = w_sum[0]/sumofweight;
		w_sum[1] = w_sum[1]/sumofweight;
		w_sum[2] = w_sum[2]/sumofweight;
		//System.out.println(Arrays.toString(w_sum));
		return w_sum;
	}

	/**
	 * This function receives an arraylist of samples (Wifi_Samples objects) of a specific access point and calculates its approximated location.
	 * This function was built to ease the use of algorithm 1 by algorithm 2.
	 * @param samples The list of scans.
	 * @return array of doubles with lat, lon, alt (coordinates)
	 */
	public static double [] user_loc (ArrayList<Wifi_Samples> samples){

		double [] w_sum = new double [3];
		double sumofweight=0;
		for (int i=0; i<samples.size(); i++){
			double weight = samples.get(i).pi;
			w_sum[0] += samples.get(i).getLat()*weight;
			w_sum[1] += samples.get(i).getLon()*weight;
			w_sum[2] += samples.get(i).getAlt()*weight;
			sumofweight += weight;
		}
		w_sum[0] = w_sum[0]/sumofweight;
		w_sum[1] = w_sum[1]/sumofweight;
		w_sum[2] = w_sum[2]/sumofweight;
		return w_sum;
	}

	/**
	 * This function receives a full csv file and searches every mac to calculate the access's point location
	 * by scans in the csv file.
	 * @param full_csv Whole CSV file.
	 * @return Wifi Scans.
	 */
	public static Wifi_Scans findMacScans(ArrayList<Row> full_csv){

		Wifi_Scans ws = new Wifi_Scans();

		for (int i=0; i<full_csv.size(); i++){//pass throughout the rows in full csv
			Row current = new Row();
			current = full_csv.get(i);
			for (int j=0; j<current.getWifi_count(); j++){ //passing through the wifis in a row

				Wifi w = new Wifi();
				w = current.getWifi().get(j);
				String mac = w.getMac();
				boolean exists = false;
				//if first row - add scan
				if(ws.getSize() == 0){
					Wifi_Scan scan = new Wifi_Scan();
					scan.setWifi(w);
					ArrayList<Wifi_Scan> calc = new ArrayList<Wifi_Scan>();
					double [] loc = new double[3];
					calc = Wifi_Scans.numOfPoints(full_csv, mac, numOfScans);//all the process is in numOfPoints
					loc = ap_location(calc);
					scan.setLat(loc[0]);
					scan.setLon(loc[1]);
					scan.setAlt(loc[2]);
					ws.add_wifiScan(scan);
				}else{
					//if not first row - search the file that will be returned to see if the mac already exists
					for (int k=0; k<ws.getSize(); k++){ //passing through list to see if we already have this mac
						if (mac.equals(ws.get_scan(k).getWifi().getMac()))
							exists=true;
					}
					if (!exists){
						Wifi_Scan scan = new Wifi_Scan();
						scan.setWifi(w);
						ArrayList<Wifi_Scan> calc = new ArrayList<Wifi_Scan>();
						double [] loc = new double[3];
						calc = Wifi_Scans.numOfPoints(full_csv, mac, numOfScans); //all the process is in numOfPoints
						loc = ap_location(calc);
						scan.setLat(loc[0]);
						scan.setLon(loc[1]);
						scan.setAlt(loc[2]);
						ws.add_wifiScan(scan);
					}
				}
			}		

		}
		return ws;
	}
}
