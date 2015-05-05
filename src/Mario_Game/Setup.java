package Mario_Game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Setup extends BasicGame {
	
	public Level1 L1 = new Level1();
	
	public int gameLevel = 1;
	
	public Setup(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		L1.sendToInit();
				
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		L1.sendToUpdate();		
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		L1.sendToRender();		
	}

	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Setup("Setup Test"));
		app.setDisplayMode(800, 600, false);
		app.start();
	}


}
