package poseidon.common.view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import poseidon.player.view.LevelView;


public class BullpenView extends JPanel
{
	Bullpen model;  // The state of the Bullpen
	LevelView game;  // The GUI of the Level
	ArrayList<PieceView> pieces;  // The Pieces in the Bullpen

	/**
	 * Create the panel.
	 */
	public BullpenView(Bullpen model, LevelView view)
	{
		this.model = model;
		game = view;

		setLayout(null);
		createPieces();
		setPreferredSize(new Dimension(60 * pieces.size(), 60));
		for(int i = 0; i < 10; i++)
		{
			pieces.get(i).setBounds(i*60,0,60,60);
			add(pieces.get(i));
		}
	}

	
	/*************************
	 *  Getters and setters  *
	 *************************/

	
	
	/**
	 *  Creates the PieceView objects based on the model's Bullpen
	 */
	public Boolean createPieces()
	{
		pieces = new ArrayList<PieceView>(model.getSize());
		// TODO use iterator?
		for(int i = 0; i < model.getSize(); i++)  // TODO use proper method
		{
			pieces.add(new PieceView(model.getPiece(i)));  // TODO use proper method
		}
		
		return true;
	}
	/**
	 *  Updates display when the model changes
	 *  @return
	 */
	public Boolean update()
	{
		setPreferredSize(new Dimension(60 * pieces.size(), 60));
		for(int i = 0; i < 10; i++)
		{
			pieces.get(i).setBounds(i*60,0,60,60);
			add(pieces.get(i));
		}
		repaint();  // TODO figure out if this is the correct method to call here
		
		return true;
	}
}
