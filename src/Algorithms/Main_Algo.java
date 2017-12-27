package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;
import Wifi_Data.*;
import Tests.*;
import General.*;

import java.util.Scanner;

/**
 * The purpose of this class is to execute the queries that have to do with the algorithm class.
 * It receives an input from the user using the command prompt to run the files in the next formats:
 * java -jar Algo1.jar <input_file> <output_file> <number_of_samples>
 * java -jar Algo2.jar <training_set> <testing_set> <output_file> <number_of_samples>
 * and executes the query and returns the response to the user. 
 * Please note that 'switch' was used to separate the next cases: 1) query to algo1 2) query to algo2
 * 
 * AT THIS STAGE ALGORITHM2 IS IMPLEMENTED WITHOUT 'num of samples'
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 *
 */
public class Main_Algo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int choice;
		System.out.println("Enter number of algorithm to execute: 1 or 2");
		choice=Integer.parseInt(scan.nextLine());
		switch (choice){
			case 1: choice=1;
				//receiving all input needed to run algorithm 1
				String input="";
				String output1="";
				int num_samples =0;
				System.out.println("Enter input file path (entire path) for Algorithm 1, "
						+ "Enter output file path (entire path) for Algorithm 1, "
						+ "Enter number of samples wanted: ");
				input = scan.next();
				output1 = scan.next();
				num_samples = Integer.parseInt(scan.next());
				//sending data received to execute algorithm 1
				ArrayList<Row> full_csv1 = new ArrayList<Row>();
				Wifi_Scans scansByLoc = new Wifi_Scans();
				full_csv1 = Csv.csv_to_file(input);
				scansByLoc = Algorithm1.findMacScans(full_csv1);
				Wifi_Scans.print_Scans(scansByLoc, output1);
				
				
				break;
			case 2: choice=2;
				//receiving all input needed to run algorithm 2
				String input_missing="";
				String full_input="";
				String output2=""; 
				int num_samples2 =0;
				System.out.println("Enter full data file location path (entire path) for Algorithm 2, "
						+ "missing file location path (entire path) for Algorithm 2, "
						+ "output file path (entire path) for Algorithm 2, "
						+ "number of samples wanted: ");
				full_input = scan.next();
				input_missing = scan.next();
				output2 = scan.next();
				num_samples2 = Integer.parseInt(scan.next());
				//preparing data structure received as answer from execution of algorithm 2
				ArrayList<Row> algo2 = new ArrayList<Row>();
				//data structures to hold arraylists of Row objects (the data in the csv files)
				ArrayList<Row> missing_location = new ArrayList<Row>();
				ArrayList<Row> full_csv = new ArrayList<Row>();
				//csv_to_file - function that inserts the data from a file to an arraylist
				missing_location = Csv.csv_to_file(input_missing);
				full_csv = Csv.csv_to_file(full_input);
				//execute algorithm2 - answer is inserted to algo2 arraylist
				algo2 = (Algorithm2.locations_csv(missing_location, full_csv));
				//printing to file function for a full-csv arraylist of Row objects
				Csv.pass_to_file(algo2, output2);
				Csv.pass_to_file(full_csv, "C:\\Users\\hodaya\\Desktop\\algo\\try.csv");
				
				break;
		}
		
	}

}
