package model;

import java.time.LocalTime;

public class FilaExcel {

	Float temperatura;
	LocalTime tiempo;
	
	public FilaExcel(Float temperatura, LocalTime tiempo) {
		this.temperatura = temperatura;
		this.tiempo = tiempo;
	}

	public Float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}

	public LocalTime getTiempo() {
		return tiempo;
	}

	public void setTiempo(LocalTime tiempo) {
		this.tiempo = tiempo;
	}
	
}