package test;

import java.awt.print.Printable;
import java.io.Console;

import org.lwjgl.util.vector.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class player extends BasicGame{

	public String pictureL = "pictures/animationBotL.png";
	public String pictureR = "pictures/animationBotR.png";
	public String pictureFall = "pictures/animationBotFall.png";
	
	public SpriteSheet botSheetL;
	public Animation botAnimationL;
	public SpriteSheet botSheetR;
	public Animation botAnimationR;
	public SpriteSheet botSheetFall;
	public Animation botAnimationFall;
	
	
	public Rectangle[] boxes;
//	private Rectangle playerFB; 
	private Circle playerFB; 
	
	private Circle botBoxFB; 
	private Rectangle botBoxTL;   // body Top
	private Rectangle botBoxTR;   // body Top
	private Rectangle botBoxB;   // body Bottom
	private Rectangle botBoxL;  // left
	private Rectangle botBoxR;  // right
	
	public boolean collisionFB = false;  // body
//	private boolean collisionT = false;
	private boolean collisionB = false;
	private boolean collisionL = false;  // left
	private boolean collisionR = false;  // right
//	private boolean inAir = true;
	private boolean collisionEnabled = true;
	
	
	public float size = 1;
	public float speed = 0.7f;
	public float jumpHeight = 1;
//	public float dampingAir = 0.002f;
	public float dampingCollision = 0.005f;
	public float gravity = 0.003f;
	
	public float positionX = 100;
	public float positionY = 50;
		
	private Vector2f positionV;
	private Vector2f directionV;
	private Vector2f speedV;
	private Vector2f jumpHeightV;

	
	
	private boolean inAir = true;
	private boolean onGround = false;
	private int standOnRectNr = 0;
	
	public player(String title, int positionX, int positionY) {
		super(title);
		
		this. positionX = positionX;
		this. positionY = positionY;

	}


	public void init(GameContainer container) throws SlickException {

		initPlayer(container);
		playerFB = new Circle(0,0,43*size);
//		playerFB = new Rectangle(positionX, positionY, 100, 100);
		positionV = new Vector2f(positionX, positionY);
		directionV = new Vector2f(0,0);
		speedV = new Vector2f(speed,0);
		jumpHeightV = new Vector2f(0,jumpHeight);
		
		
		botBoxFB = new Circle(0,0,43*size);
		botBoxTL = new Rectangle(0,0,51*size, 50*size);
		botBoxTR = new Rectangle(0,0,51*size, 50*size);
		botBoxB = new Rectangle(0,0,100*size, 35*size);
		botBoxL = new Rectangle(0,0,10*size, 35*size);
		botBoxR = new Rectangle(0,0,10*size, 35*size);
		
	}


	public void update(GameContainer container, int delta) throws SlickException {
		
		botAnimationL.setSpeed(speed);
		botAnimationL.update(delta);
		botAnimationR.setSpeed(speed);
		botAnimationR.update(delta);
		botAnimationFall.setSpeed(speed);
		botAnimationFall.update(delta);
		

//		moveBot(delta);
//		edgeTurn();
//		collisionTurn();
//		collisionRepeller();
		
		movePlayer(delta);
		collisionDeflection();
//		damping();
		
	}
	
	
	public void render(GameContainer container, Graphics g) throws SlickException {

//		drawBot();
		drawPlayer();
		showInfo(container, g);
	}


	
//---------------------------------------------------------------------

	public void movePlayer(int delta)
	{
		directionV.y += gravity*delta;
//		positionY += 0.2f*delta;

		positionV.add(directionV);
//		playerFB.setLocation(positionV.x - 50+5, positionV.y - 50+5);
		
		botBoxB.setLocation(positionV.x-50*size, positionV.y-5 + 10*size); // move bot collision box with bot animation

		

		
	}
	
	//------------
	
	public void collisionDeflection()
	{
		collisionB = false;
		
		for (int i = 0; i < boxes.length; i++)
		{
			if( botBoxB.intersects(boxes[i]) && collisionEnabled == true) // check if the bot collides with a groundBox
				collisionB = true;
		
		}
		
		inAir = true;
			
		while (collisionB && collisionEnabled == true)
		{
			positionV.y -= 0.001f;
			directionV.y = 0.0f;
			botBoxB.setLocation(positionV.x-50*size, positionV.y-5+10*size); // move bot collision box with bot animation
			
			collisionB = false;
			
			for (int i = 0; i < boxes.length; i++)
			{
				if( botBoxB.intersects(boxes[i])) // re-check if the bot collides with a groundBox
					{
						collisionB = true;
						standOnRectNr = i;		
					}
			}
			inAir = false;
		}
	}
	
	

	
//---------------------------------
	

		
		
		
//		inAir = true;
//		boolean collision = false;
//		
//		Vector2f collisionV;
//		Vector2f deflectV = new Vector2f(0,0);
//		
//		for (int i = 0; i < boxes.length; i++)
//		{
//			if (playerFB.intersects(boxes[i]))
//			{
//				deflectV.normalise();
//				collisionV = new Vector2f(positionV.x - boxes[i].getCenterX(), positionV.y - boxes[i].getCenterY());
//				collisionV.normalise();		
//				deflectV = (deflectV.add(collisionV)).scale(0.5f);
//				collision = true;
//				inAir = false;
//			}
//		}
//		
//		while (collision)
//		{
//			positionV.add(deflectV);
//			playerFB.setLocation(positionV);
//		}
//		
//		if (collision)
//			directionV.scale((float) (-1*0.5));	
//			
//		collision = false;
//		
//		
//		playerFB.setLocation(positionV);
		
//	}
	
//---------------------------------
	
	private void damping() {
		
//		directionV = directionV.scale(dampingAir) ;
		
	}
	
//---------------------------------	
	
	public void initPlayer(GameContainer container) throws SlickException
	{
		botSheetL = new SpriteSheet(pictureL, 100, 100);
		botAnimationL = new Animation(botSheetL, 230);
		
		botSheetR = new SpriteSheet(pictureR, 100, 100);
		botAnimationR = new Animation(botSheetR, 230);
		
		botSheetFall = new SpriteSheet(pictureFall, 100, 100);
		botAnimationFall = new Animation(botSheetFall, 230);
	}
	
//---------------------------------
	
	
	
	public void drawPlayer()
	{
	
		if (inAir)
		botAnimationFall.draw(positionV.x-50*size, positionV.y-50*size, 100 * size, 100*size);
		
		else if (directionV.x < -0.005f)
		botAnimationL.draw(positionV.x-50*size, positionV.y-50*size, 100 * size, 100*size);
		
		else if (directionV.x > 0.005f)
		botAnimationR.draw(positionV.x-50*size, positionV.y-50*size, 100 * size, 100*size);
		
		else
			botAnimationFall.draw(positionV.x-50*size, positionV.y-50*size, 100 * size, 100*size);
		
	}
	
	
	public void showInfo (GameContainer container, Graphics g) throws SlickException  // for debugging
	{
		g.drawString("direction " + directionV, 10, 30); // print collision true/false
		g.drawString("in Air: " + inAir, 10, 45); // print inAir true/false

		g.setColor(Color.lightGray);
		g.draw(botBoxL);
		g.draw(botBoxR);
		g.draw(botBoxB);
		g.draw(botBoxTL);
		g.draw(botBoxTR);
		g.draw(botBoxFB);
		g.draw(playerFB);

	}
}
