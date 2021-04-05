package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dispositivo extends JFrame implements ActionListener{
	private JButton btnLeerDispositivo;
	private JButton btnIniciarDispositivo;
	private Dimension parentDimension;
	private JComboBox comboSincronismo;
	private JLabel labelSincronismo;
	public Dispositivo(Dimension parentDimension) {
		super("Dispositvo");
		this.parentDimension = parentDimension;
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
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnLeerDispositivo,gc);
		
//		gc.gridx = 1;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		add(fieldTempInicial,gc);
		////////////////////Next Row
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(labelSincronismo,gc);
		
//		gc.gridx = 1;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		add(comboSincronismo,gc);
		////////////////////Next Row
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(comboSincronismo,gc);
		
//		gc.gridx = 1;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		add(comboSincronismo,gc);
		////////////////////Next Row
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnIniciarDispositivo,gc);
		
//		gc.gridx = 1;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		add(comboSincronismo,gc);
	}

	private void instaciateComponents() {
		btnLeerDispositivo = new JButton("Leer mediciones");
		btnLeerDispositivo.setPreferredSize(btnLeerDispositivo.getPreferredSize());
		btnLeerDispositivo.addActionListener(this);
		comboSincronismo = new JComboBox();
		labelSincronismo = new JLabel("Sincronismo (Segundos)");
		DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
		comboModel.addElement(1);
		comboModel.addElement(2);
		comboModel.addElement(3);
		comboModel.addElement(4);
		comboModel.addElement(5);
		comboModel.addElement(6);
		comboModel.addElement(7);
		comboModel.addElement(8);
		comboModel.addElement(9);
		comboModel.addElement(10);
		comboSincronismo.setModel(comboModel);
		btnIniciarDispositivo = new JButton("Iniciar");
		btnIniciarDispositivo.setPreferredSize(btnLeerDispositivo.getPreferredSize());
	}

	private void setup() {
		
		int parentWidth = (int) parentDimension.getWidth();
		int parentHeight = (int) parentDimension.getHeight();
		this.setLocation(parentWidth/4, parentHeight/4);
		setLayout(new BorderLayout());
		setSize(400, 300);
		setMinimumSize(new Dimension(400, 300));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
