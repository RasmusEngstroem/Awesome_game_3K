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

public static int points = 0;
	
public static int width = 1024;
public static int height = 768;
public int moveMargin = 150;
	
public Player player;
private ItemBlock brick; 
private EmptyItemBlock emptyBlok1;
private EmptyItemBlock emptyBlok2;
private ItemBlock item1;
	
	
//------ SETUP --------------------------------------------


	
public Level1(String title) {
		super(title);
	
	}


	//------ INIT --------------------------------------------
	public void sendToInit(GameContainer container) throws SlickException{
		background = new Image("Assets/backgroundWater.jpg");
		
		player = new Player(100 + x_posLevel, 100 + y_posLevel, container);
		player.init(container);
		brick = new ItemBlock(0,0, 5, 0, player);
		emptyBlok1 = new EmptyItemBlock(0, 0, 10, 0, player);
		emptyBlok2 = new EmptyItemBlock(0, 0, 10, 0, player);
		
	}

	
	
//------ UPDATE --------------------------------------------
	public void sendToUpdate(GameContainer container, int delta) throws SlickException {
		
		brick.update(200+ x_posLevel, 300 + y_posLevel);
		emptyBlok1.update(0+ x_posLevel, 600 + y_posLevel);
		emptyBlok2.update(800+ x_posLevel, 500 + y_posLevel);
		
		player.update(container, delta, x_posLevel, y_posLevel);
		moveLevel(container, delta);
		
	}
	
	
	
//------ RENDER --------------------------------------------
	public void sendToRender(GameContainer container, Graphics g) throws SlickException {
		
		background.draw(x_posLevel/1.001f, y_posLevel/1.001f - background.getTextureHeight()/4);
		brick.render(g);
		emptyBlok1.render(g);
		emptyBlok2.render(g);
		player.render(container, g);
		showStads(container, g);
	}
	

	void moveLevel(GameContainer container, int delta)
	{
		
		if (player.x_pos > width/2 + moveMargin)
			x_posLevel -= 1*delta;
		
		else if (player.x_pos > width/2 + moveMargin/3)
			x_posLevel -= 0.2f*delta;
		
		else if (player.x_pos < width/2 - moveMargin)
			x_posLevel += 1*delta;
		
		else if (player.x_pos < width/2- moveMargin/3)
			x_posLevel += 0.2f*delta;
		
		
		if (player.y_pos > height/2 + moveMargin)
			y_posLevel -= 1*delta;
		
		else if (player.y_pos > height/2+ moveMargin/3)
			y_posLevel -= 0.2f*delta;
		
		else if (player.y_pos < height/2 - moveMargin)
			y_posLevel += 1*delta;
		
		else if (player.y_pos < height/2- moveMargin/3)
			y_posLevel += 0.2f*delta;

	}
	
	void showStads(GameContainer container, Graphics g)
	{
		g.setColor(Color.yellow);
		
		g.drawString("Points " + points, 10, 30); // print collision true/false
	}
	
}
