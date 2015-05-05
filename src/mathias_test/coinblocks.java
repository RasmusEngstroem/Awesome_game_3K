package mathias_test;

public class coinblocks extends breakable {

	public coinblocks(int x_pos, int y_pos, int size, int rep_x, int rep_y) {
		super(x_pos, y_pos, size, rep_x, rep_y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reproduce() {
		for(int i = 0; i<rep_x; i++){
			coinblocks repcoinblock = new coinblocks(x_pos+size*i, y_pos, size, 0, 0);
		}
		for(int i = 0; i<rep_y; i++){
			coinblocks repcoinblock = new coinblocks(x_pos, y_pos+size*i, size, 0, 0);
		}
		// TODO Auto-generated method stub
		
	}

}
