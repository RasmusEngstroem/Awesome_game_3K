package Mario_Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class WinCube extends GameEntities  { // This class has a IS-A relationship with the superclass GameEntities

	public Image textureBlock; // Image assigned to the win block
	float x_pos; // Assigns an x position to the block
	float y_pos; // Assigns a y position to the block 
	public Player mario; // 
	public Rectangle boxShape; // 
	
	/**
	 * 
	 * @param x_pos the x position of the blocks
	 * @param y_pos the y position of the blocks
	 * @param mario the player
	 * @throws SlickException exception thrown when error with slick library occurs
	 */
	public WinCube(float x_pos, float y_pos, Player mario) throws SlickException {
		super(x_pos, y_pos); // Inherits from the super class 
		boxShape = new Rectangle(x_pos, y_pos, scaleSize*2, scaleSize*2); // Create a new rectangle. Scalesize is inherited from of gameobjects.
		this.mario = mario; // This mario is the same as the playable Mario. 
		textureBlock = new Image("goal.png"); // Import an image to be used on this block
		this.x_pos = x_pos; // This x position is equal to the superclass x position 
		this.y_pos = y_pos; // // This y position is equal to the superclass y position 
	}
	
	public void update(float x_pos, float y_pos){
		this.x_pos=x_pos-(scaleSize); 
		this.y_pos=y_pos-(scaleSize);
		boxShape.setCenterX(x_pos + 50 -(scaleSize/2));
		boxShape.setCenterY(y_pos + 70-(scaleSize/2));
		checkCollision(); // Call checkCollision method to determine if game is won. 
	}
	
	public void render(Graphics g){
		textureBlock.draw(x_pos, y_pos);	// Draw the winblock at x and y position
	}
	
	public void checkCollision(){ // Method that check collision between winblock and mario. 
		
		if(boxShape.intersects(mario.botBoxFB)){ // If collision box and mario collide gameWon will be true. 
			Level1.gameWon = true;
		}
	}
	
	
}
