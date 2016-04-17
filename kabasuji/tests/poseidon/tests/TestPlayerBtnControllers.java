package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.AboutPlayerController;
import poseidon.player.view.AboutPlayerView;
import poseidon.player.view.LevelPlayerView;
import junit.framework.TestCase;

public class TestPlayerBtnControllers extends TestCase{
	
	LevelPlayerView view;
	LevelPlayerModel model;
	int[] current; 
	LevelModel level;
	JButton button;
	AboutPlayerController controller; 
	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public void setUp(){
		view = new LevelPlayerView(model);
		current = new int[3];
		level = new LevelModel(null, null, 0, getName(), null);
		model = new LevelPlayerModel(current, level);
		controller = new AboutPlayerController(model, view);
	}
	
	
	public void tearDown(){
		view.getfrmKabasuji().dispose();
	}
	
	public void testAboutBtn(){
		button = view.getAbout();
		int x = 0;
		int y = 0;
		ActionEvent aboutPress = buttonPress(button);
		controller.actionPerformed(aboutPress);
		
		assertEquals(view.getCurrentView().getClass(), AboutPlayerView.class );
	}

	

}
