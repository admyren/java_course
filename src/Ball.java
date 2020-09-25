import java.awt.*;  // For Color and Graphics

public class Ball 
{
	private Vector pos; // Position vector
	private Vector vel; // Velocity vector
	private Color c; // Color
	private int r; // Radius
	private Box box;
	
	public Ball(Box box, Vector pos, Vector vel, Color c, int radius)
	{
		this.pos = pos;
		this.vel = vel;
		this.c = c;
		this.r = radius;
		this.box = box;
	}
	
	public Vector getPos()
	{
		return pos;
	}
	
	public Vector getVel()
	{
		return vel;
	}
	
	public void setPos(Vector v)
	{
		pos = v;
	}
	

	public void setVel(Vector v)
	{
		vel = v;
	}
	
	public int getRadius()
	{
		return r;
	}
	
	public void move()//Ball b)
	{
		// Friction vector 1/1000 the size of the velocity vector
		Vector frict = vel.scale(0.01);
		vel = vel.sub(frict);
		// Add the velocity vector to the position vector
		pos = pos.add(vel);
		
		// Check if wall collision
		//if((pos.getX() >= 1-((int)r/box.getWidth()) && vel.getX() > 0) || (pos.getX() <= 0 && vel.getX() < 0))
		//if(pos.getX() >= 1-((double)r)/box.getWidth() ||  vel.getX() <= 0+((double)r)/box.getWidth())
		if(pos.getX() >= 1 ||  pos.getX() <= 0)
		{
			this.setVel(this.getVel().flipSignX());
			if(vel.length() < 1/1000)
			{
				
			}
		}

		// Check if ceiling/floor collision
		//if((pos.getY() >= 1  && vel.getX() > 0) || (pos.getY() <= 0 && vel.getY() < 0))
		//if(pos.getY() >= 1-((double)r)/box.getHeight() || pos.getY() <= 0+((double)r)/box.getHeight())
		if(pos.getY() >= 1 || pos.getY() <= 0)
		{
			this.setVel(this.getVel().flipSignY());
			if(vel.length() < 1/1000)
			{
				
			}
		}
		
	}
	
public boolean collision(Ball b) {
		
		Vector temp1 = new Vector(this.pos.getX()*box.getWidth(), this.pos.getY()*box.getHeight());
		Vector temp2 = new Vector(b.pos.getX()*box.getWidth(), b.pos.getY()*box.getHeight());
		double distance = temp1.sub(temp2).length();			// Distance between the two balls
		double minDist = r + b.r;									// Min allowed distance for no collision
		
		if (distance <= minDist) {
			return true;
		} else {
			return false;
		}
	}
/*
public void collide(Ball b) {
	
	// Declare variables for readability
	Vector v1 = b.vel.sub(vel);							// b velocity vector - this velocity vector						
	Vector p1 = b.pos.sub(pos);							// p position vector - this position vector
	double numerator1 = v1.dot(p1);								// (bv - thisv)*(bp - thisp)
	double denominator1 = Math.pow(p1.length(), 2);				// |bp - thisp|^2
	
	Vector v2 = vel.sub(b.vel);							// this velocity vector - b veloc
	Vector p2 = pos.sub(b.pos);							// this position vector - b position vector
	double numerator2 = v2.dot(p2);								// (thisv - bv)*(thisp - bp)
	double denominator2 = Math.pow(p2.length(), 2);				// |thisp - bp|^2

	vel = vel.add(p1.scale(numerator1/denominator1));		// Equation for bounce collision, this against b
	b.vel = b.vel.add(p2.scale(numerator2/denominator2));	// Equation for bounce collision, b against this
	
}
*/

	public void collide(Ball b)
	{
		Vector temp1 = new Vector(this.pos.getX()*box.getWidth(), this.pos.getY()*box.getHeight());
		Vector temp2 = new Vector(b.pos.getX()*box.getWidth(), b.pos.getY()*box.getHeight());
		if(temp1.sub(temp2).length() <= r+b.r)
		{
			// Declare variables for readability
			Vector v1 = b.vel.sub(vel);							// b velocity vector - this velocity vector						
			Vector p1 = b.pos.sub(pos);							// p position vector - this position vector
			double numerator1 = v1.dot(p1);								// (bv - thisv)*(bp - thisp)
			double denominator1 = Math.pow(p1.length(), 2);				// |bp - thisp|^2
			
			Vector v2 = vel.sub(b.vel);							// this velocity vector - b veloc
			Vector p2 = pos.sub(b.pos);							// this position vector - b position vector
			double numerator2 = v2.dot(p2);								// (thisv - bv)*(thisp - bp)
			double denominator2 = Math.pow(p2.length(), 2);				// |thisp - bp|^2

			vel = vel.add(p1.scale(numerator1/denominator1));		// Equation for bounce collision, this against b
			b.vel = b.vel.add(p2.scale(numerator2/denominator2));	// Equation for bounce collision, b against this
		}
	}
	
	/*
	public void virtBallCollide(Ball b)
	{
		Vector temp1 = new Vector(this.pos.getX()*box.getWidth(), this.pos.getY()*box.getHeight());
		Vector temp2 = new Vector(b.pos.getX()*box.getWidth(), b.pos.getY()*box.getHeight());
		if(temp1.sub(temp2).length() <= r+b.r)
		{
		Vector v1 = b.vel.sub(vel);							// b velocity vector - this velocity vector						
		Vector p1 = b.pos.sub(pos);							// p position vector - this position vector
		double numerator1 = v1.dot(p1);								// (bv - thisv)*(bp - thisp)
		double denominator1 = Math.pow(p1.length(), 2);				// |bp - thisp|^2
		
		
		Vector v2 = vel.sub(b.vel);							// this velocity vector - b veloc
		Vector p2 = pos.sub(b.pos);							// this position vector - b position vector
		double numerator2 = v2.dot(p2);								// (thisv - bv)*(thisp - bp)
		double denominator2 = Math.pow(p2.length(), 2);				// |thisp - bp|^2
		
		vel = vel.add(p1.scale(numerator1/denominator1));		// Equation for bounce collision, this against b
		//b.vel = b.vel.add(p2.scale(numerator2/denominator2));	// Equation for bounce collision, b against this
		
		}

	}*/
	
	
	public void paint(java.awt.Graphics g) 
	{
		g.setColor(c);
		// Get the real coordinates of the ball
		int x = (int)pos.scale(box.getWidth()).getX();
		int y = (int)pos.scale(box.getHeight()).getY();
		g.fillOval(x-r, y-r, 2*r, 2*r);
	}
		
	

}
