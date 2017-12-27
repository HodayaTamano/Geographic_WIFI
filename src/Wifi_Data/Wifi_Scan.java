package Wifi_Data;

import java.io.*;
import java.util.*;
import Tests.*;
import Algorithms.*;
import General.*;

/** This class defines the wifi scan object, this object is an extension for Wifi class, and inherits from it.
* This object has three more fields - that represent location coordinates for a wifi scanned.
* @author Hodaya_Tamano
* @author Shir_Bentabou
* @version 21.12.2017
*/

public class Wifi_Scan extends Wifi{
	Wifi w;
	double lat;
	double lon;
	double alt;
	
	static Csv c = new Csv();
	
	/**Constructor
	 * This constructor receives all the fields of a Wifi_Scan object and inserts it to a new Wifi_Scan object.
	 */
	public Wifi_Scan(Wifi w, double lat, double lon, double alt){ //constructor that receives wifi object
		this.frequency=w.frequency;
		this.signal = w.signal;
		this.ssid = w.ssid;
		this.mac = w.mac;
		this.alt = alt;
		this.lat = lat;
		this.lon = lon;
	}
	
	/**Copy Constructor
	 * This constructor receives a Wifi_Scan object and copies it to a new Wifi_Scan object.
	 */
	public Wifi_Scan(Wifi_Scan wscan){ //copy constructor
		this.frequency=wscan.frequency;
		this.signal = wscan.signal;
		this.ssid = wscan.ssid;
		this.mac = wscan.mac;
		this.alt = wscan.alt;
		this.lat = wscan.lat;
		this.lon = wscan.lon;
	}
	
	/**Empty Constructor
	 * This constructor creates a Wifi_Scan object with all it's fields zeroed.
	 */
	public Wifi_Scan(){ //empty constructor
		w=new Wifi();
		lat = 0;
		lon = 0;
		alt = 0;
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
	
	public Wifi getWifi() {
		return this.w;
	}
	
	public void setWifi(Wifi w) {
		this.w.frequency = w.frequency;
		this.w.signal = w.signal;
		this.w.ssid = w.ssid;
		this.w.mac = w.mac;
	}
	public static Csv getC() {
		return c;
	}
	public static void setC(Csv c) {
		Wifi_Scan.c = c;
	}
	
	/**
	 * This function overrides the object toString function and returns the Wifi_Scan object in the wanted format, by the Wifi_Scan object fields.
	 * @return str - Wifi_Scan object in string.
	 */
	public String toString (){
		String str=this.w.toString()+this.lat+","+this.lon+","+this.alt+",";
		return str;
	}
}