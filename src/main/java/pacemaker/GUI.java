package pacemaker;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class GUI implements ActionListener {

	public JFrame frame;
	public JTextField txtfield_Butt;
	public JPanel bottomPanel;
	public JPanel HrtBtButton;
	public JPanel ModeButton;
	public JPanel AllButtons;
	public LivePlotting swingWorkerRealTime;
	public LivePlotting swingWorkerRealTime1;
	public List<Double> yData_heartbeat;
	public List<Double> yData_paceresponse;

	private JButton nb = new JButton("Normal HeartBeat");
	private JButton sinusNode = new JButton("Sinus Node Disease");
	private JButton atBlock = new JButton("Atrioventricular Block");
	private JButton atFib = new JButton("Atrial Fibrillation");
	private JButton aai = new JButton("AAI mode");
	private JButton vdd = new JButton("VDD mode");
	private JButton ddd = new JButton("DDD mode");

	private Wave wave_t;
	private Wave wave_b;

	public GUI(String JframTitle) {
		// initialize output

		// initialize the Frame
		swingWorkerRealTime = new LivePlotting("HeartBeat");
		swingWorkerRealTime1 = new LivePlotting("Pacemake Response");

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
		frame.add("Center", swingWorkerRealTime.Jpanel);
		frame.add("North", swingWorkerRealTime1.Jpanel);

		txtfield_Butt = new JTextField();
		bottomPanel = new JPanel();
		HrtBtButton = new JPanel();
		ModeButton = new JPanel();
		AllButtons = new JPanel();

		bottomPanel.setLayout(new BorderLayout(5, 5));
		HrtBtButton.setLayout(new GridLayout(2, 2));//80,40
		ModeButton.setLayout(new GridLayout(3, 0));//60,40
		AllButtons.setLayout(new GridLayout(0, 3 ));
		
		// add Mode buttons	
		ModeButton.add(aai);
		ModeButton.add(vdd);
		ModeButton.add(ddd);
		
		//add Heart Beat buttons
		HrtBtButton.add(nb);
		HrtBtButton.add(sinusNode);
		HrtBtButton.add(atBlock);
		HrtBtButton.add(atFib);
		
		
		//HrtBtButton.add(ModeButton);
		AllButtons.add(HrtBtButton);
		AllButtons.add(ModeButton);
		

		nb.addActionListener(this);
		sinusNode.addActionListener(this);
		atBlock.addActionListener(this);
		atFib.addActionListener(this);
		aai.addActionListener(this);
		vdd.addActionListener(this);
		ddd.addActionListener(this);
	}

	public void runGUI() {

		txtfield_Butt.setPreferredSize(new Dimension(200, 24));
		txtfield_Butt.setEditable(false);

		bottomPanel.add("North", txtfield_Butt);
		bottomPanel.add("West", AllButtons);


		frame.add(bottomPanel);
		// display the frame

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	public void setTextfield(int butterylife) {
		String life = "";
		for( int l = 0; l < butterylife ; l++)
		{
			life = life + "|";
		}
		
		txtfield_Butt.setText("Battery life : "+ life );
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
	}

}
