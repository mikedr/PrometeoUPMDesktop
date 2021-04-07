package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import gui.Chart;
import model.Database;
import model.Measurement;
import model.Pasteurization;

public class Controller {
	
	private Database db = new Database();
	
	public void addMeasurement(LocalTime tiempo, Float temperatura) {
		Measurement measurement = new Measurement(tiempo, temperatura);
		db.addMeasurement(measurement);
	}
	
	public Chart getChart() {
		return db.getChart();
	}
	
	public Database getDb() {
		return db;
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

	public void computeUP(Float temperaturaDeCorte) {
		db.getPasteurization().computePasteurization(temperaturaDeCorte);
	}

	public void generateReporte(File selectedFile) {
		db.generateReporte(selectedFile);		
	}

	public void generateMediciones(File selectedFile) {
		db.generateMediciones(selectedFile);
	}

	public void startSerialConnection() {
		
	}
}
