package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;

/**
 *  Returns to the build level screen (BuilderView) from the edit playable bullpen screen.
 *  
 *  No changes are made to the playable bullpen.
 *  
 *  @author Alex Titus
 */
public class CancelEditController implements ActionListener
{
	/** The top-level GUI object. */
	LevelBuilderView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the top-level GUI object
	 */
	public CancelEditController(LevelBuilderView view)
	{
		this.view = view;
	}
	

	/**
	 *  Display the build level screen (BuilderView).
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		BuilderView currentScreen = (BuilderView) view.getCurrentScreen();
		view.getBuilder().setContentPane(currentScreen);
		
		view.getBuilder().setVisible(true);
	}

}
