package Rasmus_Test;

import org.newdawn.slick.Image;



public abstract class blocks extends entity{

	public int rep_x;
	public int rep_y;
	
	public blocks(int x_pos, int y_pos, int x_size, int y_size, Image thisimage) {
		super(x_pos, y_pos, x_size, y_size, thisimage);
		//this.rep_x = rep_x;
		//this.rep_y = rep_y;	
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public abstract void reproduce ();
	
	
}
