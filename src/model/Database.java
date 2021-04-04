package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.JFreeChart;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

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

	public void generateReporte(File selectedFile) {
		JFreeChart chart = getChart().getChart();
		Document document = new Document (new Rectangle(1000,707));
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document,
			new FileOutputStream(selectedFile));
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}
		document.open();
        Font fontbold = FontFactory.getFont("Arial", 40, Font.BOLD);
        Paragraph p = new Paragraph("Reporte pasteurización", fontbold);
        p.setSpacingAfter(20);
        p.setAlignment(1); // Center
        try {
			document.add(p);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		PdfPTable table = new PdfPTable(2);
//			PdfPCell cell = new PdfPCell(new Paragraph("Resultados"));
		PdfPCell cell = new PdfPCell();
		cell.setColspan(2);
		table.addCell(cell);
		table.addCell(getTempInicial());
		table.addCell(Float.toString(getPasteurization().getTempInicial())+getDegreesCelsius());
		table.addCell(getTempMax());
		table.addCell(Float.toString(getPasteurization().getTempMaxima())+getDegreesCelsius());
		table.addCell(getTempFinal());
		table.addCell(Float.toString(getPasteurization().getTempFinal())+getDegreesCelsius());
		table.addCell(getTempCorte());
		table.addCell(Float.toString(getPasteurization().getTempCorte())+getDegreesCelsius());
		table.addCell(getTiempTotal());
		table.addCell(getPasteurization().getTiempTotal().toString());
		table.addCell(getTiempUp());
		table.addCell(getPasteurization().getTiempUP().toString());
		table.addCell(getUp());
		table.addCell(Float.toString(getPasteurization().getUp()));				
		try {
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		PdfContentByte cb = writer.getDirectContent();
		PdfTemplate tp = cb.createTemplate(1000, 707);
		Graphics2D g2d = tp.createGraphics(800, 600,
		new DefaultFontMapper());
		Rectangle2D r2d = new Rectangle2D.Double(200, 150, 600, 400);
		chart.draw(g2d, r2d);
		g2d.dispose();
		cb.addTemplate(tp, 0, 0);
		document.close();
	}

	public void generateMediciones(File selectedFile) {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Mediciones");
	    int rowCount = 0;
	    Row row = sheet.createRow(rowCount);
	    Cell cell = row.createCell(1);
	    cell.setCellValue("Tiempo");
	    cell = row.createCell(2);
	    cell.setCellValue("Temperatura");
	    List<Measurement> measurements = new ArrayList<Measurement>();
	    measurements = getMeasurements();
	    for (Measurement aMeasurement : measurements) {
	        row = sheet.createRow(++rowCount);
	        writeBook(aMeasurement, row);
	    }
	    try {
		    FileOutputStream outputStream = new FileOutputStream(selectedFile);
		    workbook.write(outputStream);				    	
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }		
	}
	
	private void writeBook(Measurement aMeasurement, Row row) {
	    Cell cell = row.createCell(1);
	    cell.setCellValue(aMeasurement.getTiempo().toString());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(aMeasurement.getTemperatura());
	}
}
