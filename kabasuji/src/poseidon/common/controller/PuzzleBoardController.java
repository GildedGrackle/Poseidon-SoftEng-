package poseidon.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;
import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelModel;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.player.view.LevelView;

/**
 *  Controls mouse events on the Board.
 *  
 *  Handles drawing Pieces on the Board on mouseover, placing Pieces on click,
 *  and allowing for Pieces to be moved once placed on the Board by drag & drop.
 *  
 * @author Alex Titus
 */
public class PuzzleBoardController extends MouseAdapter
{
	/** The Board state. */
	Board boardModel;
	/** The visual representation of the Board */
	BoardView boardView;
	/** The Bullpen state. */
	Bullpen bullpenModel;
	/** The visual representation of the Bullpen */
	BullpenView bullpenView;

	
	/**
	 *  Constructor.
	 * @param model  the Level that contains the Board
	 * @param view  the represenation of the Board
	 */
	public PuzzleBoardController(Board boardModel, BoardView boardView,
			Bullpen bullpenModel, BullpenView bullpenView)
	{
		this.boardModel = boardModel;
		this.boardView = boardView;
		this.bullpenModel = bullpenModel;
		this.bullpenView = bullpenView;
	}
	
	
	/**
	 *  Draws the Piece selected in the Bullpen onto the Board.
	 */
	@Override
	public void mouseEntered(MouseEvent me)
	{
		PieceContainer piece = bullpenModel.getPieceSelected();
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(piece == null || pv == null)
		{
			return ;
		}
		
		// Notify Board that there is a Piece on it
		boardView.setActiveDragging(pv);
		boardView.setActiveLocation(me.getPoint());
		pv.setOnBoard(true);
		
		boardView.repaint();
	}
	
	
	/**
	 *  Removes the active dragging Piece from the Board.
	 */
	@Override
	public void mouseExited(MouseEvent me)
	{
		PieceContainer piece = bullpenModel.getPieceSelected();
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(piece == null || pv == null)
		{
			return ;
		}
		
		// Notify Board that there is a Piece on it
		boardView.setActiveDragging(null);
		boardView.setActiveLocation(null);
		pv.setOnBoard(false);
		
		boardView.repaint();
	}
	
	
	@Override
	public void mouseMoved(MouseEvent me)
	{
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(pv == null)
		{
			return ;
		}
		
		// Notify Board that the Piece has changed locations
		boardView.setActiveLocation(me.getPoint());
		
		boardView.repaint();
	}
	
	
	/**
	 *  Places a Piece on the Board.
	 *  
	 *  Places the selected Piece in the Bullpen, removing it from the Bullpen,
	 *  adding it to the Board, and updating the PieceContainer's location with
	 *  its (row, column) coordinates on the Board.
	 */
	@Override
	public void mouseClicked(MouseEvent me)
	{
		PieceContainer piece = bullpenModel.getPieceSelected();
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(piece == null || pv == null)
		{
			return ;
		}
		
		// Determine row and column of click
		int row = me.getX() / BoardView.SQUARE_SIZE;
		int col = me.getY() / BoardView.SQUARE_SIZE;
		piece.setLocation(new Point(row, col));
		
		// Add Piece to Board
		boardModel.addPiece(piece);
		boardView.addPiece(pv);
		bullpenModel.removePiece(piece);
		bullpenView.removePiece(pv);
		boardView.setActiveDragging(null);
		boardView.setActiveLocation(null);
		bullpenView.setSelectedPiece(null);
		pv.setOnBoard(true);
		
		boardView.repaint();
		bullpenView.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent me)
	{
		
	}
	
	
	@Override
	public void mouseReleased(MouseEvent me)
	{
		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent me)
	{
		
	}
}
