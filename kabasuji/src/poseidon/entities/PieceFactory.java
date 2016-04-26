package poseidon.entities;

import java.util.Random;

/**
 * Generates the 35 possible pieces 
 * @author Natalia
 */
public class PieceFactory {
	Piece [] pieces = new Piece[35];
	
	public PieceFactory () {
		Point[][] p = new Point[6][6];
		for (int i = 0; i<6;i++) {
			for (int j=0;j<6;j++){
				p[i][j] = new Point (i,j);
			}
		}
		pieces[0] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[3][0], p[4][0], p[5][0]});
		pieces[1] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[3][0], p[4][0], p[0][1]});
		pieces[2] = new Piece (new Point[] {p[3][0], p[0][1], p[1][1], p[2][1], p[3][1], p[4][1]});
		pieces[3] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[3][0], p[4][0], p[2][1]});
		pieces[4] = new Piece (new Point[] {p[1][0], p[2][0], p[1][1], p[1][2], p[0][2], p[0][3]});
		pieces[5] = new Piece (new Point[] {p[2][0], p[3][0], p[4][0], p[0][1], p[1][1], p[2][1]});
		pieces[6] = new Piece (new Point[] {p[1][0], p[2][0], p[3][0], p[4][0], p[0][1], p[1][1]});
		pieces[7] = new Piece (new Point[] {p[2][0], p[3][0], p[0][1], p[1][1], p[2][1], p[3][1]});	
		pieces[8] = new Piece (new Point[] {p[0][0], p[0][1], p[1][1], p[2][1], p[3][1], p[3][2]});
		pieces[9] = new Piece (new Point[] {p[0][0], p[0][1], p[0][2], p[1][1], p[2][1], p[3][1]});
		pieces[10] = new Piece (new Point[] {p[3][0], p[3][1], p[0][2], p[1][2], p[2][2], p[3][2]});
		pieces[11] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[0][1], p[1][1], p[2][1]});
		pieces[12] = new Piece (new Point[] {p[1][0], p[2][0], p[3][0], p[0][1], p[1][1], p[0][2]});
		pieces[13] = new Piece (new Point[] {p[2][0], p[3][0], p[1][1], p[2][1], p[0][2], p[1][2]});
		pieces[14] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[3][0], p[1][1], p[2][1]});	
		pieces[15] = new Piece (new Point[] {p[1][0], p[0][1], p[1][1], p[2][1], p[3][1], p[1][2]});
		pieces[16] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[3][0], p[1][1], p[3][1]});
		pieces[17] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[2][1], p[3][1], p[2][2]});
		pieces[18] = new Piece (new Point[] {p[0][0], p[0][1], p[0][2], p[1][2], p[2][2], p[2][3]});
		pieces[19] = new Piece (new Point[] {p[1][0], p[0][1], p[1][1], p[0][2], p[1][2], p[0][3]});
		pieces[20] = new Piece (new Point[] {p[1][0], p[2][0], p[1][1], p[2][1], p[0][2], p[1][2]});
		pieces[21] = new Piece (new Point[] {p[1][0], p[2][0], p[1][1], p[2][1], p[0][2], p[1][2]});	
		pieces[22] = new Piece (new Point[] {p[0][0], p[3][0], p[0][1], p[1][1], p[2][1], p[3][1]});
		pieces[23] = new Piece (new Point[] {p[1][0], p[2][0], p[0][1], p[1][1], p[1][2], p[2][2]});
		pieces[24] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[0][1], p[2][1], p[0][2]});
		pieces[25] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[1][1], p[2][1], p[2][2]});
		pieces[26] = new Piece (new Point[] {p[0][0], p[0][1], p[1][1], p[2][1], p[3][1], p[1][2]});
		pieces[27] = new Piece (new Point[] {p[2][0], p[2][1], p[0][2], p[1][2], p[2][2], p[3][2]});
		pieces[28] = new Piece (new Point[] {p[0][0], p[1][0], p[1][1], p[0][2], p[1][2], p[0][3]});	
		pieces[29] = new Piece (new Point[] {p[1][0], p[2][0], p[1][1], p[0][2], p[1][2], p[2][2]});
		pieces[30] = new Piece (new Point[] {p[0][0], p[1][0], p[2][0], p[1][1], p[2][1], p[1][2]});
		pieces[31] = new Piece (new Point[] {p[2][0], p[3][0], p[0][1], p[1][1], p[2][1], p[2][2]});
		pieces[32] = new Piece (new Point[] {p[0][0], p[1][0], p[1][1], p[1][2], p[2][2], p[1][3]});
		pieces[33] = new Piece (new Point[] {p[1][0], p[0][1], p[1][1], p[1][2], p[2][2], p[1][3]});
		pieces[34] = new Piece (new Point[] {p[2][0], p[1][1], p[2][1], p[0][2], p[1][2], p[1][3]});
		
		
	}
	
	/**
	 * returns the piece whose number was passed to the function.
	 * 
	 * NOTE: To avoid confusion, the pieces are numbered exactly in the way that was shown in the class slides.
	 * That means that the numbers go 1-35 instead of 0-34 as they actually do in the array.
	 * 
	 * @param pieceNum
	 * @return Piece
	 */
	public Piece getPiece(int pieceNum) {
		return pieces[pieceNum-1];
	}
	
	/**
	 * Returns a random piece.
	 * @return Piece
	 */
	public Piece getRandomPiece() {
		Random rand = new Random();
		int randomNum = rand.nextInt(35);
		return pieces[randomNum];
		
	}

}
