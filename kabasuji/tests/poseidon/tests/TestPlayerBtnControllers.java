package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;


import javax.swing.JButton;

import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.AboutPlayerController;
import poseidon.player.controller.BackPlayerController;
import poseidon.player.controller.ContinueController;
import poseidon.player.controller.ExitPlayerController;
import poseidon.player.controller.LevelSelectController;
import poseidon.player.view.AboutPlayerView;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;
import junit.framework.TestCase;

public class TestPlayerBtnControllers extends TestCase{
	
	LevelPlayerView view;
	LevelPlayerModel model;
	AboutPlayerView aboutView = new AboutPlayerView(model, view);
	int[] current; 
	LevelModel level;
	JButton button;
	AboutPlayerController controller; 
	BackPlayerController back;
	ContinueController continueControl;
	ExitPlayerController exit;
	LevelSelectController lvlSelect;
	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public void setUp(){
		view = new LevelPlayerView(model);
		current = new int[3];
		level = new LevelModel(null, null, 0, getName(), null);  // TODO sorry, I made it abstract, now you have to choose what kind
		model = new LevelPlayerModel(current, level);
		controller = new AboutPlayerController(model, view);
		back = new BackPlayerController(model, view);
		continueControl = new ContinueController(model, view);
		exit = new ExitPlayerController(view);
		lvlSelect = new LevelSelectController(model, view);
		
	}
	
	
	public void tearDown(){
		if (view != null){
			view.getfrmKabasuji().dispose();}
	}
	
	public void testAboutBtn(){
		button = view.getAbout();
		ActionEvent aboutPress = buttonPress(button);
		controller.actionPerformed(aboutPress);
		
		assertEquals(view.getCurrentView().getClass(), AboutPlayerView.class );
	}

	public void testLevelSelect(){
		button = view.getLvlSelect();
		ActionEvent lvlSelectPress = buttonPress(button);
		lvlSelect.actionPerformed(lvlSelectPress);
		
		assertEquals(view.getCurrentView().getClass(), LevelSelectView.class );
	}
	
	public void testAboutBack(){
		button = view.getAbout();
		ActionEvent aboutPress = buttonPress(button);
		controller.actionPerformed(aboutPress);
		
		button = aboutView.getBack();
		ActionEvent backPress = buttonPress(button);
		back.actionPerformed(backPress);
		
		assertNull(view.getCurrentView());
		
	}
	
	
//	public void testExit(){
//		button = view.getExit();
//		ActionEvent exitPress = buttonPress(button);
//		exit.actionPerformed(exitPress);
//		
//		assertEquals(view, null);
//	}
	
	

}
