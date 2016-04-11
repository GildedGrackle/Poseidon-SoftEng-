package poseidon.common.view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;


public class BullpenView extends JPanel
{
//	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	ArrayList<PieceView> pieces;  // The Pieces in the Bullpen

	/**
	 * Create the panel.
	 */
	public BullpenView(/* LevelBuilderModel model */)
	{
		setLayout(null);
//		model = model;
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
		// TODO
		pieces = new ArrayList<PieceView>();
		for(int i = 0; i < 10; i++)
		{
			pieces.add(new PieceView());
		}
		
		return true;
	}
	/**
	 *  Updates display when the model changes
	 *  @return
	 */
	public void update()
	{
		repaint();
	}
}
