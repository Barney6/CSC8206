
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import pacemaker.Wave;


public class WaveTest {

	@Test
	public void test() throws IOException {
		Wave wave = new Wave(200, 160);
	    
	    List<Double> y = new ArrayList<Double>();
	    List<Double> x = new ArrayList<Double>();
	    
	    y = wave.generateNormal();
	    //y.addAll(wave.generateFast(200));
	    
	    for(Double i=0.0; i<y.size(); i++)
	    	x.add(i);
	    
	    
	    // Create Chart
	    XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", x, y);
	 
	    // Show it
	    new SwingWrapper<XYChart>(chart).displayChart();

	 
	    System.in.read();
	}

}
