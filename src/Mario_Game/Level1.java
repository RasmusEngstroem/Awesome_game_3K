package Mario_Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import test.level;
import test.setupClass;

public class Level1 extends level{
	
	public Level1(String title) {
		super(title);
		
	}

	private Rectangle rect;
	
	public void sendToInit(GameContainer container) throws SlickException{
		rect = new Rectangle(200+ x_posL, 300 + y_posL, 50, 50);
	}
	
	public void sendToUpdate(GameContainer container, int delta) throws SlickException {
		
		rect.setLocation(200 + x_posL, 200 + y_posL);
		
		x_posL += 0.2f;
		
	}
	
	public void sendToRender(GameContainer container, Graphics g) throws SlickException {
		
		g.setColor(Color.lightGray);
		g.fill(rect);
	}

}
