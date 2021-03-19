package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GraphPanel extends JPanel{
	
	public GraphPanel() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {

	}

	private void instaciateComponents() {

	}
	
	private void setup() {
		Dimension dim = getPreferredSize();
		setLayout(new BorderLayout());
		dim.width = 700;
		setPreferredSize(dim);
		setVisible(true);
	}
	
}

