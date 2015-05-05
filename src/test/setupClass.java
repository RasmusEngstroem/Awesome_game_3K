package test;



import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class setupClass extends BasicGame{


	private Rectangle groundBox0;
	private Rectangle groundBox1;
	private Rectangle groundBox2;
	private Rectangle groundBox3;
	private Rectangle groundBox4;
	private Rectangle groundBox5;
	
	
	
	private static Rectangle[] boxes = new Rectangle[6];
	
	player player = new player("The Player", 100, 100);
	
//	enemy gomba = new enemy("Gomba", 100, 0);
	
	smallEnemy smallE = new smallEnemy("The Small Gomba", 200, 0);
	
	suisideEnemy suisideE = new suisideEnemy("The Suisidal", 400, 0);
	
//---------------------------------------------------------------------------------------
	
	public setupClass(String title) {
		super(title);

	}


	public void init(GameContainer container) throws SlickException {
		

		groundBox0 = new Rectangle(300, 320, 300, 50);
		groundBox1 = new Rectangle(100, 570, 1000, 50);
		groundBox2 = new Rectangle(10, 150, 300, 50);
		groundBox3 = new Rectangle(605, 300, 300, 50);
		groundBox4 = new Rectangle(10, 450, 300, 50);
		groundBox5 = new Rectangle(-50, 350, 70, 50);
		
		boxes[0] = groundBox0;
		boxes[1] = groundBox1;
		boxes[2] = groundBox2;
		boxes[3] = groundBox3;
		boxes[4] = groundBox4;
		boxes[5] = groundBox5;
		
		
		
//		gomba.size = 1f;
//		gomba.init(container);
//		gomba.boxes = boxes;
		
		
//		gomba2.init(container);
//		gomba2.boxes = boxes;
//		gomba2.positionX += 100;
//		
		
		smallE.init(container);
		smallE.boxes = boxes;
		smallE.positionX += 200;
		
		suisideE.init(container);
		suisideE.boxes = boxes;
		suisideE.positionX += 200;
		
		player.init(container);
		player.boxes = boxes;
		
	}
	
	

	public void update(GameContainer container, int delta) throws SlickException {
		
		player.update(container, delta);
		
//		gomba.update(container, delta);
//		gomba2.update(container, delta);

		smallE.update(container, delta);
		suisideE.update(container, delta);
	
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
	
		
		g.setBackground(Color.white);

		
		
		g.setColor(Color.lightGray);
		for (int i = 0; i < boxes.length; i++)
		{
			g.fill(boxes[i]);
		}
		
		player.render(container, g);
		
//		gomba.render(container, g);
//		gomba.showInfo(container, g);
		
		smallE.render(container, g);
		suisideE.render(container, g);
//		suisideE.showInfo(container, g);

	}


//--------------

	public static void main(String[] args) throws SlickException {

		AppGameContainer app = new AppGameContainer(new setupClass("Setup Test"));
		
		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);
		

		
		app.start();
		
	}



	
//--------------------------------------------------------------------------
	
	

	
	
  

}
