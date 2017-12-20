package Algorithms;

import java.util.*;
import Wifi_Data.*;
import Tests.*;
import General.*;

public class Wifi_Samples {
	double pi;
	ArrayList<Wifi> samples;
	double lat;
	double lon;
	int alt; //as in row object

	public Wifi_Samples(){ 
		pi = 0;
		samples = new ArrayList<Wifi>();
		alt = 0;
		lat = 0;
		lon = 0;
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
	public void setAlt(int alt) {
		this.alt = alt;
	}
	
	public void addWifi(Wifi w){
		this.samples.add(w);
		
	}
	
	public Wifi_Samples(double lat, double lon, int alt){ //constructor that receives lat lon alt data
		this.pi = 0;
		this.alt = alt;
		this.lat = lat;
		this.lon = lon;
		this.samples = new ArrayList<Wifi>();
	}
}
