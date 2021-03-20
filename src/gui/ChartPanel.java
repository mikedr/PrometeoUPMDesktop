package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ChartPanel extends JPanel{
	
	private TimeSeriesChartExample2 chart;
	
	public ChartPanel() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {
		add(chart.createChart(),BorderLayout.CENTER);
	}

	private void instaciateComponents() {
		chart = new TimeSeriesChartExample2();
	}
	
	private void setup() {
		Dimension dim = getPreferredSize();
		setLayout(new BorderLayout());
		dim.width = 700;
		setPreferredSize(dim);
		setVisible(true);
	}
	
}

