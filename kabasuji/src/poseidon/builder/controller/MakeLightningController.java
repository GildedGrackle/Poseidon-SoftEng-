package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;

public class MakeLightningController implements ActionListener
{
//	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	LevelBuilderView application;  // The top-level GUI object

	
	/**
	 *  Constructor
	 * @param view
	 */
	public MakeLightningController(/* LevelBuilderModel model, */ LevelBuilderView view)
	{
//		this.model = model;
		this.application = view;
	}


	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  Level Builder screen, with a new Lightning Level ready to build
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toLightningLevel();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  Level Builder screen, with a new Lightning Level ready to build
	 */
	public Boolean toLightningLevel()
	{
		BuilderView newScreen = new BuilderView(application);  // The new screen to display
		
		// Set new screen
		application.setCurrentScreen(newScreen);
		application.getBuilder().setContentPane(newScreen);
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}
}
