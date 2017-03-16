package pacemaker;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;


public class GUI implements ActionListener{
	
	public JFrame frame;
	public JTextField txtfield_Butt;
	public JPanel third_panel;
	public JPanel but_panel;
	public JPanel but_group_panel;
	public LivePlotting swingWorkerRealTime;
	public LivePlotting swingWorkerRealTime1;
	public List<Double> yData_heartbeat;
	public List<Double> yData_paceresponse;
	public boolean intialize_heartBeat;
	public boolean intialize_packmaker;
	
	private JButton nb = new JButton("Normal HeartBeat");
	private JButton sinusNode = new JButton("Sinus Node Disease");
	private JButton atBlock = new JButton("Atrioventricular Block");
	private JButton atFib = new JButton("Atrial Fibrillation");
	private JButton aai = new JButton("AAI mode");
	private JButton vdd = new JButton("VDD mode");
	private JButton ddd = new JButton("DDD mode");
	
	private Wave wave_t;
	private Wave wave_b;
	
	public GUI(String JframTitle)
	{
			//initialize output
			intialize_heartBeat = true;
			intialize_packmaker = true;
		
			//initialize the Frame
		 	swingWorkerRealTime = new LivePlotting("HeartBeat");
		 	swingWorkerRealTime1 = new LivePlotting("Pacemake Response");
		 	
		 	yData_heartbeat = new ArrayList<Double>();
		 	yData_paceresponse = new ArrayList<Double>();
		 	
		 	//initialize heart beat
		 	wave_t = new Wave(200, 160);
		 	yData_heartbeat = wave_t.generateSlow();
					 	
			frame = new JFrame(JframTitle);
			frame.setLayout(new GridLayout(3,0));
				
			// add panel to frame
			frame.add("Center",swingWorkerRealTime.Jpanel);
			frame.add("North",swingWorkerRealTime1.Jpanel);
			
			txtfield_Butt = new JTextField();
			third_panel = new JPanel();
			but_panel = new JPanel();
			but_group_panel = new JPanel();
					
			third_panel.setLayout(new BorderLayout(5,5));
			but_panel.setLayout(new FlowLayout(1,80,40));
			but_group_panel.setLayout(new GridLayout(2,3,60,40));
			
			//set group_panel button
			but_group_panel.add(sinusNode);
			but_group_panel.add(atBlock);
			but_group_panel.add(atFib);
			but_group_panel.add(aai);
			but_group_panel.add(vdd);
			but_group_panel.add(ddd);			
	        but_panel.add(nb);  
	        but_panel.add(but_group_panel);  
	        
	        nb.addActionListener(this);
	        sinusNode.addActionListener(this);
	        atBlock.addActionListener(this);
	        atFib.addActionListener(this);
	        aai.addActionListener(this);
	        vdd.addActionListener(this);
	        ddd.addActionListener(this);
	}
	
	public void runGUI()
	{
		// start pasing the parameter
		if (intialize_heartBeat)
		{
			swingWorkerRealTime.chart.setHeartBeat(yData_heartbeat);
			swingWorkerRealTime.run();
		}
		
		if (intialize_packmaker)
		{
			swingWorkerRealTime1.chart.setHeartBeat(yData_paceresponse);
			swingWorkerRealTime1.run();		
		}
		
		txtfield_Butt.setPreferredSize( new Dimension( 200, 24 ) );
		txtfield_Butt.setEditable(false);		
		
        third_panel.add("North",txtfield_Butt);
        third_panel.add("Center",but_panel);
        		
		frame.add(third_panel);
		// display the frame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);  
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setTextfield(String batterylife, String cur_mode)
	{
		txtfield_Butt.setText("          Battery life : " +batterylife+"             		    Pacing Mode: "+cur_mode);
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if(src==nb){
				intialize_heartBeat = false;
				yData_heartbeat = wave_t.generateNormal();
				swingWorkerRealTime.chart.setHeartBeat(yData_heartbeat);
				swingWorkerRealTime.run();//swingWorkerRealTime is the top one and swingWorkerRealTime is the middle one
			}
			//set top wave to normal wave dataset
		else if(src==sinusNode){
			intialize_heartBeat = false;
			yData_heartbeat = wave_t.generateSlow();
			swingWorkerRealTime.chart.setHeartBeat(yData_heartbeat);
			swingWorkerRealTime.run();//swingWorkerRealTime is the top one and swingWorkerRealTime is the middle one
		}
		/*
		else if(src==atBlock)
			// set atril block wave
		else if(src==atFib)
			// set at fib
		else if(src==aai)
			// set aai
		else if(src==vdd)
			// set vdd
		else if(src==ddd)
		*/
	}
	
}
