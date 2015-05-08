package Mario_Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class WinCube extends GameEntities  {

	public Image textureBlock;
	float x_pos;
	float y_pos;
	public Player mario;
	public Rectangle boxShape;

	
	public WinCube(float x_pos, float y_pos, Player mario) throws SlickException {
		super(x_pos, y_pos);
		boxShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize);
		this.mario = mario;
		textureBlock = new Image("Assets/goal.png");
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float x_pos, float y_pos){
		this.x_pos=x_pos-(scaleSize/2);
		this.y_pos=y_pos-(scaleSize/2);
		boxShape.setCenterX(x_pos-(scaleSize/2));
		boxShape.setCenterY(y_pos-(scaleSize/2));
		
		checkCollision();
	}
	
	public void render(){
		
		textureBlock.draw(x_pos, y_pos);
		
	}
	
	public void checkCollision(){
		
		if(boxShape.intersects(mario.botBoxFB)){
			Level1.gameWon = true;
		}
		
	}
}
