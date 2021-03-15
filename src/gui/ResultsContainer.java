package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ResultsContainer extends JPanel implements VisibleManager {
	
	private ResultsProcessPanel resultsPanel;

	
	public ResultsContainer() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {
		add(resultsPanel,BorderLayout.CENTER);
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
}
