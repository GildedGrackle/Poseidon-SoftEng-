package poseidon.player.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
import poseidon.entities.ReleaseLevel;
import poseidon.entities.ReleaseNumber;
import poseidon.player.controller.EndLevelController;
import poseidon.player.controller.LevelSelectController;
import poseidon.player.controller.LimitEndController;
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
	/** Images for the rotate button Icons*/ 
	Image rotateCW, rotateCCW, icon;
	/** For Release levels, to show numbers collected. */
	JPanel numbersCollected;
	/** The logo. */
	JLabel poseidon;

	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the overall model of the game
	 *  @param view  the base GUI object
	 */
	public LevelView(LevelPlayerModel model, LevelPlayerView view)
	{
		topModel = model;
		this.model = topModel.getPlayingLevel().getLevel();
		game = view;
		setLayout(null);
		setBackground(new Color(0, 191, 255));
		
		levelTitle = new JLabel(this.model.getLevelName());
		levelTitle.setOpaque(true);
		levelTitle.setFocusable(false);
		levelTitle.setBackground(new Color(0, 191, 255));
		levelTitle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		levelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		levelTitle.setBounds(155, 0, 380, 75);
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
		BoardController boardController = new BoardController(game, this.model, this);
		board.addMouseListener(boardController);
		board.addMouseMotionListener(boardController);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(0, 191, 255));
		leftPanel.setBounds(0, 0, 155, 631);
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
		finishButton.addActionListener(new EndLevelController(game));
		leftPanel.add(finishButton);
				
		rotateCCWButton = new JButton("");
		rotateCCWButton.setBounds(545, 160, 45, 45);
		rotateCCWButton.addActionListener(new RotateCCWController(bullpen));
		add(rotateCCWButton);
		
		rotateCWButton = new JButton("");
		rotateCWButton.setBounds(628, 160, 45, 45);
		rotateCWButton.addActionListener(new RotateCWController(bullpen));
		add(rotateCWButton);
		
		poseidon = new JLabel("");
		poseidon.setBounds(35, 40, 100, 100);
		leftPanel.add(poseidon);

		try {
			rotateCCW  = ImageIO.read(getClass().getClassLoader().getResource("images/rotateCCW.png"));
			rotateCW  = ImageIO.read(getClass().getClassLoader().getResource("images/rotateCW.png"));
			icon = ImageIO.read(getClass().getClassLoader().getResource("images/Logo.png"));
			rotateCCWButton.setIcon(new ImageIcon(rotateCCW));
			rotateCWButton.setIcon(new ImageIcon(rotateCW));
			poseidon.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
		}
		
		flipHButton = new JButton("<html><center>Horizontal<br>Flip</center></html>");
		flipHButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		flipHButton.setBounds(545, 100, 130, 50);
		flipHButton.addActionListener(new HorizontalFlipController(bullpen));
		add(flipHButton);
		
		flipVButton = new JButton("<html><center>Vertical<br>Flip</center></html>");
		flipVButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		flipVButton.setBounds(545, 40, 130, 50);
		flipVButton.addActionListener(new VerticalFlipController(bullpen));
		add(flipVButton);
		
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scoreLabel.setBounds(545, 220, 115, 25);
		add(scoreLabel);
		
		scoreView = new ScoreView(this.model);
		scoreView.setBounds(545, 250, 115, 35);
		add(scoreView);
		
		// Default limit display for Puzzle and Release Levels
		String limitDisplay = "<html>Moves:<br><center>" + this.model.getLimit() + "</center></html>";
		if(this.model.getGameMode() == LevelModel.LIGHTNING)  // If Lightning Level
		{
			limitDisplay = "<html>Time:<br><center>" + this.model.getLimit() + "</center></html>";
		}
		limitView = new JLabel(limitDisplay);
		limitView.setBackground(Color.WHITE);
		limitView.setBounds(545, 300, 140, 55);
		limitView.setHorizontalAlignment(SwingConstants.LEFT);
		limitView.setFont(new Font("Tahoma", Font.PLAIN, 25));
		limitView.addPropertyChangeListener("text", new LimitEndController(topModel, game));
		add(limitView);
		
		// Release only, for numbers collected
		if(this.model.getGameMode() == LevelModel.RELEASE)
		{
			JLabel numbersCollectedLabel = new JLabel("<html>Numbers<br>Collected:</html>");
			numbersCollectedLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			numbersCollectedLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			numbersCollectedLabel.setBounds(545, 380, 115, 50);
			add(numbersCollectedLabel);
		}
	}
	
	
	/**
	 *  Updates display when underlying model changes.
	 *  
	 *  Passes updates down to bullpen, board, score, and updates the limit.
	 */
	@Override
	public Boolean modelUpdated()
	{
		// ------- Send update requests out -------
		bullpen.modelUpdated();
		board.modelUpdated();
		scoreView.modelUpdated();
		
		// ------- Update limit -------
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
	
	
	/**
	 *  Used to draw the numbers collected in Release, otherwise normal.
	 *  
	 *  @param g  the Graphics object to render this object
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// If Release level
		if(model.getGameMode() == LevelModel.RELEASE)
		{
			// Then draw numbers collected
			Graphics drawer = g.create();
			ReleaseLevel actualModel = (ReleaseLevel) model;
			ArrayList<Integer> numbersCollected = new ArrayList<Integer>();
			
			// ------- Draw grid and numbers collected -------
			for(int i = 0; i < 3; i++)
			{
				switch(i)
				{
				case 0:
					numbersCollected = actualModel.getRedNumbers();
					break;
				case 1:
					numbersCollected = actualModel.getGreenNumbers();
					break;
				case 2:
					numbersCollected = actualModel.getYellowNumbers();
				}
				
				for(int j = 0; j < 6; j++)
				{
					// Draw grid
					drawer.setColor(Color.lightGray);
					drawer.fillRect(555 + BoardView.SQUARE_SIZE * i, 440 + BoardView.SQUARE_SIZE * j,
							BoardView.SQUARE_SIZE, BoardView.SQUARE_SIZE);
					drawer.setColor(Color.black);
					drawer.drawRect(555 + BoardView.SQUARE_SIZE * i, 440 + BoardView.SQUARE_SIZE * j,
							BoardView.SQUARE_SIZE, BoardView.SQUARE_SIZE);
					// If number collected
					if(numbersCollected.contains(j + 1))
					{
						// Then draw that too
						// Set graphics properties
						drawer.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
						switch(i + 1)
						{
						case ReleaseNumber.RED:
							drawer.setColor(Color.red);
							break;
						case ReleaseNumber.GREEN:
							drawer.setColor(Color.green);
							break;
						case ReleaseNumber.YELLOW:
							drawer.setColor(new Color(0xD0, 0xD0, 0));
							break;
						}

						// Draw number
						drawer.drawString("" + (j + 1), 555 + BoardView.SQUARE_SIZE * i + 10,
								440 + BoardView.SQUARE_SIZE * j + 22);
					}
				}
			}
		}
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The model of the Level. */
	public LevelModel getModel()
	{
		return model;
	}
	/** @return  The GUI representation of the bullpen. */
	public BullpenView getBullpen()
	{
		return bullpen;
	}
	/** @return  The GUI representation of the board. */
	public BoardView getBoard()
	{
		return board;
	}
	/** 
	 *  Sets the GUI representation of the bullpen.
	 * 
	 *  @param bullpen  the new bullpen
	 */
	public void setBullpen(BullpenView bullpen)
	{
		this.bullpen = bullpen;
	}
	/** 
	 *  Sets the GUI representation of the board.
	 *  
	 *  @param board  the new board
	 */
	public void setBoard(BoardView board)
	{
		this.board = board;
	}
	
	/** @return  The "Rotate Counter-clockwise" button. */
	public JButton getCCW(){
		return rotateCCWButton;
	}
	
	/** @return  The "Rotate Clockwise" button. */
	public JButton getCW(){
		return rotateCWButton;
	}
	
	/** @return  The "Horizontal Flip" button. */
	public JButton getHFlip(){
		return flipHButton;
	}
	
	/** @return  The "Vertical Flip" button. */
	public JButton getVFlip(){
		return flipVButton;
	}
	
	/** @return  The "Reset" button. */
	public JButton getReset(){
		return resetButton;
	}
	
	/** @return  The "Quit" button. */
	public JButton getQuit(){
		return quitButton;
	}
	
	/** @return  The "Finish Level" button. */
	public JButton getFinish(){
		return finishButton;
	}
	
	public JLabel getLimitLabel(){
		return limitView;
	}
}
