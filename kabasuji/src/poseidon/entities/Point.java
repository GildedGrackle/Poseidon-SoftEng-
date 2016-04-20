package poseidon.entities;

/**
 * Handles points, which are the elements pieces are constructed out of.
 * @author Natalia
 *
 */
public class Point {
	int row, col;
	
	Point (int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Flips the axis of the point
	 */
	void flip () {
		int temp;
		temp = this.row;
		this.row = this.col;
		this.col = temp;
	}
	
	@Override
	/**
	 * Overrides the standard equals() method for point.
	 * 
	 * Note: Checks if the point has the same value, not if it's pointing to the same exact point.
	 */
	public boolean equals(Object o){
		if (!(o instanceof Point)) { return false; }
		Point point = (Point) o;
		if(this.row == point.getRow() && this.col == point.getCol()) { return true; }
		return false;
	}
	/***********************
	 *  Getters & Setters  *
	 ***********************/
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	public void setRow(int row)
	{
		this.row = row;
	}
	public void setCol(int col)
	{
		this.col = col;
	}
	
}
