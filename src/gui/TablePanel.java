package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		setLayout(new BorderLayout());	
		Dimension dim = getPreferredSize();
		dim.width = 200;
		dim.height = 200;
		setPreferredSize(dim);
	}

	public void setData(List<LocalTime> tiempos, List<Float> temperaturas) {
		tableModel.setData(tiempos, temperaturas);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}