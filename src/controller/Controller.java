package controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	
	private List<LocalTime> tiempos;
	private List<Float> temperaturas;
	
	public Controller() {
		this.tiempos = new ArrayList<>();
		this.temperaturas = new ArrayList<>();
	}
	
	public void addMeasurement(LocalTime tiempo, Float temperatura) {
		tiempos.add(tiempo);
		temperaturas.add(temperatura);
	}

	public List<LocalTime> getTiempos() {
		return tiempos;
	}

	public List<Float> getTemperaturas() {
		return temperaturas;
	}
	
}
