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

import Mario_Game.Enemies;


public class setupClass extends BasicGame{


	private Rectangle groundBox0;
	private Rectangle groundBox1;
	private Rectangle groundBox2;
	private Rectangle groundBox3;
	private Rectangle groundBox4;
	private Rectangle groundBox5;
	
	
	public boxTest boxTest = new boxTest("1");
	
	public static boxTest[] hitBoxes = new boxTest[100];
	
	private static Rectangle[] boxes = new Rectangle[6];
	
	player player = new player("The Player", 100, 100);
	
//	enemy gomba = new enemy("Gomba", 100, 0);
	
	smallEnemy smallE = new smallEnemy("The Small Gomba", 200, 0);
	Enemies enemy = new Enemies("The Small Gomba", 200, 0, null, boxes);
	
	suisideEnemy suisideE = new suisideEnemy("The Suisidal", 400, 0);
	
//---------------------------------------------------------------------------------------
	
	public setupClass(String title) {
		super(title);

	}


	public void init(GameContainer container) throws SlickException {
		

		groundBox0 = new Rectangle(300, 300, 100, 100);
		groundBox1 = new Rectangle(400, 300, 100, 100);
		groundBox2 = new Rectangle(500, 300, 100, 100);
		groundBox3 = new Rectangle(605, 300, 300, 50);
		groundBox4 = new Rectangle(10, 400, 300, 50);
		groundBox5 = new Rectangle(100, 100, 300, 50);
		
		hitBoxes[0] = boxTest;
		boxes[0] = groundBox0;
		boxes[1] = groundBox1;
		boxes[2] = groundBox2;
		boxes[3] = groundBox3;
		boxes[4] = groundBox4;
		boxes[5] = groundBox5;
		
		boxTest.pos_x = 300;
		boxTest.pos_y = 200;
		boxTest.init(container);

//		gomba.size = 1f;
//		gomba.init(container);
//		gomba.boxes = boxes;
		
		
//		gomba2.init(container);
//		gomba2.boxes = boxes;
//		gomba2.positionX += 100;
//		
		enemy.init(container);
		
		smallE.init(container);
		smallE.boxes = boxes;
		smallE.positionX += 200;
		
		suisideE.init(container);
		suisideE.boxes = boxes;
		suisideE.positionX += 200;
		
		
		player.init(container);
		player.boxes = boxes;
//		player.hitBoxes = hitBoxes;
		player.box = boxTest;
	}
	
	

	public void update(GameContainer container, int delta) throws SlickException {
		
		enemy.update(container, delta);
		player.update(container, delta);
		
//		gomba.update(container, delta);
//		gomba2.update(container, delta);

		smallE.update(container, delta);
		suisideE.update(container, delta);
	
		boxTest.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
	
		
		g.setBackground(Color.white);

		
		
		g.setColor(Color.lightGray);
		for (int i = 0; i < boxes.length; i++)
		{
			g.fill(boxes[i]);
		}
		
		boxTest.render(container, g);
		enemy.render(container, g);
		player.render(container, g);
		player.showInfo(container, g);
		
//		gomba.render(container, g);
//		gomba.showInfo(container, g);
		
		smallE.render(container, g);
//		smallE.showInfo(container, g);
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
