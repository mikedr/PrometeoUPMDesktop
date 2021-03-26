package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;

import controller.Controller;
import model.Pasteurization;

public class ResultsContainer extends JPanel {
	
	private ResultsProcessPanel resultsPanel;
	
	public ResultsContainer() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {
		add(resultsPanel,BorderLayout.CENTER);
	}
	
	public void enableCalculadoraUP() {
		resultsPanel.enableCalculadoraUP();
	}
	
	private void instaciateComponents() {
		resultsPanel = new ResultsProcessPanel();
	}

	private void setup() {
		Dimension dim = getPreferredSize();
		setLayout(new BorderLayout());
		dim.width = 300;
		setPreferredSize(dim);
		setVisible(true);		
	}

	public void visibilizador(boolean setVisible) {
		this.setVisible(setVisible);
	}
	
	public void setController(Controller controller) {
		resultsPanel.setController(controller);
	}

	public void setMeasurementsParameters(Pasteurization pasteurization) {
		resultsPanel.setMeasurementsParameters(pasteurization);	
	}

	public void setTempCorteListener(PasteurizationListener tempCorteListener) {
		resultsPanel.setTempCorteListener(tempCorteListener);		
	}

	public void setAgregadorDeMenu(AgregadorDeMenu agregadorDeMenu) {
		resultsPanel.setAgregadorDeMenu(agregadorDeMenu);
	}
}
