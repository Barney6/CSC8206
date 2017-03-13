
import java.io.IOException;

import org.junit.Test;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import pacemaker.Wave;


public class WaveTest {

	@Test
	public void test() throws IOException {
		double[] xData = new double[1000];
	    double[] yData = new double[1000];

	    for(int i=0; i<1000; i++){
	    	xData[i] = i;
	    }
	    
	    Wave wave = new Wave(200, 120, 320);
	    yData = wave.generateWave();
	 
	    // Create Chart
	    XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
	 
	    // Show it
	    new SwingWrapper<XYChart>(chart).displayChart();
	    
	    System.in.read();
	}

}
