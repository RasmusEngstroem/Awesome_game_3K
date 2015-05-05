package Mario_Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Unbreakable extends Blocks {

	public Unbreakable(String title, int x_pos, int y_pos, Image texture,
			int rep_x, int rep_y) {
		super(title, x_pos, y_pos, texture, rep_x, rep_y);
		// TODO Auto-generated constructor stub
	}

	public void render(){
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);	
		g.drawImage(texture, x_pos, y_pos);
		texture.draw();
	}
	
	public void draw(){
		texture.draw(x_pos,y_pos);
	}
	

}
