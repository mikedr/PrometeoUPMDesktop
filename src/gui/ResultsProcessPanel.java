package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.Controller;
import model.Pasteurization;

public class ResultsProcessPanel extends JPanel implements ActionListener {
	
	private JLabel labelTempInicial;
	private JLabel labelTempMaxima;
	private JLabel labelTempFinal;
	private JLabel labelTiempTotal;
	
	private JTextField fieldTempInicial;
	private JTextField fieldTempMaxima;
	private JTextField fieldTempFinal;
	private JTextField fieldTiempTotal;
	
	private JLabel labelTempDeCorte;
	private JLabel labelTiempUP;
	private JLabel labelUP;
	
	private JTextField fieldTempDeCorte;
	private JTextField fieldEditableTempDeCorte;
	private JTextField fieldTiempUP;
	private JTextField fieldUP;
	
	private JButton okBtn;
	
	private Controller controller;
	
	private static final String DEGREES_CELSIUS = " °C";
	
	public ResultsProcessPanel() {
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
		add(labelTempInicial,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempInicial,gc);
		
		////////////////////Next Row
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTempMaxima,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempMaxima,gc);

		////////////////////Next Row
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTempFinal,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTempFinal,gc);
		
		////////////////////Next Row
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTiempTotal,gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldTiempTotal,gc);
		
		////////////////////Next Row
		gc.gridy++;
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
		
		////////////////////Next Row
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
	
	public void enableCalculadoraUP() {
		okBtn.setVisible(true);
		fieldEditableTempDeCorte.setVisible(true);
	}

	private void instaciateComponents() {
		labelTempInicial = new JLabel("Temperatura inicial: ");
		fieldTempInicial = new JTextField(5);
		fieldTempInicial.setHorizontalAlignment(JLabel.CENTER);
		fieldTempInicial.setEditable(false);
		
		labelTempMaxima = new JLabel("Temperatura máxima: ");
		fieldTempMaxima = new JTextField(5);
		fieldTempInicial.setHorizontalAlignment(JLabel.CENTER);
		fieldTempMaxima.setEditable(false);
		
		labelTempFinal = new JLabel("Temperatura final: ");
		fieldTempFinal = new JTextField(5);
		fieldTempFinal.setHorizontalAlignment(JLabel.CENTER);
		fieldTempFinal.setEditable(false);
		
		labelTiempTotal = new JLabel("Tiempo total: ");
		fieldTiempTotal = new JTextField(5);
		fieldTiempTotal.setHorizontalAlignment(JLabel.CENTER);
		fieldTiempTotal.setEditable(false);		
		
		labelTempDeCorte = new JLabel("Temperatura de corte: ");
		fieldTempDeCorte = new JTextField(5);
		fieldTempDeCorte.setHorizontalAlignment(JLabel.CENTER);
		fieldTempDeCorte.setEditable(false);
		
		labelTiempUP = new JLabel("Tiempo UP: ");
		fieldTiempUP = new JTextField(5);
		fieldTiempUP.setHorizontalAlignment(JLabel.CENTER);
		fieldTiempUP.setEditable(false);
		
		labelUP = new JLabel("UP: ");
		fieldUP = new JTextField(5);
		fieldUP.setHorizontalAlignment(JLabel.CENTER);
		fieldUP.setEditable(false);

		fieldEditableTempDeCorte = new JTextField(4);
		fieldEditableTempDeCorte.setVisible(false);
		
		okBtn = new JButton("Calcular");
		okBtn.setVisible(false);
		
		okBtn.addActionListener(this);
	}

	private void setup() {
		Dimension dim = getPreferredSize();
		dim.width = 100;
		dim.height = 200;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Resultados del proceso");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));		
	}

	public void setMeasurementsParameters (Pasteurization pasteurization) {
		fieldTempInicial.setText(Float.toString(pasteurization.getTempInicial())+DEGREES_CELSIUS);
		fieldTempMaxima.setText(Float.toString(pasteurization.getTempMaxima())+DEGREES_CELSIUS);
		fieldTempFinal.setText(Float.toString(pasteurization.getTempFinal())+DEGREES_CELSIUS);
		fieldTiempTotal.setText(pasteurization.getTiempTotal().toString());
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.computeUP(fieldEditableTempDeCorte.getText());
		fieldTempDeCorte.setText(Float.toString(controller.getPasteurization().getTempCorte())+DEGREES_CELSIUS);
		fieldTiempUP.setText(controller.getPasteurization().getTiempUP().toString());
		fieldUP.setText(Float.toString(controller.getPasteurization().getUp()));
	}

}
