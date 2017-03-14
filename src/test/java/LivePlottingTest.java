
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pacemaker.LivePlotting;
import pacemaker.Wave;

public class LivePlottingTest {

	@Test
	public void test() throws IOException {
		
		List<Double> yData = new ArrayList<Double>();
		Wave wave = new Wave(200, 160);
	    yData = wave.generateSlow(1000);
		
		LivePlotting swingWorkerRealTime = new LivePlotting("HeartBeat");
		swingWorkerRealTime.chart.setHeartBeat(yData);
		swingWorkerRealTime.run();
		System.in.read();
	}

}
