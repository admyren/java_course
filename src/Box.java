import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class Box extends JPanel
{
	private int cueBallFlag;
	private int playerFlag; // 0 is spots, 1 is stripes
	private int playerChangeFlag;
	
	private ArrayList<Ball> spotBalls = new ArrayList<Ball>();
	private ArrayList<Ball> stripeBalls = new ArrayList<Ball>();
	private Ball eightBall;
	private Ball cueBall;
	
	private ArrayList<Holes> holes = new ArrayList<Holes>();
	
	private Cue cu;

	public Box(int width, int height) 
	{
		cu = new Cue(this, 0, 0, 0, 0);
		holes.add(new Holes(this, new Vector(0, 0.5)));
		holes.add(new Holes(this, new Vector(1, 0.5)));
		holes.add(new Holes(this, new Vector(0, 1)));
		holes.add(new Holes(this, new Vector(1, 0)));
		holes.add(new Holes(this, new Vector(0, 0)));
		holes.add(new Holes(this, new Vector(1, 1)));
		
		eightBall = new Ball(this, new Vector(0.5, 0.151), new Vector(0,0), Color.black, 10);
		cueBall = new Ball(this, new Vector(0.5, 0.6), new Vector(0,0), Color.white, 10);
		
		this.setPreferredSize(new Dimension(width, height));  
		this.setBackground(Color.green);
		this.setBorder(new LineBorder(Color.black,2));
	}
	
	public void setCueBallFlag()
	{
		cueBallFlag = 1;
	}
	
	public void clearCueBallFlag()
	{
		cueBallFlag = 0;
	}
	
	public int getCueBallFlag()
	{
		return cueBallFlag;
	}
	
	public void setPlayerFlag()
	{
		playerFlag = 1;
	}
	
	public void clearPlayerFlag()
	{
		playerFlag = 0;
	}
	
	public int getPlayerFlag()
	{
		return playerFlag;
	}
	
	public void setPlayerChangeFlag()
	{
		playerChangeFlag = 1;
	}
	
	public void clearPlayerChangeFlag()
	{
		playerChangeFlag = 0;
	}
	
	public int getPlayerChangeFlag()
	{
		return playerChangeFlag;
	}
	
	
	
	public int getNrOfStripes()
	{
		return stripeBalls.size();
	}
	
	public int getNrOfSpots()
	{
		return spotBalls.size();
	}
	
	public Vector getCueBallPosition()
	{
		return cueBall.getPos();
	}
	
	public void setCueBallPosition(Vector v)
	{
		cueBall.setPos(v);
	}
	
	public void setCueBallVelocity(Vector v)
	{
		cueBall.setVel(v);
	}
	
	public void setEightBallPosition(Vector v)
	{
		eightBall.setPos(v);
	}
	
	public void setEightBallVelocity(Vector v)
	{
		eightBall.setVel(v);
	}

	public void addSpotBall(Color c, Vector position)
	{
		spotBalls.add(new Ball(this, position, new Vector(0,0), c, 10));
	}
	
	public void addStripeBall(Color c, Vector position)
	{
		stripeBalls.add(new Ball(this, position, new Vector(0,0), c, 10));
	}
	
	public boolean removeSpotBall(int index)
	{
		if(spotBalls.get(index) != null)
		{
			spotBalls.remove(index);
			return true;
		}
		return false;
	}
	
	public boolean removeStripeBall(int index)
	{
		if(stripeBalls.get(index) != null)
		{
			stripeBalls.remove(index);
			return true;
		}
		return false;
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		for(Ball ball: spotBalls)
		{
			ball.paint(g);
		}
		
		for(Ball ball: stripeBalls)
		{
			ball.paint(g);
		}
		
		eightBall.paint(g);
		cueBall.paint(g);
		cu.paint(g);
		
		for(Holes hole: holes)
		{
			hole.paintH(g);
		}
	}

	public void step()
	{	
		for(int i=0; i<stripeBalls.size(); i++)
		{
			for(int j=i+1; j<stripeBalls.size(); j++)
			{
				stripeBalls.get(i).collide(stripeBalls.get(j));
			}
			stripeBalls.get(i).collide(eightBall);
			stripeBalls.get(i).collide(cueBall);
		}
		
		for(int i=0; i<spotBalls.size(); i++)
		{
			for(int j=i+1; j<spotBalls.size(); j++)
			{
				spotBalls.get(i).collide(spotBalls.get(j));
			}
			spotBalls.get(i).collide(eightBall);
			spotBalls.get(i).collide(cueBall);
		}
		
		for(int i=0; i<spotBalls.size(); i++)
		{
			for(int j=0; j<stripeBalls.size(); j++)
			{
				spotBalls.get(i).collide(stripeBalls.get(j));
			}
		}
		
		cueBall.collide(eightBall);
		
		for(Holes hole: holes)
		{
			if(hole.collision(eightBall))
			{
				JOptionPane.showMessageDialog(this, "Game over");
				eightBall.setPos(new Vector(-0.5,-0.5));
			}
			else if(hole.collision(cueBall))
			{
				JOptionPane.showMessageDialog(this, "Place the cue ball by clicking the mouse at that position");
				this.cueBallFlag = 1;
				cueBall.setPos(new Vector(-0.5,-0.5));
			}
		}
		
		for(int i=0; i<holes.size(); i++)
		{
			for(int j=0; j<spotBalls.size(); j++)
			{
				if(holes.get(i).collision(spotBalls.get(j)))
				{
					spotBalls.remove(j);
					switch(playerFlag)
					{
						case(0):
							playerChangeFlag = 0;
						break;
						case(1):
							playerChangeFlag = 1;
						break;
					}
				}
			}
			
			for(int j=0; j<stripeBalls.size(); j++)
			{
				if(holes.get(i).collision(stripeBalls.get(j)))
				{
					stripeBalls.remove(j);
					switch(playerFlag)
					{
						case(0):
							playerChangeFlag = 1;
						break;
						case(1):
							playerChangeFlag = 0;
						break;
					}
				}
			}
		}
		
		// Move all the balls 

		eightBall.move();
		cueBall.move();
		
		for(Ball ball: spotBalls)
		{
			ball.move();
		}
		for(Ball ball: stripeBalls)
		{
			ball.move();
		}
		
		repaint();
	}    
	
	public void drawCue(int x1, int y1, int x2, int y2)
	{
		cu.moveCue(x1, y1, x2, y2);
		repaint();
	}
}