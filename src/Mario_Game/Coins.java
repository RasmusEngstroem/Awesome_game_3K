package Mario_Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Coins extends Items {

	public Image textureBlock;
	public Rectangle coinShape;
	public Rectangle marioShape;
	public boolean alive = true;
	public boolean placed = false;
	float x_pos;
	float y_pos;
	public Player mario;
	
	public Coins(float x_pos, float y_pos, Player mario) throws SlickException {
		super(x_pos, y_pos);
		this.mario = mario;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		coinShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize);

		marioShape = mario.botBoxFB;
		textureBlock = new Image("Assets/coin.png");
		// TODO Auto-generated constructor stub
	}
	
	public void update(float x, float y){
		
		this.x_pos=x-(scaleSize/2);
		this.y_pos=y-(scaleSize/2);
		
		coinShape.setCenterX(x);
		coinShape.setCenterY(y);
		
		if(alive){
			checkCollision();
		}
		
	}
	
	public void render(Graphics g){
		
		if(alive){
			g.setColor(Color.white);
			g.draw(marioShape);
			textureBlock.draw(x_pos, y_pos);
		}
		
	}
	
	public void checkCollision(){
		
		if(coinShape.intersects(marioShape) && placed && alive){
			alive = false;
			Level1.points ++;
		}
		
	}



}
