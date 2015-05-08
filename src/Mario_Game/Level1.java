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
public static int lives = 3;
	
public static int width = 1024;
public static int height = 768;
public int moveMargin = 150;
public static boolean moveLevel = true;
	
public Player player;
private ItemBlock brick; 
private EmptyItemBlock emptyBlok1;
private EmptyItemBlock emptyBlok2;
private SolidBlock solid1;
private ItemBlock item1;

private Coins coin;
private Enemies[] enemy = new Enemies[3];
	
//------ SETUP --------------------------------------------


	
public Level1(String title) {
		super(title);
	
	}


	//------ INIT --------------------------------------------
	public void sendToInit(GameContainer container) throws SlickException{
		background = new Image("Assets/backgroundWater.jpg");
		
		player = new Player(100 + x_posLevel, 100 + y_posLevel, container);
		player.init(container);
		brick = new ItemBlock(0,0, 5, 0, player, enemy);
		emptyBlok1 = new EmptyItemBlock(0, 0, 10, 0, player, enemy);
		emptyBlok2 = new EmptyItemBlock(0, 0, 10, 0, player, enemy);
		solid1 = new SolidBlock(0, 0, 10, 0, player, enemy);
		coin = new Coins(0, 0, player);
		
		for(int i = 0; i<enemy.length; i++ ){
			enemy[i]= new Enemies(400+ 100*i,0, player);
			enemy[i].init(container);
		}
	}

	
	
//------ UPDATE --------------------------------------------
	public void sendToUpdate(GameContainer container, int delta) throws SlickException {
		
		
		
		brick.update(200+ x_posLevel, 300 + y_posLevel);
		emptyBlok1.update(0+ x_posLevel, 600 + y_posLevel);
		emptyBlok2.update(800+ x_posLevel, 500 + y_posLevel);
		coin.update(300+ x_posLevel, 500 + y_posLevel);
		solid1.update(1600+ x_posLevel, 300 + y_posLevel);
		
		player.update(container, delta, x_posLevel, y_posLevel);
		for(int i = 0; i<enemy.length; i++ ){
			enemy[i].update(container, delta, x_posLevel , y_posLevel);
		}
		
		if (moveLevel)
			moveLevel(container, delta);
		
	}
	
	
	
//------ RENDER --------------------------------------------
	public void sendToRender(GameContainer container, Graphics g) throws SlickException {
		
		background.draw(x_posLevel/1.1f - 800 , y_posLevel/1.1f - 500);
		brick.render(g);
		emptyBlok1.render(g);
		emptyBlok2.render(g);
		coin.render(g);
		solid1.render(g);
		player.render(container, g);
		for(int i = 0; i<enemy.length; i++ ){
			enemy[i].render(container, g);
//			enemy[i].showInfo(container, g);
		}
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
		g.setColor(Color.red);
		
		g.drawString("Points " + points, 10, 30); // print collision true/false
		g.drawString("Lives  " + lives, 10, 45); // print collision true/false
	}
	
}
