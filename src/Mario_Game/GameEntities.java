package Mario_Game;

public abstract class GameEntities extends GameObjects{
	
	public float x_pos; //the x position of the Blocks
	public float y_pos; //the y position of the Blocks

	/**
	 * 
	 * @param x_pos the x position of the Blocks
	 * @param y_pos the Y position of the Blocks
	 */
	public GameEntities(float x_pos, float y_pos) {
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}

}
