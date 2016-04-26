package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.XMLHandler;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;
import poseidon.player.view.LevelView;

public class PlaySelectedController implements ActionListener
{
	LevelPlayerModel model;  // The top-level entity object, representing the game
	LevelSelectView select;  // The level select screen, where this was called from
	LevelPlayerView game;  // The top-level GUI object

	
	/**
	 *  Constructor
	 * @param model
	 * @param game
	 */
	public PlaySelectedController(LevelPlayerModel model, LevelSelectView select, LevelPlayerView game)
	{
		this.model = model;
		this.select = select;
		this.game = game;
	}

	
	/**
	 *  
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		playGame();
	}

	
	/**
	 *  Switches the panel currently displayed in LevelPlayerView to the
	 *  Level screen, where the level displayed is based on which level was
	 *  selected on the Level Select screen
	 */
	boolean playGame()
	{
		// Get selected level container
		LevelContainer lvlCon = select.getSelectedLevel();
		XMLHandler levelHandler = model.getXMLHandler();
		lvlCon.setLevel(levelHandler.loadXML(lvlCon.getLevelFileName() + ".xml", false));
		
		// Now set currently playing in LevelPlayerModel to the level determined above
		model.setPlayingLevel(lvlCon.getLevel());
		LevelView newScreen = new LevelView(model, game);

		// Set new screen
		// Then set new level as that screen's currently playing
		game.getCurrentView().setCurrentlyPlaying(newScreen);
		game.getfrmKabasuji().setContentPane(newScreen);

		// Display the new screen
		game.getfrmKabasuji().setVisible(true);

		return true;
	}
}
