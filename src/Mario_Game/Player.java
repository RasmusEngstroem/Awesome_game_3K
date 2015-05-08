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



public class Player extends GameEntities{

	public String pictureL = "Assets/botPlayerL.png";
	public String pictureR = "Assets/botPlayerR.png";
	public String pictureFall = "Assets/botPlayerF.png";
	
	public SpriteSheet botSheetL;
	public Animation botAnimationL;
	public SpriteSheet botSheetR;
	public Animation botAnimationR;
	public SpriteSheet botSheetFall;
	public Animation botAnimationFall;
	
	
	public Rectangle[] boxes;
//	public hitBoxes[] boxes;
//	public boxTest box = new boxTest("2");
	
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
	
	
	public float size = 0.95f;
	public float speed = 2f;
	public float jumpHeight = 0.8f;
//	public float dampingAir = 0.002f;
	public float damping = 1.01f;
	public float gravity = 0.01f;
	
	public float screen_pos_x;
	public float screen_pos_y;
	
	
	public float positionX = 100;
	public float positionY = 50;
		
	public Vector2f positionV;
	public Vector2f directionV;


	Vector2f pushObjectV = new Vector2f(0,0);
	Vector2f collisionV = new Vector2f(0,0);
	private boolean inAir = true;
	private boolean onGround = false;
	private boolean inCollision = false;
	public boolean jump = false;
	private int standOnRectNr = 0;
	
	Vector2f collisionVPrint = new Vector2f(0,0); 
	float variable = 0;
	
	boolean collisionL = false;
	boolean collisionR = false;
	boolean collisionU = false;
	boolean collisionD = false;
	boolean collisionGT = false;
	
	public boolean alive = true;
	public boolean hitByEnemy = false;
	boolean immortal = false;
	float timer = 4;
	int delta = 0;
	
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


	public void update(GameContainer container, int delta,float screen_pos_x, float screen_pos_y) throws SlickException {
		
		
		
		botAnimationL.setSpeed(speed * (1-directionV.x));
		botAnimationL.update(delta);
		botAnimationR.setSpeed(speed* (1+ directionV.x));
		botAnimationR.update(delta);
		botAnimationFall.setSpeed(speed);
		botAnimationFall.update(delta);
		this.screen_pos_x = screen_pos_x;
		this.screen_pos_y = screen_pos_y;

		if (!alive)
		death();
	
		movePlayer(container,delta);
		collisionDeflection(container,delta);
		damping(delta);
		
		if (hitByEnemy)
			looseLive(delta);
	
		
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
			directionV.x -= (speed/500)*delta;
		
		if(input.isKeyDown(Input.KEY_D))
			directionV.x += (speed/500)*delta;
		
		if(input.isKeyDown(Input.KEY_W) && onGround && pushObjectV.x == 0 && pushObjectV.y !=0 )
			{
//			positionV.y -= 20;
			directionV.y -= jumpHeight*delta;
			}
		
		if (jump)
		{
			directionV.y += jumpHeight*13;
			jump = false;
		}

		positionV.add(directionV);
//		positionV.x+=screen_pos_x;
//		positionV.y+=screen_pos_y;
		
		x_pos = botBoxFB.getCenterX();
		y_pos = botBoxFB.getCenterY();
		
		botBoxFB.setLocation(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-5 - 40 *size); // move bot collision box with bot animation
		botBoxGT.setLocation(positionV.x + screen_pos_x-40*size, positionV.y + screen_pos_y + 35*size);
		
		botBoxL.setLocation(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y - 50 *size);
		botBoxR.setLocation(positionV.x + screen_pos_x+ 45*size, positionV.y + screen_pos_y - 50 *size);
		botBoxT.setLocation(positionV.x + screen_pos_x+ 0-45*size, positionV.y + screen_pos_y - 60 *size);
		botBoxB.setLocation(positionV.x + screen_pos_x+ 0-40*size, positionV.y + screen_pos_y + 30 *size);

	
	}
	
	//------------
	
	public void collisionDeflection(GameContainer container,int delta)
	{
		pushObjectV = new Vector2f(0,0);
		inCollision = false;
		
		inAir = true;
		onGround = false;
		
		if (collisionR)
		{
			pushObjectV.x = -1;
			directionLock("x", -1);
		}
		
		if (collisionL)
		{
			pushObjectV.x = 1;
			directionLock("x", 1);
		}
		
		if (collisionD)
		{
			pushObjectV.y = -1;
			directionLock("y", -1);
		}
		

		if (collisionU)
		{
			pushObjectV.y = 1;
			directionLock("y", 1);
		}
		
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

		if (collisionEnabled)
		{
			positionV.x += (pushObjectV.x/5)*delta;
			positionV.y += (pushObjectV.y/5)*delta;
			

		
//		if (pushObjectV.y != 0 )
//			directionV.y = 0;

//		if (pushObjectV.x != 0)
//			directionV.x = 0;
		}
	}

		

//---------------------------------
	
	private void damping( int delta) {
		
//		directionV = directionV.scale(1-damping/delta) ;
		directionV.x /= (damping);
		directionV.y /= (damping);
//		float a = 0.0015f;
//		float b = 0.003f;
//		
//		if (directionV.x > 0.01f)
//			directionV.x -= a*delta;
//		else if (directionV.x < -0.01f)
//			directionV.x += a*delta;
//		else
//			directionV.x = 0;
//		
//		if (directionV.y > 0.01f)
//			directionV.y -= b*delta;
//		else if (directionV.y < -0.01f)
//			directionV.y += b*delta;
//		else
//			directionV.y = 0;
		if (onGround)
			directionV.y /= (damping);
		
	}
	
//---------------------------------	
	
	public void initPlayer(GameContainer container) throws SlickException
	{
		botSheetL = new SpriteSheet(pictureL, 100, 100);
		botAnimationL = new Animation(botSheetL, 700);
		
		botSheetR = new SpriteSheet(pictureR, 100, 100);
		botAnimationR = new Animation(botSheetR, 700);
		
		botSheetFall = new SpriteSheet(pictureFall, 100, 100);
		botAnimationFall = new Animation(botSheetFall, 700);
	}
	
//---------------------------------
	
	
	
	public void drawPlayer()
	{
	
		if ( !onGround)
		botAnimationFall.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
		else if (directionV.x < -0.005f)
		botAnimationL.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
		else if (directionV.x > 0.005f)
		botAnimationR.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
		else
			botAnimationFall.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
	}
	
	
	void directionLock(String lockDirection, int direction)
	{
		if (lockDirection == "x")
		{
			if (directionV.x < 0f && direction == 1)
				directionV.x*=-1*0.3f;
			
			if (directionV.x > 0f && direction == -1)
				directionV.x*=-1*0.3f;

			}

		else if (lockDirection == "y")
		{
			if (directionV.y < 0f && direction == 1)
				directionV.y*=-1*0.3f;
			
			if (directionV.y > 0f && direction == -1)
				directionV.y*=-1*0.3f;

			
		}
		
	}
	
	public void showInfo (GameContainer container, Graphics g) throws SlickException  // for debugging
	{
		g.setColor(Color.lightGray);
		
		g.drawString("L " + collisionL, 10 + positionV.x , 30); // print collision true/false
		g.drawString("R " + collisionR, 10+ positionV.x, 45); // print collision true/false
		g.drawString("U: " + collisionU, 10+ positionV.x, 60); // print inAir true/false
		g.drawString("D: " + collisionD, 10+ positionV.x, 75); // print inAir true/false
		g.drawString("looseLive: " + hitByEnemy, 10+ positionV.x, 90); // print inAir true/false
		g.drawString("positionX " + positionV.x, 10, 105); // print inAir true/false
		
//		g.drawString("variable: " + variable, 10, 60); // print inAir true/false
		
		
		g.setColor(Color.lightGray);
//		g.draw(botBoxL);
//		g.draw(botBoxR);
		g.draw(botBoxFB);
//		g.draw(botBoxT);
//		g.draw(botBoxB);
//		g.draw(botBoxGT);


	}
	
	private void death()
	{
		collisionEnabled = false;
		Level1.moveLevel = false;
	}
	
	private void looseLive(int delta)
	{
		
		if (!immortal)
		{
			Level1.lives --;
			immortal = true;
		}
		if (immortal)
			timer -= 0.001f*delta;
			
		if (timer <= 0) 
		{
			timer = 4;
			immortal = false;
			hitByEnemy = false;
		}
			
		
	}
	

}
