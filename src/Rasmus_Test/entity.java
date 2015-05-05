package Rasmus_Test;

import org.newdawn.slick.Image;

public abstract class entity {

	public int x_pos;
	public int y_pos;
	//public static int x_size; 
	//public static int y_size; 
	

	public entity(int x_pos, int y_pos) //public entity(int x_pos, int y_pos, int x_size, int y_size, Image texture)
	{
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
	
}
//public entity(int x_pos, int y_pos, int size){
//	this.x_pos = x_pos;
//	this.y_pos = y_pos;		
//}
//
//public abstract class entity {
//
//	public int x_pos;
//	public int y_pos;
//	public int x_size; 
//	public int y_size; 
//	
//
//	public entity(int x_pos, int y_pos, int x_size, y_size, Image image)
//	{
//		this.x_pos = x_pos;
//		this.y_pos = y_pos;		
//	}
//	
//}