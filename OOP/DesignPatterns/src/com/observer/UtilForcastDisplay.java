package com.observer;

import java.util.Observable;
import java.util.Observer;

import com.observer.framework.DisplayElement;

public class UtilForcastDisplay implements Observer, DisplayElement {

	private Observable obs;
	private float currentPressure = 29.92f;
	private float lastPressure;

	public UtilForcastDisplay(Observable obs) {
		this.obs = obs;
		obs.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if ( o instanceof UtilWeatherData ) {
			UtilWeatherData uwd = (UtilWeatherData) o;
			lastPressure = currentPressure;
			currentPressure = uwd.getPressure();
		}

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

}