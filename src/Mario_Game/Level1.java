package Mario_Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import test.setupClass;

//-----

public class Level1 extends level{

	
	
//------ SETUP --------------------------------------------


	
public Level1(String title) {
		super(title);
	
	}


	//------ INIT --------------------------------------------
	public void sendToInit(GameContainer container) throws SlickException{
		background = new Image("Assets/backgroundWater.jpg");
		
	}

	
//------ UPDATE --------------------------------------------
	public void sendToUpdate(GameContainer container, int delta) throws SlickException {
		

		
	}
//------ RENDER --------------------------------------------
	public void sendToRender(GameContainer container, Graphics g) throws SlickException {
		
		background.draw(x_posLevel/2, y_posLevel/2 - background.getTextureHeight()/4);

	}

}
