package poseidon.player.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import poseidon.common.controller.BullpenController;
import poseidon.common.controller.BoardController;
import poseidon.common.controller.HorizontalFlipController;
import poseidon.common.controller.RotateCCWController;
import poseidon.common.controller.RotateCWController;
import poseidon.common.controller.VerticalFlipController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.ILevelView;
import poseidon.common.view.IModelUpdated;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.LevelSelectController;
import poseidon.player.controller.ResetController;

/**
 *  Renders the Kabasuji game Level for playing Levels.
 *  
 * @author Alex Titus
 */
public class LevelView extends JPanel implements IModelUpdated, ILevelView
{
	/** The top-level representation of the game. */
	LevelPlayerModel topModel;
	/** The state of the Level. */
	LevelModel model;
	/** The top-level GUI object. */
	LevelPlayerView game;
	/** The graphical representation of the Bullpen. */
	BullpenView bullpen;
	/** The graphical representation of the Board. */
	BoardView board;
	/** To return the board to its initial state. */
	JButton resetButton;
	/** To return to the Level Select screen (LevelSelectView). */
	JButton quitButton;
	/** To prematurely end a Level. */
	JButton finishButton;
	/** To rotate a Piece clockwise. */
	JButton rotateCWButton;
	/** To rotate a Piece counter-clockwise. */
	JButton rotateCCWButton;
	/** To flip a Piece horizontally. */
	JButton flipHButton;
	/** To flip a Piece vertically. */
	JButton flipVButton;
	/** Allows Bullpen to be scrolled if more than 7 Pieces. */
	JScrollPane bullpenContainer;
	/** Name of the Level. */
	JLabel levelTitle;
	/** The label for the star-based score. */
	JLabel scoreLabel;
	/** The score for the Level, in stars. */
	ScoreView scoreView;
	/** The current move/time limit for the Level. */
	JLabel limitView;

	/**
	 *  Constructor.
	 *  
	 *  @param model  the overall model of the game
	 *  @param view  the base GUI object
	 */
	public LevelView(LevelPlayerModel model, LevelPlayerView view)
	{
		topModel = model;
		this.model = topModel.getPlayingLevel();
		game = view;
		setLayout(null);
		
		levelTitle = new JLabel(this.model.getLevelName());
		levelTitle.setOpaque(true);
		levelTitle.setFocusable(false);
		levelTitle.setBackground(new Color(0, 191, 255));
		levelTitle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		levelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		levelTitle.setBounds(150, 0, 380, 75);
		add(levelTitle);
		
		bullpen = new BullpenView(this.model.getPlayableBullpen());
		bullpenContainer = new JScrollPane();
		bullpenContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bullpenContainer.setViewportView(bullpen);
		bullpenContainer.setBounds(160, 85, 360, 78);
		add(bullpenContainer);
		
		board = new BoardView(this.model.getBoard());
		board.setBounds(160, 195, 361, 361);
		add(board);
		
		// Add Bullpen and Board controllers
		BullpenController bullpenCont = new BullpenController(this.model.getPlayableBullpen(), bullpen, board);
		bullpen.addMouseListener(bullpenCont);
		BoardController boardController = new BoardController(this.model, this);
		board.addMouseListener(boardController);
		board.addMouseMotionListener(boardController);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(0, 191, 255));
		leftPanel.setBounds(0, 0, 150, 615);
		add(leftPanel);
		leftPanel.setLayout(null);
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		resetButton.setBounds(10, 290, 130, 40);
		resetButton.addActionListener(new ResetController(topModel, game));
		leftPanel.add(resetButton);
		
		quitButton = new JButton("Quit");
		quitButton.addActionListener(
				new LevelSelectController(topModel, game));
		quitButton.setBounds(10, 340, 130, 40);
		leftPanel.add(quitButton);
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		finishButton = new JButton("<html><center>Finish<br>Level</center></html>");
		finishButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		finishButton.setBounds(10, 508, 130, 87);
		leftPanel.add(finishButton);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(0, 191, 255));
		rightPanel.setBounds(530, 0, 150, 615);
		add(rightPanel);
		rightPanel.setLayout(null);
		
		rotateCCWButton = new JButton("<");
		rotateCCWButton.setBounds(10, 160, 45, 45);
		rotateCCWButton.addActionListener(new RotateCCWController(bullpen));
		rightPanel.add(rotateCCWButton);
		
		rotateCWButton = new JButton(">");
		rotateCWButton.setBounds(93, 160, 45, 45);
		rotateCWButton.addActionListener(new RotateCWController(bullpen));
		rightPanel.add(rotateCWButton);
		
		flipHButton = new JButton("<html><center>Horizontal<br>Flip</center></html>");
		flipHButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		flipHButton.setBounds(10, 100, 130, 50);
		flipHButton.addActionListener(new HorizontalFlipController(bullpen));
		rightPanel.add(flipHButton);
		
		flipVButton = new JButton("<html><center>Vertical<br>Flip</center></html>");
		flipVButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		flipVButton.setBounds(10, 40, 130, 50);
		flipVButton.addActionListener(new VerticalFlipController(bullpen));
		rightPanel.add(flipVButton);
		
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scoreLabel.setBounds(10, 250, 115, 25);
		rightPanel.add(scoreLabel);
		
		scoreView = new ScoreView(this.model, this);
		scoreView.setBounds(10, 280, 115, 35);
		rightPanel.add(scoreView);
		
		// Default limit display for Puzzle and Release Levels
		String limitDisplay = "<html>Moves:<br><center>" + this.model.getLimit() + "</center></html>";
		if(this.model.getGameMode() == LevelModel.LIGHTNING)  // If Lightning Level
		{
			limitDisplay = "<html>Time:<br><center>" + this.model.getLimit() + "</center></html>";
		}
		limitView = new JLabel(limitDisplay);
		limitView.setBackground(Color.WHITE);
		limitView.setBounds(10, 340, 140, 55);
		limitView.setHorizontalAlignment(SwingConstants.LEFT);
		limitView.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rightPanel.add(limitView);
	}
	
	
	/**
	 *  Updates display when underlying model changes.
	 *  
	 *  Passes updates down to bullpen, board, score, and updates the limit.
	 */
	@Override
	public Boolean modelUpdated()
	{
		bullpen.modelUpdated();
		board.modelUpdated();
		scoreView.modelUpdated();
		
		switch(model.getGameMode())
		{
		case LevelModel.PUZZLE:
			limitView.setText("<html>Moves:<br><center>" + model.getLimit() + "</center></html>");
			break;
		case LevelModel.LIGHTNING:
			limitView.setText("<html>Time:<br><center>" + model.getLimit() + "</center></html>");
			break;
		case LevelModel.RELEASE:
			limitView.setText("<html>Moves:<br><center>" + model.getLimit() + "</center></html>");
			break;
		}
		
		repaint();
		
		return true;
	}

	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** Returns the model of the Level. */
	public LevelModel getModel()
	{
		return model;
	}
	/** Returns the GUI representation of the Bullpen. */
	public BullpenView getBullpen()
	{
		return bullpen;
	}
	/** Returns the GUI representation of the Board. */
	public BoardView getBoard()
	{
		return board;
	}
	/** Sets the GUI representation of the Bullpen. */
	public void setBullpen(BullpenView bullpen)
	{
		this.bullpen = bullpen;
	}
	/** Sets the GUI representation of the Board. */
	public void setBoard(BoardView board)
	{
		this.board = board;
	}
	
	public JButton getCCW(){
		return rotateCCWButton;
	}
	
	public JButton getCW(){
		return rotateCWButton;
	}
	
	public JButton getHFlip(){
		return flipHButton;
	}
	
	public JButton getVFlip(){
		return flipVButton;
	}
	
	public JButton getReset(){
		return resetButton;
	}
	
	public JButton getQuit(){
		return quitButton;
	}
	
	public JButton getFinish(){
		return finishButton;
	}
}
