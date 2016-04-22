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
import poseidon.common.controller.PuzzleBoardController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.LevelSelectController;

/**
 *  Renders the Kabasuji game Level.
 *  
 * @author Alex Titus
 */
public class LevelView extends JPanel
{
	LevelPlayerModel topModel;  // The top-level representation of the game
	LevelModel model;  // The state of the Level
	LevelPlayerView game;  // The top-level GUI object
	BullpenView bullpen;  // The graphical representation of the Bullpen
	BoardView board;  // The graphical representation of the Board
	JButton resetButton;  // To return the board to its initial state
	JButton quitButton;  // To return to the Level Select screen (LevelSelectView)
	JButton finishButton;  // To prematurely end a Level
	JButton rotateCWButton;  // To rotate a Piece clockwise
	JButton rotateCCWButton;  // To rotate a Piece counter-clockwise
	JButton flipButton;  // To flip a Piece
	JScrollPane bullpenContainer;  // Allows Bullpen to be scrolled if more than 7 Pieces
	JLabel levelTitle;  // Name of the Level
	JLabel scoreLabel;  // The label for the star-based score
	ScoreView scoreView;  // The score for the Level, in stars
	JLabel limitView;  // The current move/time limit for the Level

	/**
	 * Create the panel.
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
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(0, 191, 255));
		leftPanel.setBounds(0, 0, 150, 615);
		add(leftPanel);
		leftPanel.setLayout(null);
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(10, 290, 130, 40);
		leftPanel.add(resetButton);
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
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
		rotateCCWButton.setBounds(10, 125, 45, 45);
		rightPanel.add(rotateCCWButton);
		
		rotateCWButton = new JButton(">");
		rotateCWButton.setBounds(93, 125, 45, 45);
		rightPanel.add(rotateCWButton);
		
		flipButton = new JButton("Flip");
		flipButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		flipButton.setBounds(10, 75, 130, 40);
		rightPanel.add(flipButton);
		
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scoreLabel.setBounds(10, 250, 115, 25);
		rightPanel.add(scoreLabel);
		
		scoreView = new ScoreView(this.model, this);
		scoreView.setBounds(10, 280, 115, 35);
		rightPanel.add(scoreView);
		
		bullpen = new BullpenView(this.model.getPlayableBullpen());
		bullpenContainer = new JScrollPane();
		bullpenContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bullpenContainer.setViewportView(bullpen);
		bullpenContainer.setBounds(160, 85, 360, 78);
		add(bullpenContainer);
		
		board = new BoardView(this.model.getBoard());
		board.setBounds(160, 195, 361, 361);
		add(board);
		
		// Add Bullpen controllers
		bullpen.addMouseListener(new BullpenController(this.model.getPlayableBullpen(), bullpen));
		
		if(this.model.getGameMode() == LevelModel.PUZZLE)  // If Puzzle Level
		{
			// Then label limit as Moves remaining and add appropriate Board controller
			String limitDisplay = "<html>Moves:<br><center>" + this.model.getLimit() + "</center></html>";
			limitView = new JLabel(limitDisplay);
			limitView.setBackground(Color.WHITE);
			limitView.setBounds(10, 340, 140, 55);
			rightPanel.add(limitView);
			limitView.setHorizontalAlignment(SwingConstants.LEFT);
			limitView.setFont(new Font("Tahoma", Font.PLAIN, 25));
			
			board.addMouseListener(new PuzzleBoardController(this.model.getBoard(), this.board,
					this.model.getPlayableBullpen(), this.bullpen));
		}
		else if(this.model.getGameMode() == LevelModel.LIGHTNING)  // If Lightning Level
		{
			String limitDisplay = "<html>Time:<br><center>" + this.model.getLimit() + "</center></html>";
			limitView = new JLabel(limitDisplay);
			limitView.setBackground(Color.WHITE);
			limitView.setBounds(10, 340, 140, 55);
			rightPanel.add(limitView);
			limitView.setHorizontalAlignment(SwingConstants.LEFT);
			limitView.setFont(new Font("Tahoma", Font.PLAIN, 25));
			
//			board.addMouseListener(new LightningBoardController(this.model, board));
		}
		else if(this.model.getGameMode() == LevelModel.RELEASE)  // If Release Level
		{
			String limitDisplay = "<html>Moves:<br><center>" + this.model.getLimit() + "</center></html>";
			limitView = new JLabel(limitDisplay);
			limitView.setBackground(Color.WHITE);
			limitView.setBounds(10, 340, 140, 55);
			rightPanel.add(limitView);
			limitView.setHorizontalAlignment(SwingConstants.LEFT);
			limitView.setFont(new Font("Tahoma", Font.PLAIN, 25));
			
//			board.addMouseListener(new ReleaseBoardController(this.model, board));
		}
	}

	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public BullpenView getBullpen()
	{
		return bullpen;
	}

	public BoardView getBoard()
	{
		return board;
	}

	public void setBullpen(BullpenView bullpen)
	{
		this.bullpen = bullpen;
	}

	public void setBoard(BoardView board)
	{
		this.board = board;
	}
	
	
}
