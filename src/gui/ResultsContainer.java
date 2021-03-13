package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ResultsContainer extends JPanel implements VisibleManager {
	
	private ResultsProcessPanel resultsPanel;
	private ResultsUPPanel resultsUPPanel;
	
	public ResultsContainer() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {
		add(resultsPanel,BorderLayout.NORTH);
		add(resultsUPPanel,BorderLayout.CENTER);		
	}

	private void instaciateComponents() {
		resultsPanel = new ResultsProcessPanel();
		resultsUPPanel = new ResultsUPPanel();// TODO Auto-generated method stub
	}

	private void setup() {
		Dimension dim = getPreferredSize();
		setLayout(new BorderLayout());
		dim.width = 400;
		setPreferredSize(dim);
		setVisible(false);		
	}

	public void visibilizador(boolean setVisible) {
		this.setVisible(setVisible);
	}
}
