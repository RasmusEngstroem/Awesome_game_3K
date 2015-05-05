package Rasmus_Test;

import org.newdawn.slick.Image;

public abstract class unbreak extends blocks {
	public String lol;
	
	public int rep_x;
	public int rep_y;
	
	public Image unbreakImage = null; 
	
	
	public unbreak(int x_pos, int y_pos,int size, int rep_x, int rep_y) {
		super(x_pos, y_pos, size, rep_x, rep_y);
		// TODO Auto-generated constructor stub
	}
	
	public void taketheimage(Image thatimage)
	{
		unbreakImage = thatimage;
	}
	public void paint()
	{
		unbreakImage.draw(100,100,100,100);
	}



}
