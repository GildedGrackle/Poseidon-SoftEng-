package poseidon.tests;

import junit.framework.TestCase;
import poseidon.player.view.AboutPlayerView;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelView;

public class TestPlayerViews extends TestCase
{
	LevelPlayerView game;
	AboutPlayerView aboutView;
	
	public void setUp()
	{
		aboutView = new AboutPlayerView(game);
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
		LevelView curPlay = aboutView.getCurrentlyPlaying();
		
		assertTrue(curPlay == null);
	}
	
	
	public void testGetInstance()
	{
		// Check that initially instance doesn't exist
	  assertTrue(game == null);
	  
	  // Try to access instance, causing it to be created
	  game = new LevelPlayerView();
	  
	  // Check that instance now exists
		assertTrue(game instanceof LevelPlayerView);
	}
}
