package Rasmus_Test;

import org.newdawn.slick.Image;

public class map {
	
	public static void buildground()
	{
		int startingpoint = blocks.x_pos;
		for( int i = 0; i < 1000; i+=50)
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos);
		}

	}
	
	

	

}
