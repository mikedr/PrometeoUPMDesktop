package model;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Pasteurization {

	private float tempInicial;
	private float tempMaxima;
	private float tempFinal;
	private float tempCorte;
	private float up;
	private LocalTime tiempTotal;
	private LocalTime tiempUP;
	private List<LocalTime> tiempos;
	private List<Float> temperaturas;
	
	public Pasteurization(List<LocalTime> tiempos, List<Float> temperaturas) {
		this.tiempos = tiempos;
		this.temperaturas = temperaturas;
		computeTempInicial();
		computeTempFinal();
		computeTempMaxima();
		computeTiempoTotal();
	}
	
	public float calculateUP(float temp, float deltaTiemp) {
		float constant = 1.389f;
		float uP;
		uP = (float) (deltaTiemp * (Math.pow(constant,(temp-60))));
		return uP;
	}
	
	private float calculateTimeDelta(List<LocalTime> tiempos) {
		LocalTime t0 = tiempos.get(0);
		LocalTime t1 = tiempos.get(1);
		int t0Int = t0.toSecondOfDay();
		int t1Int = t1.toSecondOfDay();
		Integer dif = t1Int - t0Int;
		Float difF = new Float(dif);
		float difInMinutes = difF/60;
		return difInMinutes;
	}

	private void computeTempFinal() {
		this.tempFinal = temperaturas.get(temperaturas.size()-1);
	}

	private void computeTiempoTotal() {
		this.tiempTotal = tiempos.get(tiempos.size()-1);
	}

	private void computeTempMaxima() {
		List<Float> sortedTemp = temperaturas;
		Collections.sort(sortedTemp);
		this.tempMaxima = sortedTemp.get(sortedTemp.size()-1);
	}

	private void computeTempInicial() {
		this.tempInicial = temperaturas.get(0);
	}
	
	public void computePasteurization(Float tempCorte) {
		this.tempCorte = tempCorte;
		computeUP();
	}

	private void computeUP() {
		int pos = 0;
		float uP = 0F;
		float timeDelta = calculateTimeDelta(tiempos);
		LocalTime timeStartUP = null;
		LocalTime timeEndUP = null;
		for(Float aTemp : temperaturas) {
			if(aTemp > tempCorte) {
				if(timeStartUP == null) {
					timeStartUP = tiempos.get(pos);
				}
				uP = uP + calculateUP(aTemp,timeDelta);
				timeEndUP = tiempos.get(pos);
			}
		    pos++;
		}
		this.up = ((float) (Math.floor(uP * 100) / 100));
		this.tiempUP = timeEndUP.minusSeconds(timeStartUP.toSecondOfDay());
	}

	public float getTempCorte() {
		return tempCorte;
	}

	public void setTempCorte(float tempCorte) {
		this.tempCorte = tempCorte;
	}

	public float getTempInicial() {
		return tempInicial;
	}

	public float getTempMaxima() {
		return tempMaxima;
	}

	public float getTempFinal() {
		return tempFinal;
	}

	public float getUp() {
		return up;
	}

	public LocalTime getTiempTotal() {
		return tiempTotal;
	}

	public LocalTime getTiempUP() {
		return tiempUP;
	}

	public List<LocalTime> getTiempos() {
		return tiempos;
	}

	public List<Float> getTemperaturas() {
		return temperaturas;
	}
	
}