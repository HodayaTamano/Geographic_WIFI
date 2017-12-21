package Algorithms;

import java.util.*;
import Wifi_Data.*;
import Tests.*;
import General.*;

/** This class is responsible for calculating the access point approximated location, given an arraylist of scans of the access point.
* @author Hodaya_Tamano
* @author Shir_Bentabou
* @version 21.12.2017
*/

public class Algorithm1 {

	/**This function receives an arraylist of scans of a specific access point and calculates its approximated location.
	 * @param scansByNum
	 * @return array of doubles with lat, lon, alt (coordinates)
	 */
	public static double [] ap_location (ArrayList<Wifi_Scan> scansByNum){
		double [] w_sum = new double [3];//loc
		//loc[0] - lat, loc[1] - lon, loc[2] - alt
		//loc[0]=scansByNum.
		double sumofweight=0;
		for (int i=0; i<scansByNum.size(); i++){
			double weight = 1 /Math.pow(scansByNum.get(i).getWifi(scansByNum.get(i)).getSignal(),2);
			w_sum[0] += scansByNum.get(i).getLat()*weight;
			w_sum[1] += scansByNum.get(i).getLon()*weight;
			w_sum[2] += scansByNum.get(i).getAlt()*weight;
			sumofweight += weight;
		}
		w_sum[0] = w_sum[0]/sumofweight;
		w_sum[1] = w_sum[1]/sumofweight;
		w_sum[2] = w_sum[2]/sumofweight;
		return w_sum;
	}
	
	/**This function receives an arraylist of samples (Wifi_Samples objects) of a specific access point and calculates its approximated location.
	 * This function was built to ease the use of algorithm 1 by algorithm 2.
	 * @param scansByNum
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

	
}
