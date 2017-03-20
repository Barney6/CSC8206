package pacemaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI implements ActionListener {

	public JFrame frame;
	public JProgressBar butteryL;
	public JPanel bottomPanel;
	public JPanel HrtBtButton;
	public JPanel ModeButton;
	public JPanel AllButtons;
	public JPanel Function;
	public LivePlotting swingWorkerRealTime;
	public LivePlotting swingWorkerRealTime1;
	public List<Double> yData_heartbeat;
	public List<Double> yData_paceresponse;
	public FaultInjection fiFrame;
	public Setting settingFrame;
	public Container buttery;
	public Timer timer;
	public JButton example_button;

	private JButton nb = new JButton("Normal HeartBeat");
	private JButton sinusNode = new JButton("Sinus Node Disease");
	private JButton atBlock = new JButton("Atrioventricular Block");
	private JButton atFib = new JButton("Atrial Fibrillation");
	private JButton aai = new JButton("AAI mode");
	private JButton vdd = new JButton("VDD mode");
	private JButton ddd = new JButton("DDD mode");
	private JButton set = new JButton("Setting");
	private JButton finj = new JButton("Fault Injection");
	private JButton switch_button = new JButton("On/Off");

	private Wave wave_t;
	private Wave wave_b;

	public GUI(String JframTitle) {
		// initialize output

		// initialize the Frame
		swingWorkerRealTime = new LivePlotting("HeartBeat");
		swingWorkerRealTime1 = new LivePlotting("Pacemake Response");
		fiFrame = new FaultInjection();
		settingFrame = new Setting();
		

		yData_heartbeat = new ArrayList<Double>();
		yData_paceresponse = new ArrayList<Double>();

		// initialize heart beat
		wave_t = new Wave(200, 160);
		wave_b = new Wave(200, 160);
		
		yData_heartbeat = wave_t.generateNormal();
		swingWorkerRealTime.chart.setHeartBeat(yData_heartbeat);
		swingWorkerRealTime.run();

		frame = new JFrame(JframTitle);
		frame.setLayout(new GridLayout(3, 0));

		// add panel to frame
		frame.add(swingWorkerRealTime.Jpanel);
		frame.add(swingWorkerRealTime1.Jpanel);

		butteryL = new JProgressBar();
		bottomPanel = new JPanel();
		HrtBtButton = new JPanel();
		ModeButton = new JPanel();
		AllButtons = new JPanel();
		Function = new JPanel();

		bottomPanel.setLayout(new BorderLayout(5, 5));
		HrtBtButton.setLayout(new GridLayout(2, 2));//80,40
		ModeButton.setLayout(new GridLayout(4, 1));//60,40
		AllButtons.setLayout(new GridLayout(1, 4 ));
		Function.setLayout(new GridLayout(2, 1 ));
		
		//add function buttons
		Function.add(set);
		Function.add(finj);
		
		
		// add Mode buttons
		ModeButton.add(switch_button);
		ModeButton.add(aai);
		ModeButton.add(vdd);
		ModeButton.add(ddd);
		
		//add Heart Beat buttons
		HrtBtButton.add(nb);
		HrtBtButton.add(sinusNode);
		HrtBtButton.add(atBlock);
		HrtBtButton.add(atFib);
		
		//create buttery
		buttery = new Container();
		buttery.setLayout(new BorderLayout()); 
		
		
		//create buttery bar
		butteryL = new JProgressBar();  
		butteryL.setMinimum(0);  
		butteryL.setMaximum(100);  
		butteryL.setValue(100);  
		butteryL.setStringPainted(true);  
		//butteryL.addChangeListener(this);  
		butteryL.setPreferredSize(new Dimension(200, 30));
		
		//add buttery bar to container
		timer = new Timer(50, this); 		
		example_button = new JButton("On/Off");
		buttery.add("Center",butteryL);
		buttery.add("East",example_button);
		example_button.addActionListener(this);
		
		//HrtBtButton.add(ModeButton);
		AllButtons.add(HrtBtButton);
		AllButtons.add(ModeButton);
		AllButtons.add(Function);
	
		nb.addActionListener(this);
		sinusNode.addActionListener(this);
		atBlock.addActionListener(this);
		atFib.addActionListener(this);
		aai.addActionListener(this);
		vdd.addActionListener(this);
		ddd.addActionListener(this);
		set.addActionListener(this);
		finj.addActionListener(this);
		switch_button.addActionListener(this);
	}

	public void runGUI() {

		bottomPanel.add("North", buttery);
		bottomPanel.add("West", AllButtons);

		frame.add(bottomPanel);
		// display the frame

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if (src == nb) {
			swingWorkerRealTime.cancel();
			yData_heartbeat = wave_t.generateNormal();
			swingWorkerRealTime.chart.setHeartBeat(yData_heartbeat);
			swingWorkerRealTime.run();// swingWorkerRealTime is the top one and
										// swingWorkerRealTime is the middle one
		}
		// set top wave to normal wave dataset
		else if (src == sinusNode) {
			swingWorkerRealTime.cancel();
			yData_heartbeat = wave_t.generateSlow();
			swingWorkerRealTime.chart.setHeartBeat(yData_heartbeat);
			swingWorkerRealTime.run();// swingWorkerRealTime is the top one and
										// swingWorkerRealTime is the middle one
		}
		/*
		 * else if(src==atBlock) // set atril block wave else if(src==atFib) //
		 * set at fib else if(src==aai) // set aai else if(src==vdd) // set vdd
		 * else if(src==ddd)
		 */
		  else if(src==set)
		  {
			  settingFrame.viewFrame();
		  }
		  else if(src==finj)
		  {
			  fiFrame.viewFrame();
		  }
		  else if(src==switch_button)//pasue and resume
		  {
			  if(!swingWorkerRealTime.pause)
			  {
					swingWorkerRealTime.pause();
			  }
			  else if(swingWorkerRealTime.pause)
			  {
					swingWorkerRealTime.resume();
			  }
		  }else if(src==example_button) 
		  {
			  timer.start();		  		  
		  }else if (src == timer) {  
	            int value = butteryL.getValue();  
	            if (value > 0) {  
	                value--;  
	                butteryL.setValue(value);  
	            }
	            if(value<10){
	                vdd.setEnabled(false);
	                ddd.setEnabled(false);                
	                // anything that can be require when buttery is lower than 10 is implement here
	                
	            }       
	        }  


	}
}
