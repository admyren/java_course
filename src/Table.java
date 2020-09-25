import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.*;

public class Table extends JFrame
implements ActionListener, MouseListener, MouseMotionListener
{
	//------Global variables-----
	public enum Player
	{
		PLAYER1,
		PLAYER2
	};
	
	//---------------------------
	
	private Box box;
	private Timer timer;
	//private MyGUI GUI;

	
	private int xPressed;
	private int yPressed;
	private int xReleased;
	private int yReleased;
	
	public Table(int width, int height, int delay)
	{
		box = new Box(width, height);
		timer = new Timer(delay, this);
		
		
		add(box);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Register for mouse events
        box.addMouseListener(this);
        addMouseListener(this);
        setPreferredSize(new Dimension(450, 450));
		
		//Register for mouse events
        box.addMouseMotionListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(450, 450));
        
        this.addBalls();
        
		timer.start();	
	}
	
	public void setPlayerFlag()
	{
		box.setPlayerFlag();
	}
	
	public void clearPlayerFlag()
	{
		box.clearPlayerFlag();
	}
	
	public int getPlayerFlag()
	{
		return box.getPlayerFlag();
	}
	
	public void setCueBallFlag()
	{
		box.setCueBallFlag();
	}
	
	public void clearCueBallFlag()
	{
		box.clearCueBallFlag();
	}
	
	public int getCueBallFlag()
	{
		return box.getCueBallFlag();
	}
	
	public void setPlayerChangeFlag()
	{
		box.setPlayerChangeFlag();
	}
	
	public void clearPlayerChangeFlag()
	{
		box.clearPlayerChangeFlag();
	}
	
	public int getPlayerChangeFlag()
	{
		return box.getPlayerChangeFlag();
	}
	
	public void resetBalls()
	{
		this.removeBalls();
		this.addBalls();
		box.setCueBallVelocity(new Vector(0,0));
		box.setCueBallPosition(new Vector(0.5, 0.6));
		box.setEightBallVelocity(new Vector(0,0));
		box.setEightBallPosition(new Vector(0.5, 0.151));
	}
	
	public void addBalls()
	{
		box.addSpotBall(Color.blue, new Vector(0.396, 0.1));
        box.addStripeBall(Color.red, new Vector(0.448, 0.1));
        box.addStripeBall(Color.red, new Vector(0.5, 0.1));
        box.addStripeBall(Color.red, new Vector(0.552, 0.1));
        box.addStripeBall(Color.red, new Vector(0.604, 0.1));
        
        box.addSpotBall(Color.blue, new Vector(0.422, 0.1255));
        box.addSpotBall(Color.blue, new Vector(0.474, 0.1255));
        box.addSpotBall(Color.blue, new Vector(0.526, 0.1255));
        box.addStripeBall(Color.red, new Vector(0.578, 0.1255));
        
        box.addSpotBall(Color.blue, new Vector(0.448, 0.151));
        //box.addSpotBall(Color.red, new Vector(0.5, 0.151));
        box.addSpotBall(Color.blue, new Vector(0.552, 0.151));
        
        box.addStripeBall(Color.red, new Vector(0.474, 0.1765));
        box.addSpotBall(Color.blue, new Vector(0.526, 0.1765));
        
        box.addStripeBall(Color.red, new Vector(0.5, 0.202));
	}
	
	
	
	public void removeBalls()
	{
		int nrOfBalls = box.getNrOfSpots();
		int i;
		for(i=0; i<nrOfBalls; i++)
		{
			box.removeSpotBall(0);
		}
		nrOfBalls = box.getNrOfStripes();
		for(i=0; i<nrOfBalls; i++)
		{
			box.removeStripeBall(0);
		}
	}
	
	public int getNrOfStripes()
	{
		return box.getNrOfStripes();
	}
	
	public int getNrOfSpots()
	{
		return box.getNrOfSpots();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		box.step();
		box.drawCue(xPressed, yPressed, xReleased, yReleased);
	}

	public void mousePressed(MouseEvent e) 
    {
		switch(box.getCueBallFlag())
		{
		case(0):
			Vector v = box.getCueBallPosition();
			xPressed = (int)(v.getX()*box.getWidth());
			yPressed = (int)(v.getY()*box.getHeight());
			System.out.println(xPressed + ", " + yPressed);
			xReleased = e.getX();
			yReleased = e.getY();
			break;
		case(1):
			box.setCueBallVelocity(new Vector(0,0));
			box.setCueBallPosition(new Vector((double)e.getX()/box.getWidth(), (double)e.getY()/box.getHeight()));
		}
    }
    
    public void mouseReleased(MouseEvent e) 
    {
    	if(box.getCueBallFlag() == 0)
    	{
    		int vectX = (xPressed-xReleased)/10;
    		int vectY = (yPressed-yReleased)/10;

    		box.setCueBallVelocity(new Vector((double)vectX/box.getWidth(), (double)vectY/box.getHeight()));
    		xPressed = 0;
    		yPressed = 0;
    		xReleased = 0;
    		yReleased = 0;
    		
    		setPlayerChangeFlag();
    		/*
    		switch(box.getPlayerFlag())
    		{
    			case(0):
    				box.setPlayerFlag();
    			break;
    			case(1):
    				box.clearPlayerFlag();
    			break;
    		}
    		*/
    	}
    	else
    	{
    		box.clearCueBallFlag();
    	}
    }
    
    public void mouseDragged(MouseEvent e)
    {
    	Vector v = box.getCueBallPosition();
    	xReleased = e.getX();
    	yReleased = e.getY();
    	xPressed = (int)(v.getX()*box.getWidth());
		yPressed = (int)(v.getY()*box.getHeight());
    }
   
    public void mouseMoved(MouseEvent e)
    {
    }
    
    public void mouseEntered(MouseEvent e) 
    {
    }
    
    public void mouseExited(MouseEvent e) 
    {
    }
    
    public void mouseClicked(MouseEvent e) 
    {
    }
}
