import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;


/**
 * This example generates a KML file with a placemark and a chart for each continent. The chart is generated with the Google Chart API and
 * show the area (surface of the earth) of each continent.
 * 
 * Google Chart API example: http://chart.apis.google.com/chart?cht=p3&chd=t:60,40&chs=250x100&chl=Hello|World
 */
public class KML_by_lib {
	static ArrayList<Wifi> max_signal = new ArrayList<Wifi>();

	
	public static void main(String[] args) throws FileNotFoundException{
		String path ="C:\\Users\\hodaya\\Desktop\\data\\general_csv.csv"; //path of input file <--
		ArrayList<Row> data_kml = pass_to_kml(path);
		//System.out.println(max_signal.toString());
		data_kml = remove_lows(data_kml); //erases low signal wifi's
		Scanner s = new Scanner(System.in);
		int filter;
		System.out.println("Enter by what field you want to filter: 1-ID, 2-Time, 3-Location (ANYTHING ELSE PRINTS EVERYTHING)");
		filter=Integer.parseInt(s.nextLine());
		switch (filter){				
			case 1: filter=1;
				System.out.println("Enter ID:");
				String id = s.nextLine();
				filter_by_ID(data_kml, id);
				break;
			case 2: filter=2;
				System.out.println("Enter beginning time by format: yyyy-MM-dd hh:mm:ss:");
				String b_date = s.nextLine();
				System.out.println("Enter ending time by format: yyyy-MM-dd hh:mm:ss:");
				String e_date = s.nextLine();
				Time b_time=new Time();
				Time e_time = new Time();
				e_time.setDATE(e_date);
				b_time.setDATE(b_date);
				filter_by_Time(data_kml, b_time, e_time);
				break;
			case 3: filter=3;
				System.out.println("Enter lat value of the point:");
				double lat_point = s.nextDouble();
				System.out.println("Enter lon value of the point:");
				double lon_point = s.nextDouble();
				System.out.println("Enter radius by time-area units:");
				double t_area = s.nextDouble();
				filter_by_Location(data_kml, lat_point, lon_point, t_area);
				break;
			default:
			kml_file(data_kml);
		}
		System.out.println("File was printed.");
		
		
	}
	/**
	 * This function filters the data in data_kml file by the ID String that the user inputs,
	 *  and sends it to kml_file for it to be printed in a file.
	 * @param data_kml
	 * @param filter_ID
	 * @throws FileNotFoundException
	 */
	public static void filter_by_ID (ArrayList<Row> data_kml, String filter_ID) throws FileNotFoundException{
		int i=0;
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			if (r.getId()!=filter_ID){
				data_kml.remove(r);
				i--;
			}
			i++;
		}
		kml_file(data_kml);
	}
	
	/**
	 * This function filters the data in data_kml file by the time range that the user inputs by format (yyyy-MM-dd hh:mm:ss) ,
	 * and sends it to kml_file for it to be printed in a file.
	 * 
	 * @param data_kml
	 * @param filter_begin
	 * @param filter_end
	 * @throws FileNotFoundException
	 */
	public static void filter_by_Time (ArrayList<Row> data_kml, Time filter_begin, Time filter_end) throws FileNotFoundException{
		int i=0;
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			Time d = new Time();
			d.setDATE(r.getTimeString());
			if (d.getDateDATE().before(filter_begin.getDateDATE()) || d.getDateDATE().after(filter_end.getDateDATE()))
				{
					data_kml.remove(r);
					i--;
				}
			i++;
		}
		kml_file(data_kml);
	}
	
	/**
	 * This function filters the data in data_kml file by the location range calculated by a point and area_time  that the user inputs,
	 * and sends it to kml_file for it to be printed in a file.
	 * @param data_kml
	 * @param from_lat
	 * @param from_lon
	 * @param radius
	 * @throws FileNotFoundException
	 */
	public static void filter_by_Location (ArrayList<Row> data_kml, double from_lat, double from_lon, double radius) throws FileNotFoundException{
		int i=0;
		double time_area = 0.0166666667;
		//radius*time area
		while (i<data_kml.size()){
			Row r = data_kml.get(i);
			if (r.getLat()>(from_lat+(radius*time_area)) || r.getLat()<(from_lat-(radius*time_area))
					|| r.getLon()>(from_lon+(radius*time_area)) || r.getLon()<(from_lon-(radius*time_area))){
				data_kml.remove(r);
				i--;
			}
			i++;
		}
		kml_file(data_kml);
	}
	
	/**
	 * This function receives a Wifi object and checks if the signal is stronger than the signal of the same wifi object (using mac to compare) in 
	 * the max_signal ArrayList or not.
	 * @param w - Wifi object
	 * @return boolean
	 */
	private static boolean should_delete_wifi(Wifi w){
		for (int i=0; i<max_signal.size(); i++){
			if (w.getMac().equals(max_signal.get(i).getMac())){
				if (w.getSignal()<max_signal.get(i).getSignal())
					return true;
				else
					return false;
			}
		}
		return false;
	}
	

	/**
	 * This function passes throughout the kml file (data_kml) and checks if the wifi object in every row should be deleted according to the maximal
	 * signal in the max_signal list using the {@link should_delete_wifi} above. If it is lower, this Wifi point shouldn't be represented in the kml file.
	 * @param data_kml
	 * @return
	 */
	public static ArrayList<Row> remove_lows(ArrayList<Row> data_kml){
		for (int i=0; i<data_kml.size(); i++){
			for(int j=0; j<data_kml.get(i).getWifi().size(); j++){
				if (should_delete_wifi(data_kml.get(i).getWifi().get(j))){
					data_kml.get(i).getWifi().remove(j);
					data_kml.get(i).setWifi_count(data_kml.get(i).getWifi().size());
					j--;
				}
			}
		}
		return data_kml;		
	}
	
	/**
	 * This function receives a Wifi object and checks if the wifi already exists in the max_signal list - if it does, and the signal of the object is 
	 * stronger than the one recorded in max_signal it 'swaps' between them in the list.
	 * If the list is empty or if the specific mac doesn't exist - it adds the wifi to the max_signal list.
	 * @param w - Wifi object.
	 */
	private static void choose_max(Wifi w){
		if (max_signal==null){
			max_signal.add(w);
		}
		else{
			for (int i=0; i<max_signal.size(); i++){
				if (max_signal.get(i).getMac().equals(w.getMac())){
					if (max_signal.get(i).getSignal()<w.getSignal()){
						max_signal.remove(i);
						max_signal.add(w);
					}
					return;
				}
			}
			max_signal.add(w);
		}
	}
	/**
	 * This function prints the data_kml ArrayList into a file received from {@link pass_to_kml} function row by row, checking that the rows are legal.
	 * The rows from the data_kml ArrayList are printed in kml format with all the tags needed.
	 * Note that this function uses the placemarkWithChart to print the Wifi points in the file as the wanted kml format by the JAK library.
	 * @param table
	 */
	public static void kml_file(ArrayList <Row> table)throws FileNotFoundException{

		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument().withName("Kml").withOpen(true);
		
		// create a Folder
		Folder folder = doc.createAndAddFolder();
		folder.withName("Wifi Locations").withOpen(true);
		// create Placemark elements
		//here we insert the points in a for-loop
		for (int j=0; j<table.size(); j++){
			for (int i=0; i<table.get(j).wifi_count; i++){
				try{
					createPlacemarkWithChart(doc, folder, table.get(j).getLon(), table.get(j).getLat(),
							table.get(j).getTimeString(), table.get(j).getWifi().get(i));
				}catch(IndexOutOfBoundsException e){
					System.out.println("Couldn't write point to file");
				}
			}
		}
		// print and save
		kml.marshal(new File("general_kml.kml")); //writes to project folder in workspace <--
	}
/**
 * This function uses the JAK library and formats wifi networks to be represented as points in a map,
 *  by the kml format.
 * @param document
 * @param folder
 * @param lon
 * @param lat
 * @param time
 * @param w
 */


	private static void createPlacemarkWithChart(Document document, Folder folder,double lon,double lat,String time, Wifi w) {
		
		Placemark placemark = folder.createAndAddPlacemark();
		placemark.withName(w.getSsid())
		.withDescription("MAC:"+w.getMac()+"</b><br/>Signal: <b>"+w.getSignal()+"</b><br/>Frequency: <b>"+w.getFrequency()+"</b>");
		String newT=time.replace(' ','T');
		newT+='Z';
		placemark.createAndSetTimeStamp().setWhen(newT);
		placemark.createAndSetPoint().addToCoordinates(lon, lat); // set coordinates
	}
	
	/**
	 * This function reads from the csv file received and passes row by row, holds the relevant data in a Wifi object, compares it to max_signal using
	 * ({@link choose_max} function and decides whether or not it should add this wifi data to the data_kml file that will be printed.
	 * Uses choose_max function.
	 * @param path - The path of the csv file.
	 * @return data_kml - The file that will be printed with the maximal values of every wifi scanned in the data_csv file.
	 */
	public static ArrayList<Row> pass_to_kml(String path){
			ArrayList<Row> data_kml = new ArrayList<Row>();
			BufferedReader br=null;
			File file = new File(path); 
			String line = ""; //differentiates between rows
			String csvSplitBy = ","; //differentiates between fields
			try{
				br = new BufferedReader(new FileReader(file));
				String[] readline; //stores row from file
				String first_line = br.readLine();
				if (first_line != null && first_line.split(csvSplitBy).length==46){	
					//this part checks if line is legal
					while ((line = br.readLine())!= null)
					{ 
							readline = line.split(csvSplitBy); //inserting row to array
							Row r = new Row();
							ArrayList<Wifi> w = new ArrayList<Wifi>();
							r.setTime(readline[0]);
							r.setId(readline[1]);
							try{
								r.setLat(Double.parseDouble(readline[2]));
								r.setLon(Double.parseDouble(readline[3]));
								r.setAlt(Integer.parseInt(readline[4]));
								r.setWifi_count(Integer.parseInt(readline[5]));
							}catch(NumberFormatException e){
								continue;
							}
							for (int i=0; i<r.wifi_count; i++){
								try{
								Wifi tmpw=new Wifi();
								tmpw.setSsid(readline[6+i*4]);
								tmpw.setMac(readline[7+i*4]);
								tmpw.setFrequency(Integer.parseInt(readline[8+i*4]));
								tmpw.setSignal(Integer.parseInt(readline[9+i*4]));
								choose_max(tmpw);
								w.add(tmpw);
								}catch(NumberFormatException e){
									continue;
								}catch(ArrayIndexOutOfBoundsException e){
									break;
								}
							}
							r.setWifi(w);
							data_kml.add(r);
					}
				}
			}
			//exceptions
			catch(FileNotFoundException e){
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
			return data_kml;
	}
}



