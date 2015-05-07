package Rasmus_Test;

import org.newdawn.slick.Image;

public class map { // this class will be used to call the function that will spawn the ground. 
	
	
	
	
	
	public static void buildground()
	{
		int startingpoint = blocks.x_pos;
		int lastbox = 0; 
		
		
		for( int i = 0; i < 500; i+=50) // Det her loop spawner bare lange sektioner. 
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos);
			lastbox = i; 
		}
	
		lastbox+=150; //+= 150 laver et hul i ground som man kan falde igennem. 
		
		for( int i = lastbox; i < lastbox+500; i+=50) // Change the arguments from the previous one. i < lastbox +500 because is 500 from previous one. 
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos); 
			if(lastbox > lastbox+451 ){
			lastbox = i; 
			}
		}
		lastbox+=250; 
		for( int i = lastbox; i < lastbox+500; i+=50) // Change the arguments from the previous one. i < lastbox +500 because is 500 from previous one. 
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos); 
			if(lastbox > lastbox+451 ){
			lastbox = i; 
			}
		}
	
	}
	
	public static void buildplatforms()
	{
		 
	}

	

}
