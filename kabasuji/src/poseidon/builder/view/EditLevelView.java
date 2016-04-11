package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import poseidon.builder.controller.BackBuilderController;

public class EditLevelView extends JPanel implements IBuilderScreen
{
//	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	LevelBuilderView application;  // The top-level GUI object
	JButton deleteButton;  // To delete selected Level
	JButton editButton;  // To edit selected Level
	JButton addButton;  // To add selected Level to game
	JButton backButton;  // To return to the main menu (LevelBuilderView)
//	... addedLevels;  // Custom Levels added to the game
//	... savedLevels;  // Custom Levels saved but not added to the game


	/**
	 * Creates the panel
	 */
	public EditLevelView(LevelBuilderView view)
	{
		this.application = view;
//		getLevels();  // Fills addedLevels and savedLevels
		initialize();
	}
	
	
	public void initialize()
	{
		setLayout(null);
		
		JLabel title = new JLabel("Edit Level");
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(225, 9, 219, 101);
		add(title);
		
		JLabel inGameLabel = new JLabel("In Game");
		inGameLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inGameLabel.setBounds(25, 105, 120, 30);
		add(inGameLabel);
		
		JLabel inGPzlLabel = new JLabel("Puzzle Levels");
		inGPzlLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inGPzlLabel.setBounds(25, 160, 160, 30);
		add(inGPzlLabel);
		
		JLabel inGLtngLabel = new JLabel("Lightning Levels");
		inGLtngLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inGLtngLabel.setBounds(25, 200, 180, 30);
		add(inGLtngLabel);
		
		JLabel inGRlsLabel = new JLabel("Release Levels");
		inGRlsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inGRlsLabel.setBounds(25, 240, 160, 30);
		add(inGRlsLabel);
		
		JLabel notInGameLabel = new JLabel("Not in Game");
		notInGameLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		notInGameLabel.setBounds(25, 315, 140, 30);
		add(notInGameLabel);
		
		JLabel notGPzlLabel = new JLabel("Puzzle Levels");
		notGPzlLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		notGPzlLabel.setBounds(25, 370, 160, 30);
		add(notGPzlLabel);
		
		JLabel notGLtngLabel = new JLabel("Lightning Levels");
		notGLtngLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		notGLtngLabel.setBounds(25, 410, 180, 30);
		add(notGLtngLabel);
		
		JLabel notGRlsLabel = new JLabel("Release Levels");
		notGRlsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		notGRlsLabel.setBounds(25, 450, 160, 30);
		add(notGRlsLabel);
		
		editButton = new JButton("Edit");
		editButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		editButton.setBounds(508, 530, 120, 80);
		add(editButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		backButton.setBounds(25, 530, 120, 80);
		backButton.addActionListener(
				new BackBuilderController(application));
		add(backButton);
		
		addButton = new JButton("Add to Game");
		addButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		addButton.setBounds(317, 530, 165, 80);
		add(addButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		deleteButton.setBounds(171, 530, 120, 80);
		add(deleteButton);
		
		/*
		 * Create addedLevels and savedLevels selectors
		 */
	}

	
	/**
	 *  Updates the display when the model changes
	 */
	@Override
	public void update()
	{
		// TODO Probably don't need to do anything
		
	}

}
