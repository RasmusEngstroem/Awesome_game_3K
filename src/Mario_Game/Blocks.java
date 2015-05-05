package Mario_Game;

import org.newdawn.slick.Image;

public abstract class Blocks extends GameEntities{
	
	public int rep_x;
	public int rep_y;

	public Blocks(String title, int x_pos, int y_pos, Image texture, int rep_x, int rep_y) {
		super(title, x_pos, y_pos, texture);
		this.rep_x = rep_x;
		this.rep_y = rep_y;
	}

}
