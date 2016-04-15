package poseidon.tests;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;
import junit.framework.TestCase;

public class TestPlayerControllers extends TestCase
{
	LevelPlayerView view;
	LevelPlayerModel model;
	int[] current; 
	LevelModel level;
	
	public void setUp(){
		view = new LevelPlayerView(model);
//		current = new int[3];
//		level = new LevelModel(null, null, 0, getName(), null);
//		model = new LevelPlayerModel(current, level);
	}
	
	
	public void tearDown(){
		
	}
	
	
	public void testUpdate(){
		
	}
	
	public void testSelectPiece(){
		
	}

}
