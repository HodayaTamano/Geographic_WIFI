package Wifi_Data;

import java.util.*;

public class Wifi_Scans {
	static ArrayList<Wifi_Scan> all_scans = new ArrayList<Wifi_Scan>();

	public static Wifi_Scan get_scan(int i) {
		return all_scans.get(i);
	}

	public static boolean add_wifiScan(Wifi_Scan w) {
		return all_scans.add(w);
	}
	
	public static ArrayList<Wifi_Scan> numOfPoints(String mac, int num) {
		ArrayList<Wifi_Scan> scansByNum = new ArrayList<Wifi_Scan>();
		scansByNum = getAllScans_byMac(mac);
		//if (scansByNum.size() < num)
		//num<=0
		for (int i=num; i<scansByNum.size(); i++)
			scansByNum.remove(i);
		
		return scansByNum;
	}
	
	//this function returns a sorted array list of wifi scans for a specific Mac by signal
	public static ArrayList<Wifi_Scan> getAllScans_byMac(String mac) {
		ArrayList<Wifi_Scan> scans_byMac = new ArrayList<Wifi_Scan>();
		for (int i=0; i<all_scans.size(); i++){
			if (all_scans.get(i).getMac().equals(mac)){
				scans_byMac.add(all_scans.get(i));
			}
		}

		sortingBySignal(scans_byMac);
		return scans_byMac;
	}
	public static ArrayList<Wifi_Scan> sortingBySignal(ArrayList<Wifi_Scan> scans_byMac) {
		//please note that we helped ourselves with the code of our colleague in: https://github.com/GeekCSA
		Collections.sort(scans_byMac,  new Comparator<Wifi_Scan>() {
			@Override
			public int compare(Wifi_Scan ws1, Wifi_Scan ws2) {
				return Integer.compare(ws1.w.getSignal(),ws2.w.getSignal());
			}
		});
		return scans_byMac;
	}

	
}