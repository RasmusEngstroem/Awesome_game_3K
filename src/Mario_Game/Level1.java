package Mario_Game;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import test.setupClass;

public class Level1 extends level{

//-----

public int width = 1400;
public int height = 1000;
public int moveMargin = 150;
	
public Player player;
private BrickBlock brick; 
private EmptyItemBlock emptyBlok1;
	
	
//------ SETUP --------------------------------------------


	
public Level1(String title) {
		super(title);
	
	}


	//------ INIT --------------------------------------------
	public void sendToInit(GameContainer container) throws SlickException{
		background = new Image("Assets/backgroundWater.jpg");
		
		player = new Player(100 + x_posLevel, 100 + y_posLevel, container);
		player.init(container);
		brick = new BrickBlock(0,0, 10, 0, player);
		emptyBlok1 = new EmptyItemBlock(0, 0, 30, 0, player);
	}

	
	
//------ UPDATE --------------------------------------------
	public void sendToUpdate(GameContainer container, int delta) throws SlickException {
		
		brick.update(200+ x_posLevel, 300 + y_posLevel);
		emptyBlok1.update(10+ x_posLevel, 600 + y_posLevel);
		
		player.update(container, delta, x_posLevel, y_posLevel);
		moveLevel(container, delta);
		
	}
	
	
	
//------ RENDER --------------------------------------------
	public void sendToRender(GameContainer container, Graphics g) throws SlickException {
		
		background.draw(x_posLevel/1.001f, y_posLevel/1.001f - background.getTextureHeight()/4);
		brick.render(g);
		emptyBlok1.render(g);
		player.render(container, g);
	}
	

	void moveLevel(GameContainer container, int delta)
	{
		if (player.x_pos > width/2 + moveMargin)
			x_posLevel -= 1;
		else if (player.x_pos < width/2 - moveMargin)
			x_posLevel += 1;
		if (player.y_pos > height/2 + moveMargin)
			y_posLevel -= 1;
		else if (player.y_pos < height/2 - moveMargin)
			y_posLevel += 1;
	}
	
}
