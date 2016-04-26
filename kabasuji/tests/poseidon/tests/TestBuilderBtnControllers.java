package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import poseidon.entities.LevelModel;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.builder.controller.AboutBuilderController;
import poseidon.builder.controller.BackBuilderController;
import poseidon.builder.controller.EditLevelController;
import poseidon.builder.controller.ExitBuilderController;
import poseidon.builder.controller.NewLevelController;
import poseidon.builder.view.AboutBuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.NewLevelView;
import poseidon.builder.view.EditLevelView;
import junit.framework.TestCase;

public class TestBuilderBtnControllers extends TestCase{

		
	
	LevelBuilderView view;
	LevelBuilderModel model;
	AboutBuilderView aboutView = new AboutBuilderView(model, view);
	LevelContainer lvlContainer;
	int[] current; 
	LevelModel level;
	JButton button;
	AboutBuilderController aboutController; 
	BackBuilderController back;
	NewLevelController newLevelControl;
	ExitBuilderController exit;
	EditLevelController editLvlSelect;
		
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName() );
		}
		
		public void setUp(){
			view = new LevelBuilderView();
			current = new int[3];
			// level = new LevelModel(null, null, 0, getName(), null);  // TODO sorry, I made it abstract, now you have to choose what kind
			model = new LevelBuilderModel();
			model.setBuildingLevel(lvlContainer);
			aboutController = new AboutBuilderController(model, view);
			back = new BackBuilderController(model, view);
			newLevelControl = new NewLevelController(model, view);
			exit = new ExitBuilderController(model, view);
			editLvlSelect = new EditLevelController(model, view); 
			
		}
		
		
		public void tearDown(){
				view.getBuilder().dispose();
		}
		
		public void testAboutBtn(){
			button = view.getAbout();
			ActionEvent aboutPress = buttonPress(button);
			aboutController.actionPerformed(aboutPress);
			
			assertEquals(view.getCurrentScreen().getClass(), AboutBuilderView.class );
		}

		public void testNewLevel(){
			button = view.getNewLevel();
			ActionEvent newLvlPress = buttonPress(button);
			newLevelControl.actionPerformed(newLvlPress);
			
			assertEquals(view.getCurrentScreen().getClass(), NewLevelView.class );
		}
		
		public void testAboutBack(){
			button = view.getAbout();
			ActionEvent aboutPress = buttonPress(button);
			aboutController.actionPerformed(aboutPress);
			
			button = aboutView.getBackButton();
			ActionEvent backPress = buttonPress(button);
			back.actionPerformed(backPress);
			
			assertNull(view.getCurrentScreen());
			
		}
		
		public void testEditBtn(){
			button = view.getEditLvl();
			ActionEvent editPress = buttonPress(button);
			editLvlSelect.actionPerformed(editPress);
			
			assertEquals(view.getCurrentScreen().getClass(), EditLevelView.class);
		}
		
}
