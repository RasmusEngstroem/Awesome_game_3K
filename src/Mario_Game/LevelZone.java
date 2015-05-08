package Mario_Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class LevelZone extends GameEntities {

	public float width;
	public float height;
	public Rectangle boxShape;
	public Player mario;
	
	
	public LevelZone(float x_pos, float y_pos, float width, float height, Player mario) {
		super(x_pos, y_pos);
		this.width = width;
		this.height = height;
		boxShape = new Rectangle(x_pos, y_pos, width, height);
		this.mario = mario;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float x_pos, float y_pos){
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		
		boxShape.setCenterX(x_pos);
		boxShape.setCenterY(y_pos);
		checkIfInsideLevel();
	}
	
	public void render(Graphics g){
		g.setColor(Color.magenta);
		g.fill(boxShape);
		g.draw(boxShape);
		
	}
	
	public void checkIfInsideLevel(){
		if(!boxShape.intersects(mario.botBoxFB)){
			Level1.lives = -1;
		}
	}
	

}
