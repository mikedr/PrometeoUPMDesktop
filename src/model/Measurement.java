package model;

import java.io.Serializable;
import java.time.LocalTime;

public class Measurement implements Serializable{

	LocalTime tiempo;
	Float temperatura;
	
	public Measurement(LocalTime tiempo, Float temperatura) {
		super();
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
