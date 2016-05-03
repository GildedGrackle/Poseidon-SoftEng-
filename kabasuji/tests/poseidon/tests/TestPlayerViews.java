package poseidon.tests;

import junit.framework.TestCase;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.AboutPlayerView;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelView;

public class TestPlayerViews extends TestCase
{
	LevelPlayerView game;
	AboutPlayerView aboutView;
	LevelPlayerModel model;
	
	public void setUp()
	{

	}
	
	
	public void tearDown()
	{
		game  = null;
	}
	

	/**
	 *  Should return null
	 */
	public void testGetCurrentlyPlaying()
	{
		aboutView = new AboutPlayerView(model, game);
		LevelView curPlay = aboutView.getCurrentlyPlaying();
		
		assertTrue(curPlay == null);
	}
	
	
	public void testGetInstance()
	{
		// Check that initially instance doesn't exist
	  assertTrue(game == null);
	  
	  // Try to access instance, causing it to be created
	  game = new LevelPlayerView(model);
	  
	  // Check that instance now exists
		assertTrue(game instanceof LevelPlayerView);
	}
	
	public void testBoardView(){
		
	}
}
