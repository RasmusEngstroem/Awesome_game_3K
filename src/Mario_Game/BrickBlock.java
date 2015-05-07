package Mario_Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class BrickBlock extends Breakable {
	
	public Image textureBlock;
	
	public Rectangle boxShape;
	public Rectangle breakHitBox;
	public Rectangle marioHead;
	public boolean alive = true;
	public boolean placed = false;
	public String  t = "kasse";
	public int rep_x;
	public int rep_y;
	float x_pos;
	float y_pos;
	
	public Player mario;
	public BrickBlock[] kasse;
	
	public BrickBlock(float x_pos, float y_pos, int rep_x, int rep_y, Player mario) throws SlickException {
		super(x_pos, y_pos, rep_x, rep_y);
		boxShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize);
		breakHitBox = new Rectangle(x_pos, y_pos, scaleSize-4, 10);
		this.mario = mario;
		marioHead = mario.botBoxT;
		this.rep_x = rep_x;
		this.rep_y = rep_y;
		textureBlock = new Image("Assets/blokBreakable.png");

		placeClones();
		// TODO Auto-generated constructor stub
	}
	
	public void update(float x, float y){
		
		this.x_pos=x-(scaleSize/2);
		this.y_pos=y-(scaleSize/2);
		
		boxShape.setCenterX(x);
		boxShape.setCenterY(y);
		breakHitBox.setCenterX(x);
		breakHitBox.setCenterY(y+(scaleSize/2)-5);
		placed = true;
		if( rep_x != 0){
			for(int i=0; i< rep_x; i++){
				kasse[i].update(x+scaleSize*i+scaleSize, y);
				kasse[i].checkCollision();
			}
		}
	}
	//aijhdjk
	
	public void render(Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(alive){
		g.setColor(Color.white);
		g.draw(boxShape);
		g.draw(breakHitBox);
		textureBlock.draw(x_pos, y_pos);
		breakBox();
		}
		if( rep_x != 0){
			for(int i=0; i< rep_x; i++){
				kasse[i].render(g);
			}
		}
	}
	
	public void breakBox(){
		if(breakHitBox.intersects(marioHead) && placed && alive){
			alive=false;
			System.out.println("hit");
			mario.collisionD = true;
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
		

	}
	
	public void placeClones() throws SlickException{
		
		
		if( rep_x != 0){
			BrickBlock[] kasse = new BrickBlock[rep_x];
			for(int i=0; i< rep_x; i++){
				kasse[i]= new BrickBlock(x_pos, x_pos, 0, 0, mario);
			
			} 
			this.kasse = kasse;
		}
		
		
		
	}
	
	
	
	

}
