package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import poseidon.builder.controller.BackBuilderController;
import poseidon.builder.controller.ColSizeController;
import poseidon.builder.controller.LimitController;
import poseidon.builder.controller.RowSizeController;
import poseidon.common.controller.BoardController;
import poseidon.common.controller.BullpenController;
import poseidon.common.controller.HorizontalFlipController;
import poseidon.common.controller.RotateCCWController;
import poseidon.common.controller.RotateCWController;
import poseidon.common.controller.VerticalFlipController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.ILevelView;
import poseidon.entities.Board;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelModel;

import javax.swing.ScrollPaneConstants;
import javax.swing.text.NumberFormatter;

/**
 *  Creates the GUI used to create Kabasuji levels.
 *  
 *  @author Alex Titus
 */
public class BuilderView extends JPanel implements IBuilderScreen, ILevelView
{
	/** The top-level GUI object. */
	LevelBuilderView application;
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel topmodel;
	/** The state of the level under construction. */
	LevelModel model;
	/** The GUI game board being built. */
	BoardView board;
	/** To hold GUI infinite bullpen. */
	JScrollPane bullpenContainer;
	/** The GUI infinite bullpen, to help build the board and for hints. */
	BullpenView bullpen;
	/** To save the level to a file. */
	JButton saveButton;
	/** To undo change. */
	JButton undoButton;
	/** To redo change. */
	JButton redoButton;
	/** To add hint to board. */
	JButton addHintButton;
	/** To make changes to the playable bullpen. */
	JButton editPlayBullpenButton;
	/** To reset board. */
	JButton resetButton;
	/** To flip piece vertically. */
	JButton verFlipButton;
	/** To flip piece horizontally. */
	JButton horFlipButton;
	/** To rotate a piece clock-wise. */
	JButton rotateCWButton;
	/** To rotate a piece counter-clock-wise. */
	JButton rotateCCWButton;
	/** To return to the main menu (LevelBuilderView). */
	JButton quitButton;
	/** To change the board's width. */
	JFormattedTextField colSizeInput;
	/** To change the board's height. */
	JFormattedTextField rowSizeInput;
	/** To change the level's limit. */
	JFormattedTextField limitInput;
	/** The name of this level. */
	JLabel title;
	/** Label for the limit input field. */
	JLabel limitLabel;


	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level model of the application
	 *  @param view  the top-level GUI of the application
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
		
		// Add board and bullpen controllers
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
		
		// Default limit display for Puzzle and Release levels
		String limitDisplay = "Move Limit:";
		if(this.model.getGameMode() == LevelModel.LIGHTNING)  // If Lightning level
		{
			// Then label limit as time instead of moves
			limitDisplay = "Time Limit:";
		}
		limitLabel = new JLabel(limitDisplay);
		limitLabel.setBackground(Color.WHITE);
		limitLabel.setBounds(555, 220, 105, 35);
		limitLabel.setHorizontalAlignment(SwingConstants.LEFT);
		limitLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(limitLabel);
		
		NumberFormat limitFormat = NumberFormat.getIntegerInstance();
		limitFormat.setMinimumIntegerDigits(1);
		limitFormat.setMaximumIntegerDigits(5);
		NumberFormatter limitFormatter = new NumberFormatter(limitFormat);
		limitFormatter.setMinimum(new Integer(1));
		limitFormatter.setMaximum(new Integer(99999));
		
		limitInput = new JFormattedTextField(limitFormatter);
		limitInput.setValue(model.getLimit());
		limitInput.setBounds(555, 255, 110, 30);
		limitInput.setColumns(10);
		limitInput.addPropertyChangeListener("value", new LimitController(this.getModel()));
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
		
		NumberFormat boardSizeFormat = NumberFormat.getIntegerInstance();
		boardSizeFormat.setMinimumIntegerDigits(1);
		boardSizeFormat.setMaximumIntegerDigits(2);
		NumberFormatter boardSizeFormatter = new NumberFormatter(boardSizeFormat);
		boardSizeFormatter.setMinimum(new Integer(1));
		boardSizeFormatter.setMaximum(new Integer(Board.MAXCOLS));  // Board is square, so shouldn't matter
		
		rowSizeInput = new JFormattedTextField(boardSizeFormatter);
		rowSizeInput.setValue(Board.MAXROWS);
		rowSizeInput.setToolTipText("Enter new number of rows here.");
		rowSizeInput.setColumns(10);
		rowSizeInput.setBounds(10, 320, 45, 30);
		rowSizeInput.addPropertyChangeListener("value", new RowSizeController(this));
		add(rowSizeInput);
		
		colSizeInput = new JFormattedTextField(boardSizeFormatter);
		colSizeInput.setValue(Board.MAXCOLS);
		colSizeInput.setToolTipText("Enter new number of columns here.");
		colSizeInput.setColumns(10);
		colSizeInput.setBounds(70, 320, 45, 30);
		colSizeInput.addPropertyChangeListener("value", new ColSizeController(this));
		add(colSizeInput);
		
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
		bullpen.modelUpdated();
		board.modelUpdated();
		
		repaint();
		
		return true;
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
	 *  @return  the BullpenView object (the rendered infinite Bullpen) associated with this BuilderView
	 */
	@Override
	public BullpenView getBullpen()
	{
		return bullpen;
	}


	/**
	 *  @return  the LevelModel object associated with this BuilderView
	 */
	@Override
	public LevelModel getModel()
	{
		return model;
	}
	
	
	/** @return  the JFormattedTextField responsible for the board's row size */
	public JFormattedTextField getRowSizeInput()
	{
		return rowSizeInput;
	}
	
	
	/** @return  the JFormattedTextField responsible for the board's column size */
	public JFormattedTextField getColSizeInput()
	{
		return colSizeInput;
	}
}
