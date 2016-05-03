package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;

import poseidon.entities.Board;
import poseidon.entities.IBoardLogic;
import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.LightningLevel;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.entities.ReleaseNumber;
import poseidon.entities.Square;
import poseidon.entities.XMLHandler;
import poseidon.player.controller.AboutPlayerController;
import poseidon.player.controller.BackPlayerController;
import poseidon.player.controller.ContinueController;
import poseidon.player.controller.EndLevelController;
import poseidon.player.controller.ExitPlayerController;
import poseidon.player.controller.LevelSelectController;
import poseidon.player.controller.LimitEndController;
import poseidon.player.controller.PlaySelectedController;
import poseidon.player.controller.SelectLevelController;
import poseidon.player.controller.TimeController;
import poseidon.player.view.AboutPlayerView;
import poseidon.player.view.EndLevelView;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;
import poseidon.player.view.LevelView;
import junit.framework.TestCase;

public class TestPlayer extends TestCase{
	
	LevelPlayerView view;
	LevelPlayerModel model;
	AboutPlayerView aboutView = new AboutPlayerView(model, view);
	int[] current; 
	LevelModel testLevels;
	JButton button;
	AboutPlayerController controller; 
	BackPlayerController back;
	ContinueController continueControl;
	ExitPlayerController exit;
	LevelSelectController lvlSelect;
	PlaySelectedController playSelect;
	LevelSelectView lvlSelectView;
	SelectLevelController selectLevel;
	LevelContainer lvlContainer; 
	EndLevelController endLevel;
	LevelContainer lvlContainerLightning;
	LimitEndController limitCont;
	TimeController timeController;
	LevelModel lvlModel;
	TestBoardLogic testBoardLogic;
	
	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public class TestBoardLogic implements IBoardLogic{

		public Boolean shouldAddList (Board board, PieceContainer piece){
			return true;
		}
		
		public Boolean shouldRemovePiece (Board board, PieceContainer piece){
			return true;
		}
		public Boolean canSelectPieces(){
			return true;
		}
		
		public Boolean isValid(Board board, PieceContainer piece, Point location){
			return true;
		}
		public Boolean selectablePieceAt(Board board, int row, int col){
			return true;
		}

		@Override
		public Boolean canEdit() {
			return false;
		}

		@Override
		public Boolean shouldAddList() {
			return true;
		}

		@Override
		public void placePiece(Board board, PieceContainer piece) {
			return;
		}

		@Override
		public void setHint(Board board, int row, int col) {
			return;
		}
		
		@Override
		public void setReleaseNumber(Board board, int row, int col, ReleaseNumber rn) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public void setUp(){
		XMLHandler.makeExampleLevels();
		testLevels = XMLHandler.getTestLevels()[2];
		int[] currentLvl = new int[]{
				1, 1, 1};
		lvlContainer = new LevelContainer("puzzle0.xml", 0, 0, testLevels, 0);
		model = new LevelPlayerModel(currentLvl, lvlContainer);
		view = new LevelPlayerView(model);
		current = new int[3];
		controller = new AboutPlayerController(model, view);
		back = new BackPlayerController(model, view);
		continueControl = new ContinueController(model, view);
		exit = new ExitPlayerController(view);
		lvlSelect = new LevelSelectController(model, view);
		lvlSelectView = new LevelSelectView(model, view);
		playSelect = new PlaySelectedController(model, lvlSelectView, view);
		selectLevel = new SelectLevelController(lvlSelectView);
		
	}
	
	
	public void tearDown(){
			view.getFrame().dispose();
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
	
	public void testContinueButton(){
		button = view.getContinue();
		ActionEvent continuePress = buttonPress(button);
		continueControl.actionPerformed(continuePress);
		
		assertEquals(view.getCurrentView().getCurrentlyPlaying().getClass(), LevelView.class);
	}
	
	public void testPlaySelectLevel(){
		
		view.setCurrentView(new LevelSelectView(model, view));
		lvlSelectView.setSelectedLevel(lvlContainer);

		assertEquals(lvlSelectView.getSelectedLevel(), lvlContainer);
		
		button = lvlSelectView.getPlayButton();
		
		ActionEvent	playPress = buttonPress(button);
		playSelect.actionPerformed(playPress);
		
		assertEquals(model.getPlayingLevel(), lvlContainer);
	}
	

	/**
	 *  Should return null
	 */
	public void testGetCurrentlyPlaying()
	{
		aboutView = new AboutPlayerView(model, view);
		LevelView curPlay = aboutView.getCurrentlyPlaying();
		
		assertTrue(curPlay == null);
	}
	
	
	public void testEndLevel(){
		button = view.getContinue();
		ActionEvent continuePress = buttonPress(button);
		continueControl.actionPerformed(continuePress);
		
		endLevel = new EndLevelController(view);
		
		button = view.getCurrentView().getCurrentlyPlaying().getFinish();
		ActionEvent endLvl = buttonPress(button);
		endLevel.actionPerformed(endLvl);
		
		assertEquals(EndLevelView.class, view.getCurrentView().getClass());
	}
	
	public void testAutomaticEnd(){
		LightningLevel lightningLevel = new LightningLevel (1, "lightning0.xml", testLevels.getPlayableBullpen(), 
				testLevels.getInfiniteBullpen(), new Board(new Square[12][12], testBoardLogic), false, true);
		lvlContainerLightning = new LevelContainer("lightning0.xml", 0, 0, lightningLevel, 0);
		LevelView lvlView = new LevelView(model, view);
		lightningLevel.setMaxLimit(1);
		
		view.setCurrentView(new LevelSelectView(model, view));
		lvlSelectView.setSelectedLevel(lvlContainerLightning);

		assertEquals(lvlSelectView.getSelectedLevel(), lvlContainerLightning);
		
		button = lvlSelectView.getPlayButton();
		
		ActionEvent	playPress = buttonPress(button);
		playSelect.actionPerformed(playPress);
		
		assertEquals(model.getPlayingLevel(), lvlContainerLightning);
		lightningLevel.initialize(lvlView);
		
		limitCont = new LimitEndController (model, view);
		timeController = new TimeController (lightningLevel, new LevelView (model, view));
		
		timeController.actionPerformed(null);
		lightningLevel.decrementTime();
		PropertyChangeEvent timeChange = new PropertyChangeEvent(lvlView.getLimitLabel(), getName(), "<html>Time:<br><center>" + lightningLevel.getLimit() + "</center></html>", "<html>Time:<br><center>" + "0" + "</center></html>");
		limitCont.propertyChange(timeChange);
		
		assertEquals(view.getCurrentView().getClass(), EndLevelView.class);

	
	}
	
	
	

}
