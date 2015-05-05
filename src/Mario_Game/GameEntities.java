package Mario_Game;

import org.newdawn.slick.Image;

public abstract class GameEntities extends GameObjects{
	
	public int x_pos;
	public int y_pos;
	public Image texture;

	public GameEntities(String title, int x_pos, int y_pos, Image texture) {
		super(title);
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.texture = texture;
	}

}
