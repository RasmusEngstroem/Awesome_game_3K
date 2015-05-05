package test;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class suisideEnemy extends enemy {

	public suisideEnemy(String title, int positionX, int positionY) {
		super(title, positionX, positionY);
		
		this.size = 0.7f;  // change size at start
		this.speed = 1.2f;
		
		this.pictureL = "pictures/animationBotLG.png";
		this.pictureR = "pictures/animationBotRG.png";
		this.pictureFall = "pictures/animationBotFallG.png";
		
	}



	public void edgeTurn()
	{
		
	}

}
