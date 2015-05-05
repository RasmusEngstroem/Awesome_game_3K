package Mario_Game;

import org.newdawn.slick.Image;

public class Breakable extends Blocks {
	
	public GameObjects transformTo;
	public GameObjects containThis;
	
	public Breakable(String title, int x_pos, int y_pos, Image texture,
			int rep_x, int rep_y, GameObjects transformTo, GameObjects containThis) {
		super(title, x_pos, y_pos, texture, rep_x, rep_y);
		this.transformTo = transformTo;
		this.containThis = containThis;
	}

}
