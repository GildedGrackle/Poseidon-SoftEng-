package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import poseidon.builder.controller.BackBuilderController;
import poseidon.builder.controller.MakeLightningController;
import poseidon.builder.controller.MakePuzzleController;
import poseidon.builder.controller.MakeReleaseController;
import poseidon.entities.LevelBuilderModel;

/**
 *  The new level creation screen, where new levels can be created.
 *   
 *  @author Alex Titus
 */
public class NewLevelView extends JPanel implements IBuilderScreen{
	
	/** The top-level state object. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView application;
	/** To create a new Puzzle Level. */
	JButton newPuzzleButton;
	/** To create a new Lightning Level. */
	JButton newLightningButton;
	/** To create a new Release Level. */
	JButton newReleaseButton;
	/** To return to main menu (LevelBuilderView). */
	JButton backButton;

	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level state object
	 *  @param view  the top-level GUI object
	 */
	public NewLevelView(LevelBuilderModel model, LevelBuilderView view){
		this.model = model;
		application = view;
		
		initialize();
	}

	
	/**
	 *  Creates the panel.
	 */
	public void initialize()
	{
		setLayout(null);
		
		JLabel title = new JLabel("New Level");
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(225, 9, 219, 101);
		add(title);
		
		newPuzzleButton = new JButton("Puzzle Level");
		newPuzzleButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		newPuzzleButton.setBounds(243, 149, 184, 60);
		newPuzzleButton.addActionListener(new MakePuzzleController(model, application));
		add(newPuzzleButton);
		
		newLightningButton = new JButton("Lightning Level");
		newLightningButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		newLightningButton.setBounds(243, 271, 184, 60);
		newLightningButton.addActionListener(new MakeLightningController(model, application));
		add(newLightningButton);
		
		newReleaseButton = new JButton("Release Level");
		newReleaseButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		newReleaseButton.setBounds(243, 392, 184, 60);
		newReleaseButton.addActionListener(new MakeReleaseController(model, application));
		add(newReleaseButton);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		backButton.setBounds(25, 530, 120, 84);
		backButton.addActionListener(new BackBuilderController(model, application));
		add(backButton);
	}
	
	
	/**
	 *  Updates the display when the model changes.
	 *  
	 *  @return  False - nothing can be updated on this screen.
	 */
	@Override
	public Boolean modelUpdated()
	{
		return false;
	}
	

				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The "New Puzzle" button, which creates a new Puzzle level. */
	public JButton getNewPuzzle(){
		return newPuzzleButton;
	}
	
	/** @return  The "New Lightning" button, which creates a new Lightning level. */
	public JButton getNewLightning(){
		return newLightningButton;
	}
	
	/** @return  The "New Relese" button, which creates a new Release level. */
	public JButton getNewRelease(){
		return newReleaseButton;
	}
	
}
