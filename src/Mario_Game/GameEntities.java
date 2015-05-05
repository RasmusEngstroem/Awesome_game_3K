package Mario_Game;

import org.newdawn.slick.Image;

public abstract class GameEntities extends GameObjects{
	
	public float x_pos;
	public float y_pos;
	public Image texture;

	public GameEntities(String title, float x_pos, float y_pos, Image texture) {
		super(title);
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.texture = texture;
	}

}
