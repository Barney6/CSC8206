import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pacemaker.LivePlotting;
import pacemaker.Wave;
import pacemaker.Chart;

public class LivePlottingTest {

	@Test
	public void test() throws IOException {
	
		List<Double> normalyData = new ArrayList<Double>();
		Chart c = new Chart(null);
		LivePlotting lp = new LivePlotting(null);
		Wave norm = new Wave(20, 16);
	    normalyData = norm.generateNormal();
	    
	    List<Double> slwoData = new ArrayList<Double>();
		LivePlotting lps = new LivePlotting(null);
		Wave slow = new Wave(20, 16);
		slwoData = slow.generateSlow();
	    
		c.setHeartBeat(normalyData);
		lp.setInterval(1);
		lp.run();
		
//		lps.setHeartBeat(slwoData);
//		lps.setInterval(1);
//		lps.run();
//		
		System.in.read();
	}

}
