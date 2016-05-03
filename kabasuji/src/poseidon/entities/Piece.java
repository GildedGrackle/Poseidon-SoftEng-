package poseidon.entities;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Handles pieces that are placed on the board/bullpen.
 * @author Natalia
 */
public class Piece {
	/** The 6 points that construct a piece.*/
	Point [] piece = new Point[6];
	
	/** The color of the constituent squares of this piece. */
	Color pieceColor;
	
	/** The color of the edges of the constituent squares of this piece. */
	Color pieceBorder;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param piece  the Points that make up this Piece's shape
	 */
	public Piece(Point[] piece) {
		Set<Point> myPoints = new HashSet<>(Arrays.asList(piece));
		if (myPoints.size() != 6) {
			throw new IllegalArgumentException("Piece must have 6 points");
		}
		this.piece = piece;
		
		// Pick a random color for the Piece
		switch(new Random().nextInt(5))
		{
		case 0:
			pieceColor = Color.yellow;
			pieceBorder = Color.orange;
			break;
		case 1:
			pieceColor = Color.pink;
			pieceBorder = Color.red;
			break;
		case 2:
			pieceColor = Color.cyan;
			pieceBorder = Color.blue;
			break;
		case 3:
			pieceColor = Color.green;
			pieceBorder = Color.darkGray;
			break;
		case 4:
			pieceColor = Color.orange;
			pieceBorder = Color.red;
			break;
		}
	}
	
	
	/**
	 *  Constructor for random pieces
	 */
	public Piece() {
		PieceFactory factory = new PieceFactory();
		this.piece = factory.getRandomPiece().getPiece();
		
		// Pick a random color for the Piece
		switch(new Random().nextInt(5))
		{
		case 0:
			pieceColor = Color.yellow;
			pieceBorder = Color.orange;
			break;
		case 1:
			pieceColor = Color.pink;
			pieceBorder = Color.red;
			break;
		case 2:
			pieceColor = Color.cyan;
			pieceBorder = Color.blue;
			break;
		case 3:
			pieceColor = Color.green;
			pieceBorder = Color.darkGray;
			break;
		case 4:
			pieceColor = Color.orange;
			pieceBorder = Color.red;
			break;
		}
	}

	
	/**
	 * Finds the Bottom left point of the piece (even if isn't a part of it) and returns it.
	 * 
	 * Note: Private function that helps with rotating the pieces.
	 * @return Point - The Bottom left point of the piece.
	 */
	private Point findBotLeft() {
		
		int botRow = piece[0].getRow();
		int temp;
		
		for (int i=1; i < piece.length; i++) {			//Finds the largest row value
			temp = piece[i].getRow();
			if (temp > botRow) {
				botRow = temp;
			}
		}
		Point botLeft = new Point(botRow,0);			//Returns the point in the 0 col with the highest row
		return botLeft;
	}
	
	/**
	 * Finds the top right point of the piece (even if isn't a part of it) and returns it.
	 * 
	 * Note: Private function that helps with rotating the pieces.
	 * @return Point - The top right point of the piece.
	 */
	private Point findTopRight() {
		
		int rightCol = piece[0].getCol();
		int temp;
		
		for (int i=1; i < piece.length; i++) {			//Finds the largest col value
			temp = piece[i].getCol();
			if (temp > rightCol) {
				rightCol = temp;
			}
		}
		Point topRight = new Point(0, rightCol);			//Returns the point in the 0 row with the highest col
		return topRight;
	}
	
	/**
	 * Rotates the piece Clockwise
	 */
	public void rotateCW() {
		Point newPivotPoint;
		int row, col;		
		newPivotPoint = findBotLeft();				//Finding the bottom left piece as the new pivot
		newPivotPoint.flip();						//Flips the axis on the pivot point
																														
		for (int i=0; i< piece.length; i++) {
			piece[i].flip();						//Flips the axis on the point			
			row = newPivotPoint.getRow() + piece[i].getRow();	//finding the piece's new row/col
			col = newPivotPoint.getCol() - piece[i].getCol();
			piece[i].setRow(row);
			piece[i].setCol(col);
		}
		
	}
	
	/**
	 * Rotates the piece counter Clockwise
	 */
	public void rotateCCW() {
		Point newPivotPoint;
		int row, col;		
		newPivotPoint = findTopRight();				//Finding the top right piece as the new pivot
		newPivotPoint.flip();						//Flips the axis on the pivot point
																														
		for (int i=0; i< piece.length; i++) {
			piece[i].flip();						//Flips the axis on the point			
			row = newPivotPoint.getRow() - piece[i].getRow();	//finding the piece's new row/col
			col = newPivotPoint.getCol() + piece[i].getCol();
			piece[i].setRow(row);
			piece[i].setCol(col);
		}
		
	}

	/**
	 * Horizontally flips the piece
	 */
	public void flipHorizontal() {
		Point newPivotPoint;
		int col;
		
		newPivotPoint = findTopRight();				//Finding the top right piece as the new pivot
		
		for(int i=0; i<piece.length; i++) {
			col = newPivotPoint.getCol() - piece[i].getCol();
			piece[i].setCol(col);
		}
	}
	
	/**
	 *  Vertically flips the piece
	 */
	public void flipVertical() {
		Point newPivotPoint;
		int row;
		
		newPivotPoint = findBotLeft();				//Finding the top right piece as the new pivot
		
		for(int i=0; i<piece.length; i++) {
			row = newPivotPoint.getRow() - piece[i].getRow();
			piece[i].setRow(row);
		}
	}
	
	@Override
	/**
	 * Overrides the standard equals() method for Pieces.
	 *  
	 * Disregards the container. Thus, comparing two pieces that are in different locations but "look" the same
	 * would produce true as the answer.
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Piece)) { return false; }
		Piece otherPiece = (Piece) o;
		Set<Point> myPoints = new HashSet<>(Arrays.asList(this.piece));
		Set<Point> otherPoints = new HashSet<>(Arrays.asList(otherPiece.getPiece()));
		if (myPoints.equals(otherPoints)) {return true;}
		return false;
	}
	
	/**
	 * Checks if a piece is a rotated variation of a piece it's compared to.
	 * 
	 * @param o  the object being compared to
	 * @return  Whether the piece is a rotated variation of the compared piece
	 */
	public boolean equalsWithPosition(Object o){
		boolean check = false;
		if(!(o instanceof Piece)) { return false; }
		if(this.equals(o)) {check = true;}
		Piece myPiece = (Piece) o;
		for(int i =0; i<3; i++) {
			myPiece.rotateCW();
			if(this.equals(o)) {check = true;}
		}
		myPiece.rotateCW(); 
		myPiece.flipHorizontal();
		if(this.equals(o)) {check = true;}
		for(int j =0; j<3; j++) {
			myPiece.rotateCW();
			if(this.equals(o)) {check = true;}
		}
		myPiece.rotateCW();
		myPiece.flipHorizontal();
		return check;
			
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<piece.length; i++) {
			sb.append(String.format("(%d, %d):", piece[i].getRow(), piece[i].getCol()));
		}
		return sb.toString();
	}

				/*********************
				 *  Getter & Setter  *
				 *********************/
	/** @return  The constituent Points of this Piece. */
	public Point[] getPiece()
	{
		return piece;
	}
	/** @return  The Color of the piece (the fill). */
	public Color getPieceColor()
	{
		return pieceColor;
	}
	/** @return  The Color of the piece's border.*/
	public Color getPieceBorder()
	{
		return pieceBorder;
	}
}
