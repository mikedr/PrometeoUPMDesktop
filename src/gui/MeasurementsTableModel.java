package gui;

import java.time.LocalTime;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Measurement;

public class MeasurementsTableModel extends AbstractTableModel {
	
	private List<Measurement> db;
	
	private String[] colNames = {"Tiempo", "Temperatura"};
	
	public MeasurementsTableModel() {
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData (List<Measurement> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return null;
	}

}