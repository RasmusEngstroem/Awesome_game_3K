package mathias_test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class test_setup extends BasicGame {

	public test_setup(String title) {
		super(title);
	}

	public void init(GameContainer container) throws SlickException {		
	}

	public void update(GameContainer container, int delta) throws SlickException {		
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawRect(100, 100, 100, 100);
	}

	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new test_setup("Setup Test"));
		app.setDisplayMode(800, 600, false);
		
		app.start();
	}

}
