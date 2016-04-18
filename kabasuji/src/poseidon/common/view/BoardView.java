package poseidon.common.view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import poseidon.entities.Board;
import poseidon.player.view.LevelView;

public class BoardView extends JPanel
{
	Board board;  // The model of the Board
	ArrayList<PieceView> pieces;  // The Pieces currently on the Board
	PieceView activeDragging;  // The Piece currently being dragged (if applicable)
	

	/**
	 * Create the panel.
	 */
	public BoardView(Board board)
	{
		this.board = board;
		pieces = new ArrayList<PieceView>();
		activeDragging = null;
	}
	
	
	public void paint(Graphics g)  // TODO Figure out which method to use for double-buffering
	{
		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 12; j++)
			{
				g.drawRect(30*i, 30*j, 30, 30);
			}
		}
	}
}
