package poseidon.entities;

public class Point {
	int row, col;
	
	Point (int row, int col) {
		this.row = row;
		this.col = col;
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
