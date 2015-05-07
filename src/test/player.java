package test;

import java.awt.print.Printable;
import java.io.Console;

import org.lwjgl.util.vector.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class player extends BasicGame{

	public String pictureL = "pictures/playerL84.png";
	public String pictureR = "pictures/playerR84.png";
	public String pictureFall = "pictures/animationPlayerIdle_82.png";
	
	public SpriteSheet botSheetL;
	public Animation botAnimationL;
	public SpriteSheet botSheetR;
	public Animation botAnimationR;
	public SpriteSheet botSheetFall;
	public Animation botAnimationFall;
	
	
	public Rectangle[] boxes;
//	public hitBoxes[] boxes;
	public boxTest box = new boxTest("2");
	
	private Rectangle botBoxFB;   // Full Body
	private Rectangle botBoxT;   // body Top
	private Rectangle botBoxB;   // body Bottom
	private Rectangle botBoxL;  // left
	private Rectangle botBoxR;  // right
	private Rectangle botBoxGT;  // ground check
	
	public boolean collisionFB = false;  // body
	private boolean collisionB = false;

//	private boolean inAir = true;
	private boolean collisionEnabled = true;
	
	
	public float size = 1;
	public float speed = 0.7f;
	public float jumpHeight = 1.2f;
//	public float dampingAir = 0.002f;
	public float damping = 0.003f;
	public float gravity = 0.003f;
	
	public float positionX = 100;
	public float positionY = 50;
		
	private Vector2f positionV;
	private Vector2f directionV;


	Vector2f pushObjectV = new Vector2f(0,0);
	Vector2f collisionV = new Vector2f(0,0);
	private boolean inAir = true;
	private boolean onGround = false;
	private boolean inCollision = false;
	private int standOnRectNr = 0;
	
	Vector2f collisionVPrint = new Vector2f(0,0); 
	float variable = 0;
	
	boolean collisionL = false;
	boolean collisionR = false;
	boolean collisionU = false;
	boolean collisionD = false;
	
	
	public player(String title, int positionX, int positionY) {
		super(title);
		
		this. positionX = positionX;
		this. positionY = positionY;

	}


	public void init(GameContainer container) throws SlickException {

		initPlayer(container);


		positionV = new Vector2f(positionX, positionY);
		directionV = new Vector2f(0,0);
		
		botBoxL = new Rectangle(0,0,10*size, 85*size);
		botBoxR = new Rectangle(0,0,10*size, 85*size);
		botBoxT = new Rectangle(0,0,85*size, 10*size);
		botBoxB = new Rectangle(0,0,85*size, 10*size);
		botBoxFB = new Rectangle(0,0,100*size, 85*size);
		botBoxGT = new Rectangle(0,0,80*size, 10*size);

		
	}


	public void update(GameContainer container, int delta) throws SlickException {
		
		botAnimationL.setSpeed(speed * (1-directionV.x));
		botAnimationL.update(delta);
		botAnimationR.setSpeed(speed* (1+ directionV.x));
		botAnimationR.update(delta);
		botAnimationFall.setSpeed(speed);
		botAnimationFall.update(delta);
		

	
		movePlayer(container,delta);
		collisionDeflection2(container,delta);
		damping();
		
	}
	
	
	public void render(GameContainer container, Graphics g) throws SlickException {

		drawPlayer();

	}


	
//---------------------------------------------------------------------

	public void movePlayer(GameContainer container,int delta)
	{
		Input input = container.getInput();
		
		directionV.y += gravity*delta;

		if(input.isKeyDown(Input.KEY_A))
			directionV.x -= speed/500*delta;
		
		if(input.isKeyDown(Input.KEY_D))
			directionV.x += speed/500*delta;
		
		if(input.isKeyDown(Input.KEY_W) &&  pushObjectV.x == 0 && pushObjectV.y != 1 && !inAir )
			directionV.y -= jumpHeight;
		
		positionV.add(directionV);

		
		botBoxFB.setLocation(positionV.x-50*size, positionV.y-5 - 40 *size); // move bot collision box with bot animation
		botBoxGT.setLocation(positionV.x-40*size, positionV.y + 35*size);
		
		botBoxL.setLocation(positionV.x-50*size, positionV.y - 40 *size);
		botBoxR.setLocation(positionV.x + 45*size, positionV.y - 40 *size);
		botBoxT.setLocation(positionV.x+ 0-45*size, positionV.y - 50 *size);
		botBoxB.setLocation(positionV.x+ 0-40*size, positionV.y + 40 *size);

	
	}
	
	//------------
	
	public void collisionDeflection(GameContainer container,int delta)
	{
		pushObjectV = new Vector2f(0,0);
		inCollision = false;
		
		inAir = true;
		
		
		if (collisionL)
			pushObjectV.x = -1;
		
		if (collisionR)
			pushObjectV.x = 1;
		
		if (collisionU)
		{
			pushObjectV.y = -1;
			onGround = true;
		}
		
		if (collisionD)
			pushObjectV.y = 1;
		
		if (collisionL || collisionR || collisionU || collisionD)
			collisionFB = true;
//		
//		for (int i = 0; i < boxes.length; i++)
//		{
//			if( botBoxFB.intersects(boxes[i])) // check if the bot collides with a groundBox
//			{
//				
//				if (botBoxFB.getCenterY()+ (0.5f * botBoxFB.getHeight())+0.5f > boxes[i].getCenterY()- 0.5f * boxes[i].getHeight())
//					pushObjectV.y = -1;
//				
//				if (botBoxFB.getCenterY()+ 0.5f * botBoxFB.getHeight()+1f > boxes[i].getCenterY()+ 0.5f * boxes[i].getHeight())
//					pushObjectV.y = 1;
//				
//				if (botBoxFB.getCenterX()+ 0.5f * botBoxFB.getWidth()-0.5f < boxes[i].getCenterX()- 0.5f * boxes[i].getWidth()  && botBoxFB.getCenterY()+ 0.5 * botBoxFB.getHeight()-1f > boxes[i].getCenterY()- 0.5 * boxes[i].getHeight())
//					pushObjectV.x = -1;
//				
//				if (botBoxFB.getCenterX()- 0.5f * botBoxFB.getWidth() + 0.7f > boxes[i].getCenterX()+ 0.5f * boxes[i].getWidth()  && botBoxFB.getCenterY()+ 0.5 * botBoxFB.getHeight()-1f > boxes[i].getCenterY()- 0.5 * boxes[i].getHeight())
//					pushObjectV.x = 1;
//				
//				
//				inAir = false;
//				inCollision = true;
//			}
			

			
//			collisionVPrint = pushObjectV;
//
////			while (botBoxFB.intersects(boxes[i]))
//			{
				positionV.x += pushObjectV.x/100;
				positionV.y += pushObjectV.y/100;
				
//				botBoxFB.setLocation(positionV.x-50*size, positionV.y-5-40*size); // move bot collision box with bot animation
//				botBoxGT.setLocation(positionV.x-40*size, positionV.y + 35*size);
//				
//				botBoxL.setLocation(positionV.x-50*size, positionV.y - 40 *size);
//				botBoxR.setLocation(positionV.x + 45*size, positionV.y - 40 *size);
//				botBoxT.setLocation(positionV.x+ 0-45*size, positionV.y - 50 *size);
//				botBoxB.setLocation(positionV.x+ 0-40*size, positionV.y + 40 *size);

//			}
//			
//
//			if (botBoxL.intersects(boxes[i]))
//				{
//					standOnRectNr = i;
//					onGround = true;
//				}
//			
//			if (botBoxL.intersects(box.hitBox))
//			{
//				System.out.println("hit box");
//			}
//
//		}
		
		if (pushObjectV.y != 0 )
			directionV.y *= -1/2;

		if (pushObjectV.x != 0)
			directionV.x *= -1/2;
	}

	public void collisionDeflection2(GameContainer container,int delta)
	{
		pushObjectV = new Vector2f(0,0);
		inCollision = false;
		
		inAir = true;
		onGround = false;
		
		for (int i = 0; i < boxes.length; i++)
		{
			if( botBoxFB.intersects(boxes[i])) // check if the bot collides with a groundBox
			{
				
				if (botBoxFB.getCenterY()+ (0.5f * botBoxFB.getHeight())+0.5f > boxes[i].getCenterY()- 0.5f * boxes[i].getHeight())
					pushObjectV.y = -1;
				
				if (botBoxFB.getCenterY()+ 0.5f * botBoxFB.getHeight()+1f > boxes[i].getCenterY()+ 0.5f * boxes[i].getHeight())
					pushObjectV.y = 1;
				
				if (botBoxFB.getCenterX()+ 0.5f * botBoxFB.getWidth()-0.5f < boxes[i].getCenterX()- 0.5f * boxes[i].getWidth()  && botBoxFB.getCenterY()+ 0.5 * botBoxFB.getHeight()-1f > boxes[i].getCenterY()- 0.5 * boxes[i].getHeight())
					pushObjectV.x = -1;
				
				if (botBoxFB.getCenterX()- 0.5f * botBoxFB.getWidth() + 0.7f > boxes[i].getCenterX()+ 0.5f * boxes[i].getWidth()  && botBoxFB.getCenterY()+ 0.5 * botBoxFB.getHeight()-1f > boxes[i].getCenterY()- 0.5 * boxes[i].getHeight())
					pushObjectV.x = 1;
				
				
				inAir = false;
				inCollision = true;
			}
			

			
			collisionVPrint = pushObjectV;

			while (botBoxFB.intersects(boxes[i]))
			{
				positionV.x += pushObjectV.x/100;
				positionV.y += pushObjectV.y/100;
				botBoxFB.setLocation(positionV.x-50*size, positionV.y-5-40*size); // move bot collision box with bot animation
				
			}
			

			if (botBoxGT.intersects(boxes[i]))
				{
					standOnRectNr = i;
					onGround = true;
				}

		}
		
		if (pushObjectV.y != 0 )
			directionV.y *= -1/2;

		if (pushObjectV.x != 0)
			directionV.x *= -1/2;
	}

//---------------------------------
	
	private void damping() {
		
		directionV = directionV.scale(1-damping) ;
//		if (onGround)
//			directionV.y *= (1-damping);
		
	}
	
//---------------------------------	
	
	public void initPlayer(GameContainer container) throws SlickException
	{
		botSheetL = new SpriteSheet(pictureL, 84, 100);
		botAnimationL = new Animation(botSheetL, 270);
		
		botSheetR = new SpriteSheet(pictureR, 83, 100);
		botAnimationR = new Animation(botSheetR, 270);
		
		botSheetFall = new SpriteSheet(pictureFall, 82, 100);
		botAnimationFall = new Animation(botSheetFall, 270);
	}
	
//---------------------------------
	
	
	
	public void drawPlayer()
	{
	
		if ( !onGround)
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
		g.setColor(Color.lightGray);
		
		g.drawString("direction " + directionV.x, 10, 30); // print collision true/false
		g.drawString("direction " + directionV.y, 10, 45); // print collision true/false
		g.drawString("collision: " + collisionFB, 10, 60); // print inAir true/false
		g.drawString("on Ground: " + onGround, 10, 75); // print inAir true/false
//		g.drawString("variable: " + variable, 10, 60); // print inAir true/false
		
		
		g.setColor(Color.lightGray);
		g.draw(botBoxL);
		g.draw(botBoxR);
		g.draw(botBoxFB);
		g.draw(botBoxT);
		g.draw(botBoxB);
//		g.draw(botBoxFB);


	}
}
