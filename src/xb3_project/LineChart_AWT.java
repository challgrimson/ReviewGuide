package xb3_project;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class LineChart_AWT extends ApplicationFrame {

	   public LineChart_AWT( String applicationTitle , String chartTitle, DefaultCategoryDataset dataset) {
	      super(applicationTitle);
	      JFreeChart lineChart = ChartFactory.createLineChart(
	         chartTitle,
	         "Date","Rating",
	         dataset,
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      ChartPanel chartPanel = new ChartPanel( lineChart );
	      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	      setContentPane( chartPanel );
	   }

	   /*private DefaultCategoryDataset createDataset() {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      dataset.addValue( 15 , "trend" , "1970" );
	      dataset.addValue( 30 , "trend" , "1980" );
	      dataset.addValue( 60 , "trend" ,  "1990" );
	      dataset.addValue( 120 , "trend" , "2000" );
	      dataset.addValue( 240 , "trend" , "2010" );
	      dataset.addValue( 300 , "trend" , "2014" );
	      return dataset;
	   }*/
	   
	   /*public static void main( String[ ] args ) {
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		  dataset.addValue( 15 , "trend" , "1970" );
	      dataset.addValue( 30 , "trend" , "1980" );
	      dataset.addValue( 60 , "trend" ,  "1990" );
	      dataset.addValue( 120 , "trend" , "2000" );
	      dataset.addValue( 240 , "trend" , "2010" );
	      dataset.addValue( 300 , "trend" , "2014" );
	      LineChart_AWT chart = new LineChart_AWT(
	         "Ratings Vs Date" ,
	         "Ratings vs Date",
	         dataset);
	      chart.pack( );
	      RefineryUtilities.centerFrameOnScreen( chart );
	      chart.setVisible( true );
	   }*/
	}
