package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalTime;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import model.Measurement;

public class TablePanel extends JPanel {
	private JTable table;
	private MeasurementsTableModel tableModel;
	
	public TablePanel() {
		setup();
		instaciateComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(new JScrollPane(table),BorderLayout.CENTER);		
	}

	private void instaciateComponents() {
		tableModel = new MeasurementsTableModel();
		table = new JTable(tableModel);		
	}

	private void setup() {
		Dimension dim = getPreferredSize();
		setLayout(new BorderLayout());
		dim.width = 300;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Mediciones");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);	
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}

	public void setData(List<Measurement> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}