package Rasmus_Test;

public class map { // this class will be used to call the function that will spawn the ground. 

	public static void buildground()
	{
		int groundY = 700; 
		int blocksize = 50; 
		int gapsizesmall = 200; 
		int gapsizelarge = 150; 
	
		
		for( int i = 0; i <= 70*blocksize; i+=50) // This loop will spawn the first part of the map. 70 tiles. 
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 
		}
		//System.out.println(lastbox);
		
		for( int i = (70*blocksize+gapsizesmall) ; i <= ((70*blocksize+gapsizesmall)+(15*blocksize)); i+=50) 
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 	
		}
		
		for(int i = ((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall; i <= (((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall+(65*blocksize)); i+=50)
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 
		}
		
		for(int i = (((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall+(65*blocksize))+gapsizelarge; i <= (((70*blocksize+gapsizesmall)+(15*blocksize))+gapsizesmall+(65*blocksize))+gapsizesmall+(60*blocksize); i+=50)
		{
			blocks.draw(blocks.x_pos+i,blocks.y_pos,50,50); 
		}
	
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
	
 static int buildtriangles(int length, int height)
	{
	 
	 	if(height == 0)
	 	{
	 		return 0; 
	 	}
	 	
	 	for( int i = height; i <= length;i++)
	 	{
	 		blocks.draw(length,height,50,50); 
	 	} 
	 	
	 	return buildtriangles(length-1, height);
		
	}
		
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
	
	
	
	

	

}
