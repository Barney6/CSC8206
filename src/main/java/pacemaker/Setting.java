package pacemaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Setting implements ActionListener{
	
	public JFrame Setting_frame;
	
	public  JTextField setting1;
	public  JTextField setting2;
	public  JTextField setting3;
	
	public 	JLabel set1Label;
	public  JLabel set2Label;
	public  JLabel set3Label;
	public  JButton submit;
	
	
	public Setting()
	{
		
			//set container
		   Container content = new Container(); 
		   //content.setBackground(Color.white); 
		   content.setLayout(new GridLayout(3,2,25,20)); 
		   
		   
		   //set labels and textfield
		   setting1 = new JTextField("exampleSetting1");
		   setting2 = new JTextField("exampleSetting2");;
		   setting3 = new JTextField("exampleSetting3");;
		  		   
		   set1Label = new JLabel("Setting1 :",set1Label.CENTER);
		   set2Label = new JLabel("Setting2 :",set2Label.CENTER);
		   set3Label = new JLabel("Setting3 :",set3Label.CENTER);
		   
		   // set submit
		   submit = new JButton("Confirm");
		   submit.addActionListener(this);
		  	   
		   content.add(set1Label);
		   content.add(setting1);
		   content.add(set2Label);
		   content.add(setting2);
		   content.add(set3Label);
		   content.add(setting3);
		   		   			
		   Setting_frame = new JFrame("Pacemaker Setting");
		   Setting_frame.setLayout(new BorderLayout(5, 5));
		   Setting_frame.setBounds(512, 260, 300, 300);
		   
		   Setting_frame.add(content,"Center");
		   Setting_frame.add(submit,"South");
		  		   
		   Setting_frame.pack();
		   Setting_frame.setVisible(false);
		   
		   
		   // layout setting here, what it contains.
		
	}
	
	public void viewFrame()
	{
		Setting_frame.setVisible(true);
		
	}
	
	
	public void submitSetting()
	{
		Setting_frame.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if (src == submit) {
			submitSetting();
		}
		
	}

}
