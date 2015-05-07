package Mario_Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class EmptyItemBlock extends Unbreakable {

	public Rectangle boxShape;
	public Rectangle marioHead;
	public boolean alive = true;
	public boolean placed = false;
	public String  t = "kasse";
	public int rep_x;
	public int rep_y;
	
	public Player mario;
	public EmptyItemBlock[] kasse;
	
	public EmptyItemBlock(float x_pos, float y_pos, int rep_x, int rep_y, Player mario) {
		super(x_pos, y_pos, rep_x, rep_y);
		boxShape = new Rectangle(x_pos, y_pos, scaleSize, scaleSize);
		this.mario = mario;
		marioHead = mario.botBoxT;
		this.rep_x = rep_x;
		this.rep_y = rep_y;
		placeClones();
		// TODO Auto-generated constructor stub
	}
	
	public void update(float x, float y){
		boxShape.setCenterX(x);
		boxShape.setCenterY(y);
		placed = true;
		if( rep_x != 0){
			for(int i=0; i< rep_x; i++){
				kasse[i].update(x+scaleSize*i, y);
				kasse[i].checkCollision();
			}
		}
	}
	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(alive){
		g.setColor(Color.white);
		g.draw(boxShape);
		}
		if( rep_x != 0){
			for(int i=0; i< rep_x; i++){
				kasse[i].render(g);
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
	}
	
	public void placeClones(){
		if( rep_x != 0){
			EmptyItemBlock[] kasse = new EmptyItemBlock[rep_x];
			for(int i=0; i< rep_x; i++){
				kasse[i]= new EmptyItemBlock(x_pos, x_pos, 0, 0, mario);
			} 
			this.kasse = kasse;
		}
	}

}