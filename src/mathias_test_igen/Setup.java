package mathias_test_igen;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Setup extends BasicGame {

	public Mario kasse;
	public Box brick;
	
	public Setup(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		kasse = new Mario(0,0, container);
		brick = new Box(0,0, container, kasse,20);
		
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		kasse.updatePosition(container.getInput().getMouseX(),container.getInput().getMouseY());

		brick.updatePosition(40,40);
		brick.checkCollision();

	}

	@Override
	public void render(GameContainer container, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		kasse.drawMask(arg1);
		brick.drawMask(arg1);
	}

	
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Setup("Setup Test"));
		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
