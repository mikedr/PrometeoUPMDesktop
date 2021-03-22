package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import model.Measurement;
import model.Pasteurization;

public class ChartPanel extends JPanel{
	
	private Chart chart;
	
	public ChartPanel() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {
		add(chart.getPanel(),BorderLayout.CENTER);
	}
	
	public void createChartTemp(List<Measurement> measurements) {
		chart.createChartTemperature(measurements);
		add(chart.getPanel(),BorderLayout.CENTER);
	}
	
	public void createChartTempAndUP(Pasteurization pasteurization) {
		chart.createChartTemperatureAndUP(pasteurization);
		add(chart.getPanel(),BorderLayout.CENTER);
	}

	private void instaciateComponents() {
		chart = new Chart();
	}
	
	private void setup() {
		Dimension dim = getPreferredSize();
		setPreferredSize(dim);
		setVisible(true);
	}
	
}

