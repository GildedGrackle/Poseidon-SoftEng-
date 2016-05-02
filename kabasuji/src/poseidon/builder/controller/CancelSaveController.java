package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;

/**
 * Returns to the build level screen (BuilderView) from the save level screen.
 *  
 * No changes are made to the level model.
 * 
 * @author Jacob
 */
public class CancelSaveController implements ActionListener {

	/** The top-level GUI object. */
	LevelBuilderView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  The top-level GUI object.
	 */
	public CancelSaveController(LevelBuilderView view)
	{
		this.view = view;
	}
	
	
	/**
	 *  Display the build level screen (BuilderView).
	 *  
	 *  @param ae  The initiating event (not used).
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		BuilderView currentScreen = (BuilderView) view.getCurrentScreen();
		view.getFrame().setContentPane(currentScreen);
		
		view.getFrame().setVisible(true);
	}

}
