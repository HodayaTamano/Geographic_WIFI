package Wifi_Data;

import java.io.*;
import java.util.*;
import Tests.*;
import Algorithms.*;
import General.*;

public class Wifi_Scan extends Wifi{
	Wifi w;
	double lat;
	double lon;
	double alt;
	
	static Csv c = new Csv();
	
	public Wifi_Scan(Wifi w, double lat, double lon, double alt){ //constructor that receives wifi object
		this.frequency=w.frequency;
		this.signal = w.signal;
		this.ssid = w.ssid;
		this.mac = w.mac;
		this.alt = alt;
		this.lat = lat;
		this.lon = lon;
	}
	public Wifi_Scan(Wifi_Scan wscan){ //copy constructor
		this.frequency=wscan.frequency;
		this.signal = wscan.signal;
		this.ssid = wscan.ssid;
		this.mac = wscan.mac;
		this.alt = wscan.alt;
		this.lat = wscan.lat;
		this.lon = wscan.lon;
	}
	public Wifi_Scan(){ //empty constructor
		w=new Wifi();
		alt = 0;
		lat = 0;
		lon = 0;
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
	public Wifi getWifi(Wifi_Scan ws) {
		return ws.w;
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


}