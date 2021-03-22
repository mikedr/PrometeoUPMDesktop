package gui;
import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import model.Measurement;
import model.Pasteurization;

public class Chart extends JPanel{  
  
	private static final long serialVersionUID = 1L;
  
	private TimeSeries serieTemperatura;
	private TimeSeries serieUP;
	
    private ChartPanel panel;
    
    public Chart() {
		XYPlot plot = new XYPlot();

		DefaultXYItemRenderer splineRendererTemp = new DefaultXYItemRenderer();
    splineRendererTemp.setBaseShapesVisible(false);
    
    plot.setRenderer(0, splineRendererTemp);//use default fill paint for first series
    XYAreaRenderer splinerenderer = new XYAreaRenderer();
    splinerenderer.setSeriesFillPaint(0, Color.BLUE);
    
    NumberAxis rangeAxis = new NumberAxis("Temperatura");
    plot.setRangeAxis(0, rangeAxis);
    
    NumberAxis domainAxis = new NumberAxis("X Axis");
    plot.setDomainAxis(domainAxis);
    
    plot.mapDatasetToRangeAxis(0, 0);

    DateAxis dateAxis = new DateAxis("");
    plot.setDomainAxis(dateAxis);
    
    JFreeChart chart = new JFreeChart(plot);
    
    this.panel = new ChartPanel(chart);
	}
  
    public void createChartTemperature(List<Measurement> measurements) {
    	TimeSeriesCollection dataset1 = new TimeSeriesCollection();
    	serieTemperatura = new TimeSeries("Temperatura vs Tiempo", Second.class);
		for(Measurement aMeasurement : measurements) {
			serieTemperatura.add(new Second(aMeasurement.getTiempo().getSecond(), aMeasurement.getTiempo().getMinute(), 
					aMeasurement.getTiempo().getHour(), 1, 1, 2021), aMeasurement.getTemperatura()); 				
		}
		dataset1.addSeries(serieTemperatura);
		XYPlot plot = new XYPlot();
		plot.setDataset(0, dataset1);

		DefaultXYItemRenderer splineRendererTemp = new DefaultXYItemRenderer();
		splineRendererTemp.setBaseShapesVisible(false);
    
		plot.setRenderer(0, splineRendererTemp);//use default fill paint for first series
		XYAreaRenderer splinerenderer = new XYAreaRenderer();
		splinerenderer.setSeriesFillPaint(0, Color.BLUE);
    
	    NumberAxis rangeAxis = new NumberAxis("Temperatura");
	    plot.setRangeAxis(0, rangeAxis);
	    
	    NumberAxis domainAxis = new NumberAxis("X Axis");
	    plot.setDomainAxis(domainAxis);
	    
	    plot.mapDatasetToRangeAxis(0, 0);
	
	    DateAxis dateAxis = new DateAxis("");
	    plot.setDomainAxis(dateAxis);
	    
	    JFreeChart chart = new JFreeChart(plot);
	    
	    this.panel = new ChartPanel(chart);  
  }  
  
	public void createChartTemperatureAndUP(Pasteurization pasteurization) {  
	    serieUP = new TimeSeries("UP vs Tiempo", Second.class);
	    int i = 0;
		for(LocalTime unTiempo : pasteurization.getTiempos()) {
			serieUP.add(new Second(unTiempo.getSecond(), unTiempo.getMinute(), 
					unTiempo.getHour(), 1, 1, 2021), pasteurization.getUPs().get(i)); 		
			i++;
		}
	    TimeSeriesCollection dataset1 = new TimeSeriesCollection();
	    TimeSeriesCollection dataset2 = new TimeSeriesCollection();
	    dataset1.addSeries(serieTemperatura);
	    dataset2.addSeries(serieUP);
	    
	    //construct the plot
	    XYPlot plot = new XYPlot();
	    plot.setDataset(0, dataset1);
	    plot.setDataset(1, dataset2);
	    
	    DefaultXYItemRenderer splineRendererTemp = new DefaultXYItemRenderer();
	    splineRendererTemp.setBaseShapesVisible(false);
	    splineRendererTemp.setPaint(Color.RED);
	    plot.setRenderer(0, splineRendererTemp);
	    
//	    XYAreaRenderer splinerenderer = new XYAreaRenderer();
//	    splinerenderer.setSeriesFillPaint(0, Color.BLUE);
	    
	    DefaultXYItemRenderer splineRendererUP = new DefaultXYItemRenderer();
	    plot.setRenderer(1, splineRendererUP);
	    splineRendererUP.setBaseShapesVisible(false);
	    splineRendererUP.setPaint(Color.BLUE);
	    
	    plot.setRangeAxis(0, new NumberAxis("Temperatura"));
	    plot.setRangeAxis(1, new NumberAxis("UP"));
	    plot.setDomainAxis(new NumberAxis("X Axis"));
	    
	    plot.mapDatasetToRangeAxis(0, 0);
	    plot.mapDatasetToRangeAxis(1, 1);   
	    
	    DateAxis domainAxis = new DateAxis("");
	    plot.setDomainAxis(domainAxis);
	    
	    JFreeChart chart = new JFreeChart(plot);
	    
	    this.panel = new ChartPanel(chart);
	  }

	public ChartPanel getPanel() {
		return panel;
	}
	
}  