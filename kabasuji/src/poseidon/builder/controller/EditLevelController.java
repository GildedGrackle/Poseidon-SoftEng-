package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.EditLevelView;
import poseidon.builder.view.IBuilderScreen;
import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.BuilderBullpenLogic;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;

/**
 *  Loads the selected level for editing.
 *  
 *  @author Alex Titus
 */
public class EditLevelController implements ActionListener
{
	/** The top-level entity object. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView application;
	/** The screen with the selected level information. */
	EditLevelView select;
	

	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object
	 *  @param application  the top-level GUI object
	 */
	public EditLevelController(LevelBuilderModel model, LevelBuilderView application,
			EditLevelView select)
	{
		this.model = model;
		this.application = application;
		this.select = select;
	}


	/**
	 *  Creates and displays the build level screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Get selected level container
		LevelContainer lvlCon = select.getSelectedLevel();
		lvlCon.getLevel().setInfiniteBullpen(new Bullpen(new BuilderBullpenLogic()));

		// Now set currently playing in LevelPlayerModel to the level determined above
		model.setBuildingLevel(lvlCon);
		BuilderView newScreen = new BuilderView(model, application);

		// Set new screen
		// Then set new level as that screen's currently playing
		application.setCurrentScreen((IBuilderScreen) newScreen);
		application.getFrame().setContentPane(newScreen);

		// Display the new screen
		application.getFrame().setVisible(true);
		newScreen.modelUpdated();
	}

}
