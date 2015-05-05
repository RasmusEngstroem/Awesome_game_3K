package test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class smallEnemy extends enemy {
	
	int test = 0;
	
	public smallEnemy(String title, int positionX, int positionY) {  // initialize
		super(title, positionY, positionY);
		
		this.size = 0.5f;  // change size at start
		this.speed = 1.7f;
		
		this.pictureL = "pictures/animationBotLO.png";
		this.pictureR = "pictures/animationBotRO.png";
		this.pictureFall = "pictures/animationBotFallO.png";
		
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException { // overrider the inherited render function
		
		this.drawBot();
		
	}




	
	
}
