package poseidon.entities;

import poseidon.common.view.ILevelView;
import poseidon.common.view.PieceView;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;

/**
 * Moving piece from the board to the bullpen.
 * Only applicable for puzzle mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class BoardToBullpenMove implements IMove{
	/** The Level. */
	LevelModel game;
	/** The GUI representation of the Level. */
	ILevelView view;
	/** Location of the Piece on the Board. */
	Point from;
	/** The Piece to move. */
	PieceContainer piece;
	/** The color represenation of the Piece being moved. */
	PieceView draggedPiece;
	
	
	/**
	 *  Constructor.
	 *  
	 * @param game  model of the Level
	 * @param view  the GUI representation of the Level
	 * @param piece  the Piece to move, must have a valid location on the Board
	 * @param from  the location of the Piece on the Board
	 */
	public BoardToBullpenMove(LevelModel game, ILevelView view, PieceContainer piece) {
		this.game = game;
		this.view = view;
		this.from = piece.getLocation();
		this.piece = piece;
		this.draggedPiece = view.getBoard().getActiveDragging();
	}
	
	
	/**
	 *  Move is valid unless limit has been reached.
	 *  
	 *  The only way this can be called is if there is an actual Piece being
	 *  dragged off of the Board, due to checks at BoardController.mouseClicked
	 *  and BoardController.mouseReleased
	 */
	public Boolean isValid() {
		if(game.getLimit() <= 0) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 *  Removes the Piece from the Board and adds it to the Bullpen.
	 */
	public Boolean doMove() {
		
		if(isValid())
		{
			// Inform piece of new location
			piece.setLocation(new Point(-1, -1));  // Piece is "Nowhere"
			
			// Add piece to Bullpen  
			view.getBullpen().getModel().addPiece(piece);
			view.getBullpen().addPiece(draggedPiece);
			
			// Remove piece from Board not required because was removed during
			// BoardController.mousePressed()
			game.getBoard().setActiveDragged(null);
			view.getBoard().setActiveDragging(null);
			
			
			// Decrease the number of moves remaining
			game.decrementLimit();
			
			return true;
		}
		
		
		return false;
	}
	
	
	/**
	 * Returns false since board to bullpen moves are only possible in puzzle, where moves are un-undoable.
	 */
	public Boolean undoMove() {
		return false;
	}
}
