package Mario_Game;

public abstract class GameEntities extends GameObjects{
	
	public float x_pos;
	public float y_pos;

	public GameEntities(float x_pos, float y_pos) {
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}

}
