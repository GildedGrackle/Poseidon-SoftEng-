package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.EditPlayableBullpenView;
import poseidon.builder.view.LevelBuilderView;

/**
 *  Opens the edit playable bullpen screen (EditPlayableBullpenView).
 *  
 *  @author Alex Titus
 */
public class ToEditPlayableBullpenController implements ActionListener
{
	/** The top-level GUI object. */
	LevelBuilderView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the top-level GUI object
	 */
	public ToEditPlayableBullpenController(LevelBuilderView view)
	{
		this.view = view;
	}

	
	/**
	 *  Creates and displays the edit playable bullpen screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		EditPlayableBullpenView editScreen = new EditPlayableBullpenView(view);
		view.getFrame().setContentPane(editScreen);
		// Don't set view.currentScreen so we can return easier
		
		// Display new screen
		view.getFrame().setVisible(true);
	}

}
