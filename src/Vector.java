
public class Vector {
	private double x;
	private double y;

	
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public Vector add(Vector v)
	{
		double x = this.x + v.x;
		double y = this.y + v.y;
		Vector ve = new Vector(x, y);
		return ve;
	}
	public double angle()
	{
		return Math.atan2(this.y, this.x); // Origo is in the left upper corner
	}
	
	public double distance(Vector v) 
	{
		double dx = this.x - v.x;
		double dy = this.y - v.y;
		
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public double dot(Vector v) 
	{
		return this.x*v.x + this.y*v.y;
	}
	
	public Vector flipSignX() 
	{
		Vector v = new Vector(-this.x, this.y);
		return v;
	}
	
	public Vector flipSignY() 
	{
		Vector v = new Vector(this.x, -this.y);
		return v;	
	}
	
	public double getX() 
	{
		return this.x;
	}
	
	public double getY() 
	{
		return this.y;
	}
	
	public double length() 
	{
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	public static Vector polar(double length, double angle) 
	{
		double x = length*Math.cos(angle);
		double y = length*Math.sin(angle);
		Vector v = new Vector(x, y);
		return v;
	}
	
	public static Vector randomVector(double len) 
	{
		double x = Math.random();
		double y = Math.sqrt(len*len - x*x);
		Vector v = new Vector(x, y);
		return v;
	}
	
	public Vector scale(double d) 
	{
		double x = d*this.x;
		double y = d*this.y;
		Vector v = new Vector(x, y);
		return v;
	}
	
	public Vector sub(Vector v)
	{
		double x = this.x - v.x;
		double y = this.y - v.y;
		Vector vc = new Vector(x, y);
		return vc;
	}
	
	public String toString()
	{
		String str = "(" + this.x + ", " + this.y + ")";
		return str;
	}
	
	public static void main(String[] args)
	{
		Vector a = new Vector(3,4);
		Vector b = new Vector(2,7);
		System.out.println(a.add(b).toString());
		System.out.println(a.angle());
		System.out.println(b.angle());
	}
}

