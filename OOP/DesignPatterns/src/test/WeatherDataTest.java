package test;

import com.observer.*;

public class WeatherDataTest {
	
	private WeatherData wd;
	
	
	public void testObservers () {
		
		wd = new WeatherData ();
		CurrentConditionsDisplay ccd1 = new CurrentConditionsDisplay (wd);
		printDisplayers();
		CurrentConditionsDisplay ccd2 = new CurrentConditionsDisplay (wd);
		printDisplayers();
		CurrentConditionsDisplay ccd3 = new CurrentConditionsDisplay (wd);
		printDisplayers();
		ccd1.update(65, 55, 1014);
		ccd2.update(63, 54, 1012);
		ccd3.update(67, 52, 1034);
		
	/*	wd.removeObserver(ccd2);
		printDisplayers();
		wd.removeObserver(ccd1);
		printDisplayers();
		wd.removeObserver(ccd1);
		printDisplayers();*/
	}
	
	public void testStations () {
		wd.setMesurements(70, 70, 1033);
	}
	
	
	private void printDisplayers () {
	
		wd.printObservers();
	}
}

