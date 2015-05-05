package Rasmus_Test;

import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class blocks extends entity{

	public static int x_pos; 
	public static int y_pos; 
	public int x_size; 
	public int y_size; 
	
	public static Rectangle boundingbox; 
	public static Image myImage;
	
	public blocks(int x_pos, int y_pos, int x_size, int y_size, Image texture) {
		super(x_pos, y_pos); //	super(x_pos, y_pos, x_size, y_size, texture);
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.x_size = x_size; 
		this.y_size = y_size; 
		this.myImage = texture;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public static void draw(int pos_x, int pos_y) //public static void draw(int pos_x, int pos_y)
	{
		myImage.draw(pos_x, pos_y,50,50); 
		boundingbox = new Rectangle(pos_x, pos_y,50,50);
	}
	
}
