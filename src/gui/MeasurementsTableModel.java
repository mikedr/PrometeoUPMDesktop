package gui;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MeasurementsTableModel extends AbstractTableModel {
	
	private List<LocalTime> tiempos;
	private List<Float> temperaturas;
	
	private String[] colNames = {"Tiempo", "Temperatura"};
	
	public MeasurementsTableModel() {
		this.tiempos = new ArrayList<>();
		this.temperaturas = new ArrayList<>();
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData (List<LocalTime> tiempos, List<Float> temperaturas) {
		this.tiempos = tiempos;
		this.temperaturas = temperaturas;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return tiempos.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return null;
	}

}