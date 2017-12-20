package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;
import Wifi_Data.*;
import Tests.*;
import General.*;

public class Algorithm2 {

	final static int square = 2; 
	final static int norm = 10000; 
	final static double sig_diff = 0.4; 
	final static int min_diff = 3;
	static int no_signal = -120; 
	final static int diff_no_sig = 100; 


	public static ArrayList<Row> locations_csv(ArrayList<Row> missing_location, ArrayList<Row> full_csv){

		ArrayList<Row> final_csv = new ArrayList<Row>();

		for (int i=0; i<missing_location.size(); i++){
			Row current = new Row();
			current = missing_location.get(i);
			search_in_csv(current, full_csv);
		}
		return final_csv;
	}


	private static ArrayList<Wifi_Samples> search_in_csv(Row original, ArrayList<Row> full_csv){

		ArrayList<Wifi_Samples> answer = new ArrayList<Wifi_Samples>();

		for (int i=0; i<full_csv.size(); i++){//pass throughout the csv and search for matching samples by mac
			Row current = new Row();
			current = full_csv.get(i);
			for (int j=0; j<original.getWifi().size(); j++){
				Wifi original_wifi = original.getWifi().get(j);
				Wifi_Samples ws = new Wifi_Samples(current.getLat(), current.getLon(), current.getAlt());
				for (int k=0; k<current.getWifi().size(); k++){
					Wifi current_wifi = original.getWifi().get(j);
					if (current_wifi.getMac().equals(original_wifi.getMac())){
						ws.addWifi(current_wifi);
					}
				}
				answer.add(ws);
				//user_location(ws, original);
			}	
		}
		return answer;
	}


	public static double [] user_location (ArrayList<Wifi_Samples> ws, Row original){
		//loc[0] - lat, loc[1] - lon, loc[2] - alt
		double [] loc = new double[3];

		for (int i=0; i<ws.size(); i++){//throughout the samples
			double pi=1;
			for (int j=0; j<ws.get(i).getSamples().size(); i++){//throught the wifi sample arraylist
				
				for (int k=0; j<original.getWifi().size(); i++){//throught the original wifi arraylist
					int diff=0;
					if (ws.get(i).getSamples().get(j).getMac().equals(original.getWifi().get(k).getMac())){
						if (ws.get(i).getSamples().get(j).getSignal() == no_signal){
							ws.get(i).getSamples().get(j).setSignal(diff_no_sig);
						}else{
							diff=Math.abs(ws.get(i).getSamples().get(j).getSignal()-original.getWifi().get(k).getSignal());
							diff=Math.max(diff,min_diff);
						}
						pi = pi*norm/Math.pow(diff, sig_diff)*Math.pow(original.getWifi().get(k).getSignal(), square);
					}
				}
				
			}
			ws.get(i).setPi(pi);
			//select strongest samples by pi value
			//calculate location answer using algo1
			//return location answer
		}
		return loc;
	}

	/**
	* Returns the k rows with the highest y values.
	* Implemented using Streams.
	*/
	//	public List<WifiSample> kRowsWithHighestY(int k) {
	//		return samples
	//			.stream()
	//			.parallel()
	//			.sorted(Comparator.comparing(sample -> -sample.getY()))
	//			.limit(k)
	//			.collect(Collectors.toList());
	//	}



	public static void main(String[] args){
	/*	ArrayList<Wifi> macs = new ArrayList<Wifi>();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter by how many macs to measure your location: (minimum three macs)");
		int num = Integer.parseInt(s.nextLine());
		for (int i=0; i<num; i++){
			Wifi w = new Wifi();
			System.out.println("Enter mac:");
			w.setMac(s.nextLine());
			System.out.println("Enter signal:");
			w.setSignal(Integer.parseInt(s.nextLine()));
			macs.add(w);
		}*/
		
	}
}
