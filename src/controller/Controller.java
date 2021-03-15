package controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Database;
import model.Measurement;

public class Controller {
	
	Database db = new Database();
	
	public void addMeasurement(LocalTime tiempo, Float temperatura) {
		Measurement measurement = new Measurement(tiempo, temperatura);
		db.addMeasurement(measurement);
	}

	public List<Measurement> getMeasurements() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
		LocalTime tiempo = LocalTime.parse("00:00:00",dateTimeFormatter);
		Float temperatura = 23.5F;
		Measurement measurement = new Measurement(tiempo, temperatura);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		db.addMeasurement(measurement);
		return db.getMeasurements();
	}
	
}
