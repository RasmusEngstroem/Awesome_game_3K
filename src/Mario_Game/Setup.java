package Mario_Game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Setup extends BasicGame {
	
	public Level1 L1 = new Level1("level 1");
	
	public int gameLevel = 1;
	
	public static int width = 1024; // The width of the game screen 	
	public static int height = 768; // The height of the game screen. 
	
//------ SETUP --------------------------------------------
	
	public Setup(String title) {
		super(title);
		
	}

	
//------ INIT --------------------------------------------
	
	public void init(GameContainer container) throws SlickException {
		L1.sendToInit(container); // Receive the init section of Level1.class
		
		
	}

	
//------ UPDATE --------------------------------------------	
	
	public void update(GameContainer container, int delta) throws SlickException {
		L1.sendToUpdate(container,delta);	 // Receive the update 	from the Level1.class
	}
	
	
//------ RENDER --------------------------------------------
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white); // Set the default background of slick2dgame libary to white. 
		
		L1.sendToRender(container,g); // Receive render section from Level1.class		
	}

	
//------ MAIN --------------------------------------------
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Setup("Main Setup"));
		app.setTargetFrameRate(200); // Set desired frame rate of the game.
		app.setDisplayMode(width, height, false); // Set displaymode of the game using height and width from above. False is a bool for fullscreen. 
//		app.setAlwaysRender(true);
		app.start(); // Start the app. 
	}


}
