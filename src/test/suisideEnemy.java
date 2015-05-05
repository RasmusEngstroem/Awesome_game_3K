package test;

public class suisideEnemy extends enemy {

	public suisideEnemy(String title, int positionX, int positionY) {
		super(title, positionX, positionY);
		
		this.size = 0.7f;  // change size at start
		this.speed = 1.2f;
		
	}
	
	public void edgeTurn()
	{
		
	}

}
