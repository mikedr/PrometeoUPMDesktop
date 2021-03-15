package controller;

import java.time.LocalTime;
import java.util.ArrayList;

import model.Measurement;

public class Controller {
	
	private ArrayList<Measurement> db = new ArrayList<>(); 
	
	public void addMeasurement(LocalTime tiempo, Float temperatura) {
		Measurement measurement = new Measurement(tiempo, temperatura);
		db.add(measurement);
	}

	public ArrayList<Measurement> getMeasurements() {
		return db;
	}
	
}
