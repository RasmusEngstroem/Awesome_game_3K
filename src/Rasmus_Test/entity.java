package Rasmus_Test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public /*abstract*/ class entity {

	public int x_pos;
	public int y_pos;
	public int size;
	
	public Image texture;
	
	public entity(int x_pos, int y_pos, int x_size, int y_size, Image thistexture){
		this.x_pos = x_pos;		
		this.y_pos = y_pos;
		this.texture = thistexture; 
		//texture.draw(x_pos, y_pos, x_size, y_size);
	}
	
//	public void givetextures() throws SlickException
//	{
//		texture = new Image("Assets/marioblock.png");
//		texture2 = new Image("Assets/marioblock2.png");
//	}
	
	
	
}
