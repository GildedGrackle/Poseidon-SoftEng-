package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;

import junit.framework.TestCase;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;

public class TestCommonControllers extends TestCase{

	LevelPlayerView view;
	LevelPlayerModel model;
	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public void setUp(){
		
		view = new LevelPlayerView(model);
	}
	
	public void tearDown(){
		view.getfrmKabasuji().dispose();
	}
	
	public void testHFlip(){
		
	}
	
	public void testVFlip(){
		
	}
	
	public void testRedoManger(){
		
	}
	
	public void testRotateCCW(){
		
	}
	
	public void testRotateCW(){
		
	}
	
}
