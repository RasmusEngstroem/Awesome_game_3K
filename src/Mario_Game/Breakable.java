package Mario_Game;

import org.newdawn.slick.Image;

public class Breakable extends Blocks {
	
	public GameObjects transformTo;
	public GameObjects containThis;
	
	/**
	 * 
	 * @param title
	 * @param x_pos the x position of the block
	 * @param y_pos the x position of the block
	 * @param texture the texture that should be drawn on the block
	 * @param rep_x the number of similar blocks that should be spawned next to the block
	 * @param rep_y the number of similar blocks that should be spawned on top of the block
	 * @param transformTo the block which this should transform to when hit by the player
	 * @param containThis the item that should be inside the block
	 */
	public Breakable(String title, float x_pos, float y_pos, Image texture,
			int rep_x, int rep_y, GameObjects transformTo, GameObjects containThis) {
		super(title, x_pos, y_pos, texture, rep_x, rep_y);
		this.transformTo = transformTo;
		this.containThis = containThis;
		whenBreak();
	}
	
	public void whenBreak (){
		
	}

}
