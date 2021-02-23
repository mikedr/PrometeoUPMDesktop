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

public class ResultsPanel extends JPanel implements VisibleManager{
	
	private JLabel labelTempInicial;
	private JLabel labelTempMaxima;
	private JLabel labelTempDeCorte;
	private JLabel labelTempFinal;
	private JTextField fieldTempInicial;
	private JTextField fieldTempMaxima;
	private JTextField fieldTempDeCorte;
	private JTextField fieldTempFinal;
	
	private JButton okBtn;
	
	public ResultsPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setVisible(false);
		labelTempInicial = new JLabel("Temperatura inicial: ");
		fieldTempInicial = new JTextField(4);
		fieldTempInicial.setEditable(false);
		
		labelTempMaxima = new JLabel("Temperatura máxima: ");
		fieldTempMaxima = new JTextField(4);
		fieldTempMaxima.setEditable(false);
		
		labelTempDeCorte = new JLabel("Temperatura de corte: ");
		fieldTempDeCorte = new JTextField(4);
		fieldTempDeCorte.setEditable(false);
		
		labelTempFinal = new JLabel("Temperatura final: ");
		fieldTempFinal = new JTextField(4);
		fieldTempFinal.setEditable(false);
		
		okBtn = new JButton("Calcular");
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
		add(labelTempDeCorte,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempDeCorte,gc);

		////////////////////Third Row	
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridy = 2;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTempMaxima,gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempMaxima,gc);
		
		////////////////////Fourth Row	
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridy = 3;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTempFinal,gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempFinal,gc);		
		
		////////////////////Fifth row
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 4;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn,gc);
	}

	@Override
	public void visibilizador(boolean setVisible) {
		this.setVisible(setVisible);
	}
}
