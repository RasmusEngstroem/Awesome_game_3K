package Rasmus_Test;

import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public abstract class blocks extends entity{

	public static int x_pos;  // Jeg ved ikke hle thvorfor de skal være static. 
	public static int y_pos; 
	public int x_size; 
	public int y_size; 
	
	public static Circle boundingbox; // Forsøg på collision box - jannik skal implementere det han har lavet for mit virker ikke. 
	public static Image myImage;
	
	public blocks(int x_pos, int y_pos, int x_size, int y_size, Image texture, Circle boundingbox2) {
		super(x_pos, y_pos); //	super(x_pos, y_pos, x_size, y_size, texture);
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.x_size = x_size; 
		this.y_size = y_size; 
		this.myImage = texture;
		this.boundingbox = boundingbox2; 
		// TODO Auto-generated constructor stub
	}
	
	
	
	// Dette er bare en draw function der drawer en cube. 
	public static void draw(int pos_x, int pos_y, int size_x, int size_y) //public static void draw(int pos_x, int pos_y)
	{
		myImage.draw(pos_x, pos_y,size_x,size_y); 
		//boundingbox = new Circle(pos_x, pos_y,50);
		
	}




//	private static Circle Circle(int pos_x, int pos_y, int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
