package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import poseidon.entities.LevelModel;
import poseidon.entities.Piece;
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
import poseidon.builder.controller.CancelEditController;
import poseidon.builder.controller.ToEditLevelController;
import poseidon.builder.controller.ToEditPlayableBullpenController;
import poseidon.builder.controller.ExitBuilderController;
import poseidon.builder.controller.MakeLightningController;
import poseidon.builder.controller.MakePuzzleController;
import poseidon.builder.controller.MakeReleaseController;
import poseidon.builder.controller.NewLevelController;
import poseidon.builder.controller.RedoController;
import poseidon.builder.controller.SetBullpenController;
import poseidon.builder.controller.UndoController;
import poseidon.builder.view.AboutBuilderView;
import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.NewLevelView;
import poseidon.common.controller.BoardController;
import poseidon.common.view.BoardView;
import poseidon.common.view.PieceView;
import poseidon.builder.view.EditLevelView;
import poseidon.builder.view.EditPlayableBullpenView;
import junit.framework.TestCase;

public class TestBuilderBtnControllers extends TestMouseEvents{

		
	
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
	ToEditLevelController editLvlSelect;
	NewLevelView newLvlView;
	MakePuzzleController makePuzCont;
	MakeLightningController makeLightCont;
	MakeReleaseController makeReleaseCont;
	ToEditPlayableBullpenController editBullpenScreen;
	SetBullpenController setBullpenController;
	EditPlayableBullpenView editBullpenView;
	CancelEditController cancelBullEdit;

		
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
			editLvlSelect = new ToEditLevelController(model, view); 
			newLvlView = new NewLevelView(model, view);
			makePuzCont = new MakePuzzleController(model, view);
			makeLightCont = new MakeLightningController(model, view);
			makeReleaseCont = new MakeReleaseController(model, view);
			editBullpenScreen = new ToEditPlayableBullpenController(view);
			
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
			 
			editBullpenView = new EditPlayableBullpenView(view);
			
			setBullpenController = new SetBullpenController(view, editBullpenView, model.getBuildingLevel().getLevel().getPlayableBullpen());
			
			editBullpenView.getPieceCountsPanel().getInputs()[0].setValue(4);
			
			button = editBullpenView.getDoneButton();
			ActionEvent doneEditing = buttonPress(button);
			setBullpenController.actionPerformed(doneEditing);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			assertEquals(model.getBuildingLevel().getLevel().getPlayableBullpen().getSize(), 4);
			
			button = builderView.getSetPlayBull();
			editBullpenScreen.actionPerformed(setBullpen);
			
			editBullpenView.getPieceCountsPanel().getInputs()[2].setValue(6);
			
			cancelBullEdit = new CancelEditController(view);
			button =editBullpenView.getCancelButton();
			ActionEvent cancelEditing = buttonPress(button);
			cancelBullEdit.actionPerformed(cancelEditing);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			assertEquals(model.getBuildingLevel().getLevel().getPlayableBullpen().getSize(), 4);
			
		}
		
		public void testUndoManager(){
			button = view.getNewLevel();
			ActionEvent newLvlPress = buttonPress(button);
			newLevelControl.actionPerformed(newLvlPress);
			
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makePuzCont.actionPerformed(newPuzzle);
			
			BuilderView builderView = new BuilderView(model, view);
			
			PieceContainer selectedPiece = model.getBuildingLevel().getLevel().getInfiniteBullpen().getPiece(1);
		
			model.getBuildingLevel().getLevel().getInfiniteBullpen().setPieceSelected(selectedPiece);
			PieceView selectedPieceView = builderView.getBullpen().getPieceView(1);
			builderView.getBullpen().setSelectedPiece(selectedPieceView); 
			
			BoardController boardController = new BoardController(model.getBuildingLevel().getLevel() , builderView);
			
			MouseEvent movePiece = createBuilderMoved(builderView, builderView.getBoard(), 0, 0);
			boardController.mouseMoved(movePiece);
			
			assertEquals(builderView.getBoard().getActiveDragging(), selectedPieceView);
			
			MouseEvent pressed = createBuilderPress(builderView, builderView.getBoard(), 0, 0);
			boardController.mousePressed(pressed);
			
			assertTrue(model.getBuildingLevel().getLevel().getBoard().getPieces().contains(selectedPiece));
			assertEquals(builderView.getBoard().getActiveDragging(), null);
			
			UndoController undo = new UndoController(builderView);
			
			button = builderView.getUndo();
			ActionEvent newUndo = buttonPress(button);
			
			undo.actionPerformed(newUndo);
			
			assertFalse(model.getBuildingLevel().getLevel().getBoard().getPieces().contains(selectedPiece));
			
			RedoController redo = new RedoController(builderView);
			
			button = builderView.getRedo();
			ActionEvent newRedo = buttonPress(button);
			redo.actionPerformed(newRedo);
			
			assertTrue(model.getBuildingLevel().getLevel().getBoard().getPieces().contains(selectedPiece));
			assertEquals(builderView.getBoard().getActiveDragging(), null);
			
		}
		
		public void testBuilderDoubleClick(){
			button = view.getNewLevel();
			ActionEvent newLvlPress = buttonPress(button);
			newLevelControl.actionPerformed(newLvlPress);
			
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makePuzCont.actionPerformed(newPuzzle);
			
			BuilderView builderView = new BuilderView(model, view);
			
			BoardController boardController = new BoardController(model.getBuildingLevel().getLevel() , builderView);
			
			MouseEvent doubleClick = createDoubleClicked(builderView, builderView.getBoard(), 0, 0);
			boardController.mouseClicked(doubleClick);
			
			assertEquals(model.getBuildingLevel().getLevel().getBoard().getSquare(0, 0).getType(), -1);
			
			boardController.mouseClicked(doubleClick);
			
			assertEquals(model.getBuildingLevel().getLevel().getBoard().getSquare(0, 0).getType(), LevelModel.PUZZLE);
			
		}

		
		
}
