import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import pacemaker.LivePlotting;
import pacemaker.Wave;

public class LivePlottingTest {

	@Test
	public void test() throws IOException {
		
		double[] yData = new double[1000];
		Wave wave = new Wave(200, 120, 320);
	    yData = wave.normalBeat(true);
		
		LivePlotting swingWorkerRealTime = new LivePlotting();
		swingWorkerRealTime.setHeartBeat(yData);
		swingWorkerRealTime.setIntervalTime(1);
		swingWorkerRealTime.run();
		System.in.read();
	}

}
