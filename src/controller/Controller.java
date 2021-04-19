package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import controller.serialConnection.CommunicationWithDevice;
import controller.serialConnection.ManagerSerialComm;
import gui.Chart;
import model.Database;
import model.Measurement;
import model.Pasteurization;

public class Controller {
	
	private Database db = new Database();
	private ManagerSerialComm managerSerialComm = new ManagerSerialComm(this);
	
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

	public boolean startSerialConnection() {
		return managerSerialComm.setConection();
	}
	
	public void readMediciones() {
		managerSerialComm.sendPacketToDevice(CommunicationWithDevice.FLAG_READ);
	}
	
	public void createMeasurementsFile() {
		
	}
}
