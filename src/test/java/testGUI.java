import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pacemaker.GUI;
import pacemaker.LivePlotting;
import pacemaker.Wave;


public class testGUI {
	@Test
	
	public void test() throws IOException {
		
		//LivePlotting swingWorkerRealTime = new LivePlotting("HeartBeat");
		//List<Double> yData = new ArrayList<Double>();

		
		//Wave wave = new Wave(200, 160);
		//yData = wave.generateSlow();		
		//swingWorkerRealTime.chart.setHeartBeat(yData);
		//swingWorkerRealTime.run();
    
		//LivePlotting swingWorkerRealTime1 = new LivePlotting("Pacemake Response");
		//List<Double> yData1 = new ArrayList<Double>();
		
		//Wave wave1 = new Wave(200, 160);
		//yData1 = wave1.generateSlow();
		//swingWorkerRealTime1.chart.setHeartBeat(yData1);
		//swingWorkerRealTime1.run();	
		
		GUI gui = new GUI("");
		gui.setTextfield("100%", "null");
		gui.runGUI();
		System.in.read();
		
	}

}
