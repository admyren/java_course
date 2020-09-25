import java.awt.*;

public class Holes 
{
	
	private int r;
	private Color c;
	private Vector position;
	private Box box;
	
	Holes(Box box, Vector position)
	{
		this.position = position;
		this.c = Color.black;
		this.r = 40;
		this.box = box;
	}
	
	public boolean collision(Ball b)
	{
		Vector temp1 = new Vector(this.position.getX()*box.getWidth(), this.position.getY()*box.getHeight());
		Vector temp2 = new Vector(b.getPos().getX()*box.getWidth(), b.getPos().getY()*box.getHeight());
		if(temp1.sub(temp2).length() <= ((r/2)+b.getRadius()))
		{
			return true;
		}
		return false;
	}
	
	public void paintH(Graphics g) 
	{
		g.setColor(c);
		// Get the real coordinates of the ball
		int x = (int)position.scale(box.getWidth()).getX();
		int y = (int)position.scale(box.getHeight()).getY();
		g.fillOval(x-r, y-r, 2*r, 2*r);
		
	}

}
