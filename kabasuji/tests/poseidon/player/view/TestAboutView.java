package poseidon.player.view;

import junit.framework.TestCase;

public class TestAboutView extends TestCase
{
	LevelPlayerView view;
	AboutPlayerView aboutView;
	
	public void setUp()
	{
		aboutView = new AboutPlayerView(view);
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
		LevelView curPlay = aboutView.getCurrentlyPlaying();
		
		assertTrue(curPlay == null);
	}
}
