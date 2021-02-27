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

public class ResultsUPPanel extends JPanel {
	
	private JLabel labelTempDeCorte;
	private JLabel labelTiempUP;
	private JLabel labelUP;
	
	private JTextField fieldTempDeCorte;
	private JTextField fieldEditableTempDeCorte;
	private JTextField fieldTiempUP;
	private JTextField fieldUP;
	
	private JButton okBtn;
	
	public ResultsUPPanel() {
		setup();
		instaciateComponents();
		addComponents();
	}

	private void addComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		////////////////////First Row
		gc.gridy = 0;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(labelTempDeCorte,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempDeCorte,gc);
		
		////////////////////Next Row	
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTiempUP,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTiempUP,gc);

		////////////////////Next Row	
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelUP,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldUP,gc);
		////////////////////Eighth row
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(fieldEditableTempDeCorte,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(okBtn,gc);			
	}

	private void instaciateComponents() {
		labelTempDeCorte = new JLabel("Temperatura de corte: ");
		fieldTempDeCorte = new JTextField(4);
		fieldTempDeCorte.setEditable(false);
		
		labelTiempUP = new JLabel("Tiempo UP: ");
		fieldTiempUP = new JTextField(4);
		fieldTiempUP.setEditable(false);
		
		labelUP = new JLabel("UP: ");
		fieldUP = new JTextField(4);
		fieldUP.setEditable(false);

		fieldEditableTempDeCorte = new JTextField(4);
		fieldEditableTempDeCorte.setEditable(true);
		
		okBtn = new JButton("Calcular");		
	}

	private void setup() {
		Dimension dim = getPreferredSize();
		dim.width = 200;
		dim.height = 200;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Resultados de pasturización");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);	
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}

}
