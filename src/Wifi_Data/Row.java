
import java.util.ArrayList;
import java.util.Date;


/**
 * This class defines a Row object, its fields and some functions needed to work properly with this class throughout our project.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 1.0.0 - 9.11.17
 */

//Row object fields
public class Row {
	Time time; //first seen
	String id;
	double lat;
	double lon;
	int alt;
	int wifi_count; //from 1 to 10
	ArrayList<Wifi> wifi;

	//Irrelevant Fields  - AuthMode, AccuracyMeters
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
 * public double getLat() - Gets Lat field as double.
 * public boolean setLat(double lat) - Sets Lat as double, checking if the value is legal.
 * public double getLon() - Gets Lon field as double.
 * public boolean setLon(double lon) - Sets Lon as double, checking if the value is legal.
 * public int getAlt() - Gets Alt field as int.
 * public void setAlt(int alt) - Sets Alt as the int received.
 * public int getWifi_count() - Gets the wifi_count field.
 * public void setWifi_count(int size) - Sets the wifi_count as the int received (sent as the size of list from Csv).
 * public ArrayList<Wifi> getWifi() - Returns the Wifi ArrayList in this row.
 * public void setWifi(ArrayList<Wifi> wifi) - Sets the Wifi ArrayList to the list received.
 * @return
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
	///
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	///
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

	public int getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
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

	public void setWifi(ArrayList<Wifi> wifi) {
		this.wifi = wifi;
	}
	/**
	 * This function overrides the object toString function and returns the Row object in the wanted format, by the Row object fields.
	 * @return str - Row object in string.
	 */
	public String toString (){
	String str=time.getStringDATE()+","+id+","+lat+","+lon+","+alt+","+wifi_count+",";
	for(int i=0;i<wifi.size();i++)
		str+=wifi.get(i).toString();
	return str;
	}
}