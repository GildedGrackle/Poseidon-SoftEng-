package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import poseidon.entities.LevelModel;
import poseidon.entities.PieceContainer;
import poseidon.entities.PieceFactory;
import poseidon.entities.PuzzleLevel;
import poseidon.entities.PuzzleSquare;
import poseidon.entities.Square;
import poseidon.entities.Board;
import poseidon.entities.BuilderBoardLogic;
import poseidon.entities.BuilderBullpenLogic;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.builder.controller.AboutBuilderController;
import poseidon.builder.controller.BackBuilderController;
import poseidon.builder.controller.EditLevelController;
import poseidon.builder.controller.EditPlayableBullpenController;
import poseidon.builder.controller.ExitBuilderController;
import poseidon.builder.controller.MakeLightningController;
import poseidon.builder.controller.MakePuzzleController;
import poseidon.builder.controller.MakeReleaseController;
import poseidon.builder.controller.NewLevelController;
import poseidon.builder.view.AboutBuilderView;
import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.NewLevelView;
import poseidon.builder.view.EditLevelView;
import poseidon.builder.view.EditPlayableBullpenView;
import junit.framework.TestCase;

public class TestBuilderBtnControllers extends TestCase{

		
	
	LevelBuilderView view;
	LevelBuilderModel model;
	AboutBuilderView aboutView = new AboutBuilderView(model, view);
	LevelContainer lvlContainer;
	int[] current; 
	JButton button;
	AboutBuilderController aboutController; 
	BackBuilderController back;
	NewLevelController newLevelControl;
	ExitBuilderController exit;
	EditLevelController editLvlSelect;
	NewLevelView newLvlView;
	MakePuzzleController makePuzCont;
	MakeLightningController makeLightCont;
	MakeReleaseController makeReleaseCont;
	EditPlayableBullpenController editBullpenScreen;

		
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName() );
		}
		
		public void setUp(){
			model = new LevelBuilderModel();
			view = new LevelBuilderView(model);
			current = new int[3];
			aboutController = new AboutBuilderController(model, view);
			back = new BackBuilderController(model, view);
			newLevelControl = new NewLevelController(model, view);
			exit = new ExitBuilderController(model, view);
			editLvlSelect = new EditLevelController(model, view); 
			newLvlView = new NewLevelView(model, view);
			makePuzCont = new MakePuzzleController(model, view);
			makeLightCont = new MakeLightningController(model, view);
			makeReleaseCont = new MakeReleaseController(model, view);
			editBullpenScreen = new EditPlayableBullpenController(view);
			
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
		
		public void testNewPuzzleLvl(){
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makePuzCont.actionPerformed(newPuzzle);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			assertTrue(makePuzCont.toPuzzleLevel());
		}
		
		public void testNewLightningLvl(){
			button = newLvlView.getNewLightning();
			ActionEvent newLightning = buttonPress(button);
			makeLightCont.actionPerformed(newLightning);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			assertTrue(makeLightCont.toLightningLevel());
			
		}
		
		public void testNewReleaseLvl(){
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makeReleaseCont.actionPerformed(newPuzzle);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			assertTrue(makeReleaseCont.toReleaseLevel());
			
		}
		
		public void testSetBullpen(){
			button = view.getNewLevel();
			ActionEvent newLvlPress = buttonPress(button);
			newLevelControl.actionPerformed(newLvlPress);
			
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makePuzCont.actionPerformed(newPuzzle);
			
			BuilderView builderView = new BuilderView(model, view);
			
			button = builderView.getSetPlayBull();
			ActionEvent setBullpen = buttonPress(button);
			editBullpenScreen.actionPerformed(setBullpen);
			
			assertEquals(EditPlayableBullpenView.class, view.getBuilder().getContentPane().getClass());
			 
			
		}
		
		
}
