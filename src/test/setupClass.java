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



public class setupClass extends BasicGame{

	private SpriteSheet botSheetL;
	private Animation botAnimationL;
	private SpriteSheet botSheetR;
	private Animation botAnimationR;
	private SpriteSheet botSheetFall;
	private Animation botAnimationFall;
	
	private Rectangle groundBox0;
	private Rectangle groundBox1;
	private Rectangle groundBox2;
	private Rectangle groundBox3;
	
	private Rectangle botBox;   // body
	private Rectangle botBoxL;  // left
	private Rectangle botBoxR;  // right
	
	public float positionX = 300f;
	public float positionY = 100f;
	private int standOnRectNr = 0;
	
	public float speed = 0.8f;
	public float size = 1f;
	
	private float mouseLastX = 0f;
	private float mouseLastY = 0f;
	
	
	public boolean collisionB = false;  // body
	private boolean collisionL = false;  // left
	private boolean collisionR = false;  // right
	private boolean inAir = true;
	private boolean collisionEnabled = true;
	
	
	private String direction = "Left"; 
	
	private Rectangle[] boxes = new Rectangle[4];
	
	
//---------------------------------------------------------------------------------------
	
	public setupClass(String title) {
		super(title);

	}


	public void init(GameContainer container) throws SlickException {
		
		botSheetL = new SpriteSheet("pictures/animationBotL.png", 100, 100);
		botAnimationL = new Animation(botSheetL, 250);
		
		botSheetR = new SpriteSheet("pictures/animationBotR.png", 100, 100);
		botAnimationR = new Animation(botSheetR, 250);
		
		botSheetFall = new SpriteSheet("pictures/animationBotFall.png", 100, 100);
		botAnimationFall = new Animation(botSheetFall, 250);
		
		botBox = new Rectangle(positionX, positionY, 100*size, 89*size);
		groundBox0 = new Rectangle(300, 320, 300, 50);
		groundBox1 = new Rectangle(100, 500, 1000, 50);
		groundBox2 = new Rectangle(10, 150, 300, 50);
		groundBox3 = new Rectangle(605, 300, 300, 50);
		
		botBoxL = new Rectangle(0,0,10*size,10*size);
		botBoxR = new Rectangle(0,0,10*size,10*size);
		
		boxes[0] = groundBox0;
		boxes[1] = groundBox1;
		boxes[2] = groundBox2;
		boxes[3] = groundBox3;
		
	}
	
	

	public void update(GameContainer container, int delta) throws SlickException {
		
		botAnimationL.setSpeed(speed);
		botAnimationL.update(delta);
		botAnimationR.setSpeed(speed);
		botAnimationR.update(delta);
		botAnimationFall.setSpeed(speed);
		botAnimationFall.update(delta);
		
		
		moveBot();
		collisionRepeller();
		collisionTurn();
			
		
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
		
		g.setBackground(Color.white);

		
		
		g.setColor(Color.lightGray);
		for (int i = 0; i < boxes.length; i++)
		{
			g.fill(boxes[i]);
		}
		
		drawBot();
		
//		g.drawString("collides: " + collisionB, 10, 30); // print collision true/false
		g.drawString("in Air: " + inAir, 10, 45); // print inAir true/false
		g.drawString("box nr underneath: " + standOnRectNr, 10, 60); // print inAir true/false
		g.drawString("direction: " + direction, 10, 75); // print inAir true/false
		g.drawString("size: " + size + " speed: " + speed, 10, 90); // print inAir true/false
		g.drawString("Alive: " + collisionEnabled, 10, 105); // print inAir true/false

		g.setColor(Color.lightGray);
//		g.draw(botBoxL);
//		g.draw(botBoxR);
//		g.draw(botBox);
	}


//--------------

	public static void main(String[] args) throws SlickException {

		AppGameContainer app = new AppGameContainer(new setupClass("Setup Test"));
		
		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);
		
		
		app.start();
		
	}
	
//--------------------------------------------------------------------------
	
	
	public void moveBot()
	{
		positionY += 0.2f;
		botBox.setLocation(positionX-50*size, positionY-50*size); // move bot collision box with bot animation
		botBoxL.setLocation(positionX-60*size-5, positionY-5+60*size);
		botBoxR.setLocation(positionX+60*size-5, positionY-5+60*size);
		
		if (direction == "Left" && inAir == false)
		{
			positionX -= 0.2f * speed*size;
		}
		else if (direction == "Right" && inAir == false)
		{
			positionX += 0.2f * speed*size;
		}
		
	}
	
//--------------
	
	public void collisionRepeller()
	{
		collisionB = false;
		
		for (int i = 0; i < boxes.length; i++)
		{
			if( botBox.intersects(boxes[i]) && collisionEnabled == true) // check if the bot collides with a groundBox
				collisionB = true;
		
		}
		
		inAir = true;
			
		while (collisionB && collisionEnabled == true)
		{
			positionY -= 0.01f;
			botBox.setLocation(positionX-50*size, positionY-50*size); // move bot collision box with bot animation
			
			collisionB = false;
			
			for (int i = 0; i < boxes.length; i++)
			{
				if( botBox.intersects(boxes[i])) // re-check if the bot collides with a groundBox
					{
						collisionB = true;
						standOnRectNr = i;		
					}
			}
			
			inAir = false;
		}
	}
	
//--------------	
	
	public void collisionTurn()
	{
		collisionL = false;
		collisionR = false;
		
		for (int i = 0; i < boxes.length; i++)
		{
			if( botBoxL.intersects(boxes[i])) // re-check if the bot collides with a groundBox
				{
					collisionL = true;
				}
			if( botBoxR.intersects(boxes[i])) // re-check if the bot collides with a groundBox
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
	
	public void dead()
	{
		collisionEnabled = false;
		
	}

}
