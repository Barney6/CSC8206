import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;
import org.knowm.xchart.XChartPanel;

import pacemaker.LivePlotting;
import pacemaker.Wave;


public class frameTest {

	@Test
	public void test() throws IOException {
		
		LivePlotting swingWorkerRealTime = new LivePlotting("HeartBeat");
		List<Double> yData = new ArrayList<Double>();
		Wave wave = new Wave(200, 160);
	    yData = wave.generateSlow(1000);
	    swingWorkerRealTime.chart.setHeartBeat(yData);
	    swingWorkerRealTime.run();
	    
	    /*
	    LivePlotting swingWorkerRealTime1 = new LivePlotting("HeartBeat");
		List<Double> yData1 = new ArrayList<Double>();
		Wave wave1 = new Wave(200, 160);
	    yData1 = wave1.generateSlow(1000);
	    swingWorkerRealTime1.chart.setHeartBeat(yData);
	    swingWorkerRealTime1.run();		
		*/
	    
	    //initialize the Frame
		JFrame frame = new JFrame("Just a test JFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add panel to frame
		frame.add(swingWorkerRealTime.Jpanel,BorderLayout.NORTH);
		//frame.add(swingWorkerRealTime1.Jpanel,BorderLayout.SOUTH);
		
		
		// display the frame
		frame.pack();
		frame.setVisible(true);
		System.in.read();
		
	}
}
