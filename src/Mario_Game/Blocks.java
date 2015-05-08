package Mario_Game;

public abstract class Blocks extends GameEntities{
	
	public int rep_x; // used to place objects next to this
	public int rep_y; // used to place objects on top of to this
	
	
	/**
	 * 
	 * @param x_pos the x position of the Blocks
	 * @param y_pos the Y position of the Blocks
	 * @param rep_x the amount of blocks that is placed beside the original Blocks
	 * @param rep_y the amount of blocks that is placed on top of the original Blocks
	 */
	public Blocks(float x_pos, float y_pos, int rep_x, int rep_y) {
		super(x_pos, y_pos); //Inherits from the super class
		this.rep_x = rep_x; //sets the class variable to be equal to the input variable
		this.rep_y = rep_y; //sets the class variable to be equal to the input variable
	}

}
