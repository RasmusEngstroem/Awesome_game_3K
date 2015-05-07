package mathias_test_igen;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Setup extends BasicGame {

	public Mario kasse;
	public Box brick;
	public Mario_2 player;
	public test.player mario;
	
	public Setup(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		//mario = new test.player("t", 0, 0);
		//mario.init(container);
		player = new Mario_2(1, 1, container);
		player.init(container);
		//kasse = new Mario(0,0, container);
		brick = new Box(0,0, container, player,20);
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		//mario.update(container, delta);
		//kasse.updatePosition(container.getInput().getMouseX(),container.getInput().getMouseY());
		player.update(container, delta);
		brick.updatePosition(40,200);
		brick.checkCollision();

	}

	@Override
	public void render(GameContainer container, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		//mario.render(container, arg1);
		
		//kasse.drawMask(arg1);
		player.render(container, arg1);
		brick.drawMask(arg1);
		
	}

	
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Setup("Setup Test this"));
		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);
		app.start();
	}

}
