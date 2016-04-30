package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;

import javax.swing.JButton;

import poseidon.builder.controller.BackBuilderController;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;

/**
 *  Screen for performing operations on saved custom Levels.
 *  
 *  Can add Levels to the game, remove added Levels from the game,
 *  edit Levels, and delete Levels.
 *  
 * @author Alex Titus
 */
public class EditLevelView extends JPanel implements IBuilderScreen
{
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView application;
	/** To delete selected Level. */
	JButton deleteButton;
	/** To edit selected Level. */
	JButton editButton;
	/** To add selected Level to game. */
	JButton addButton;
	/** To return to the main menu (LevelBuilderView). */
	JButton backButton;
	/** To hold the added levels. */
	JScrollPane addLevelsContainer;
	/** To hold the saved levels. */
	JScrollPane savedLevelsContainer;
	/** Custom Levels added to the game. */
	SelectableEditLevelsView addedLevels;
	/** Custom Levels saved but not added to the game. */
	SelectableEditLevelsView savedLevels;
	/** The level selected to edit by the user. */
	LevelContainer selectedLevel;


	/**
	 *  Constructor.
	 * 
	 *  @param view  the previous screen, the one returned to by "Back" button
	 */
	public EditLevelView(LevelBuilderModel model, LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
		initialize();
	}
	
	
	/**
	 *  Creates the panel.
	 */
	void initialize()
	{
		setLayout(null);
		
		JLabel title = new JLabel("Edit Level");
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(225, 9, 219, 101);
		add(title);
		
		JLabel inGameLabel = new JLabel("In Game");
		inGameLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		inGameLabel.setBounds(25, 100, 120, 30);
		add(inGameLabel);
		
		JLabel inGPzlLabel = new JLabel("Puzzle Levels");
		inGPzlLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inGPzlLabel.setBounds(25, 135, 160, 30);
		add(inGPzlLabel);
		
		JLabel inGLtngLabel = new JLabel("Lightning Levels");
		inGLtngLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inGLtngLabel.setBounds(25, 190, 180, 30);
		add(inGLtngLabel);
		
		JLabel inGRlsLabel = new JLabel("Release Levels");
		inGRlsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inGRlsLabel.setBounds(25, 245, 160, 30);
		add(inGRlsLabel);
		
		JLabel notInGameLabel = new JLabel("Not in Game");
		notInGameLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		notInGameLabel.setBounds(25, 330, 160, 30);
		add(notInGameLabel);
		
		JLabel notGPzlLabel = new JLabel("Puzzle Levels");
		notGPzlLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		notGPzlLabel.setBounds(25, 370, 160, 30);
		add(notGPzlLabel);
		
		JLabel notGLtngLabel = new JLabel("Lightning Levels");
		notGLtngLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		notGLtngLabel.setBounds(25, 425, 180, 30);
		add(notGLtngLabel);
		
		JLabel notGRlsLabel = new JLabel("Release Levels");
		notGRlsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		notGRlsLabel.setBounds(25, 480, 160, 30);
		add(notGRlsLabel);
		
		editButton = new JButton("Edit");
		editButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		editButton.setBounds(505, 580, 120, 45);
		editButton.setEnabled(false);  // Initially not usable
		add(editButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		backButton.setBounds(25, 580, 120, 45);
		backButton.addActionListener(
				new BackBuilderController(model, application));
		add(backButton);
		
		addButton = new JButton("Add to Game");
		addButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		addButton.setEnabled(false);  // Initially not usable
		addButton.setBounds(315, 580, 165, 45);
		add(addButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		deleteButton.setBounds(170, 580, 120, 45);
		deleteButton.setEnabled(false);  // Initially not usable
		add(deleteButton);
		
		addLevelsContainer = new JScrollPane();
		addLevelsContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		addLevelsContainer.setBounds(230, 115, 390, 202);
		add(addLevelsContainer);
		
		savedLevelsContainer = new JScrollPane();
		savedLevelsContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		savedLevelsContainer.setBounds(230, 345, 390, 202);
		add(savedLevelsContainer);
	}

	
	/**
	 *  Updates the display when the model changes.
	 *  
	 *  Updates when Levels are deleted, when Levels are added to the game,
	 *  or when Levels are removed from the game.
	 */
	@Override
	public Boolean modelUpdated()
	{
		addedLevels.modelUpdated();
		savedLevels.modelUpdated();
		return false;
	}
	
	
	/**
	 *  Resets all level select buttons to have blue backgrounds.
	 */
	public void resetSelectColors()
	{
		addedLevels.resetSelectColors();
		savedLevels.resetSelectColors();
	}
	
	
	public void enableButtons()
	{
		deleteButton.setEnabled(true);
		editButton.setEnabled(true);
		addButton.setEnabled(true);
	}
	
	
	public void setSelectedLevel(LevelContainer newLevel)
	{
		this.selectedLevel = newLevel;
	}

}
