package Mario_Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class ItemBlock extends Breakable {

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
	
	public ItemBlock(float x_pos, float y_pos, int rep_x, int rep_y, Player mario, Enemies[] enemy) throws SlickException {
		super(x_pos, y_pos, rep_x, rep_y);
		boxShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize);
		breakHitBox = new Rectangle(x_pos, y_pos, scaleSize-4, 10);
		this.mario = mario;
		marioHead = mario.botBoxT;
		this.rep_x = rep_x;
		this.rep_y = rep_y;
		textureBlock = new Image("Assets/blokItem.png");
		this.enemy = enemy;
		placeClones();
	}
	
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
		if(!alive){
			empty.update(x, y);
			coin.update(x+scaleSize/4, y-scaleSize);
			empty.checkCollision();
		}
	}
	
	public void render(Graphics g) throws SlickException {
		if(alive){
		textureBlock.draw(x_pos, y_pos);
		breakBox();
		}
		if(!alive){
			empty.render(g);
			coin.render(g);
		}
		if( rep_x != 0){
			for(int i=0; i< rep_x; i++){
				kasse[i].render(g);
			}
		}
	}
	
	public void breakBox() throws SlickException{
		if(breakHitBox.intersects(marioHead) && placed && alive){
			alive=false;
			System.out.println("hit");
			mario.collisionD = true;
			empty = new EmptyItemBlock(x_pos+(scaleSize/2), y_pos+(scaleSize/2), 0, 0, mario, enemy);
			coin = new Coins(x_pos+(scaleSize/2)-scaleSize, x_pos+(scaleSize/2)-scaleSize, mario);
			for(int i = 0; i < enemy.length; i++){
				if(boxShape.intersects(enemy[i].botBoxGT)){
					enemy[i].dead();
				}
			}
		}
	}
	
	public void checkCollision(){
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
			ItemBlock[] kasse = new ItemBlock[rep_x];
			for(int i=0; i< rep_x; i++){
				kasse[i]= new ItemBlock(x_pos, x_pos, 0, 0, mario, enemy);			
			} 
			this.kasse = kasse;
		}
	}

}
