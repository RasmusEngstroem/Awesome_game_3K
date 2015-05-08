package Mario_Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SolidBlock extends Unbreakable {

	public Image textureBlock;
	
	public Rectangle boxShape; // Declare a rectangle
	public Rectangle marioHead; // Declare 
	public boolean alive = true; // A boolean used to check if the player is still alive 
	public boolean placed = false;
	public String  t = "kasse";
	public int rep_x;
	public int rep_y;
	public Player mario;
	public SolidBlock[] kasse;
	public Enemies[] enemy;
	float x_pos;
	float y_pos;
	
	/**
	 * 
	 * @param x_pos the x position of the block 
	 * @param y_pos the y position of the block 
	 * @param rep_x the amount of block to be placed to the right of the original block 
	 * @param rep_y the amount of block to be placed underneath of the original block 
	 * @param mario the player
	 * @param enemy the enemy
	 * @throws SlickException exception thrown when error with slick library occurs
	 */
	
	public SolidBlock(float x_pos, float y_pos, int rep_x, int rep_y, Player mario, Enemies[] enemy) throws SlickException { 
		super(x_pos, y_pos, rep_x, rep_y); // Inherits from the superclass
		boxShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize); // The collision box used to detect collision between this block and other gamobjects. 
		this.mario = mario; // this mario we use here will be the same as the one parsed as argument when the class is declared. 
		marioHead = mario.botBoxT; // This version of the mario collision detection will be the same as the one parsed when the class is declared. 
		this.rep_x = rep_x; // The x position of the box will be the same as the one parsed. 
		this.rep_y = rep_y; //-||-
		textureBlock = new Image("blokSolid.png"); // Import Image to use for texture to this block.
		this.enemy = enemy; // Enemies expressed in this class will be the same as the enemies from the class where this class is called. 
		placeClones(); // Calls the placeClones(); method. 
	}
	
	public void update(float x, float y){ // Update to be used in Level1 
		this.x_pos=x-(scaleSize/2); // 
		this.y_pos=y-(scaleSize/2);
		boxShape.setCenterX(x);
		boxShape.setCenterY(y);
		placed = true;
		checkCollision(); // Call of collision detection method 
		if( rep_x != 0){ // if x_rep is not 0 run the for-loop
			for(int i=0; i< rep_x; i++){ // This for loop updates the position for all the created blocks in the array. 
				kasse[i].update(x+scaleSize*i+scaleSize, y);
			}
		}
	}
	
	public void render(Graphics g) {
		if(alive){ // As long as the player is alive, draw the blocks. 
			textureBlock.draw(x_pos, y_pos);
		}
		if( rep_x != 0){ // If rep_x is not zero, render all the blocks in the array. 
			for(int i=0; i< rep_x; i++){
				kasse[i].render(g);
			}
		}
	}
	

	
	public void checkCollision(){ 
		// The collision box of mario consist of many different separate collision boxes. 
		// Below is a collection of if statements to check if the different parts of that collision box collides with the Solidblocks
		if(boxShape.intersects(mario.botBoxT) && placed && alive){
			mario.collisionU = true;
		}
		if(boxShape.intersects(mario.botBoxB) && placed && alive){
			mario.collisionD = true;
		}
		if(boxShape.intersects(mario.botBoxL) && placed && alive){
			mario.collisionL = true;
		}
		
		if(boxShape.intersects(mario.botBoxR) && placed && alive){
			mario.collisionR = true;
		}
		if(boxShape.intersects(mario.botBoxGT) && placed && alive){
			mario.collisionGT = true;
		}
		for(int i = 0; i < enemy.length; i++){
			
			// And this part of the collision detection is to check for collision with enemies. 
			
			if(boxShape.intersects(enemy[i].botBoxB) && placed && alive){
				enemy[i].collisionB = true;
			}

			if(boxShape.intersects(enemy[i].botBoxTL) && placed && alive){
				enemy[i].collisionTL = true;
			}
			if(boxShape.intersects(enemy[i].botBoxTR) && placed && alive){
				enemy[i].collisionTR = true;
			}
			
			if(boxShape.intersects(enemy[i].botBoxL) && placed && alive){
				enemy[i].collisionL = true;
			}
			if(boxShape.intersects(enemy[i].botBoxR) && placed && alive){
				enemy[i].collisionR = true;
			}
			if(boxShape.intersects(enemy[i].botBoxGT) && placed && alive){
				enemy[i].collisionGT = true;
			}
	
		}
	}
	
	public void placeClones() throws SlickException{ 
		if( rep_x != 0){ // If rep_x is not 0 
			SolidBlock[] kasse = new SolidBlock[rep_x]; // Create new array 
			for(int i=0; i< rep_x; i++){ // Initialize the blocks in the array. 
				kasse[i]= new SolidBlock(x_pos, x_pos, 0, 0, mario, enemy);
			} 
			this.kasse = kasse;
		}
	}

}
