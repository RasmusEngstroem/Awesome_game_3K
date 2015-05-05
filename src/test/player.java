package test;

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
	private Circle playerFB; 
	
	public float size = 1;
	public float speed = 0.2f;
	public float jumpHeight = 1;
//	public float dampingAir = 0.002f;
	public float dampingCollision = 0.005f;
	public float gravity = 0.003f;
	
	public float positionX = 100;
	public float positionY = 100;
	
	private Vector2f positionV;
	private Vector2f directionV;
	private Vector2f speedV;
	private Vector2f jumpHeightV;

	
	
	private boolean inAir = true;
	private boolean onGround = false;
	
	public player(String title, int positionX, int positionY) {
		super(title);
		
		this. positionX = positionX;
		this. positionY = positionY;

	}


	public void init(GameContainer container) throws SlickException {

		initPlayer(container);
		playerFB = new Circle(0,0,43*size);
		positionV = new Vector2f(positionX, positionY);
		directionV = new Vector2f(0,0);
		speedV = new Vector2f(speed,0);
		jumpHeightV = new Vector2f(0,jumpHeight);
		
	}


	public void update(GameContainer container, int delta) throws SlickException {
		
		botAnimationL.setSpeed(speed);
		botAnimationL.update(delta);
		botAnimationR.setSpeed(speed);
		botAnimationR.update(delta);
		botAnimationFall.setSpeed(speed);
		botAnimationFall.update(delta);
		

		
		
		movePlayer();
		collisionDeflection();
//		damping();
		
	}
	
	
	public void render(GameContainer container, Graphics g) throws SlickException {

		drawPlayer();
		showInfo(container, g);
	}


	
//---------------------------------------------------------------------

	
	private void movePlayer() {
		directionV.add(new Vector2f(0,1).scale(gravity));
		
		
		
		positionV.add(directionV);
	}
	
//---------------------------------
	
	private void collisionDeflection() {
	
		inAir = true;
		boolean collision = false;
		
		Vector2f collisionV;
		Vector2f deflectV = new Vector2f(0,0);
		
		for (int i = 0; i < boxes.length; i++)
		{
			if (playerFB.intersects(boxes[i]))
			{
				deflectV.normalise();
				collisionV = new Vector2f(positionX - boxes[i].getCenterX(), positionY - boxes[i].getCenterY());
				collisionV.normalise();		
				deflectV = (deflectV.add(collisionV)).scale(0.5f);
				collision = true;
				inAir = false;
			}
		}
		
		while (collision)
		{
			positionV.add(deflectV);
			playerFB.setLocation(positionV);
		}
		
		directionV.scale((float) (-1*0.5));
		playerFB.setLocation(positionV);
		
	}
	
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
		
		else if (directionV.x <= 0)
		botAnimationL.draw(positionV.x-50*size, positionV.y-50*size, 100 * size, 100*size);
		
		else if (directionV.x > 0)
		botAnimationR.draw(positionV.x-50*size, positionV.y-50*size, 100 * size, 100*size);

		
		
	}
	
	
	public void showInfo (GameContainer container, Graphics g) throws SlickException  // for debugging
	{
		g.drawString("direction " + directionV, 10, 30); // print collision true/false
		g.drawString("in Air: " + inAir, 10, 45); // print inAir true/false

		g.draw(playerFB);

	}
}
