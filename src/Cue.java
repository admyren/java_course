// For Color and Graphics
import java.awt.Color;

public class Cue {
	private int xPressed;
    private int yPressed;
    private int xReleased;
	private int yReleased;
	private Color c;
	private Box box;
	
	public Cue(Box box, int x1, int y1, int x2, int y2)
	{
		this.xPressed = x1;
		this.yPressed = y1;
		this.xReleased = x2;
		this.yReleased = y2;
		this.box = box;
		c = new Color(120, 120, 120); //Black cue
	}
	
	public void moveCue(int x1, int y1, int x2, int y2)
	{
		xPressed = x1;
		yPressed = y1;
		xReleased = x2;
		yReleased = y2;
	}
	
	public void paint(java.awt.Graphics g) 
	{
		g.setColor(c);
		int x = xPressed - xReleased;
		int y = yPressed - yReleased;
		int xEnd = xPressed + x;
		int yEnd = yPressed + y;
		//g.drawLine(xPressed, yPressed, xReleased, yReleased);
		g.drawLine(xEnd, yEnd, xReleased, yReleased);
	}
}

