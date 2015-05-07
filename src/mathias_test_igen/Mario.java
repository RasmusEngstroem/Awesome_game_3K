package mathias_test_igen;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Mario extends GameObject{
	
	public boolean hit_L;
	public boolean hit_T;
	public boolean hit_R;
	public boolean hit_B;

	
	public Rectangle boxShape;
	public Rectangle headHitbox;
	public Rectangle foodHitbox;

	public Mario(float x_pos, float y_pos, GameContainer container) {
		super(x_pos, y_pos, container);
		boxShape = new Rectangle(x_pos, y_pos, sizeScale, sizeScale);
		headHitbox = new Rectangle(x_pos, y_pos, sizeScale, 10);
		foodHitbox = new Rectangle(x_pos, y_pos, sizeScale, 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawMask(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.draw(boxShape);
		g.draw(headHitbox);
		g.draw(foodHitbox);
	}

	@Override
	public void updatePosition(float x, float y) {
		// TODO Auto-generated method stub
		boxShape.setCenterX(x);
		boxShape.setCenterY(y);
		headHitbox.setCenterX(x);
		headHitbox.setCenterY(y-(sizeScale/2)+5);
		foodHitbox.setCenterX(x);
		foodHitbox.setCenterY(y+(sizeScale/2)-5);
	}

}
