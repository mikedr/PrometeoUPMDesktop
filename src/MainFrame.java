import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame{
	
	private GraphPanel textPanel;
	private Toolbar toolbar;
	private ResultsContainer resultsContainer;
	
	public MainFrame () {
		super("Prometeo UP Meter");
		setup();
		instaciateComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(resultsContainer,BorderLayout.WEST);
		add(textPanel,BorderLayout.CENTER);
		add(toolbar,BorderLayout.NORTH);		
	}

	private void instaciateComponents() {
		textPanel = new GraphPanel();
		toolbar = new Toolbar();
		resultsContainer = new ResultsContainer();
		toolbar.setVisibleManager(new VisibleManager() {
			@Override
			public void visibilizador(boolean setVisible) {
				resultsContainer.setVisible(setVisible);
			}
		});
	}

	private void setup() {
		setLayout(new BorderLayout());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
