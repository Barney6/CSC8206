import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pacemaker.LivePlotting;
import pacemaker.Wave;

public class LivePlottingTest {

	@Test
	public void test() throws IOException {
		
		List<Integer> yData = new ArrayList<Integer>();
		Wave wave = new Wave(200, 160);
	    yData = wave.generateNormal();
		
		LivePlotting swingWorkerRealTime = new LivePlotting();
		swingWorkerRealTime.setHeartBeat(yData);
		swingWorkerRealTime.setIntervalTime(1);
		swingWorkerRealTime.run();
		System.in.read();
	}

}
