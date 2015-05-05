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


public class enemy extends BasicGame{

	public Rectangle[] boxes;
	
	private SpriteSheet botSheetL;
	private Animation botAnimationL;
	private SpriteSheet botSheetR;
	private Animation botAnimationR;
	private SpriteSheet botSheetFall;
	private Animation botAnimationFall;
	

	private Circle botBoxFB; 
	private Rectangle botBoxTL;   // body Top
	private Rectangle botBoxTR;   // body Top
	private Rectangle botBoxB;   // body Bottom
	private Rectangle botBoxL;  // left
	private Rectangle botBoxR;  // right
	
	public float positionX = 300f;
	public float positionY = 100f;
	private int standOnRectNr = 0;
	
	public float speed = 0.6f;
	public float size = 1f;
	
	private float mouseLastX = 0f;
	private float mouseLastY = 0f;
	
	
	public boolean collisionFB = false;  // body
//	private boolean collisionT = false;
	private boolean collisionB = false;
	private boolean collisionL = false;  // left
	private boolean collisionR = false;  // right
	private boolean inAir = true;
	private boolean collisionEnabled = true;
	
	
	private String direction = "Left"; 
	

	
	
//---------------------------------------------------------------------------------------
	
	public enemy(String title, int positionX, int positionY) {
		super(title);
		this. positionX = positionX;
		this. positionY = positionY;
	}


	public void init(GameContainer container) throws SlickException {
		
		botSheetL = new SpriteSheet("pictures/animationBotL.png", 100, 100);
		botAnimationL = new Animation(botSheetL, 230);
		
		botSheetR = new SpriteSheet("pictures/animationBotR.png", 100, 100);
		botAnimationR = new Animation(botSheetR, 230);
		
		botSheetFall = new SpriteSheet("pictures/animationBotFall.png", 100, 100);
		botAnimationFall = new Animation(botSheetFall, 230);
		
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
		
		
		
		moveBot(delta);
		edgeTurn();
		collisionTurn();
		collisionRepeller();

			
		
		if (Math.sqrt(Math.pow((positionX - container.getInput().getMouseX()), 2)) < 50 && Math.sqrt(Math.pow((positionY - container.getInput().getMouseY()), 2)) < 50 )
		{
			if (container.getInput().getMouseY() - mouseLastY > 20)
			{
				dead();
			}
			else
			{
				if (collisionEnabled)
				{
					positionX -= mouseLastX - container.getInput().getMouseX();
					positionY -= mouseLastY - container.getInput().getMouseY();
				}
			}
		}
		
		
		mouseLastX = container.getInput().getMouseX();
		mouseLastY = container.getInput().getMouseY();
		
	}
	

	public void render(GameContainer container, Graphics g) throws SlickException {
		
		
		drawBot();
		

	}


//--------------

	public static void main(String[] args) throws SlickException {
	
	}
	
//--------------------------------------------------------------------------
	
	
	public void moveBot(int delta)
	{
		positionY += 0.2f*delta;
		botBoxFB.setLocation(positionX-43*size, positionY+(5-50)*size);
		botBoxTL.setLocation(positionX-51*size, positionY+5-50*size);
		botBoxTR.setLocation(positionX-0*size, positionY+5-50*size);
		botBoxB.setLocation(positionX-50*size, positionY-5 + 10*size); // move bot collision box with bot animation
		botBoxL.setLocation(positionX-5-60*size, positionY-5+50*size);
		botBoxR.setLocation(positionX-5+60*size, positionY-5+50*size);
		
		if (direction == "Left" && inAir == false)
		{
			positionX -= 0.2f * speed*size*delta;
		}
		else if (direction == "Right" && inAir == false)
		{
			positionX += 0.2f * speed*size*delta;
		}
		
	}
	
//--------------
	
	public void collisionRepeller()
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
			positionY -= 0.01f;
			botBoxB.setLocation(positionX-50*size, positionY-5+10*size); // move bot collision box with bot animation
			
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
	
//--------------	
	
	public void edgeTurn()
	{
		collisionL = false;
		collisionR = false;
		
		for (int i = 0; i < boxes.length; i++)
		{
			
			if( botBoxL.intersects(boxes[i]) && collisionEnabled && !inAir) // re-check if the bot collides with a groundBox
			{
				collisionL = true;
			}
			if( botBoxR.intersects(boxes[i]) && collisionEnabled && !inAir) // re-check if the bot collides with a groundBox
			{
				collisionR = true;
			}

		}
	
		
		if (!collisionL)
		{
			direction = "Right";
			
		}
		else if (!collisionR)
		{
			direction = "Left";
		}
	}

	public void collisionTurn()
	{
		for (int i = 0; i < boxes.length; i++)
		{
		
			if(botBoxTL.intersects(boxes[i]) && collisionEnabled && !inAir)
			{
				while (botBoxTL.intersects(boxes[i]))
				{
					positionX += 0.01f;
					botBoxTL.setLocation(positionX-51*size, positionY+(5-50)*size); // move bot collision box with bot animation
				}
				changeDirection();
			}

			
			if(botBoxTR.intersects(boxes[i])&& collisionEnabled && !inAir)
			{
				while (botBoxTR.intersects(boxes[i]))
				{
					positionX -= 0.01f;
					botBoxTR.setLocation(positionX-0*size, positionY+(5-50)*size); // move bot collision box with bot animation
				}
				changeDirection();
			}
		}
	}
	
//--------------
	
	public void drawBot()
	{
	
		if (inAir)
		botAnimationFall.draw(positionX-50*size, positionY-50*size, 100 * size, 100*size);
		
		else if (direction == "Left")
		botAnimationL.draw(positionX-50*size, positionY-50*size, 100 * size, 100*size);
		
		else if (direction == "Right")
		botAnimationR.draw(positionX-50*size, positionY-50*size, 100 * size, 100*size);

	}
	
//--------------
	public void changeDirection()
	{
		if (direction == "Right")
			direction = "Left";
		else if (direction == "Left")
			direction = "Right";
	}
	
	
	public void dead()
	{
		collisionEnabled = false;
		
	}
	
	public void showInfo (GameContainer container, Graphics g) throws SlickException  // for debugging
	{
		g.drawString("collides: " + collisionB, 10, 30); // print collision true/false
		g.drawString("in Air: " + inAir, 10, 45); // print inAir true/false
		g.drawString("box nr underneath: " + standOnRectNr, 10, 60); // print inAir true/false
		g.drawString("direction: " + direction, 10, 75); // print inAir true/false
		g.drawString("size: " + size + " speed: " + speed, 10, 90); // print inAir true/false
		g.drawString("Alive: " + collisionEnabled, 10, 105); // print inAir true/false

		g.setColor(Color.lightGray);
		g.draw(botBoxL);
		g.draw(botBoxR);
		g.draw(botBoxB);
		g.draw(botBoxTL);
		g.draw(botBoxTR);
		g.draw(botBoxFB);
	}
  

}
