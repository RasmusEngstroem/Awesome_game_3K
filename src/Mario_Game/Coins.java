package Mario_Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Coins extends Items {

	public Image textureBlock; //the image drawn on the box
	public Rectangle coinShape; //the shape of the block
	public Rectangle marioShape; //the shape of the player
	public boolean alive = true; //the state that determines if the coin has been hit
	public boolean placed = false; //is true when the coin is placed at the right coordinates
	float x_pos; //the x position of the Blocks
	float y_pos; //the y position of the Blocks
	public Player mario; //the player
	
	/**
	 * 
	 * @param x_pos the x position of the Blocks
	 * @param y_pos the Y position of the Blocks
	 * @param mario the player
	 * @throws SlickException an exception thrown we something goes wrong in the slick library
	 */
	public Coins(float x_pos, float y_pos, Player mario) throws SlickException {
		super(x_pos, y_pos);
		this.mario = mario;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		coinShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize);
		marioShape = mario.botBoxFB;
		textureBlock = new Image("coin.png");
	}
	
	/**
	 * used to update the position of the coin
	 * @param x the x position of the Blocks
	 * @param y the y position of the Blocks
	 */
	public void update(float x, float y){
		this.x_pos=x-(scaleSize/2);
		this.y_pos=y-(scaleSize/2);
		coinShape.setCenterX(x);
		coinShape.setCenterY(y);
		placed = true;
		if(alive){
			checkCollision();
		}
	}
	
	/**
	 * used to render the visuals of the coin
	 * @param g the slick graphics
	 */
	public void render(Graphics g){
		if(alive){ //renders if the coin hasn't been picked up
			textureBlock.draw(x_pos, y_pos);
		}
	}
	
	/**
	 * checks the collision with the player
	 */
	public void checkCollision(){
		if(coinShape.intersects(marioShape) && placed && alive){ //checks the collision with the player
			alive = false;
			Level1.points ++; //gives a point to the player
		}
	}

}
