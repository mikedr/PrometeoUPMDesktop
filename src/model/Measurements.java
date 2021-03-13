package model;

import java.time.LocalTime;

public class Measurements {

	private LocalTime tiempo;
	private Float temperatura;
	
	public Measurements(LocalTime tiempo, Float temperatura) {
		this.tiempo = tiempo;
		this.temperatura = temperatura;
	}

	public LocalTime getTiempo() {
		return tiempo;
	}

	public void setTiempo(LocalTime tiempo) {
		this.tiempo = tiempo;
	}

	public Float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}

}
