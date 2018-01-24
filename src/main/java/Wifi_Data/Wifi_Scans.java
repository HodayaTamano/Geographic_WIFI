package Wifi_Data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/** This class defines the Wifi_Scans object, which is an array list of Wifi_Scan objects.
* @author Hodaya_Tamano
* @author Shir_Bentabou
* @version 27.12.2017
*/


public class Wifi_Scans {
	ArrayList<Wifi_Scan> all_scans;
	
	public Wifi_Scans (){
		this.all_scans = new ArrayList<Wifi_Scan>();
	}

	public Wifi_Scan get_scan(int i) {
		return this.all_scans.get(i);
	}

	public boolean add_wifiScan(Wifi_Scan w) {
		return this.all_scans.add(w);
	}
	
	/**
	 * This function returns the size of Wifi_Scans.
	 * @return The list size.
	 */
	public int getSize(){
		return this.all_scans.size();
	}
	/**
	 * This function removes the unnecessary scan from a Wifi_Scans list after it was sorted by 'pi' value 
	 * (measures resemblance)to the number of samples wanted by the users input.
	 * @param full_csv arraylist of rows.
	 * @param mac relevant mac.
	 * @param num - number of samples wanted
	 * @return ArrayList of num rows of Wifi_Scan objects
	 */
	public static ArrayList<Wifi_Scan> numOfPoints(ArrayList<Row> full_csv, String mac, int num) {
		ArrayList<Wifi_Scan> scansByNum = new ArrayList<Wifi_Scan>();
		scansByNum = getAllScans_byMac(full_csv, mac);
		if (scansByNum.size() < num){
			System.out.println("Not Enough Scans!");
		}
		else{
			for (int i=num; i<scansByNum.size(); i++)
				scansByNum.remove(i);
		}
		return scansByNum;
	}
	
	/**
	 * This function returns all the scans for a specific mac given to it.
	 * @param full_csv arraylist of rows.
	 * @param mac relevant mac.
	 * @return ArrayList of all Wifi_Scan objects that match the mac given
	 */
	public static ArrayList<Wifi_Scan> getAllScans_byMac(ArrayList<Row> full_csv, String mac) {
		ArrayList<Wifi_Scan> scans_byMac = new ArrayList<Wifi_Scan>();
		
		for (int i=0; i<full_csv.size(); i++){//pass throughout the rows in full csv
			Row current = new Row();
			current = full_csv.get(i);
			for (int j=0; j<current.getWifi_count(); j++){ //passing through the wifi's in a row
				Wifi w = new Wifi();
				w = current.getWifi().get(j);
				if (mac.equals(w.getMac())){ //if wifi was found insert to all scans by the same mac table(array list)
					Wifi_Scan myscan = new Wifi_Scan(w, current.getLat(), current.getLon(), current.getAlt());
					scans_byMac.add(myscan);
				}
			}
		}
		return scans_byMac;
	}
	
	/**
	 * This function returns a sorted array list of wifi scans for a specific Mac by strongest signal.
	 * @param scans_byMac arraylist of wifi scans.
	 * @return ArrayList of Wifi_Scan objects.
	 */
	public static ArrayList<Wifi_Scan> sortingBySignal(ArrayList<Wifi_Scan> scans_byMac) {
		//please note that we helped ourselves with the code of our colleague in: https://github.com/GeekCSA
		Collections.sort(scans_byMac,  new Comparator<Wifi_Scan>() {
			@Override
			public int compare(Wifi_Scan ws1, Wifi_Scan ws2) {
				return Double.compare(ws1.w.getSignal(),ws2.w.getSignal());
			}
		});
		return scans_byMac;
	}
	
	/**
	 * This function passes a Wifi_Scan object to an external file in the path given.
	 * @param wss wifi scan list.
	 * @param path string of path.
	 */
	public static void print_Scans(Wifi_Scans wss, String path){
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try{
			FileWriter fw = new FileWriter(path, false); //path to write file <--
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			for (int j=0; j<wss.getSize(); j++){
				pw.println(wss.get_scan(j).toString());
				//System.out.println(wss.get_scan(j).toString());
			}
			pw.flush();
			pw.close();
		}catch(Exception e){
			System.out.println("Couldn't write rows to table!");
		}
	}


}