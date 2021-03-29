package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jfree.chart.JFreeChart;

import gui.Chart;

public class Database {

	private ArrayList<Measurement> measurements;
	private Pasteurization pasteurization;
	private Chart chart;
	private static final String TEMP_INICIAL = "Temperatura inicial";
	private static final String TEMP_MAX = "Temperatura máxima";
	private static final String TEMP_FINAL = "Temperatura final";
	private static final String TEMP_CORTE = "Temperatura de corte";
	private static final String TIEMP_TOTAL = "Tiempo total";
	private static final String TIEMP_UP = "Tiempo UP";
	private static final String UP = "UP";
	private static final String DEGREES_CELSIUS = " °C";
	
	public Database() {
		measurements = new ArrayList<Measurement>();
		chart = new Chart();
	}
	
	public void addMeasurement(Measurement measurement) {
		measurements.add(measurement);
	}
	
	public Chart getChart() {
		return chart;
	}
	
	public List<Measurement> getMeasurements() {
		return measurements;
	}
	
	public Pasteurization getPasteurization() {
		return pasteurization;
	}

	public static String getDegreesCelsius() {
		return DEGREES_CELSIUS;
	}

	public static String getTempInicial() {
		return TEMP_INICIAL;
	}

	public static String getTempMax() {
		return TEMP_MAX;
	}

	public static String getTempFinal() {
		return TEMP_FINAL;
	}

	public static String getTempCorte() {
		return TEMP_CORTE;
	}

	public static String getTiempTotal() {
		return TIEMP_TOTAL;
	}

	public static String getTiempUp() {
		return TIEMP_UP;
	}

	public static String getUp() {
		return UP;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public void loadFromFile(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr); 
		String line;
		while((line = br.readLine()) != null) {  
			parseLine(line);
		}
		fr.close();
		pasteurization = new Pasteurization(measurements);
	}

	private void parseLine(String line) {
		StringTokenizer st;
		String controlField, idField, batteryField, tempField, timeField, sanitizedTime;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
		LocalTime tiempo;
		Float temperatura;
		Measurement measurement;
		st = new StringTokenizer(line,"/");
		controlField = st.nextToken();
		if(controlField.equals(new String("INF"))) {
			try {
				idField = st.nextToken();
			    batteryField = st.nextToken();
			    tempField = st.nextToken();
			    temperatura = Float.parseFloat(tempField);
			    timeField = st.nextToken();
			    sanitizedTime = timeSanitizer(timeField);
			    tiempo = LocalTime.parse(sanitizedTime, dateTimeFormatter);
			    measurement = new Measurement(tiempo, temperatura);
			    measurements.add(measurement);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	 private String timeSanitizer(String timeToSanitize) {
		int dotIndex = timeToSanitize.indexOf('.');
		if (dotIndex > 0) {
		    String temporalString = timeToSanitize.substring(0, dotIndex);
		    StringBuilder fixedTime = new StringBuilder(temporalString);
		    if(temporalString.length()==7) {
		    	fixedTime.insert(temporalString.length()-1, '0');
		    }
		    return fixedTime.toString();
		} else {
			return timeToSanitize;
		}
	 }
}
