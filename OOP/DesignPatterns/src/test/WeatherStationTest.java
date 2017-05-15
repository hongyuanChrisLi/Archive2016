package test;

import com.observer.*;

public class WeatherStationTest {
	
	WeatherData wd;
	
	public void setWeatherStation ( WeatherData wd) {
		this.wd = wd;
	}
	
	public void testWeatherStation () {
		
		wd.setMesurements(70, 70, 1033);
	
	}
	
}