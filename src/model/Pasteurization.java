package model;

import java.time.LocalTime;
import java.util.List;

public class Pasteurization {

	private Float tempInicial;
	private Float tempMaxima;
	private Float tempFinal;
	private Float tempCorte;
	private Float up;
	private LocalTime tiempTotal;
	private LocalTime tiempUP;
	private List<Measurements> measurements;
	
	public Pasteurization(List<Measurements> measurements) {
		this.measurements = measurements;
		computeTempInicial();
		computeTempInicial();
		computeTempMaxima();
		computeTiempoTotal();
	}

	private void computeTiempoTotal() {
		
	}

	private void computeTempMaxima() {
		
	}

	private void computeTempInicial() {
		
	}
	
}
