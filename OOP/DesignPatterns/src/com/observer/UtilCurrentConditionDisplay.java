package com.observer;


import java.util.Observable;
import java.util.Observer;

public class UtilCurrentConditionDisplay implements Observer {

	private float temperature;
	private float humidity;
	private Observable obs;
	
	public UtilCurrentConditionDisplay (Observable obs ){
		this.obs = obs;
		obs.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof UtilWeatherData ){
			UtilWeatherData uwd = (UtilWeatherData) o;
			this.temperature = uwd.getTemperature();
			this.humidity = uwd.getHumidity();
			display();
		}
		
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
		+ "F degrees and " + humidity + "% humidity");
		}
	
	
}