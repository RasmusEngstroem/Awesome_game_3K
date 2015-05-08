package Mario_Game;

public abstract class Blocks extends GameEntities{
	
	public int rep_x;
	public int rep_y;
	
	
	/**
	 * 
	 * @param x_pos the x position 
	 * @param y_pos
	 * @param rep_x
	 * @param rep_y
	 */
	public Blocks(float x_pos, float y_pos, int rep_x, int rep_y) {
		super(x_pos, y_pos);
		this.rep_x = rep_x;
		this.rep_y = rep_y;
	}

}
