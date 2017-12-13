
/**
 * This class defines a Wifi object, its fields and some functions needed to work properly with this class throughout our project.
 * @author Hodaya_Tamano
 * @author Shir_Bentabou
 * @version 1.0.0 - 9.11.17
 */

public class Wifi {
	String ssid; //network name
	String mac;
	int signal; //RSSI - the higher the better
	int frequency; //channel

	/**
	 * Getters and setters for this class:
	 * public String getSsid() - This function gets the ssid field as String.
	 * public void setSsid(String ssid) - This function sets the ssid as the String received.
	 * public String getMac() - This function gets the mac field as String.
	 * public void setMac(String mac) - This function sets the mac as the String received.
	 * public int getSignal() - This function gets the signal field as int.
	 * public void setSignal(int signal) - This function sets the signal as the int received.
	 * public int getFrequency() - This function gets the frequency field as int.
	 * public void setFrequency - This function sets the frequency as the int received.
	 */
	
	
	/**
	 * This function overrides the object toString function and returns the Wifi object in the wanted format, by the Wifi object fields.
	 * @return Wifi object in string.
	 */
	public String toString(){
		return(ssid+","+mac+","+frequency+","+signal+",");
	}

	public String getSsid() {
		return ssid;
	}


	public void setSsid(String ssid) {
		this.ssid = ssid;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public int getSignal() {
		return signal;
	}


	public void setSignal(int signal) {
		
		this.signal = signal;
	}


	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	/**
	 * This function compares a Wifi abject to another by every field and returns true if they are equal, and false if not.
	 * @param w - a Wifi object
	 * @return boolean
	 */
	public boolean equalsTo(Wifi w){
		if (this.ssid.equals(w.ssid) &&
				this.mac.equals(w.mac) &&
				this.frequency == w.frequency &&
				this.signal == w.signal)
			return true;
		else
			return false;
	}
	
}
