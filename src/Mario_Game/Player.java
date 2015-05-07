package Mario_Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import test.boxTest;

public class Player extends GameEntities{

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
//	public hitBoxes[] boxes;
	public boxTest box = new boxTest("2");
	
	public Rectangle botBoxFB;   // Full Body
	public Rectangle botBoxT;   // body Top
	public Rectangle botBoxB;   // body Bottom
	public Rectangle botBoxL;  // left
	public Rectangle botBoxR;  // right
	public Rectangle botBoxGT;  // ground check
	
	public boolean collisionFB = false;  // body
	private boolean collisionB = false;

//	private boolean inAir = true;
	private boolean collisionEnabled = true;
	
	
	public float size = 1;
	public float speed = 0.7f;
	public float jumpHeight = 2.2f;
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
	boolean collisionGT = false;
	
	public Player(float x_pos, float y_pos, GameContainer container) {
		super(x_pos, y_pos);
		positionY = y_pos;
		positionX = x_pos;
		// TODO Auto-generated constructor stub
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
		collisionDeflection(container,delta);
		damping(delta);
		
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
		
		if(input.isKeyDown(Input.KEY_W) && onGround && pushObjectV.x == 0 && pushObjectV.y !=0 )
			{
//			positionV.y -= 20;
			directionV.y -= jumpHeight;
			}
			
		
		positionV.add(directionV);

		
		botBoxFB.setLocation(positionV.x-50*size, positionV.y-5 - 40 *size); // move bot collision box with bot animation
		botBoxGT.setLocation(positionV.x-40*size, positionV.y + 35*size);
		
		botBoxL.setLocation(positionV.x-50*size, positionV.y - 50 *size);
		botBoxR.setLocation(positionV.x + 45*size, positionV.y - 50 *size);
		botBoxT.setLocation(positionV.x+ 0-45*size, positionV.y - 60 *size);
		botBoxB.setLocation(positionV.x+ 0-40*size, positionV.y + 30 *size);

	
	}
	
	//------------
	
	public void collisionDeflection(GameContainer container,int delta)
	{
		pushObjectV = new Vector2f(0,0);
		inCollision = false;
		
		inAir = true;
		onGround = false;
		
		if (collisionR)
			pushObjectV.x = -1;
		
		if (collisionL)
			pushObjectV.x = 1;
		
		if (collisionD)
			pushObjectV.y = -1;

		if (collisionU)
			pushObjectV.y = 1;
		
		if (collisionGT)
			onGround = true;
		
		if (collisionL || collisionR || collisionU || collisionD)
			collisionFB = true;
		
		collisionU = false;
		collisionD = false;
		collisionL = false;
		collisionR = false;
		collisionFB = false;
		collisionGT = false;

		positionV.x += pushObjectV.x/10*delta;
		positionV.y += pushObjectV.y/10*delta;
				

		
		if (pushObjectV.y != 0 )
			directionV.y = 0;

		if (pushObjectV.x != 0)
			directionV.x = 0;
	}

		

//---------------------------------
	
	private void damping( int delta) {
		
		directionV = directionV.scale(1-damping*delta) ;
//		if (onGround)
//			directionV.y *= (1-damping);
		
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
		
		g.drawString("L " + collisionL, 10, 30); // print collision true/false
		g.drawString("R " + collisionR, 10, 45); // print collision true/false
		g.drawString("U: " + collisionU, 10, 60); // print inAir true/false
		g.drawString("D: " + collisionD, 10, 75); // print inAir true/false
		g.drawString("onGround: " + onGround, 10, 90); // print inAir true/false
		g.drawString("inAir: " + inAir, 10, 105); // print inAir true/false
		
//		g.drawString("variable: " + variable, 10, 60); // print inAir true/false
		
		
		g.setColor(Color.lightGray);
		g.draw(botBoxL);
		g.draw(botBoxR);
		g.draw(botBoxFB);
		g.draw(botBoxT);
		g.draw(botBoxB);
		g.draw(botBoxGT);


	}

}
