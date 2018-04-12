package xb3_project;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Creates a line chart of given values for view
 * The creation of the line graph is through the use of an external library "jfreechart-1.0.1.jar" and another library it used "jcommon-1.0.0.jar"
 * http://www.jfree.org/jfreechart/
 * http://www.jfree.org/jcommon/
 */
@SuppressWarnings("serial") //no intention of serializing class
public class LineChart extends ApplicationFrame {
	/**
	 * Puts together a line chart based on desired values
	 * @param applicationTitle  The title of the application
	 * @param chartTitle The charts title
	 * @param dataset   The data for the chart of type DefaultCategoryDataset
	 */
	   public LineChart( String applicationTitle , String chartTitle, DefaultCategoryDataset dataset) {
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
	}
