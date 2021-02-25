import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ResultsContainer extends JPanel implements VisibleManager {
	
	private ResultsProcessPanel resultsPanel;
	private ResultsUPPanel resultsUPPanel;
	
	public ResultsContainer() {
		Dimension dim = getPreferredSize();
		setLayout(new BorderLayout());
		
//		Border innerBorder = BorderFactory.createTitledBorder("Parametros");
//		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
//		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		dim.width = 400;
		setPreferredSize(dim);

		setVisible(false);
		resultsPanel = new ResultsProcessPanel();
		resultsUPPanel = new ResultsUPPanel();
		
		add(resultsPanel,BorderLayout.NORTH);
		add(resultsUPPanel,BorderLayout.CENTER);
	}

	public void visibilizador(boolean setVisible) {
		this.setVisible(setVisible);
	}
}
