package Rasmus_Test;

public class map { // this class will be used to call the function that will spawn the ground. 

	public static void buildground()
	{
		int groundY = 700; 
		int blocksize = 50; 
		int gapsizesmall = 4*blocksize; 
		int gapsizelarge = 3*blocksize; 
	
							// length of ground
		for( int i = 0; i <= 70*blocksize; i+=blocksize) // This loop will spawn the first part of the map. 70 tiles. 
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 
		}
		//System.out.println(lastbox);
					// putting in gapsize here creates the gap between the ground. 
		for( int i = (70*blocksize+gapsizesmall) ; i <= ((70*blocksize+gapsizesmall)+(15*blocksize)); i+=blocksize) 
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 	
		}
		
		for(int i = ((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall; i <= (((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall+(65*blocksize)); i+=blocksize)
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 
		}
		
		for(int i = (((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall+(65*blocksize))+gapsizelarge; i <= (((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall+(65*blocksize))+gapsizesmall+(60*blocksize); i+=blocksize)
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 
		}
	
	}
	
	public static void printrightfacetriangle(int posx, int posy, int size) // pos is self explanatory. Size is the size of the triangle.
	{

		for(int i=posx;i>=posx-(size*50);i-=50){
	        for(int j=posy; j>=i;j-=50){
	            blocks.draw(i, j, 50, 50);
	        	}
	    	}
	 }
	
	public static void printleftfacetriangle(int posx, int posy, int size) // Same here, this is just projected. 
	{

		for(int i=posx;i>=posx-(size*50);i-=50){
	        for(int j=posy; j<=i;j+=50){
	            blocks.draw(i, j, 50, 50);
	        	}
	    	}
	 }
	
		
		
//		int line = 1; 
//		while (line <= levels)
//		{
//			// posy+=50;
//			for ( int i = 1; i <= levels; i++)
//			{
//				blocks.draw(posx, posy, 50, 50);
//				posy-=50;
//				
//			}
//			line++; 
//			posx-=50;
//			
//		}
	}
	
//	public static void printFirstHalf(int m, int n){
//
//	    if(m==0){
//	        return;
//	    }
//
//	    //recursive step
//	    for(int i=m; i<=n; i++){
//	        System.out.print("*");
//	    }
//	    System.out.println();
//	    printFirstHalf(m-1,n);
	
//public static int buildtriangles(int length, int height)
//	{
//	 
//	 	if(height == 0)
//	 	{
//	 		return 0; 
//	 	}
//	 	
//	 	for( int i = height; i <= length;i++)
//	 	{
//	 		blocks.draw(length,height,50,50); 
//	 	} 
//	 	
//	 	return buildtriangles(length-50, height);
//		
//	}
//
//public static int buildtraingles2(int length, int height)
//	{
//		if(length > height )
//		{
//			return 0; 
//		}
//		
//		for( int i = 0 ; i <= length; i+=50)
//		{
//			blocks.draw(length, height, 50, 50);
//		}
//		
//		return buildtraingles2(length+50, height);
//	}
//
//public static int buildtraingles3(int posX, int posY, int height, int length)
//{
//	if(length < height)
//	{
//		return 0; 
//	}
//	for( int i = posY; i <= height; i+=50){
//	blocks.draw(posX, posY-i, 50, 50);
//	}
//	return buildtraingles3(posX-50, posY, height, length);
//}
	
	
		// Below is just a collection of garbage code that didnt work
		
//  		for( int i = 0; i < 3550; i+=50) // This loop will spawn the first part of the map. 70 tiles. 
//			{
//				blocks.draw(blocks.x_pos+i,groundY,50,50);
//				lastbox = i; 
//			}
//			System.out.println(lastbox);
//			lastbox+=150; 
//			
//			for( int i = lastbox; i < lastbox+750; i+=50) 
//			{
//				blocks.draw(blocks.x_pos+i,groundY,50,50); 
//				if(lastbox >= (lastbox + 650) ){
//				lastbox = i; 
//				}
//				
//			}
		
//		for( int i = 4650; i <= 4600 + 300; i +=50)
//		{
//			blocks.draw(blocks.x_pos+i,groundY,50,50); 	
//		}
//		
//		for( int i = 4650; i <= 4650 + 3750; i +=50)
//		{
//			blocks.draw(blocks.x_pos+i,groundY,50,50); 	
//		}
//		
//		for( int i = 8600; i <= 8400 + 3250; i +=50)
//		{
//			blocks.draw(blocks.x_pos+i,groundY,50,50); 	
//		}
		
//		for( int i = lastbox+150; i < 7500; i+=20) // Change the arguments from the previous one. i < lastbox +500 because is 500 from previous one. 
//			{
//				blocks.draw(blocks.x_pos+i,blocks.y_pos); 
//				//if(lastbox >= lastbox+500 ){
//				//lastbox = i; 
//				//}
//			}
		
//		for(int i = 5000; i < 5000 + 750; i+=50) //for(int i = lastbox; i < lastbox + 750; i+=50)
//		{
//			blocks.draw(blocks.x_pos+i,blocks.y_pos); 
//		}
	
//		lastbox+=250; 
//		for( int i = lastbox; i < lastbox+500; i+=50) // Change the arguments from the previous one. i < lastbox +500 because is 500 from previous one. 
//		{
//			blocks.draw(blocks.x_pos+i,blocks.y_pos); 
//			if(lastbox > lastbox+451 ){
//			lastbox = i; 
//			}
//		}
	
	
	
	

	


