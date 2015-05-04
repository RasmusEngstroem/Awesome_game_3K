package mathias_test;

public class blocks extends entity{

	public int rep_x;
	public int rep_y;
	
	public blocks(int x_pos, int y_pos, int rep_x, int rep_y) {
		super(x_pos, y_pos);
		this.rep_x = rep_x;
		this.rep_y = rep_y;	
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the number of blocks similar to this one that should be placed next to this one.
	 * @return the number of similar blocks that should be placed next to this one
	 */
	public int getRep_x() {
		return rep_x;
	}

	/**
	 * Change the number of blocks similar to this one that should be placed next to this one.
	 * @param rep_x the number of similar blocks that should be placed next to this one
	 */
	public void setRep_x(int rep_x) {
		this.rep_x = rep_x;
	}

	/**
	 * Returns the number of blocks similar to this one that should be placed on top of this one.
	 * @return the number of similar blocks that should be placed on top of this one
	 */
	public int getRep_y() {
		return rep_y;
	}

	public void setRep_y(int rep_y) {
		this.rep_y = rep_y;
	}
	
	/*
	 * public void main(){
	 * 
	 * //mangler en width i know men det virker når den kommer... tror jeg...
	 * 
	 * 	for(int i = 0; i < this.rep_x; i++){
	 * 		blocks block = new blocks(x_pos + width * i, y_pos, 0, 0);	
	 * 	}
	 * 
	 * 	for(int i = 0; i < this.rep_y; i++){
	 * 		blocks block = new blocks(x_pos, y_pos + width * i, 0, 0);
	 * 	}
	 * }
	 * 
	 */

}
