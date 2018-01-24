package Wifi_Data;


import General.*;
import java.util.*;

/**
 * This class defines a Row object, its fields and some functions needed to work properly with this class throughout our project.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 21.12.2017
 */

//Row object fields
public class Row {
	Time time; //first seen
	String id;
	double lat;
	double lon;
	double alt;
	int wifi_count; //from 1 to 10
	ArrayList<Wifi> wifi;
	//Irrelevant Fields  - AuthMode, AccuracyMeters
	
	/**
	 * Empty constructor - builds a Row object with zeroed values.
	 */
	public Row(){
		this.time=new Time();
		this.id = "";
		this.lat = 0;
		this.lon = 0;
		this.alt = 0;
		this.wifi_count=0;
		this.wifi = new ArrayList<Wifi>();
	}
	/**
	 * Getters and setters for this class:
	 * public String getTimeString() - Gets time field as string.
	 * public Date getTimeDate() - Gets time field as Time format.
	 * public void setTime(String time) - Sets time field using setDate from Time class.
	 * public String getId() - Gets Id field as string.
	 * public void setId(String id) - Sets Id as the string received.
	 * public double getLat() - Gets lat field as double.
	 * public boolean setLat(double lat) - Sets Lat as double, checking if the value is legal.
	 * public double getLon() - Gets Lon field as double.
	 * public boolean setLon(double lon) - Sets Lon as double, checking if the value is legal.
	 * public double getAlt() - Gets Alt field as double.
	 * public void setAlt(double alt) - Sets Alt as the double received.
	 * public int getWifi_count() - Gets the wifi_count field.
	 * public void setWifi_count(int size) - Sets the wifi_count as the int received (sent as the size of list from Csv).
	 * public ArrayList getWifi() - Returns the Wifi ArrayList in this row.
	 * public void setWifi(ArrayList wifi) - Sets the Wifi ArrayList to the list received.
	 * @return all the information - whatever function was used.
	 */
	public String getTimeString() {
		return this.time.getStringDATE();
	}
	public Date getTimeDate() {
		return this.time.getDateDATE();
	}
	public void setTime(String time) {
		this.time.setDATE(time);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}

	public boolean setLat(double lat) {
		if (lat<=90 && lat>=-90){
			this.lat = lat;
			return true;
		}else
			return false;
	}

	public double getLon() {
		return lon;
	}

	public boolean setLon(double lon) {
		if (lon<=180 && lon>=-180){
			this.lon = lon;
			return true;
		}else
			return false;	}

	public double getAlt() {
		return alt;
	}

	public void setAlt(double alt) {
		this.alt = alt;
	}

	public int getWifi_count() {
		return wifi_count;
	}
	public void setWifi_count(int size) {
		this.wifi_count=size;
	}

	public ArrayList<Wifi> getWifi() {
		return wifi;
	}

	public void addWifi(Wifi w) {
		this.wifi.add(w);
	}
	
	public void setWifi(ArrayList<Wifi> wifi) {
		this.wifi = wifi;
	}
	
	/**
	 * This function overrides the object toString function and returns the Row object in the wanted format, by the Row object fields.
	 * @return Row object in string.
	 */
	public String toString (){
	String str=time.getStringDATE()+","+id+","+lat+","+lon+","+alt+","+wifi_count+",";
	for(int i=0;i<wifi.size();i++)
		str+=wifi.get(i).toString();
	return str;
	}
	
	/**
	 * This function sorts an array list of Wifi objects by signal field.
	 * @param scans_byMac arraylist of wifi object.
	 */
	public static void sortingBySignal(ArrayList<Wifi> scans_byMac) {

		Collections.sort(scans_byMac,  new Comparator<Wifi>() {
			@Override
			public int compare(Wifi w1, Wifi w2) {
				return Double.compare(w1.getSignal(),w2.getSignal());
			}
		});
		//return scans_byMac;
	}
}
