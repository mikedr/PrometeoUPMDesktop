package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import model.Database;
import model.Measurement;
import model.Pasteurization;

public class Controller {
	
	Database db = new Database();
	
	public void addMeasurement(LocalTime tiempo, Float temperatura) {
		Measurement measurement = new Measurement(tiempo, temperatura);
		db.addMeasurement(measurement);
	}
	
	public List<Measurement> getMeasurements() {
		return db.getMeasurements();
	}
	
	public Pasteurization getPasteurization() {
		return db.getPasteurization();
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public void computeUP(String text) {
		db.getPasteurization().computePasteurization(Float.parseFloat(text));
	}
}
