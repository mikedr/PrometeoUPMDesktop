package model;

import java.util.ArrayList;
import java.util.List;

public class Database {

	private ArrayList<Measurement> measurements;
	
	public Database() {
		measurements = new ArrayList<Measurement>();
	}
	
	public void addMeasurement(Measurement measurement) {
		measurements.add(measurement);
	}
	
	public List<Measurement> getMeasurements() {
		return measurements;
	}
}
