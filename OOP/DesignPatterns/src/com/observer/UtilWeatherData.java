package com.observer;

import java.util.Observable;

public class UtilWeatherData extends Observable {

	private float temperature;
	private float humidity;
	private float pressure;

	public UtilWeatherData() {
	}

	public void measurementsChanged() {
		this.setChanged();
		this.notifyObservers();
	}

	public void setMeasurements(float temperature, float humidity,
			float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}

}
