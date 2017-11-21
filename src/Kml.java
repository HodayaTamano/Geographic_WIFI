
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class passes a .csv extension file to a .kml extension file with the information about the wifi's found in locations, showing the best 
 * location (regarding the wifi signal) of each wifi. The class max_signal ArrayList holds the strongest signal for every Wifi.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 1.0.0 - 9.11.17
 */


public class Kml {
	static ArrayList<Wifi> max_signal = new ArrayList<Wifi>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path ="C:/Users/hodaya/Wigle.csv";
		ArrayList<Row> data_kml = pass_to_kml(path);
		System.out.println(max_signal.toString());
		data_kml = remove_lows(data_kml); //erases low signal wifi's
		kml_file(data_kml);
	}
	
	/**
	 * This function prints the data_kml ArrayList into a file received from {@link pass_to_kml} function row by row, checking that the rows are legal.
	 * The rows from the data_kml ArrayList are printed in kml format with all the tags needed.
	 * @param table
	 */
	public static void kml_file(ArrayList <Row> table){
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try{
			FileWriter fw = new FileWriter("C:\\Users\\hodaya\\Wigle.kml", false);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			pw.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>");
			//if we want to change style in the future, the next row is relevant
			pw.println("<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>");
			pw.println("<Folder><name>Strongest Wifi's</name>");
			for (int j=0; j<table.size(); j++){
				for (int i=0; i<table.get(j).wifi_count; i++){
					try{
						pw.println("<Placemark>");
						pw.println("<name><![CDATA["+table.get(j).getWifi().get(i).getSsid()+"]></name>");
						pw.println("<description><![CDATA[MAC:"+table.get(j).getWifi().get(i).getMac()+"</b><br/>Signal: <b>"+table.get(j).getWifi().get(i).getSignal()+
								"</b><br/>Frequency: <b>"+table.get(j).getWifi().get(i).getFrequency()+"</b>]]></description>");
						pw.println("<Point><coordinates>"+table.get(j).getLon()+","+table.get(j).getLat()+"</coordinates></Point>");
						pw.println("</Placemark>");
					}catch(IndexOutOfBoundsException e){
						System.out.println("Couldn't write point to file");
					}	
				}
			}
			pw.println("</Folder>");
			pw.println("</Document></kml>");
			pw.flush();
			pw.close();
		}catch(Exception e){
			System.out.println("Couldn't save the kml file!");
		}
	}
	
	/**
	 * This function receives a Wifi object and checks if the signal is stronger than the signal of the same wifi object (using mac to compare) in 
	 * the max_signal ArrayList or not.
	 * @param w - Wifi object
	 * @return boolean
	 */
	public static boolean should_delete_wifi(Wifi w){
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
	public static void choose_max(Wifi w){
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
