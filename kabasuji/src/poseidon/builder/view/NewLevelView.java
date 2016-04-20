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

public class NewLevelView extends JPanel implements IBuilderScreen{
	
	LevelBuilderModel model;  // The top-level state object
	LevelBuilderView application;  // The top-level GUI object
	JButton newPuzzleButton;  // To create a new Puzzle Level
	JButton newLightningButton;  // To create a new Lightning Level
	JButton newReleaseButton;  // To create a new Release Level
	JButton backButton;  // To return to main menu (LevelBuilderView)

	/**
	 * Create the panel.
	 */
	public NewLevelView(LevelBuilderModel model, LevelBuilderView view){
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
	 *  Updates the display when the model changes
	 */
	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	public JButton getNewPuzzle(){
		return newPuzzleButton;
	}
	
}
