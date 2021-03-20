package gui;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.Range;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javafx.scene.chart.ValueAxis;

public class ChartTemperature extends JPanel{  
  
  private static final long serialVersionUID = 1L;  
  
  public ChartPanel createChart() {  
    
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

    //create the datasets
    TimeSeriesCollection dataset1 = new TimeSeriesCollection();
    dataset1.addSeries(series1);
    

    //construct the plot
    XYPlot plot = new XYPlot();
    plot.setDataset(0, dataset1);

    //customize the plot with renderers and axis
    DefaultXYItemRenderer splineRendererTemp = new DefaultXYItemRenderer();
    splineRendererTemp.setBaseShapesVisible(false);
    
//    org.jfree.chart.renderer.xy.DefaultXYItemRenderer
    
    plot.setRenderer(0, splineRendererTemp);//use default fill paint for first series
    XYAreaRenderer splinerenderer = new XYAreaRenderer();
    splinerenderer.setSeriesFillPaint(0, Color.BLUE);
//    plot.setRenderer(1, splinerenderer);
    
    NumberAxis rangeAxis = new NumberAxis("Temperatura");
    plot.setRangeAxis(0, rangeAxis);
    
//    plot.setRangeAxis(1, new NumberAxis("UP"));
    NumberAxis domainAxis = new NumberAxis("X Axis");
    plot.setDomainAxis(domainAxis);
    
    //Map the data to the appropriate axis
    plot.mapDatasetToRangeAxis(0, 0);
//    plot.mapDatasetToRangeAxis(1, 1);   

    DateAxis dateAxis = new DateAxis("");
    plot.setDomainAxis(dateAxis);
    


    //generate the chart
    JFreeChart chart = new JFreeChart(plot);
//    chart.setBackgroundPaint(Color.WHITE);
    
    ChartPanel panel = new ChartPanel(chart);  
    
    panel.setSize(800, 600);
  
    return panel;
  }  
}  