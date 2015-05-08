package Mario_Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class BrickBlock extends Breakable {
	
	public Image textureBlock; //the image drawn on the box
	public Rectangle boxShape; //the shape of the block
	public Rectangle breakHitBox; //the shape of the hit point of the block
	public Rectangle marioHead; //the shape of the head of the player
	public boolean alive = true; //the state that determines if the block has been hit
	public boolean placed = false; //is true when the block is placed at the right coordinates
	public int rep_x; // used to place objects next to this
	public int rep_y; // used to place objects on top of to this
	float x_pos;  //the x position of the Blocks
	float y_pos; //the Y position of the Blocks
	public Player mario; //the player
	public Enemies[] enemy; //an array of all the enemies
	public BrickBlock[] kasse; //an array of the blocks placed next to this
	
	/**
	 * 
	 * @param x_pos the x position of the Blocks
	 * @param y_pos the Y position of the Blocks
	 * @param rep_x the amount of blocks that is placed beside the original Blocks
	 * @param rep_y the amount of blocks that is placed on top of the original Blocks
	 * @param mario the player
	 * @param enemy  an array of all enemies
	 * @throws SlickException an exception thrown we something goes wrong in the slick library
	 */
	public BrickBlock(float x_pos, float y_pos, int rep_x, int rep_y, Player mario, Enemies[] enemy) throws SlickException {
		super(x_pos, y_pos, rep_x, rep_y); //Inherits from the super class
		boxShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize); //the shape of the object
		breakHitBox = new Rectangle(x_pos, y_pos, scaleSize-4, 10); //the shape of the break point of the cube
		this.mario = mario; //the player
		marioHead = mario.botBoxT; //the players head
		this.rep_x = rep_x; //used to place objects next to this
		this.rep_y = rep_y; // used to place objects on top of this
		textureBlock = new Image("blokBreakable.png"); //the image drawn on the box
		this.enemy = enemy; //an array of all the enemies
		placeClones();//place the blocks that should be placed next to this from the rep_x
	}
	
	/**
	 * used to update the position of the block
	 * @param x the x position of the Blocks
	 * @param y the y position of the Blocks
	 */
	public void update(float x, float y){
		this.x_pos=x-(scaleSize/2);
		this.y_pos=y-(scaleSize/2);
		boxShape.setCenterX(x);
		boxShape.setCenterY(y);
		breakHitBox.setCenterX(x);
		breakHitBox.setCenterY(y+(scaleSize/2)-5);
		placed = true;
		checkCollision();
		if( rep_x != 0){
			for(int i=0; i< rep_x; i++){
				kasse[i].update(x+scaleSize*i+scaleSize, y);
			}
		}
	}
	
	/**
	 * used to render the visuals of the Block
	 * @param g the slick graphics
	 * @throws SlickException an exception thrown we something goes wrong in the slick library
	 */
	public void render(Graphics g) throws SlickException {
		if(alive){ //renders the block if it hasn't been destroyed
		textureBlock.draw(x_pos, y_pos);
		breakBox();
		}
		if( rep_x != 0){ //draws the blocks next to this if there is any
			for(int i=0; i< rep_x; i++){
				kasse[i].render(g);
			}
		}
	}
	
	/** 
	 * used to detect if the block should break
	 */
	public void breakBox(){
		if(breakHitBox.intersects(marioHead) && placed && alive){ //checks if the player hits the block
			alive=false;
			mario.collisionD = true;
			for(int i = 0; i < enemy.length; i++){ //checks if there is an enemy on top
				if(boxShape.intersects(enemy[i].botBoxGT)){
					enemy[i].dead();
				}
			}
		}
	}
	
	/**
	 * checks the collision with the player and the enemies
	 */
	public void checkCollision(){ //checks the collision with the player
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
		for(int i = 0; i < enemy.length; i++){ //checks the collision with all enemies
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
	
	/**
	 * used to place the blocks next to this
	 * @throws SlickException an exception thrown we something goes wrong in the slick library
	 */
	public void placeClones() throws SlickException{
		if( rep_x != 0){ //place blocks next to this if there is any
			BrickBlock[] kasse = new BrickBlock[rep_x];
			for(int i=0; i< rep_x; i++){
				kasse[i]= new BrickBlock(x_pos, x_pos, 0, 0, mario, enemy);
			} 
			this.kasse = kasse;
		}
	}

}
