package Algorithms;

import java.util.*;
import Wifi_Data.*;


/**
 * This class defines the Wifi_Samples object which is used to hold samples of scans for an access point for the
 * use of Algorithm 2 in this package. It holds an arraylist of Wifi objects, location variables and pi representing 
 * the 'weight' of the sample - it's resemblance to the input sample given by the user. 
 * (Locates samples that include one or more of the mac's that are given by the user.)
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 21.12.2017
 */

public class Wifi_Samples {
	double pi;
	ArrayList<Wifi> samples;
	double lat;
	double lon;
	double alt; //as in row object

	/**Empty Constructor
	 * This constructor creates a new Wifi_Sample object with all it's values zeroed.
	 */
	public Wifi_Samples(){ 
		pi = 0;
		samples = new ArrayList<Wifi>();
		lat = 0;
		lon = 0;
		alt = 0;
	}
	public double getPi() {
		return pi;
	}
	public void setPi(double pi) {
		this.pi = pi;
	}
	public ArrayList<Wifi> getSamples() {
		return samples;
	}
	public void setSamples(ArrayList<Wifi> samples) {
		this.samples = samples;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getAlt() {
		return alt;
	}
	public void setAlt(double alt) {
		this.alt = alt;
	}
	
	public void addWifi(Wifi w){
		this.samples.add(w);
		
	}
	/**
	 * This constructor receives location data only and inserts the values to a new Wifi_Samples object.
	 * All the other values are zeroed.
	 * @param lat latitude
	 * @param lon longitude
	 * @param alt altitude
	 */
	public Wifi_Samples(double lat, double lon, double alt){ //constructor that receives lat lon alt data
		this.pi = 0;
		this.alt = alt;
		this.lat = lat;
		this.lon = lon;
		this.samples = new ArrayList<Wifi>();
	}
	
	/**
	 *  This function sorts an array list of Wifi_Samples by pi field, that represents the resemblance of a 
	 *  sample to the input sample given.
	 * @param samples Wifi samples.
	 */
	public static void sortingByPi(ArrayList<Wifi_Samples> samples) {

		Collections.sort(samples,  new Comparator<Wifi_Samples>() {
			@Override
			public int compare(Wifi_Samples ws1, Wifi_Samples ws2) {
				return Double.compare(ws1.getPi(),ws2.getPi());
			}
		});
		
	}
}

