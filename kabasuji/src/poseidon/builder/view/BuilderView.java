package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import poseidon.builder.controller.BackBuilderController;
import poseidon.common.controller.BoardController;
import poseidon.common.controller.BullpenController;
import poseidon.common.controller.HorizontalFlipController;
import poseidon.common.controller.RotateCCWController;
import poseidon.common.controller.RotateCWController;
import poseidon.common.controller.VerticalFlipController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.ILevelView;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelModel;

import javax.swing.ScrollPaneConstants;

/**
 *  Creates the GUI used to create Kabasuji Levels.
 *  
 *  @author Alex Titus
 */
public class BuilderView extends JPanel implements IBuilderScreen, ILevelView
{
	/** The top-level GUI object. */
	LevelBuilderView application;
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel topmodel;
	/** The state of the Level under construction. */
	LevelModel model;
	/** The game Board being built. */
	BoardView board;
	/** To hold Infinite Bullpen. */
	JScrollPane bullpenContainer;
	/** The Infinite Bullpen, to help build the Board and for Hints. */
	BullpenView bullpen;
	/** To save the Level to a file. */
	JButton saveButton;
	/** To undo change. */
	JButton undoButton;
	/** To redo change. */
	JButton redoButton;
	/** To add Hint to Board. */
	JButton addHintButton;
	/** To make changes to the Playable Bullpen. */
	JButton editPlayBullpenButton;
	/** To reset Board. */
	JButton resetButton;
	/** To flip Piece vertically. */
	JButton verFlipButton;
	/** To flip Piece horizontally. */
	JButton horFlipButton;
	/** To rotate a Piece clock-wise. */
	JButton rotateCWButton;
	/** To rotate a Piece counter-clock-wise. */
	JButton rotateCCWButton;
	/** To return to the Main Menu (LevelBuilderView). */
	JButton quitButton;
	/** To change the Board's width. */
	JTextField colSize;
	/** To change the Board's height. */
	JTextField rowSize;
	/** To change the Level's limit. */
	JFormattedTextField limitInput;
	JLabel title;
	JLabel limitLabel;


	/**
	 *  Constructor.
	 */
	public BuilderView(LevelBuilderModel model, LevelBuilderView view)
	{
		this.topmodel = model;
		application = view;
		this.model = topmodel.getBuildingLevel().getLevel();
		
		initialize();
	}
	
	
	/**
	 *  Creates and adds the view objects for each of the game components.
	 */
	void initialize()
	{
		setLayout(null);
		
		board = new BoardView(model.getBoard());
		board.setBounds(160, 250, 361, 361);
		add(board);
		
		bullpen = new BullpenView(model.getInfiniteBullpen());
		bullpenContainer = new JScrollPane(bullpen);
		bullpenContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bullpenContainer.setBounds(160, 80, 360, 78);
		add(bullpenContainer);
		
		BoardController boardController = new BoardController(model, this);
		board.addMouseListener(boardController);
		board.addMouseMotionListener(boardController);
		bullpen.addMouseListener(new BullpenController(model.getInfiniteBullpen(), bullpen, board));
		
		rotateCCWButton = new JButton("<");
		rotateCCWButton.setBounds(540, 160, 45, 45);
		rotateCCWButton.addActionListener(new RotateCCWController(bullpen));
		add(rotateCCWButton);
		
		horFlipButton = new JButton("<html><center>Horizontal<br>Flip</center></html>");
		horFlipButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		horFlipButton.setBounds(540, 100, 130, 50);
		horFlipButton.addActionListener(new HorizontalFlipController(bullpen));
		add(horFlipButton);
		
		verFlipButton = new JButton("<html><center>Vertical<br>Flip</center></html>");
		verFlipButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		verFlipButton.setBounds(540, 40, 130, 50);
		verFlipButton.addActionListener(new VerticalFlipController(bullpen));
		add(verFlipButton);
		
		rotateCWButton = new JButton(">");
		rotateCWButton.setBounds(623, 160, 45, 45);
		rotateCWButton.addActionListener(new RotateCWController(bullpen));
		add(rotateCWButton);
		
		title = new JLabel(model.getLevelName());
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(176, 22, 310, 45);
		add(title);
		
		// Default limit display for Puzzle and Release Levels
		String limitDisplay = "Move Limit:";
		if(this.model.getGameMode() == LevelModel.LIGHTNING)  // If Lightning Level
		{
			// Then label limit as time remaining
			limitDisplay = "Time Limit:";
		}
		limitLabel = new JLabel(limitDisplay);
		limitLabel.setBackground(Color.WHITE);
		limitLabel.setBounds(555, 220, 105, 35);
		limitLabel.setHorizontalAlignment(SwingConstants.LEFT);
		limitLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(limitLabel);
		
		limitInput = new JFormattedTextField();
		limitInput.setValue(model.getLimit());
		limitInput.setBounds(555, 255, 110, 30);
		limitInput.setColumns(10);
		add(limitInput);
		
		
		addHintButton = new JButton("Hint");
		addHintButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		addHintButton.setBounds(555, 375, 110, 45);
		add(addHintButton);
		
		editPlayBullpenButton = new JButton("Choose Pieces");
		editPlayBullpenButton.setBounds(555, 450, 110, 90);
		add(editPlayBullpenButton);
		
		undoButton = new JButton("Undo");
		undoButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		undoButton.setBounds(10, 160, 110, 45);
		add(undoButton);
		
		redoButton = new JButton("Redo");
		redoButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		redoButton.setBounds(10, 215, 110, 45);
		add(redoButton);
		
		JLabel dimensionLabel = new JLabel("Size:");
		dimensionLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		dimensionLabel.setBounds(35, 280, 55, 30);
		add(dimensionLabel);
		
		rowSize = new JTextField();
		rowSize.setColumns(10);
		rowSize.setBounds(10, 320, 45, 30);
		add(rowSize);
		
		colSize = new JTextField();
		colSize.setColumns(10);
		colSize.setBounds(70, 320, 45, 30);
		add(colSize);
		
		JLabel xLabel = new JLabel("X");
		xLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xLabel.setBounds(55, 327, 15, 16);
		add(xLabel);
		
		quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		quitButton.setBounds(10, 405, 110, 55);
		quitButton.addActionListener(new BackBuilderController(topmodel, application));
		add(quitButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		saveButton.setBounds(10, 500, 110, 55);
		add(saveButton);
	}

	
	/**
	 *  Updates the display when the model is changed.
	 */
	@Override
	public Boolean modelUpdated()
	{
		// TODO Auto-generated method stub
		return false;
	}

				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/**
	 *  Returns the BoardView object associated with this BuilderView.
	 */
	@Override
	public BoardView getBoard()
	{
		return board;
	}


	/**
	 *  Returns the BullpenView object (the rendered infinite Bullpen) associated with this BuilderView.
	 */
	@Override
	public BullpenView getBullpen()
	{
		return bullpen;
	}


	/**
	 *  Returns the LevelModel object associated with this BuilderView. 
	 */
	@Override
	public LevelModel getModel()
	{
		return model;
	}
}
