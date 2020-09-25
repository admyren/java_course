import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class MyGUI extends JFrame implements ActionListener
{	
	private Table tab;
	private Timer timer;
	private JButton restart = new JButton("Restart");
	private JButton quit = new JButton("Quit");
	private JButton start = new JButton("Start");
	private JLabel stripeCnt = new JLabel("# of stripes left");
	private JLabel spotCnt = new JLabel("# of spotted left");
	private JTextField delayField;
	private JLabel playerField;
	
	private int quitFlag = 1;
	
	public MyGUI()
	{
		timer = new Timer(50, this); // Set the uppdate freq. on the GUI to 20Hz
		//tab = new Table(400, 720, 1);
		restart.addActionListener(this);
		quit.addActionListener(this);
		start.addActionListener(this);
		delayField = new JTextField("Delay");
		playerField = new JLabel();
		
		spotCnt.setPreferredSize(new Dimension(110,20));
		spotCnt.setOpaque(true);
		spotCnt.setBackground(Color.white);
		spotCnt.setForeground(Color.black);
		spotCnt.setFont(new Font("Arial", Font.PLAIN, 15));
		//spotCnt.setText("On your marks");
		spotCnt.setHorizontalAlignment(JLabel.CENTER);
		spotCnt.setVerticalAlignment(JLabel.TOP);

		stripeCnt.setPreferredSize(new Dimension(120,20));
		stripeCnt.setOpaque(true);
		stripeCnt.setBackground(Color.white);
		stripeCnt.setForeground(Color.black);
		stripeCnt.setFont(new Font("Arial", Font.PLAIN, 15));
		//stripeCnt.setText("Elapsed time");
		stripeCnt.setHorizontalAlignment(JLabel.CENTER);
		stripeCnt.setVerticalAlignment(JLabel.TOP);
		
		playerField.setPreferredSize(new Dimension(200,20));
		playerField.setOpaque(true);
		//playerField.setBackground(Color.white);
		playerField.setForeground(Color.red);
		playerField.setFont(new Font("Arial", Font.PLAIN, 20));
		//stripeCnt.setText("Elapsed time");
		playerField.setHorizontalAlignment(JLabel.CENTER);
		playerField.setVerticalAlignment(JLabel.TOP);
		
		//add(tab);
		add(restart);
		add(quit);
		add(start);
		add(stripeCnt);
		add(spotCnt);
		add(delayField);
		add(playerField);
		
		this.setLayout(new FlowLayout()); // enklast...
		this.setPreferredSize(new Dimension(400,300));
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timer.start();
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == timer)
		{
			if(quitFlag == 0)
			{
			stripeCnt.setText("# of stripes left: " + tab.getNrOfStripes());
			spotCnt.setText("# of spots left: " + tab.getNrOfSpots());
			
			if(tab.getCueBallFlag() == 0)
			{
				if(tab.getPlayerChangeFlag() == 1)
				{
					switch(tab.getPlayerFlag())
					{
	    				case(0):
	    					tab.setPlayerFlag();
	    					playerField.setText("Player 1");
	    				break;
	    				case(1):
	    					tab.clearPlayerFlag();
	    					playerField.setText("Player 2");
	    				break;
					}
				tab.clearPlayerChangeFlag();
				}
				/*
				switch(tab.getPlayerFlag())
				{
					case(0):
						playerField.setText("Player 1");
					break;
					case(1):
						playerField.setText("Player 2");
					break;
				}
				*/
			}
			}
		}
		else if(e.getSource() == restart)
		{
			if(quitFlag == 0)
			{
			tab.resetBalls();
			}
		}
		else if(e.getSource() == quit)
		{
			if(quitFlag == 0)
			{
			tab.dispose();
			quitFlag = 1;
			}
			//System.exit(0);
		}
		else if(e.getSource() == start)
		{
			if(quitFlag == 1)
			{
				tab = new Table(400, 720, 1);
				quitFlag = 0;
			}
		}
	}
	
	public static void main(String[] args)
	{
		MyGUI gui = new MyGUI();
	}
	
	
}
