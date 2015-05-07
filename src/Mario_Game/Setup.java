package Mario_Game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Setup extends BasicGame {
	
	public Level1 L1 = new Level1("level 1");
	
	public int gameLevel = 1;
	
	
//------ SETUP --------------------------------------------
	
	public Setup(String title) {
		super(title);
		
	}

	
//------ INIT --------------------------------------------
	
	public void init(GameContainer container) throws SlickException {
		L1.sendToInit(container);
		
		
	}

	
//------ UPDATE --------------------------------------------	
	
	public void update(GameContainer container, int delta) throws SlickException {
		L1.sendToUpdate(container,delta);		
	}
	
	
//------ RENDER --------------------------------------------
	public void render(GameContainer container, Graphics g) throws SlickException {
		L1.sendToRender(container,g);		
	}

	
//------ MAIN --------------------------------------------
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Setup("Main Setup"));
		app.setDisplayMode(1400, 1000, false);
		app.start();
	}


}
