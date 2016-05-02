package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.EditPlayableBullpenView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.Bullpen;
import poseidon.entities.PieceContainer;
import poseidon.entities.PieceFactory;
import poseidon.entities.Point;

/**
 *  Sets the playable bullpen of the level currently being built and returns
 *  to the build level screen (BuilderView).
 *  
 *  @author Alex Titus
 */
public class SetBullpenController implements ActionListener
{
	/** The top-level GUI object. */
	LevelBuilderView view;
	/** The edit screen, with information about piece counts. */
	EditPlayableBullpenView editView;
	/** The playable bullpen. */
	Bullpen playable;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the top-level GUI object
	 *  @param editView  the screen with the information about piece counts
	 *  @param playable  the playable bullpen
	 */
	public SetBullpenController(LevelBuilderView view, EditPlayableBullpenView editView,
			Bullpen playable)
	{
		this.view = view;
		this.editView = editView;
		this.playable = playable;
	}

	
	/**
	 *  Fills the playable with the described number of pieces and returns to the
	 *  build level screen (BuilderView).
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Create new bullpen
		// Create pieces
		ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
		JFormattedTextField[] data = editView.getPieceCountsPanel().getInputs();
		for(int i = 0; i < data.length; i++)  // Go through all input values
		{
			int pieceCount = (int) data[i].getValue();
			for(int j = 0; j < pieceCount; j++)
			{
				PieceFactory fac = new PieceFactory();
				PieceContainer newPiece = new PieceContainer(fac.getPiece(i+1), new Point(-1, -1));
				pieces.add(newPiece);
			}
		}
		playable.setPieces(pieces);  // Set new pieces
		
		// Set screen
		BuilderView currentScreen = (BuilderView) view.getCurrentScreen();
		view.getFrame().setContentPane(currentScreen);
		
		// Display new screen
		view.getFrame().setVisible(true);
	}

}
