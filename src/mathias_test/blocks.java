package mathias_test;

public abstract class blocks extends entity{

	public int rep_x;
	public int rep_y;
	
	public blocks(int x_pos, int y_pos, int size, int rep_x, int rep_y) {
		super(x_pos, y_pos, size);
		this.rep_x = rep_x;
		this.rep_y = rep_y;	
		// TODO Auto-generated constructor stub
	}
	
	public abstract void reproduce ();
	
	
}
