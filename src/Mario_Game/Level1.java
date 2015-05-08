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
public static boolean gameOver = false;
public static boolean gameWon = false;

public LevelZone levelZone;
public Player player;
private ItemBlock brick; 
private EmptyItemBlock emptyBlok1;
private EmptyItemBlock emptyBlok2;

private ItemBlock item1;

private Coins coin;
private WinCube winCube;

private Enemies[] enemy = new Enemies[8];


private SolidBlock solid1;
private SolidBlock solid2;
private SolidBlock solid3;
private SolidBlock solid4;

private ItemBlock itemBlok1;
private ItemBlock itemBlok2;
private ItemBlock itemBlok3;
private ItemBlock itemBlok4;
private ItemBlock itemBlok5;
private ItemBlock itemBlok6;
private ItemBlock itemBlok7;

private BrickBlock brickBlok1;
private BrickBlock brickBlok2;
private BrickBlock brickBlok3;
private BrickBlock brickBlok4;
private BrickBlock brickBlok5;



	
//------ SETUP --------------------------------------------


	
public Level1(String title) {
		super(title);
	
	}


	//------ INIT --------------------------------------------
	public void sendToInit(GameContainer container) throws SlickException{
		background = new Image("Assets/backgroundWater.jpg");
		
		player = new Player(100 + x_posLevel, -500 + y_posLevel, container);
		player.init(container);

		winCube = new WinCube(0, 0, player);
		
		solid1 = new SolidBlock(0, 0, 12, 0, player, enemy);
		solid2 = new SolidBlock(0, 0, 4, 0, player, enemy);
		solid3 = new SolidBlock(0, 0, 6, 0, player, enemy);
		solid4 = new SolidBlock(0, 0, 11, 0, player, enemy);
		
		itemBlok1 = new ItemBlock(0, 0, 0, 0, player, enemy);
		itemBlok2 = new ItemBlock(0, 0, 2, 0, player, enemy);
		itemBlok3 = new ItemBlock(0, 0, 0, 0, player, enemy);
		itemBlok4 = new ItemBlock(0, 0, 2, 0, player, enemy);
		itemBlok5 = new ItemBlock(0, 0, 0, 0, player, enemy);
		itemBlok6 = new ItemBlock(0, 0, 1, 0, player, enemy);
		itemBlok7 = new ItemBlock(0, 0, 2, 0, player, enemy);
		
		brickBlok1 = new BrickBlock(0, 0, 1, 0, player, enemy);
		brickBlok2 = new BrickBlock(0, 0, 0, 0, player, enemy);
		brickBlok3 = new BrickBlock(0, 0, 2, 0, player, enemy);
		brickBlok4 = new BrickBlock(0, 0, 0, 0, player, enemy);
		brickBlok5 = new BrickBlock(0, 0, 2, 0, player, enemy);
		
		
		
		levelZone = new LevelZone(x_posLevel, y_posLevel, 24700, 3000, player);
		
		
		
			enemy[0]= new Enemies(600 ,0, player);
			enemy[0].init(container);
			enemy[1]= new Enemies(600 ,-300, player);
			enemy[1].init(container);
			enemy[2]= new Enemies(1800 ,-700, player);
			enemy[2].init(container);
			enemy[3]= new Enemies(2000 ,-700, player);
			enemy[3].init(container);
			enemy[4]= new Enemies(2600 ,-700, player);
			enemy[4].init(container);
			enemy[5]= new Enemies(3300 ,-700, player);
			enemy[5].init(container);
			enemy[6]= new Enemies(3600 ,-700, player);
			enemy[6].init(container);
			enemy[7]= new Enemies(3900 ,-700, player);
			enemy[7].init(container);

		

	}

	
	
//------ UPDATE --------------------------------------------
	public void sendToUpdate(GameContainer container, int delta) throws SlickException {
		
		if (lives <= 0)
			gameOver = true;
		
		if (!gameWon )
		{
			if (!gameOver  )
			{
			
		

				
				solid1.update(0+ x_posLevel, 0 + y_posLevel);
				solid2.update(1500+ x_posLevel, 0 + y_posLevel);
				solid3.update(2200+ x_posLevel, 0 + y_posLevel);
				solid4.update(3000+ x_posLevel, 0 + y_posLevel);
				
				itemBlok1.update(400+ x_posLevel, -300 + y_posLevel);
				itemBlok2.update(600+ x_posLevel, -300 + y_posLevel);
				itemBlok3.update(700+ x_posLevel, -600 + y_posLevel);
				itemBlok4.update(1600+ x_posLevel, -300 + y_posLevel);
				itemBlok5.update(1900+ x_posLevel, -600 + y_posLevel);
				itemBlok6.update(2300+ x_posLevel, -600 + y_posLevel);
				itemBlok7.update(2400+ x_posLevel, -300 + y_posLevel);
				
				brickBlok1.update(1000+ x_posLevel, -300 + y_posLevel);
				brickBlok2.update(1800+ x_posLevel, -600 + y_posLevel);
				brickBlok3.update(2000+ x_posLevel, -600 + y_posLevel);
				brickBlok4.update(2300+ x_posLevel, -300 + y_posLevel);
				brickBlok5.update(3400+ x_posLevel, -300 + y_posLevel);
				
				for(int i = 0; i<enemy.length; i++ ){
					enemy[i].update(container, delta, x_posLevel , y_posLevel);
				}	
				
				
				winCube.update(3900 + x_posLevel, -110 + y_posLevel);
				
				moveLevel(container, delta);
				}
			}
		player.update(container, delta, x_posLevel, y_posLevel);
		levelZone.update(x_posLevel, y_posLevel);
	}
	
	
	
//------ RENDER --------------------------------------------
	public void sendToRender(GameContainer container, Graphics g) throws SlickException {
		
		
		background.draw(x_posLevel/1.1f - 800 , y_posLevel/1.1f - 500);
		
		
		levelZone.render(g);

		solid1.render(g);
		solid2.render(g);
		solid3.render(g);
		solid4.render(g);
		
		itemBlok1.render(g);
		itemBlok2.render(g);
		itemBlok3.render(g);
		itemBlok4.render(g);
		itemBlok5.render(g);
		itemBlok6.render(g);
		itemBlok7.render(g);
		
		brickBlok1.render(g);
		brickBlok2.render(g);
		brickBlok3.render(g);
		brickBlok4.render(g);
		brickBlok5.render(g);
		
		winCube.render(g);
		player.render(container, g);
		player.showInfo(container, g);
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
		g.setColor(Color.black);
		
		g.drawString("Points " + points, 10, 30); // print collision true/false
		g.drawString("Lives  " + lives, 10, 45); // print collision true/false
	
		if (gameOver && !gameWon)
			endScreen(container, g);
		
		if (gameWon)
			winScreen(container, g);
	}
	
	void endScreen(GameContainer container, Graphics g)
	{
		g.setColor(Color.black);
		g.drawString("GameOver", width/2-50, height/2);	
	}
	
	void winScreen(GameContainer container, Graphics g)
	{
		g.setColor(Color.red);
		g.drawString("You win!", width/2-50, height/4);
		
	}
	
}
