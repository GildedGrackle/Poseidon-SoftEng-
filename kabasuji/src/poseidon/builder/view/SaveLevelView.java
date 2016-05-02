package poseidon.builder.view;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import poseidon.builder.controller.CancelEditController;
import poseidon.builder.controller.CancelSaveController;
import poseidon.builder.controller.ConfirmSaveController;
import poseidon.builder.controller.LimitController;
import poseidon.builder.controller.SetBullpenController;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelModel;

/**
 * The level-saving dialogue for the builder, asks for level name and whether to include in the game.
 * 
 * @author Jacob
 */
public class SaveLevelView extends JPanel implements IBuilderScreen {

	/** The top-level entity. */
	LevelBuilderModel topModel;
	/** The state of the level under construction. */
	LevelModel model;
	/** The top-level GUI object. */
	LevelBuilderView view;
	/** Level name input. */
	JTextField nameInput;
	/** Label for the name input field. */
	JLabel nameLabel;
	/** Check box for whether to add the level to the player. */
	JCheckBox addToGameCheckBox;
	/** Button to cancel saving and return to the build level screen (BuilderView). */
	JButton cancelButton;
	/** Button to confirm saving and return to the build level screen (BuilderView). */
	JButton saveButton;
	
	
	/**
	 *  Constructor, creates all view elements and attached controllers.
	 *  
	 *  @param view  The top-level GUI object.
	 */
	public SaveLevelView(LevelBuilderModel topModel, LevelModel model, LevelBuilderView view) {
		// Nothing should actually change in the model until save button pressed.
		this.topModel = topModel;
		this.model = model;
		this.view = view;
		
		setLayout(null);
		
		JLabel title = new JLabel("Save Custom Level");
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(160, 20, 440, 45);
		add(title);
		
		nameLabel = new JLabel("Level Name:");
		nameLabel.setBackground(Color.WHITE);
		nameLabel.setBounds(285, 220, 110, 35);
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(nameLabel);

		nameInput = new JTextField(model.getLevelName());
		nameInput.setBounds(285, 255, 110, 30);
		nameInput.setColumns(10); // What does this do?
		add(nameInput);
		
		addToGameCheckBox = new JCheckBox("Make playable in game?", model.getIsAddedToPlayer());
		addToGameCheckBox.setBounds(260, 290, 165, 35);
		add(addToGameCheckBox);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cancelButton.setBounds(35, 550, 145, 45);
		cancelButton.addActionListener(new CancelSaveController(view));
		add(cancelButton);
		
		saveButton = new JButton("Done");
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		saveButton.setBounds(500, 550, 145, 45);
		saveButton.addActionListener(new ConfirmSaveController(topModel, view, this, model));
		add(saveButton);
	}
	
	
	/**
	 *  Updates display when model changes, but the model cannot change while on this screen.
	 *  
	 *  @return  False
	 */
	@Override
	public Boolean modelUpdated() {
		return false;
	}
	
	
	/** @return  The newly specified name for saving. */
	public String getSaveName() {
		return nameInput.getText();
	}
	
	
	/** @return  Boolean if level should be added to player. */
	public boolean getAddToGame() {
		return addToGameCheckBox.isSelected();
	}
}
