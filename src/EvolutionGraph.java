
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 *	EvolutionGraph produces a graph that display the average fitness of creatures over 
 * a series of generation. Uses the JFreeChart library to produce the graph.
 * @author thoda061
 */
public class EvolutionGraph extends ApplicationFrame{
	
	public EvolutionGraph(String appTitle, String graphName, XYDataset dataset) {
		super(appTitle);
		JFreeChart graph = ChartFactory.createXYLineChart(
			graphName,
			"Generation", "Fitness",
			dataset,
			PlotOrientation.VERTICAL,
			true,true,false);
		
		ChartPanel graphPanel = new ChartPanel(graph);
		graphPanel.setPreferredSize(new java.awt.Dimension(660, 367));
		XYPlot plot = graph.getXYPlot();
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(0.5f));
		renderer.setSeriesShapesVisible(0, false);
		plot.setRenderer(renderer);
		setContentPane(graphPanel);
	}
}
