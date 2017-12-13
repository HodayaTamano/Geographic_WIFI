
import java.util.*;
import java.io.*;


/**
 * This class passes a collection of .csv files from a folder to a united csv file that holds the information as a table
 * with time, phone id, geographic data and all data collected about wifi's in a location.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 1.0.0 - 9.11.17
 */

public class Csv {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path ="C:/Users/hodaya/Desktop/data"; //change path here <-
		ArrayList<Row> data_csv = pass_to_table(path);
		for (int i=0; i<data_csv.size(); i++){
			//Row r = data_csv.get(i);
			System.out.println(data_csv.get(i).toString());
		}
		pass_to_file(data_csv);
	}

	/**
	 * This method receives a path of a folder and returns an ArrayList of the files whose extension is ".csv".
	 * This is done by "getAbsolutePath" that returned the whole filepath to a String and checking the extension
	 * with the "endsWith" function from String class.
	 * @param path - receives a path of a folder in the computer.
	 * @return ArrayList of Strings with the names of the csv files in path.
	 */
	public static ArrayList<String> filenames(String path){
		File [] files = new File (path).listFiles();
		ArrayList<String> results = new ArrayList<String>();
		for (File file : files){
			if (file.isFile()){
				String str = file.getAbsolutePath();
				if (str.endsWith(".csv"))
					results.add(file.getName());
			}
		}
		return results;
	}
/**
 * This functions passes on all csv files in a folder (after sending to {@link filenames}). First it checks if the file itself is legal.
 * Every file is read, one row at a time (every row passes a legality check that it is a wifi network and not empty).
 * Every row from each file is inserted to an array and splitted by commas (as the csv definition indicates).
 * Temporary Row and Wifi objects hold the data while it is checked, and if it is new and legal it is inserted to the data_csv file.
 * @param path - receives a path of a folder in the computer.
 * @return data_csv - data_csv in returned, each row in file is a Row object.
 */
	//"C:/Users/hodaya/Downloads/WigleWifi_20171030201415.csv"
	public static ArrayList<Row> pass_to_table(String path){

		ArrayList<String> results = filenames(path);
		ArrayList<Row> data_csv = new ArrayList<Row>();
		BufferedReader br=null;
		
		//goes through files in folder
		for (int i=0; i<results.size(); i++){ 
			File file = new File(path+"/"+results.get(i)); 		//concats path and filename
			String line = ""; 									//differentiates between rows
			String csvSplitBy = ","; 							//differentiates between fields
			try{
				br = new BufferedReader(new FileReader(file));
				String[] readline;								 //stores row from file
				String first_line = br.readLine(); 
				String id = "";									 //relevant to the whole file
				if (first_line != null){
					readline = first_line.split(csvSplitBy);
					if (readline.length!=8)
					{
						System.out.println("goodbye");
						continue ;
					}					
					
					//this part checks if row is legal
					id = readline[5];
					try{
						id = id.substring(8);
					}catch(StringIndexOutOfBoundsException e){
						System.out.println("goodbye");
						continue;
					}							//this part checks if id is legal		
				}
				line = br.readLine(); 			//jumping the contents row

				if (line==null || line.split(csvSplitBy).length!=11)
				{
					System.out.println("goodbye");
					continue;
				}
				//this part checks if line is legal
				while ((line = br.readLine())!= null)
				{ 
					if (line.contains("WIFI") && !(line.contains(",,"))){   //if the info is relevant
						//inserting row data to new row object
						readline = line.split(csvSplitBy); //inserting row to array
						Row r = new Row();
						Wifi w = new Wifi();
						r.setTime(readline[3]);
						r.setId(id);
						w.setSsid(readline[1]);
						w.setMac(readline[0]);
						//checks if the values inserted are in the right format
						try{
							r.setLat(Double.parseDouble(readline[6]));
							r.setLon(Double.parseDouble(readline[7]));
							r.setAlt(Integer.parseInt(readline[8]));
							w.setFrequency(Integer.parseInt(readline[4]));
							w.setSignal(Integer.parseInt(readline[5]));
						}catch(NumberFormatException e){
							continue;
						}
						//if data_csv is empty, insert row without checking if the data already exists in table
						if (data_csv.size()==0)
						{
							r.wifi.add(w);
							r.setWifi_count(r.wifi.size());
							data_csv.add(r);
						} 
						else{		//here we check if data already exists in table
							int flag_insert=0;
							for(int j=0;j<data_csv.size();j++)
							{				//checks if row matching to row object data already exists in data_csv
								Row cur = data_csv.get(j);
								if (cur.getTimeString().equals(r.getTimeString()) && 
									cur.getId().equals(r.getId()) && cur.getAlt()==r.getAlt()
									&& cur.getLat()==r.getLat() && cur.getLon()==r.getLon())
								{          //if row exists we go through the wifi list to check if wifi data already exists
									ArrayList<Wifi> wifi_list = cur.getWifi();
									boolean wifi_exists = false;
									for (int k=0; k<wifi_list.size(); k++)
									{
										if (wifi_list.get(k).equalsTo(w))
										{
											wifi_exists = true;
											break;
										}
									} //if wifi doesn't exist and the list is smaller than 10 wifi objects we insert the new wifi object
									  //if wifi doesn't exist and the list is full (10 objects in list) we switch with a weaker wifi (if there is)
									if (!wifi_exists)
									{
										if (wifi_list.size()<10)
										{
											wifi_list.add(w);
											flag_insert=1;
											data_csv.get(j).setWifi(wifi_list);
											data_csv.get(j).setWifi_count(wifi_list.size());
											break;
										}	
										for (int k=0; k<wifi_list.size(); k++)
											if (wifi_list.get(k).signal<w.signal)
											{
												wifi_list.remove(k);
												wifi_list.add(w);
												flag_insert=1;
												data_csv.get(j).setWifi(wifi_list);
												data_csv.get(j).setWifi_count(wifi_list.size());
												break;
											}
									}
									break;
								}
							}		//if we get till here it means that this is a new wifi in a new row and we add new row to data_csv
							if (flag_insert==0)
							{
								r.wifi.add(w);
								r.setWifi_count(r.wifi.size());
								data_csv.add(r);
							}
						}
					}
				}
				//exceptions
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if( br !=null)
				{
					try{
						br.close();
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
		return data_csv;
	}
/**
 *This function passes through all the rows in the file, that has the same format as Row object and prints the data with commas in between 
 *the fields as a csv file. 
 * @param table - the file we want to print, row format
 */
	//WRITE
	private static void pass_to_file(ArrayList <Row> table){
		BufferedWriter bw = null;
		PrintWriter pw = null;
		String Fields = "Time, ID, Latitude, Longtitude, Altitude, #Wifi,";
		for (int i=1; i<11; i++){
			Fields = Fields+"SSID"+i+", Mac"+i+", Frequency"+i+", Signal"+i+",";
		}
		try{
			//C:/Users/hodaya/Desktop/data
			FileWriter fw = new FileWriter("C:\\Users\\hodaya\\Desktop\\data\\general_csv.csv", false); //path to write file <--
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.println(Fields);
			for (int j=0; j<table.size(); j++){
				pw.println(table.get(j).toString());
			}
			pw.flush();
			pw.close();
		}catch(Exception e){
			System.out.println("Couldn't write rows to table!");
			
		}
		
	}
	
	
	
}
