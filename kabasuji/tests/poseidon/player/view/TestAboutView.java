package poseidon.player.view;

import junit.framework.TestCase;

public class TestAboutView extends TestCase
{
	AboutPlayerView view;
	
	public void setUp()
	{
		view = new AboutPlayerView();
	}
	
	
	public void tearDown()
	{
		view = null;
	}
	

	/**
	 *  Should return null
	 */
	public void testGetCurrentlyPlaying()
	{
		LevelView curPlay = view.getCurrentlyPlaying();
		
		assertTrue(curPlay == null);
	}
}
