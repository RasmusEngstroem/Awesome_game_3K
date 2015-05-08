package Mario_Game;

// libraries
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Level1 extends level{  // a level which has inherited from the level class. in order to build multiple levels

//-----

// declare the level variables
public static int points = 0;
public static int lives = 3;
	
public static int width = 1024;
public static int height = 768;
public int moveMargin = 150;
public static boolean moveLevel = true;
public static boolean gameOver = false;
public static boolean gameWon = false;


// Declare the objects used in the game
public LevelZone levelZone;
public Player player;

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
		background = new Image("backgroundWater.jpg");  // fill in the background
		
		
		
		player = new Player(100 + x_posLevel, -500 + y_posLevel, container); // spawn the player at position x + the" position of the screen", y + "position of screen"
		player.init(container);
		levelZone = new LevelZone(x_posLevel, y_posLevel, 24700, 3000, player); // spawn the kill-zone area by position x, y, width, height, access to the player
		winCube = new WinCube(0, 0, player); // spawn the goal to find in the level by position x, y, access to the player for collision check
		
		solid1 = new SolidBlock(0, 0, 12, 0, player, enemy); // spawn all solid blocks. Position x, y, repetitions, access to enemy array
		solid2 = new SolidBlock(0, 0, 4, 0, player, enemy);
		solid3 = new SolidBlock(0, 0, 6, 0, player, enemy);
		solid4 = new SolidBlock(0, 0, 11, 0, player, enemy);
		
		itemBlok1 = new ItemBlock(0, 0, 0, 0, player, enemy); // spawn all item blocks. -||-
		itemBlok2 = new ItemBlock(0, 0, 2, 0, player, enemy);
		itemBlok3 = new ItemBlock(0, 0, 0, 0, player, enemy);
		itemBlok4 = new ItemBlock(0, 0, 2, 0, player, enemy);
		itemBlok5 = new ItemBlock(0, 0, 0, 0, player, enemy);
		itemBlok6 = new ItemBlock(0, 0, 1, 0, player, enemy);
		itemBlok7 = new ItemBlock(0, 0, 2, 0, player, enemy);
		
		brickBlok1 = new BrickBlock(0, 0, 1, 0, player, enemy); // spawn all breakable blocks. -||-
		brickBlok2 = new BrickBlock(0, 0, 0, 0, player, enemy);
		brickBlok3 = new BrickBlock(0, 0, 2, 0, player, enemy);
		brickBlok4 = new BrickBlock(0, 0, 0, 0, player, enemy);
		brickBlok5 = new BrickBlock(0, 0, 2, 0, player, enemy);
		

		enemy[0]= new Enemies(600 ,0, player);  // spawn all enemies - position, player access
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
		
		if (lives <= 0)  // check if the lives of the player is low enough for a Game Over
			gameOver = true;
		
		if (!gameWon ) // stop updating if the game is won - for effect
		{
			if (!gameOver  ) // stop updating if the game is lost - for effect
			{
			
			
				solid1.update(0+ x_posLevel, 0 + y_posLevel);  // keep game objects in sync with level position to being able to pan the level / move the screen
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
				
				for(int i = 0; i<enemy.length; i++ ){ 							// update the array of enemies
					enemy[i].update(container, delta, x_posLevel , y_posLevel);
				}	
				
				
				winCube.update(3900 + x_posLevel, -110 + y_posLevel);			// update the goal position
				levelZone.update(x_posLevel, y_posLevel);  // levelzone position updated
				
				moveLevel(container, delta);  // function to move the level according to player
				}
			}
		player.update(container, delta, x_posLevel, y_posLevel); // update the player even though lost or won
	}
	
	
	
//------ RENDER --------------------------------------------
	public void sendToRender(GameContainer container, Graphics g) throws SlickException {
		
		
		background.draw(x_posLevel/1.1f - 1500 , y_posLevel/1.1f - 1000); // draw the background at position, but slow it down a little- for effect
		
		
//		levelZone.render(g); 

		solid1.render(g); // draw game objects by calling the render function
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

		for(int i = 0; i<enemy.length; i++ ){  // draw all enemies in array
			enemy[i].render(container, g);
		}
		showStads(container, g);
	}
	

	
//-------- FUNCTIONS-------------------------------------------
	
	
	void moveLevel(GameContainer container, int delta)  // function for moving the levels position according to player position
	{
		
		if (player.x_pos > width/2 + moveMargin) // move fast Left, if player position is out of middle margin
			x_posLevel -= 1*delta;
		
		else if (player.x_pos > width/2 + moveMargin/3) // move slowly Left to orientate the player in the middle of the screen
			x_posLevel -= 0.2f*delta;
		
		else if (player.x_pos < width/2 - moveMargin) // Right
			x_posLevel += 1*delta;
		
		else if (player.x_pos < width/2- moveMargin/3) // Right
			x_posLevel += 0.2f*delta;
		
		
		if (player.y_pos > height/2 + moveMargin) // same on y-axis
			y_posLevel -= 1*delta;
		
		else if (player.y_pos > height/2+ moveMargin/3)
			y_posLevel -= 0.2f*delta;
		
		else if (player.y_pos < height/2 - moveMargin)
			y_posLevel += 1*delta;
		
		else if (player.y_pos < height/2- moveMargin/3)
			y_posLevel += 0.2f*delta;

	}
	
	
	void showStads(GameContainer container, Graphics g) // draw info for the player - points, life
	{
		g.setColor(Color.black);
		
		g.drawString("Points " + points, 10, 30); // print collision true/false
		g.drawString("Lives  " + lives, 10, 45); // print collision true/false
	
		if (gameOver && !gameWon)  // if game over
			endScreen(container, g); 
		
		if (gameWon)  // if game won
			winScreen(container, g);
	}
	
	
	void endScreen(GameContainer container, Graphics g)  // functions for displaying win and game-over screen. This was separately so, that special graphical effects could have been used
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
