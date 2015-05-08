package Mario_Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class WinCube extends GameEntities  {

	public Image textureBlock;
	float x_pos;
	float y_pos;
	public Player mario;
	
	public WinCube(float x_pos, float y_pos) throws SlickException {
		super(x_pos, y_pos);
		textureBlock = new Image("Assets/goal.png");
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		
		checkCollision();
	}
	
	public void render(){}
	
	public void checkCollision(){}
}
