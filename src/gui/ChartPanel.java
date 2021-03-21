package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ChartPanel extends JPanel{
	
	private ChartTemperatureAndUP chart;
	
	public ChartPanel() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {
		add(chart.createChart(),BorderLayout.CENTER);
	}

	private void instaciateComponents() {
		chart = new ChartTemperatureAndUP();
	}
	
	private void setup() {
		Dimension dim = getPreferredSize();
		setPreferredSize(dim);
		setVisible(true);
	}
	
}

