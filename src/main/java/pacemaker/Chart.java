package pacemaker;

import java.util.List;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;

public class Chart {
	
	public XYChart XYchart;
	public List<Double> heartbeat;
	
	public Chart()
	{
		XYchart = QuickChart.getChart("", "", "", "randomWalk", new double[] { 0 },new double[] { 0 });
		XYchart.getStyler().setLegendVisible(false);
		XYchart.getStyler().setXAxisTicksVisible(false);
		XYchart.getStyler().setYAxisTicksVisible(false);
		
	}
	
	public void setHeartBeat(List<Double> yData) {
		heartbeat = yData;
	}

}
