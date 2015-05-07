package Mario_Game;



import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class boxTest extends BasicGame{

	public Rectangle hitBox;
	public float pos_x = 0;
	public float pos_y = 0;
	
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
		hitBox = new Rectangle(pos_x, pos_y, 100, 100);
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	
	public boxTest(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		g.setColor(Color.lightGray);
		g.fill(hitBox);
		
	}



	
	
	
}
