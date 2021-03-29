package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;

import controller.Controller;
import model.Measurement;
import model.Pasteurization;

public class ChartPanel extends JPanel{
	
	private Controller controller;
	
	public ChartPanel(Controller controller) {
		this.controller = controller;
		setup();
		addComponents();
	}

	private void addComponents() {
		add(controller.getChart().getPanel(),BorderLayout.CENTER);
	}
	
	public void createChartTemp(List<Measurement> measurements) {
		controller.getChart().createChartTemperature(measurements);
		add(controller.getChart().getPanel(),BorderLayout.CENTER);
	}
	
	public void createChartTempAndUP(Pasteurization pasteurization) {
		controller.getChart().createChartTemperatureAndUP(pasteurization);
		add(controller.getChart().getPanel(),BorderLayout.CENTER);
	}

	private void setup() {
		Dimension dim = getPreferredSize();
		setPreferredSize(dim);
		setVisible(true);
	}
	
}

