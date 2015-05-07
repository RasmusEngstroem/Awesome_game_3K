package mathias_test_igen;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Box extends GameObject {

	public Rectangle boxShape;
	public Rectangle breakHitBox;
	public Rectangle marioHead;
	public int clone_x;
	public boolean alive = true;
	public boolean placed = false;
	public String  t = "kasse";
	
	public Mario mario;
	public Box[] kasse;
	

	
	public Box(float x_pos, float y_pos, GameContainer container,
			Mario mario, int clone_x) {
		super(x_pos, y_pos, container);
		boxShape = new Rectangle(x_pos, y_pos, sizeScale, sizeScale);
		breakHitBox = new Rectangle(x_pos, y_pos, sizeScale-4, 10);
		this.mario = mario;
		marioHead = mario.headHitbox;
		this.clone_x = clone_x;
		placeClones();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void updatePosition(float x, float y){
		boxShape.setCenterX(x);
		boxShape.setCenterY(y);
		breakHitBox.setCenterX(x);
		breakHitBox.setCenterY(y+(sizeScale/2)-5);
		placed = true;
		if( clone_x != 0){
			for(int i=0; i< clone_x; i++){
				kasse[i].updatePosition(x+sizeScale*i, y);
			}
		}
	}
	
	public void drawMask(Graphics g) {
		// TODO Auto-generated method stub
		if(alive){
		g.setColor(Color.white);
		g.draw(boxShape);
		g.draw(breakHitBox);
		breakBox();
		}
		if( clone_x != 0){
			for(int i=0; i< clone_x; i++){
				kasse[i].drawMask(g);
			}
		}
	}
	
	public void breakBox(){
		if(breakHitBox.intersects(marioHead) && placed && alive){
			alive=false;
			System.out.println("hit");
		}
	}
	
	public void placeClones(){
		
		if( clone_x != 0){
			Box[] kasse = new Box[clone_x];
			for(int i=0; i< clone_x; i++){
				kasse[i]= new Box(0,0, container, mario ,0);
			
			} 
			this.kasse = kasse;
		}
		
		
		
	}
	

}
