package mathias_test_igen;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Mario extends GameObject{
	
	public boolean hit_L = false;
	public boolean hit_T = false;
	public boolean hit_R = false;
	public boolean hit_B = false;

	
	public Rectangle boxShape;
	public Rectangle headHitbox;
	public Rectangle footHitbox;
	public Rectangle rHitbox;
	public Rectangle lHitbox;
	

	public Mario(float x_pos, float y_pos, GameContainer container) {
		super(x_pos, y_pos, container);
		boxShape = new Rectangle(x_pos, y_pos, sizeScale, sizeScale);
		headHitbox = new Rectangle(x_pos, y_pos, sizeScale, 10);
		footHitbox = new Rectangle(x_pos, y_pos, sizeScale, 10);
		rHitbox = new Rectangle(x_pos, y_pos, 10, sizeScale);
		lHitbox = new Rectangle(x_pos, y_pos, 10, sizeScale);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawMask(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.draw(boxShape);
		g.draw(headHitbox);
		g.draw(footHitbox);
		g.draw(lHitbox);
		g.draw(rHitbox);
	}

	@Override
	public void updatePosition(float x, float y) {
		
		
		
		// TODO Auto-generated method stub
		boxShape.setCenterX(x);
		boxShape.setCenterY(y);
		
		lHitbox.setCenterX(x-(sizeScale/2)+5);
		lHitbox.setCenterY(y);
		
		rHitbox.setCenterX(x+(sizeScale/2)-5);
		rHitbox.setCenterY(y);
		
		headHitbox.setCenterX(x);
		headHitbox.setCenterY(y-(sizeScale/2)+5);
		
		footHitbox.setCenterX(x);
		footHitbox.setCenterY(y+(sizeScale/2)-5);
		
		if(hit_L){
			System.out.println("hit l");
		
			hit_L = false;
		}
		if(hit_T){
			System.out.println("hit t");
		
			hit_T = false;
		}
		if(hit_B){
			System.out.println("hit B");
		
			hit_B = false;
		}
		if(hit_R){
			System.out.println("hit R");
		
			hit_R = false;
		}
	}

}
