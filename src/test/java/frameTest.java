
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Test;

import pacemaker.LivePlotting;
import pacemaker.Wave;


public class frameTest {

	@Test
	public void test() throws IOException {
		
		LivePlotting swingWorkerRealTime = new LivePlotting("HeartBeat");
		List<Double> yData = new ArrayList<Double>();
		Wave wave = new Wave(200, 160);
	    yData = wave.generateSlow();
	    swingWorkerRealTime.chart.setHeartBeat(yData);
	    swingWorkerRealTime.run();
	    	    
	    LivePlotting swingWorkerRealTime1 = new LivePlotting("Pacemake Resource");
		List<Double> yData1 = new ArrayList<Double>();
		Wave wave1 = new Wave(200, 160);
	    yData1 = wave1.generateSlow();
	    swingWorkerRealTime1.chart.setHeartBeat(yData1);
	    swingWorkerRealTime1.run();		
		
	    //initialize the Frame
		JFrame frame = new JFrame("Just a test JFrame");
		frame.setLayout(new GridLayout(3,0));
		
		// add panel to frame
		frame.add("Center",swingWorkerRealTime.Jpanel);
		frame.add("North",swingWorkerRealTime1.Jpanel);

		//create new Jpanel in Jframe
		JPanel third_panel = new JPanel();
		JPanel but_panel = new JPanel();
		JPanel but_group_panel = new JPanel();
		
		JTextField txtfield_Butt = new JTextField();
		
		txtfield_Butt.setText("          Battery life : 100%             	    Current Mode: ");
		txtfield_Butt.setPreferredSize( new Dimension( 200, 24 ) );
		txtfield_Butt.setEditable(false);
		
		//set panel layout		
		third_panel.setLayout(new BorderLayout(5,5));
		but_panel.setLayout(new FlowLayout(1,80,40));
		but_group_panel.setLayout(new GridLayout(2,3,60,40));
		
		
		//set group_panel button
		but_group_panel.add(new JButton("Sinus Node Disease"));
		but_group_panel.add(new JButton("Atrioventricular Block"));
		but_group_panel.add(new JButton("Atrial Fibrillation"));
		but_group_panel.add(new JButton("AAI mode"));
		but_group_panel.add(new JButton("VDD mode"));
		but_group_panel.add(new JButton("DDD mode"));
		
        but_panel.add(new JButton("Normal HeartBeat"));        
        but_panel.add(but_group_panel);
        
        third_panel.add("North",txtfield_Butt);
        third_panel.add("Center",but_panel);
        		
		frame.add(third_panel);
		// display the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);  
		frame.pack();
		frame.setVisible(true);
		System.in.read();
		
	}
}
