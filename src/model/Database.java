package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {

	private ArrayList<Measurement> measurements;
	
	public Database() {
		measurements = new ArrayList<Measurement>();
	}
	
	public void addMeasurement(Measurement measurement) {
		measurements.add(measurement);
	}
	
	public List<Measurement> getMeasurements() {
		return measurements;
	}
	
	public void loadFromFile(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr); 
		StringBuffer sb = new StringBuffer();
		String line;
		while((line=br.readLine()) != null) {  
			sb.append(line);
			sb.append("\n");   
		}
		fr.close();
		System.out.println(sb.toString());
		System.out.println("Contents of File: "); 
	}
}
