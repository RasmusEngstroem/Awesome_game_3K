package Mario_Game;

import mathias_test_igen.Box;
import mathias_test_igen.Mario_2;

import org.newdawn.slick.geom.Rectangle;

public abstract class Blocks extends GameEntities{
	
	public int rep_x;
	public int rep_y;
	
	public Blocks(float x_pos, float y_pos, int rep_x, int rep_y) {
		super(x_pos, y_pos);
		this.rep_x = rep_x;
		this.rep_y = rep_y;
		// TODO Auto-generated constructor stub
	}




}
