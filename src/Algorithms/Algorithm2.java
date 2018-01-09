package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;
import Wifi_Data.*;
import Tests.*;
import General.*;

/**
 * This class is responsible for calculating user location, given the wifi scan that the user inputs.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 27.12.2017
 */


public class Algorithm2 {
	//these are all variables that are used to calculate the location. set to final.
	final static int square = 2; 
	final static int norm = 10000; 
	final static double sig_diff = 0.4; 
	final static int min_diff = 3;
	final static int no_signal = -120; 
	final static int diff_no_sig = 100; 
	final static int num_of_samples = 4; //to find location we need at least 3 measurements

	/**
	 * This function receives two files - one with missing location and another full csv file, and sends every row
	 * to the missing_location function to calculate the user's location. (row here is referred as a scan from a user's phone).
	 * @param missing_location
	 * @param full_csv
	 * @return ArrayList
	 */
	public static ArrayList<Row> locations_csv(ArrayList<Row> missing_location, ArrayList<Row> full_csv){

		double [] loc = new double [3]; //to receive answer from search_in_csv
		for (int i=0; i<missing_location.size(); i++){
			Row current = new Row();
			current = missing_location.get(i);
			//System.out.println(current);
			loc = search_in_csv(current, full_csv); //send row to search_in_csv to calculate location
			current.setLat(loc[0]);
			current.setLon(loc[1]);
			current.setAlt((int)loc[2]);
			missing_location.set(i, current); //switch between the original Row to the Row we are holding with location data
		}
		return missing_location;
	}

	/**
	 * This function passes throughout a whole csv file and compares to a row from the missing csv file.
	 * It creates an arraylist of samples of wifis to calculate user location,
	 * then sends and receives answer from user_location.
	 * @param original
	 * @param full_csv
	 * @return
	 */
	public static double [] search_in_csv(Row original, ArrayList<Row> full_csv){

		ArrayList<Wifi_Samples> answer = new ArrayList<Wifi_Samples>();
		double [] loc = new double [3];

		for (int i=0; i<full_csv.size(); i++){//pass throughout the csv and search for matching samples by mac
			Row current = new Row();
			current = full_csv.get(i);
			for (int j=0; j<original.getWifi().size(); j++){ //passes throughout the original missing loc row with loc info from current row
				Wifi original_wifi = original.getWifi().get(j);
				Wifi_Samples ws = new Wifi_Samples(current.getLat(), current.getLon(), current.getAlt());
				for (int k=0; k<current.getWifi().size(); k++){ //passes throughout the wifi list in row
					Wifi current_wifi = current.getWifi().get(k);
					if (current_wifi.getMac().equals(original_wifi.getMac())){
						ws.addWifi(current_wifi);
					}
				}
				answer.add(ws);	
			}	
		}
		loc = user_location(answer, original);
		return loc;
	}

	/**
	 * This function calculates the user's location by an arraylist of samples and a row from the mising loc. info file.
	 * Returns the location info as an array of 3 doubles.
	 * @param ws
	 * @param original
	 * @return
	 */
	public static double [] user_location (ArrayList<Wifi_Samples> ws, Row original){

		double [] loc = new double[3]; //to return answer

		for (int i=0; i<ws.size(); i++){//throughout the samples
			double pi=1;
			for (int j=0; j<ws.get(i).getSamples().size(); j++){//throughout the wifi sample arraylist

				for (int k=0; k<original.getWifi().size(); k++){//throughout the original wifi arraylist
					double diff=0;
					if (ws.get(i).getSamples().get(j).getMac().equals(original.getWifi().get(k).getMac())){
						diff+=3;
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
			
			Wifi_Samples.sortingByPi(ws); //sort sample list by pi value

			//select strongest samples by pi value
			try{
				for (int n=num_of_samples; n<ws.size(); n++){
					ws.remove(n);
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println("Not enough samples. Goodbye.");
				break;
			}

			//calculate location answer using algo1
			loc = Algorithm1.user_loc(ws);
		}
		//return location answers
		return loc;
	}

	//this part is a preparation for the next phase in this project (3)
	public static void main(String[] args){

	}
}
