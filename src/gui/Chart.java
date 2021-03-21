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
  
	TimeSeriesCollection dataset1 = new TimeSeriesCollection();
	
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
    	TimeSeries series1 = new TimeSeries("Temperatura vs Tiempo", Second.class);
		for(Measurement aMeasurement : measurements) {
			series1.add(new Second(aMeasurement.getTiempo().getSecond(), aMeasurement.getTiempo().getMinute(), 
					aMeasurement.getTiempo().getHour(), 1, 1, 2021), aMeasurement.getTemperatura()); 				
		}
		dataset1.addSeries(series1);
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
  
	public void createChartTemperatureAndUP() {  
	    
	    //create the series - add some dummy data
	    TimeSeries series1 = new TimeSeries("Temperatura vs Tiempo", Second.class);
	    series1.add(new Second(0, 0, 0, 1, 1, 2021), 23.3);  
	    series1.add(new Second(10, 0, 0, 1, 1, 2021), 24.1);  
	    series1.add(new Second(20, 0, 0, 1, 1, 2021), 24.9);  
	    series1.add(new Second(30, 0, 0, 1, 1, 2021), 25.5);  
	    series1.add(new Second(40, 0, 0, 1, 1, 2021), 27.1);  
	    series1.add(new Second(50, 0, 0, 1, 1, 2021), 30.0);  
	    series1.add(new Second(0, 1, 0, 1, 1, 2021), 33.3);  
	    series1.add(new Second(10, 1, 0, 1, 1, 2021), 39.9);  
	    series1.add(new Second(20, 1, 0, 1, 1, 2021), 45.0);  
	    series1.add(new Second(30, 1, 0, 1, 1, 2021), 60.0);  
	    series1.add(new Second(40, 1, 0, 1, 1, 2021), 59.0);  
	    series1.add(new Second(50, 1, 0, 1, 1, 2021), 55.8);
	    series1.add(new Second(0, 2, 0, 1, 1, 2021), 54.1);  
	    series1.add(new Second(10, 2, 0, 1, 1, 2021), 50.7);  
	    series1.add(new Second(20, 2, 0, 1, 1, 2021), 45.6);  
	    series1.add(new Second(30, 2, 0, 1, 1, 2021), 40.1);  
	    series1.add(new Second(40, 2, 0, 1, 1, 2021), 35.2);  
	    series1.add(new Second(50, 2, 0, 1, 1, 2021), 33.2);
	    series1.add(new Second(0, 3, 0, 1, 1, 2021), 30.2);  
	    series1.add(new Second(10, 3, 0, 1, 1, 2021), 29.1);  
	    series1.add(new Second(20, 3, 0, 1, 1, 2021), 28.6);  
	    series1.add(new Second(30, 3, 0, 1, 1, 2021), 27.0);  
	    series1.add(new Second(40, 3, 0, 1, 1, 2021), 26.9);  
	    series1.add(new Second(50, 3, 0, 1, 1, 2021), 26.1);

	    TimeSeries series2 = new TimeSeries("UP vs Tiempo", Second.class);
	    series2.add(new Second(0, 0, 0, 1, 1, 2021), 0);  
	    series2.add(new Second(10, 0, 0, 1, 1, 2021), 0);  
	    series2.add(new Second(20, 0, 0, 1, 1, 2021), 0);  
	    series2.add(new Second(30, 0, 0, 1, 1, 2021), 0);  
	    series2.add(new Second(40, 0, 0, 1, 1, 2021), 0);  
	    series2.add(new Second(50, 0, 0, 1, 1, 2021), 2);  
	    series2.add(new Second(0, 1, 0, 1, 1, 2021), 3);  
	    series2.add(new Second(10, 1, 0, 1, 1, 2021), 4);  
	    series2.add(new Second(20, 1, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(30, 1, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(40, 1, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(50, 1, 0, 1, 1, 2021), 9);
	    series2.add(new Second(0, 2, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(10, 2, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(20, 2, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(30, 2, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(40, 2, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(50, 2, 0, 1, 1, 2021), 9);
	    series2.add(new Second(0, 3, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(10, 3, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(20, 3, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(30, 3, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(40, 3, 0, 1, 1, 2021), 9);  
	    series2.add(new Second(50, 3, 0, 1, 1, 2021), 9);

	    //create the datasets
	    TimeSeriesCollection dataset1 = new TimeSeriesCollection();
	    TimeSeriesCollection dataset2 = new TimeSeriesCollection();
	    dataset1.addSeries(series1);
	    dataset2.addSeries(series2);
	    
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