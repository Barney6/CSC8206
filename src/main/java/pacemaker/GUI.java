package pacemaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI {
	
	public JFrame frame;
	public JTextField txtfield_Butt;
	public JPanel third_panel;
	public JPanel but_panel;
	public JPanel but_group_panel;
	
	public GUI(String JframTitle, JPanel jpanel,JPanel jpanel2)
	{
			//initialize the Frame
			frame = new JFrame(JframTitle);
			frame.setLayout(new GridLayout(3,0));
				
			// add panel to frame
			frame.add("Center",jpanel);
			frame.add("North",jpanel2);
			
			txtfield_Butt = new JTextField();
			third_panel = new JPanel();
			but_panel = new JPanel();
			but_group_panel = new JPanel();
					
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
	        
	}
	
	public void runGUI()
	{
	
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
}
