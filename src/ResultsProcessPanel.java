import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ResultsProcessPanel extends JPanel {
	
	private JLabel labelTempInicial;
	private JLabel labelTempMaxima;
	private JLabel labelTempFinal;
	private JLabel labelTiempTotal;
	
	private JTextField fieldTempInicial;
	private JTextField fieldTempMaxima;
	private JTextField fieldTempFinal;
	private JTextField fieldTiempTotal;
	
	public ResultsProcessPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 200;
		dim.height = 200;
		setPreferredSize(dim);

		labelTempInicial = new JLabel("Temperatura inicial: ");
		fieldTempInicial = new JTextField(4);
		fieldTempInicial.setEditable(false);
		
		labelTempMaxima = new JLabel("Temperatura máxima: ");
		fieldTempMaxima = new JTextField(4);
		fieldTempMaxima.setEditable(false);
		
		labelTempFinal = new JLabel("Temperatura final: ");
		fieldTempFinal = new JTextField(4);
		fieldTempFinal.setEditable(false);
		
		labelTiempTotal = new JLabel("Tiempo total: ");
		fieldTiempTotal = new JTextField(4);
		fieldTiempTotal.setEditable(false);
		
		Border innerBorder = BorderFactory.createTitledBorder("Resultados del proceso");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		////////////////////First Row
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(labelTempInicial,gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempInicial,gc);
		
		////////////////////Second Row	
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridy = 1;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTempMaxima,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempMaxima,gc);

		////////////////////Third Row	
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridy = 2;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTempFinal,gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempFinal,gc);
		
		////////////////////Fourth Row	
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridy = 3;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTiempTotal,gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTiempTotal,gc);		
		
	}

}
