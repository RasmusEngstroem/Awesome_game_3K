package Mario_Game;

public abstract class Breakable extends Blocks {
	
	/**
	 * 
	 * @param x_pos the x position of the Blocks
	 * @param y_pos the Y position of the Blocks
	 * @param rep_x the amount of blocks that is placed beside the original Blocks
	 * @param rep_y the amount of blocks that is placed on top of the original Blocks
	 */
	public Breakable(float x_pos, float y_pos, int rep_x, int rep_y) {
		super(x_pos, y_pos, rep_x, rep_y); //Inherits from the super class
	}

}
