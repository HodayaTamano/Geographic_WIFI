package Algorithms;

import java.util.*;
import Wifi_Data.*;
import Tests.*;
import General.*;

public class Algorithm1 {

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

}
