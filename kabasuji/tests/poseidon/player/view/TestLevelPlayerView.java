package poseidon.player.view;

import javax.swing.JButton;
import javax.swing.JFrame;

import junit.framework.TestCase;

public class TestLevelPlayerView extends TestCase
{
	LevelPlayerView game;
	
	
//	public void setUp()
//	{
//		game = new LevelPlayerView();
//	}
//	
//	
//	public void testInitialize()
//	{
//		// Check that attributes now exist
//		assertTrue(game.frmKabasuji instanceof JFrame);
//		assertTrue(game.continueGameButton instanceof JButton);
//		assertTrue(game.levelSelectButton instanceof JButton);
//		assertTrue(game.customLevelsButton instanceof JButton);
//		assertTrue(game.aboutButton instanceof JButton);
//		assertTrue(game.exitButton instanceof JButton);
//	}	
	
	

	public void testGetInstance()
	{
		// Check that initially instance doesn't exist
	  assertTrue(LevelPlayerView.instance == null);
	  
	  // Try to access instance, causing it to be created
	  game = LevelPlayerView.getInstance();
	  
	  // Check that instance now exists
		assertTrue(game instanceof LevelPlayerView);
	}
	
}
