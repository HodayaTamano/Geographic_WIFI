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

	  private static String _ip;
	  private static String _url;
	  private static String _user;
	  private static String _password;
	  private static Connection _con = null;
	      
	    public static ArrayList<Row> read_db(String[] args) {
	    	ArrayList<Row> db = new ArrayList<Row>();
	    	_ip=args[0];
	    	_url="jdbc:mysql://"+_ip+":"+args[1]+"/"+args[4];
	    	_user=args[2];
	    	_password=args[3];
	    	db = extract_table(args);
	    	return db;
	    }

	    
	    public static ArrayList<Row> extract_table(String[] args) {
	    	ArrayList<Row> table = new ArrayList<Row>();
	        Statement st = null;
	        ResultSet rs = null;
	        try {
	            _con = DriverManager.getConnection(_url, _user, _password);
	            st = _con.createStatement();
	            PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+args[5]);
	            rs = pst.executeQuery();
	            while (rs.next()) { //rows of table
	            	
	            	int size = rs.getInt(7);
	            	int len = 7+2*size;
	            	Row current = new Row(); //object that holds the data from the row in table
	            	String time=rs.getString(2);  //gets the time
	            	current.setTime(time); //sets in current
	            	String id = rs.getString(3);
	            	current.setId(id);
	            	double lat = rs.getDouble(4);
	            	current.setLat(lat);
	            	double lon = rs.getDouble(5);
	            	current.setLon(lon);
	            	double alt = rs.getDouble(6);
	            	current.setAlt(alt);
	            	current.setWifi_count(size);
	            	
	            	for(int i=8;i<=len;i=i+2){ //fields of table - wifi fields
	            		Wifi w = new Wifi();
	            		w.setMac(rs.getString(i));
	            		w.setSignal(rs.getInt(i+1));
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
	    
	    
	    /**
	     * the function return time of last modification
	     * @param args  IP,port,user,password,database,table
	     * @return time of last modification
	     */
	    public static String TimeUPDATE(String[] args)
	    {
	        //variables
	        Statement st = null;
	        ResultSet rs = null;
	        try{
	            _ip=args[0];
	            _url="jdbc:mysql://"+_ip+":"+args[1]+"/"+args[4];
	            _user=args[2];
	            _password=args[3];
	            _con = null;
	        }catch(NullPointerException e){
	            return null;
	        }
	        String answer="null";
	        try {     
	            _con = DriverManager.getConnection(_url, _user, _password);
	            st = _con.createStatement();
	            PreparedStatement pst = _con.prepareStatement("SELECT UPDATE_TIME\n" +
	                                                          "FROM   INFORMATION_SCHEMA.TABLES\n" +
	                                                          "WHERE  TABLE_SCHEMA = 'oop_course_ariel'\n" +
	                                                          "AND    TABLE_NAME = 'ex4_db'");
	            rs = pst.executeQuery();
	            if (rs.next()) {
	                answer=rs.getString(1);
	            }
	        } catch (SQLException ex) {
	            Logger lgr = Logger.getLogger(SQL_Data.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        } finally {
	            try {
	                if (rs != null) 
	                    {rs.close();}
	                if (st != null) 
	                    { st.close(); }
	                if (_con != null) 
	                    { _con.close();  }
	            } catch (SQLException ex) {
	                
	                Logger lgr = Logger.getLogger(SQL_Data.class.getName());
	                lgr.log(Level.WARNING, ex.getMessage(), ex);
	            }
	        }
	        return answer;
	    }
	}
	
