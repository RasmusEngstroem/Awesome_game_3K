package Mario_Game;


// used libraries
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;



public class Player extends GameEntities{  // player class, which inheres game entities

	public String pictureL = "botPlayerL.png";  // save picture names as strings for easy access
	public String pictureR = "botPlayerR.png";  // pictures for the sprite sheets
	public String pictureFall = "botPlayerF.png";
	
	public SpriteSheet botSheetL;  // make space for the sprite sheets for the animation
	public Animation botAnimationL;  // the animations
	public SpriteSheet botSheetR;
	public Animation botAnimationR;
	public SpriteSheet botSheetFall;
	public Animation botAnimationFall;
	
	//The shapes used for the collision
	public Rectangle botBoxFB;   // Full Body
	public Rectangle botBoxT;   // body Top
	public Rectangle botBoxB;   // body Bottom
	public Rectangle botBoxL;  // left
	public Rectangle botBoxR;  // right
	public Rectangle botBoxGT;  // ground check
	

	
	// player variables
	public float size = 0.95f;
	public float speed = 2f;
	public float jumpHeight = 0.8f;
	public float damping = 1.01f;
	public float gravity = 0.01f;
	
	public float screen_pos_x;  // for locally storing the screen position
	public float screen_pos_y;

	public float positionX = 100;
	public float positionY = 50;
		
	public Vector2f positionV;  // vector storing the position
	public Vector2f directionV;  // vector storing the direction to be added to the position


	Vector2f pushObjectV = new Vector2f(0,0);  // vector for storing in which direction the inputs from outside pushes the player
	Vector2f collisionV = new Vector2f(0,0);
	private boolean onGround = false;
	public boolean jump = false;

	Vector2f collisionVPrint = new Vector2f(0,0); 
	float variable = 0;
	
	
	// the booleans for controlling the inputs from the enemies and bricks
	public boolean collisionFB = false; 
	private boolean collisionEnabled = true;
	
	boolean collisionL = false;
	boolean collisionR = false;
	boolean collisionU = false;
	boolean collisionD = false;
	boolean collisionGT = false;
	
	public boolean alive = true;
	public boolean hitByEnemy = false;
	boolean immortal = false;
	
	// for controlling the loose life function
	float timer = 2;
	int delta = 0;
	
	
	public Player(float x_pos, float y_pos, GameContainer container) {
		super(x_pos, y_pos);
		positionY = y_pos;
		positionX = x_pos;
		// TODO Auto-generated constructor stub
	}
	



	public void init(GameContainer container) throws SlickException { // separate initial function for if different players

		initPlayer(container); // stores the function for initializing the player - animations etc


		positionV = new Vector2f(positionX, positionY); // store the position from the instance input
		directionV = new Vector2f(0,0);
		
		botBoxL = new Rectangle(0,0,10*size, 85*size);// set size and position of the collision boxes
		botBoxR = new Rectangle(0,0,10*size, 85*size);
		botBoxT = new Rectangle(0,0,85*size, 10*size);
		botBoxB = new Rectangle(0,0,85*size, 10*size);
		botBoxFB = new Rectangle(0,0,100*size, 85*size);
		botBoxGT = new Rectangle(0,0,80*size, 10*size);

		
	}


	public void update(GameContainer container, int delta,float screen_pos_x, float screen_pos_y) throws SlickException {
		
		
		botAnimationL.setSpeed(speed * (1-directionV.x)); // set speed of animation to follow speed of player
		botAnimationL.update(delta); // update independent of screen updates
		botAnimationR.setSpeed(speed* (1+ directionV.x));
		botAnimationR.update(delta);
		botAnimationFall.setSpeed(speed);
		botAnimationFall.update(delta);
		this.screen_pos_x = screen_pos_x;  // update the position
		this.screen_pos_y = screen_pos_y;

		if (!alive) // start the death function if the alive bool is set to false from outside or inside
		death();
	
		movePlayer(container,delta); // move the player - gravity - add the direction vector
		collisionDeflection(container,delta); // puch player around according to what hits him
		damping(delta); // damp the speed
		
		if (hitByEnemy)  // start loosing life function if hit by enemy bool is set to true
			looseLive(delta);
	
		
	}
	
	
	public void render(GameContainer container, Graphics g) throws SlickException {

		drawPlayer();  // draw player function

	}


	
//---------------------------------------------------------------------

	public void movePlayer(GameContainer container,int delta)
	{
		Input input = container.getInput();
		
		directionV.y += gravity*delta;  // add gravity to direction

		if(input.isKeyDown(Input.KEY_A))  // check if the keys are pressed
			directionV.x -= (speed/500)*delta;  // move the player according to speed
		
		if(input.isKeyDown(Input.KEY_D))
			directionV.x += (speed/500)*delta;
		
		if(input.isKeyDown(Input.KEY_W) && onGround && pushObjectV.x == 0 && pushObjectV.y !=0 )
			{
			directionV.y -= jumpHeight*delta;
			}
		
		if (jump)  // if jump is set true from outer place - here the enemies - make the layer jump/throw up in the air
		{
			directionV.y += jumpHeight*13;
			jump = false;
		}

		positionV.add(directionV); // add direction to the position

		
		x_pos = botBoxFB.getCenterX();  // update the inherited position vector
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
		pushObjectV = new Vector2f(0,0);  // reset the vector for storing the direction to push

		onGround = false;
		
		if (collisionR)  // if the collision booleans are set true from other classes
		{
			pushObjectV.x = -1; // set the direction to push
			directionLock("x", -1);  // make the direction vector go uppersite direction - bounce
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
		
		collisionU = false; // reset to false
		collisionD = false;
		collisionL = false;
		collisionR = false;
		collisionFB = false;
		collisionGT = false;

		if (collisionEnabled)  // if collision is enabled
		{
			positionV.x += (pushObjectV.x/5)*delta; // push in the desired direction
			positionV.y += (pushObjectV.y/5)*delta;
			

		}
	}

		

//---------------------------------
	
	private void damping( int delta) { // the damping of the direction vector
		
		directionV.x /= (damping);
		directionV.y /= (damping);
		
		if (onGround)
			directionV.y /= (damping); // extra damping on ground
		
	}
	
//---------------------------------	
	
	public void initPlayer(GameContainer container) throws SlickException  // apply the pictures to the sprite sheets and apply the sprites to the animations
	{
		botSheetL = new SpriteSheet(pictureL, 100, 100);
		botAnimationL = new Animation(botSheetL, 700);
		
		botSheetR = new SpriteSheet(pictureR, 100, 100);
		botAnimationR = new Animation(botSheetR, 700);
		
		botSheetFall = new SpriteSheet(pictureFall, 100, 100);
		botAnimationFall = new Animation(botSheetFall, 700);
	}
	
//---------------------------------
	
	
	
	public void drawPlayer()  // draw the player in a render function
	{
	
		if ( !onGround) // if in the air - show fall/jump animation
		botAnimationFall.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
		else if (directionV.x < -0.005f) // if direction against left - play walking left animation
		botAnimationL.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
		else if (directionV.x > 0.005f) // right
		botAnimationR.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
		else   // else idle animation
			botAnimationFall.draw(positionV.x + screen_pos_x-50*size, positionV.y + screen_pos_y-50*size, 100 * size, 100*size);
		
	}
	
	
	void directionLock(String lockDirection, int direction) // set the axis to lock and in what direction
	{
		if (lockDirection == "x")
		{
			if (directionV.x < 0f && direction == 1)  // always turn direction vector positive x
				directionV.x*=-1*0.3f;
			
			if (directionV.x > 0f && direction == -1)  //negative
				directionV.x*=-1*0.3f;

			}

		else if (lockDirection == "y")  //y
		{
			if (directionV.y < 0f && direction == 1)
				directionV.y*=-1*0.3f;
			
			if (directionV.y > 0f && direction == -1)
				directionV.y*=-1*0.3f;

			
		}
		
	}
	
	public void showInfo (GameContainer container, Graphics g) throws SlickException  // for debugging - can be called when rendered
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
	
	private void death()  // if dead
	{
		collisionEnabled = false; // fall through ground
		Level1.moveLevel = false; // stop the level from moving
	}
	
	private void looseLive(int delta)  // for loosing life 
	{
		
		if (!immortal)
		{
			Level1.lives --; // loose one life and turn immortal
			immortal = true;
		}
		if (immortal)
			timer -= 0.001f*delta;  // as long as the timer runs
			
		if (timer <= 0)  // when timer runs out
		{
			timer = 2;         // reset timer 
			immortal = false;  // turn mortal
			hitByEnemy = false;  // reset hit boolean
		}
			
		
	}
	

}
