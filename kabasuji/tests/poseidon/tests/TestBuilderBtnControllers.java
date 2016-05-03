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
import poseidon.player.controller.ResetController;
import poseidon.player.view.LevelSelectView;
import poseidon.player.view.LevelView;
import poseidon.entities.Board;
import poseidon.entities.BuilderBoardLogic;
import poseidon.entities.BuilderBullpenLogic;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.builder.controller.AboutBuilderController;
import poseidon.builder.controller.BackBuilderController;
import poseidon.builder.controller.CancelEditController;
import poseidon.builder.controller.CancelSaveController;
import poseidon.builder.controller.ConfirmSaveController;
import poseidon.builder.controller.ToEditLevelController;
import poseidon.builder.controller.ToEditPlayableBullpenController;
import poseidon.builder.controller.ExitBuilderController;
import poseidon.builder.controller.MakeLightningController;
import poseidon.builder.controller.MakePuzzleController;
import poseidon.builder.controller.MakeReleaseController;
import poseidon.builder.controller.NewLevelController;
import poseidon.builder.controller.RedoController;
import poseidon.builder.controller.ResetBuilderController;
import poseidon.builder.controller.SaveLevelController;
import poseidon.builder.controller.SetBullpenController;
import poseidon.builder.controller.UndoController;
import poseidon.builder.view.AboutBuilderView;
import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.NewLevelView;
import poseidon.builder.view.SaveLevelView;
import poseidon.common.controller.BoardController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
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
	SaveLevelController saveButton;
	ConfirmSaveController confirmSave; 
	SaveLevelView saveView;
	CancelSaveController cancelSave; 

		
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, "" );
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
				view.getFrame().dispose();
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
			
			assertEquals(EditPlayableBullpenView.class, view.getFrame().getContentPane().getClass());
			 
			editBullpenView = new EditPlayableBullpenView(view);
			
			setBullpenController = new SetBullpenController(view, editBullpenView, model.getBuildingLevel().getPlayableBullpen());
			
			editBullpenView.getPieceCountsPanel().getInputs()[0].setValue(4);
			
			button = editBullpenView.getDoneButton();
			ActionEvent doneEditing = buttonPress(button);
			setBullpenController.actionPerformed(doneEditing);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			assertEquals(model.getBuildingLevel().getPlayableBullpen().getSize(), 4);
			
			button = builderView.getSetPlayBull();
			editBullpenScreen.actionPerformed(setBullpen);
			
			editBullpenView.getPieceCountsPanel().getInputs()[2].setValue(6);
			
			cancelBullEdit = new CancelEditController(view);
			button =editBullpenView.getCancelButton();
			ActionEvent cancelEditing = buttonPress(button);
			cancelBullEdit.actionPerformed(cancelEditing);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			assertEquals(model.getBuildingLevel().getPlayableBullpen().getSize(), 4);
			
		}
		
		public void testUndoManager(){
			button = view.getNewLevel();
			ActionEvent newLvlPress = buttonPress(button);
			newLevelControl.actionPerformed(newLvlPress);
			
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makePuzCont.actionPerformed(newPuzzle);
			
			BuilderView builderView = new BuilderView(model, view);
			
			PieceContainer selectedPiece = model.getBuildingLevel().getInfiniteBullpen().getPiece(1);
		
			model.getBuildingLevel().getInfiniteBullpen().setPieceSelected(selectedPiece);
			
			BoardController boardController = new BoardController(view, model.getBuildingLevel() , builderView);
			
			MouseEvent movePiece = createBuilderMoved(builderView, builderView.getBoard(), 0, 0);
			boardController.mouseMoved(movePiece);
			
			assertEquals(model.getBuildingLevel().getBoard().getActiveDragged(), selectedPiece);
			
			MouseEvent pressed = createBuilderPress(builderView, builderView.getBoard(), 0, 0);
			boardController.mousePressed(pressed);
			
			assertEquals(model.getBuildingLevel().getBoard().getActiveDragged(), null);
			
			UndoController undo = new UndoController(builderView);
			
			button = builderView.getUndo();
			ActionEvent newUndo = buttonPress(button);
			
			undo.actionPerformed(newUndo);
			
			assertFalse(model.getBuildingLevel().getBoard().getPieces().contains(selectedPiece));
			
			RedoController redo = new RedoController(builderView);
			
			button = builderView.getRedo();
			ActionEvent newRedo = buttonPress(button);
			redo.actionPerformed(newRedo);
			
			assertEquals(model.getBuildingLevel().getBoard().getActiveDragged(), null);
			
		}
		
		public void testBuilderDoubleClick(){
			button = view.getNewLevel();
			ActionEvent newLvlPress = buttonPress(button);
			newLevelControl.actionPerformed(newLvlPress);
			
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makePuzCont.actionPerformed(newPuzzle);
			
			BuilderView builderView = new BuilderView(model, view);
			
			BoardController boardController = new BoardController(view, model.getBuildingLevel() , builderView);
			
			MouseEvent doubleClick = createDoubleClicked(builderView, builderView.getBoard(), 0, 0);
			boardController.mouseClicked(doubleClick);
			
			assertEquals(model.getBuildingLevel().getBoard().getSquare(0, 0).getType(), -1);
			
			boardController.mouseClicked(doubleClick);
			
			assertEquals(model.getBuildingLevel().getBoard().getSquare(0, 0).getType(), LevelModel.PUZZLE);
			
		}

		public void testSave(){
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
			editBullpenView = new EditPlayableBullpenView(view);
			
			setBullpenController = new SetBullpenController(view, editBullpenView, model.getBuildingLevel().getPlayableBullpen());
			
			editBullpenView.getPieceCountsPanel().getInputs()[0].setValue(4);
			
			button = editBullpenView.getDoneButton();
			ActionEvent doneEditing = buttonPress(button);
			setBullpenController.actionPerformed(doneEditing);
			
			saveView = new SaveLevelView(model, model.getBuildingLevel(), view);
			saveButton = new SaveLevelController(model, model.getBuildingLevel(), view);
			confirmSave = new ConfirmSaveController(model, view, saveView, model.getBuildingLevel());
			cancelSave = new CancelSaveController(view); 
			
			button = builderView.getSave();
			ActionEvent saveLevel = buttonPress(button);
			saveButton.actionPerformed(saveLevel);
			
			assertEquals(view.getFrame().getContentPane().getClass(), saveView.getClass());
			
			button = saveView.getCancel();
			ActionEvent cancelSavePress = buttonPress(button);
			cancelSave.actionPerformed(cancelSavePress);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			
			button = builderView.getSave();
			saveButton.actionPerformed(saveLevel);
			button = saveView.getSave();
			ActionEvent newSave = buttonPress(button);
			confirmSave.actionPerformed(newSave);
			
			assertEquals(view.getCurrentScreen().getClass(), BuilderView.class);
			
		}
		
		public void testResetBulider(){
			button = view.getNewLevel();
			ActionEvent newLvlPress = buttonPress(button);
			newLevelControl.actionPerformed(newLvlPress);
			
			button = newLvlView.getNewPuzzle();
			ActionEvent newPuzzle = buttonPress(button);
			makePuzCont.actionPerformed(newPuzzle);
			
			BuilderView builderView = new BuilderView(model, view);
			PieceContainer selectedPiece = model.getBuildingLevel().getInfiniteBullpen().getPiece(1);
			
			BoardView board = builderView.getBoard();
			model.getBuildingLevel().getInfiniteBullpen().setPieceSelected(selectedPiece);
			
			ResetBuilderController resetController = new ResetBuilderController(model, view);
			
			button = builderView.getResetButton();
			ActionEvent reset = buttonPress(button);
			
			BoardController controller = new BoardController(view, model.getBuildingLevel(), builderView);
			
			MouseEvent movePiece = createBuilderMoved(builderView, board, 4, 5);
			controller.mouseMoved(movePiece);
			
			assertEquals(model.getBuildingLevel().getBoard().getActiveDragged(), selectedPiece);
			
			MouseEvent pressed = createBuilderPress(builderView, board, 4, 5);
			controller.mousePressed(pressed);
			
			resetController.actionPerformed(reset);	

			assertTrue(model.getBuildingLevel().getBoard().getPieces().isEmpty());
			assertNull(model.getBuildingLevel().getBoard().getActiveDragged());
			
		}
		
		public void testChangeBoardSize(){
			
		}
}
