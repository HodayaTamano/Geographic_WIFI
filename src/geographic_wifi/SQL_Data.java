package geographic_wifi;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.*;
import Wifi_Data.*;


import java.sql.Statement;
public class SQL_Data {

//	  private static String _ip;
//	  private static String _url;
//	  private static String _user;
//	  private static String _password;
//	  private static Connection _con = null;
	  private static String _ip = "5.29.193.52";
	  private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	  private static String _user = "oop1";
	  private static String _password = "Lambda1();";
	  private static Connection _con = null;
	      
	    public static ArrayList<Row> read_db(String[] args) {
	    	ArrayList<Row> db = new ArrayList<Row>();
//	    	_ip=args[0];
//	    	_url="jdbc:mysql://"+_ip+":"+args[1]+"/"+args[4];
//	    	_user=args[2];
//	    	_password=args[3];
	    	db = extract_table(args);
	    	return db;
	    }

	    
	    private static ArrayList<Row> extract_table(String[] args) {
	    	ArrayList<Row> table = new ArrayList<Row>();
	        Statement st = null;
	        ResultSet rs = null;

	  
	        try {
	            _con = DriverManager.getConnection(_url, _user, _password);
	            st = _con.createStatement();
//	            rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = "+args[4]+" AND TABLE_NAME = "+args[5]);
//	            if (rs.next()) {
//	                System.out.println("**** Update: "+rs.getString(1));
//	            }
	           
	            PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");//+args[5]);
	            rs = pst.executeQuery();
	            while (rs.next()) { //rows of table
	            	int size = rs.getInt(7);
	            	int len = 7+2*size;
	            	Row current = new Row(); //object that holds the data from the row in table
	            	String time = rs.getString(2);  //gets the time
	            	current.setTime(time); //sets in current
	            	String id = rs.getString(3);
	            	current.setId(id);
	            	String lat = rs.getString(4);
	            	current.setLat(Double.parseDouble(lat));
	            	String lon = rs.getString(5);
	            	current.setLon(Double.parseDouble(lon));
	            	String alt = rs.getString(6);
	            	current.setAlt(Double.parseDouble(alt));
	            	current.setWifi_count(size);
	            	for(int i=8;i<=len;i=i+2){ //fields of table - wifi fields
	            		Wifi w = new Wifi();
	            		w.setMac(rs.getString(i));
	            		w.setSignal(Integer.parseInt(rs.getString(i+1)));
	            		current.addWifi(w);
	            	}
	            	table.add(current);
	            }
	        //catches sql exceptions
	        } catch (SQLException ex) {
	            Logger lgr = Logger.getLogger(SQL_Data.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        } finally {
	            try { //closing connection
	                if (rs != null) {rs.close();}
	                if (st != null) { st.close(); }
	                if (_con != null) { _con.close();  }
	            } catch (SQLException ex) {      
	                Logger lgr = Logger.getLogger(SQL_Data.class.getName());
	                lgr.log(Level.WARNING, ex.getMessage(), ex);
	            }
	        }
	        return table;
	    }
	    
	    

	  
	}
	
