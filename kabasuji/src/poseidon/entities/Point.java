package poseidon.entities;

/**
 * Handles points, which are the elements pieces are constructed out of.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class Point {
	int row, col;
	
	public Point (int row, int col) {
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
	
	/**
	 * Overrides the standard equals() method for point.
	 * 
	 * Note: Checks if the point has the same value, not if it's pointing to the same exact point.
	 */
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Point)) { return false; }
		Point point = (Point) o;
		return (this.row == point.getRow() && this.col == point.getCol());
	}
	
	@Override
	public int hashCode() {
		return row + col * 31;
	}
	
	
	/**
	 *  Overrides the standard toString() method for Point.
	 */
	@Override
	public String toString()
	{
		return "(" + row + ", " + col + ")";
	}
	/***********************
	 *  Getters & Setters  *
	 ***********************/
	public int getRow()
	{
		return this.row;
	}
	public int getCol()
	{
		return this.col;
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
