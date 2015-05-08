package Mario_Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class ItemBlock extends Breakable { // This class has a IS-A relationship Brekable

	
	// Declaration of variables used in this class. 
	
	public Image textureBlock;
	public EmptyItemBlock empty;
	public Coins coin;
	public Rectangle boxShape;
	public Rectangle breakHitBox;
	public Rectangle marioHead;
	public boolean alive = true;
	public boolean placed = false;
	public int rep_x;
	public int rep_y;
	float x_pos;
	float y_pos;
	public Player mario;
	public ItemBlock[] kasse;
	public Enemies[] enemy;
	
	public ItemBlock(float x_pos, float y_pos, int rep_x, int rep_y, Player mario, Enemies[] enemy) throws SlickException { // Constructor for ItemBlock.class
		super(x_pos, y_pos, rep_x, rep_y); //Inheritance from parent. 
		boxShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize); // Spawn rectangle for colition detection for these blocks. 
		breakHitBox = new Rectangle(x_pos, y_pos, scaleSize-4, 10); // Hitbox used to destroy blocks that mario can jump head into. 
		this.mario = mario; // This mario will be the mario referenced in the call arguments. 
		marioHead = mario.botBoxT; // Same again as above. This hitbox will be same as the one references in the call. 
		this.rep_x = rep_x; // Same with the x 
		this.rep_y = rep_y; // So with the y 
		textureBlock = new Image("blokItem.png"); // Import Image texture for these blocks. 
		this.enemy = enemy; // Enemy also
		placeClones(); // Call of method to actually draw the blocks. 
	}
	
	public void update(float x, float y){ // This method is used to update the blocks. 
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
		if(!alive){ 
			empty.update(x, y);
			coin.update(x+scaleSize/4, y-scaleSize);
			empty.checkCollision();
		}
	}
	
	public void render(Graphics g) throws SlickException { // This is used to render the content of this class. 
		if(alive){	// if the block is undestroyed, draw run code below
		textureBlock.draw(x_pos, y_pos); // Draw at positions 
		breakBox(); // Call method for mario to destroy blocks that he can break with his head. 
		}
		if(!alive){
			empty.render(g); // If the block has been hit by mario, render the empty version plus a coin over the block. 
			coin.render(g);
		}
		if( rep_x != 0){ // If repetition is not 0 render all the blocks. 
			for(int i=0; i< rep_x; i++){
				kasse[i].render(g);
			}
		}
	}
	
	public void breakBox() throws SlickException{
		if(breakHitBox.intersects(marioHead) && placed && alive){   // Check collision between marios head, and the block. 
																	//If they collide and the box is places and the box is not destroyed
			alive=false; // If above is true, the block has been hit and is not alive. 
			System.out.println("hit");
			mario.collisionD = true;
			empty = new EmptyItemBlock(x_pos+(scaleSize/2), y_pos+(scaleSize/2), 0, 0, mario, enemy); //Instantiate the empty block. 
			coin = new Coins(x_pos+(scaleSize/2)-scaleSize, x_pos+(scaleSize/2)-scaleSize, mario); // Instantiate the coin that spawns on top. 
			for(int i = 0; i < enemy.length; i++){ // Collision check between the block and enemies. If they stnd on top when mario hits underneath, they die. 
				if(boxShape.intersects(enemy[i].botBoxGT)){
					enemy[i].dead();
				}
			}
		}
	}
	
	public void checkCollision(){ // COllision check with all of marios collision detection parts. 
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
		for(int i = 0; i < enemy.length; i++){ // Bots( enemies ) constist of several collision boxes also. 
												//The following determines what "body part" collides with the block. 
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
		if( rep_x != 0){
			ItemBlock[] kasse = new ItemBlock[rep_x]; // Instantiate the array which contains the blocks.
			for(int i=0; i< rep_x; i++){ 
				kasse[i]= new ItemBlock(x_pos, x_pos, 0, 0, mario, enemy);	// instantiate new blocks in the array. 		
			} 
			this.kasse = kasse;
		}
	}

}
