package Mario_Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Enemies extends GameEntities {
	
	

// easy access picture locations
	public String pictureL = "animationBotL.png";
	public String pictureR = "animationBotR.png";
	public String pictureFall = "animationBotFall.png";
	
// sprite sheets and animations
	public SpriteSheet botSheetL;
	public Animation botAnimationL;
	public SpriteSheet botSheetR;
	public Animation botAnimationR;
	public SpriteSheet botSheetFall;
	public Animation botAnimationFall;
	
// shapes to use for collision
	public Circle botBoxFB; 
	public Rectangle botBoxTL;   // body Top
	public Rectangle botBoxTR;   // body Top
	public Rectangle botBoxB;   // body Bottom
	public Rectangle botBoxL;  // left
	public Rectangle botBoxR;  // right
	public Rectangle botBoxGT; // cheeck ground
	public Rectangle botBoxT; // cheeck ground
	
	
	public float x_pos = 300f;
	public float y_pos = 100f;
	private int standOnRectNr = 0;
	public boolean inAir = true;
	public boolean onGround = false;
	public boolean collisionEnabled = true;
	
// enemy variables
	public float speed = 0.6f;
	public float size = 1f;

	
	private float mouseLastX = 0f;
	private float mouseLastY = 0f;
	
// booleans for outer control
	public boolean collisionFB = false;  // body
	public boolean collisionB = false;
	public boolean collisionL = false;  // left
	public boolean collisionR = false;  // right
	public boolean collisionGT = false;  // Ground Check
	public boolean collisionTL = false;
	public boolean collisionTR = false;
	public boolean collisionT = false; 
	

	
	private Vector2f pushObjectV; // vector for storing which direction to push when collision
	
	private String direction = "Left"; // store which direction to go
	

	float screen_posX =0;  // store screen position
	float screen_posY =0;
	
	public Player mario;  // create an access to the player 
	
	public Enemies(float x_pos, float y_pos, Player mario) { // instantiate function
		super(x_pos, y_pos);
		this. x_pos = x_pos;
		this. y_pos = y_pos;
		this.mario = mario;
	}
	
	public void init(GameContainer container) throws SlickException { // instantiate function2
		
		initBot(container);  // initialize the enemy - animations etc
		
		botBoxFB = new Circle(0,0,43*size);  // initialize the shapes in the right size
		botBoxTL = new Rectangle(0,0,51*size, 50*size);
		botBoxTR = new Rectangle(0,0,51*size, 50*size);
		botBoxB = new Rectangle(0,0,104*size, 35*size);
		botBoxL = new Rectangle(0,0,10*size, 35*size);
		botBoxR = new Rectangle(0,0,10*size, 35*size);
		botBoxGT = new Rectangle(0,0,40*size, 10*size);
		botBoxT = new Rectangle(0,0,60*size, 10*size);
		
	}
	
	

	public void update(GameContainer container, int delta, float  screen_pos_x, float  screen_pos_y) throws SlickException {  // update and pass in the scene/screen position
		
		botAnimationL.setSpeed(speed); //  update the animation
		botAnimationL.update(delta);
		botAnimationR.setSpeed(speed);
		botAnimationR.update(delta);
		botAnimationFall.setSpeed(speed);
		botAnimationFall.update(delta);
		
		this.screen_posX = screen_pos_x;
		this.screen_posY = screen_pos_y;
		
		
		moveBot(delta, screen_pos_x, screen_pos_y); // move the enemy
		edgeTurn();  // turn the enemy when reached an edge
		collisionRepeller(delta); // make the enemy not go through the blocks
		marioCollision();   // handle collision with the player
		
	}
	

	public void render(GameContainer container, Graphics g) throws SlickException {
		
		
		drawBot(); // draw the enemy
		

	}


//--------------

	public static void main(String[] args) throws SlickException {
	
	}
	
//--------------------------------------------------------------------------
	
	public void initBot(GameContainer container) throws SlickException
	{
		botSheetL = new SpriteSheet(pictureL, 100, 100);  // apply the pictures to the sprites and sprites to the animations by size between each frame
		botAnimationL = new Animation(botSheetL, 230); // and time between each update
		
		botSheetR = new SpriteSheet(pictureR, 100, 100);
		botAnimationR = new Animation(botSheetR, 230);
		
		botSheetFall = new SpriteSheet(pictureFall, 100, 100);
		botAnimationFall = new Animation(botSheetFall, 230);
	}
	
	
	public void moveBot(int delta, float screen_pos_x, float screen_pos_y) // move the enemy
	{
		y_pos += 0.2f*delta;  // apply gravity
		
		botBoxFB.setLocation(x_pos+ screen_pos_x-43*size, y_pos+ screen_pos_y+(5-50)*size);  // move bot collision box with bot animation
		botBoxTL.setLocation(x_pos+ screen_pos_x-51*size, y_pos+ screen_pos_y+5-50*size);
		botBoxTR.setLocation(x_pos+ screen_pos_x-0*size, y_pos+ screen_pos_y+5-50*size);
		botBoxB.setLocation(x_pos+ screen_pos_x-54*size, y_pos+ screen_pos_y-5 + 10*size); 
		botBoxL.setLocation(x_pos+ screen_pos_x-5-58*size, y_pos+ screen_pos_y-5+50*size);
		botBoxR.setLocation(x_pos+ screen_pos_x-5+60*size, y_pos+ screen_pos_y-5+50*size);
		botBoxGT.setLocation(x_pos + screen_pos_x-20*size, y_pos + screen_pos_y + 35*size);
		botBoxT.setLocation(x_pos + screen_pos_x-30*size, y_pos + screen_pos_y - 60*size);
		
		if (direction == "Left" && onGround)
		{
			x_pos -= 0.2f * speed*size*delta;  // move bot left if direction == left
		}
		else if (direction == "Right" && onGround)
		{
			x_pos += 0.2f * speed*size*delta;  // right
		}
		
	}
	
//--------------
	
	public void collisionRepeller(int delta)
	{
		pushObjectV = new Vector2f(0,0); // reset pushed direction vector
		
		
		inAir = true;
		onGround = false;
		

		if (collisionB)  // set direction to push enemy according to which boolean is set true
		{
			pushObjectV.y = -1;
		}
		if (collisionGT)
		{
			onGround = true;
		}
			

		collisionL = false; // reset booleans
		collisionR = false;
		collisionB = false;
		collisionFB = false;
		collisionGT = false;

		if (collisionEnabled) // move the enemy ccording to the direction to push if collision is enebled
		{
			x_pos += (pushObjectV.x/3)*delta;
			y_pos += (pushObjectV.y/3)*delta;
		}
	}
	
//--------------	
	
	public void edgeTurn()
	{
	
		if (!collisionL && collisionR )  // check is the bools for the lower hit boxes are true, this is checked from the boxes
			direction = "Right";
			
		else if (!collisionR && collisionL) // if ex the right box is not touching a block - turn around!
			direction = "Left";
	
	}

	

	
//--------------
	
	public void drawBot()
	{
	
		if (!onGround) // draw animation for falling enemy if not on ground
		botAnimationFall.draw(x_pos+ screen_posX-50*size, y_pos+ screen_posY-50*size, 100 * size, 100*size);
		
		else if (direction == "Left") // animation for walk if on ground, and direction is Left
		botAnimationL.draw(x_pos+ screen_posX-50*size, y_pos+ screen_posY-50*size, 100 * size, 100*size);
		
		else if (direction == "Right") // right
		botAnimationR.draw(x_pos+ screen_posX-50*size, y_pos+ screen_posY-50*size, 100 * size, 100*size);

	}
	
//--------------
	public void changeDirection()  // for turning the direction
	{
		if (direction == "Right") 
			direction = "Left";
		else if (direction == "Left")
			direction = "Right";
	}
	
	
	public void dead()  // if dead
	{
		collisionEnabled = false;  // go through the floor
		Level1.points ++;   // add a point
		
	}
	
	public void showInfo (GameContainer container, Graphics g) throws SlickException  // for debugging
	{
		g.drawString("collides: " + collisionB, 10, 30); // print collision true/false
		g.drawString("on Ground: " + onGround, 10, 45); // print inAir true/false
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
		g.draw(botBoxGT);
		g.draw(botBoxT);
	}
	
	public void marioCollision(){  // check if hitting marios hitboxes
		
		if(botBoxFB.intersects(mario.botBoxT) && collisionEnabled){ // if hitting marios top, set marios bool collision U to true 
			mario.collisionU = true;
		}

		if(botBoxFB.intersects(mario.botBoxB) && collisionEnabled){
			mario.collisionD = true;
		}
		if(botBoxFB.intersects(mario.botBoxL) && collisionEnabled){
			mario.collisionL = true;
			mario.hitByEnemy = true;
		}
		
		if(botBoxFB.intersects(mario.botBoxR) && collisionEnabled){
			mario.collisionR = true;
			mario.hitByEnemy = true;
		}
		if(botBoxFB.intersects(mario.botBoxGT) && collisionEnabled){
			mario.collisionGT = true;
		}
		
		if(botBoxT.intersects(mario.botBoxB ) && collisionEnabled){  // if hit from the top 
			
			mario.collisionD = true; 	// tell the player
			mario.jump=true; 			// make the player bounce
			dead();  					// turn dead
		}
		
	}
	
  

}
