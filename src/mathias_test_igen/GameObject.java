package mathias_test_igen;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class GameObject {
	
	
	public float x_pos;
	public float y_pos;
	public int sizeScale = 40;
	public GameContainer container;
	
	public GameObject(float x_pos, float y_pos, GameContainer container){
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.container = container;;
	}
	
	public abstract void drawMask(Graphics g);
	
	public abstract void updatePosition(float x, float y);
	
}
