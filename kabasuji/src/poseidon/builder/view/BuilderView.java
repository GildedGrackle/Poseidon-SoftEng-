package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import poseidon.builder.controller.BackBuilderController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.entities.LevelBuilderModel;

import javax.swing.ScrollPaneConstants;

public class BuilderView extends JPanel implements IBuilderScreen
{
	LevelBuilderView application;  // The top-level GUI object
	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	BoardView board;  // The game Board being built
	JScrollPane bullpenContainer;  // To hold Infinite Bullpen
	BullpenView bullpen;  // The Infinite Bullpen, to help build the Board and for Hints
	JButton saveButton;  // To save the Level to a file
	JButton undoButton;  // To undo change
	JButton redoButton;  // To redo change
	JButton addHintButton;  // To add Hint to Board
	JButton editPlayBullpenButton;  // To make changes to the Playable Bullpen
	JButton resetButton;  // To reset Board
	JButton flipButton;  // To flip Piece
	JButton rotateCWButton;  // To rotate a Piece clock-wise
	JButton rotateCCWButton;  // To rotate a Piece counter-clock-wise
	JButton quitButton;  // To return to the Main Menu (LevelBuilderView)
	JTextField colSize;  // To change the Board's width
	JTextField rowSize;  // To change the Board's height
	JTextField limitInput;  // To change the Level's limit


	/**
	 * Create the panel.
	 */
	public BuilderView(LevelBuilderModel model, LevelBuilderView view)
	{
		this.model = model;
		application = view;
		
		initialize();
	}
	
	
	/**
	 *  TODO
	 */
	public void initialize()
	{
		setLayout(null);
		
		rotateCCWButton = new JButton("<");
		rotateCCWButton.setBounds(555, 160, 45, 45);
		add(rotateCCWButton);
		
		flipButton = new JButton("Flip");
		flipButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		flipButton.setBounds(555, 95, 110, 55);
		add(flipButton);
		
		rotateCWButton = new JButton(">");
		rotateCWButton.setBounds(621, 160, 45, 45);
		add(rotateCWButton);
		
		board = new BoardView(model.getBuildingLevel().getLevel().getBoard());
		board.setBounds(160, 250, 361, 361);
		add(board);
		
		bullpen = new BullpenView(model.getBuildingLevel().getLevel().getInfiniteBullpen());
		bullpenContainer = new JScrollPane(bullpen);
		bullpenContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bullpenContainer.setBounds(160, 80, 360, 70);
		add(bullpenContainer);
		
		JLabel title = new JLabel("<Builder: Type>");
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(176, 22, 310, 45);
		add(title);
		
		JLabel limitLabel = new JLabel("Countdown:");
		limitLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		limitLabel.setBounds(555, 220, 105, 35);
		add(limitLabel);
		
		limitInput = new JTextField();
		limitInput.setBounds(555, 255, 110, 30);
		add(limitInput);
		limitInput.setColumns(10);
		
		addHintButton = new JButton("Hint");
		addHintButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		addHintButton.setBounds(555, 375, 110, 45);
		add(addHintButton);
		
		editPlayBullpenButton = new JButton("Choose Shapes");
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
		quitButton.addActionListener(new BackBuilderController(model, application));
		add(quitButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		saveButton.setBounds(10, 500, 110, 55);
		add(saveButton);
	}

	
	/**
	 *  Updates the display when the model is changed
	 */
	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}
}
