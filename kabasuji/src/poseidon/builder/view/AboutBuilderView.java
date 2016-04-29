package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JButton;


import poseidon.builder.controller.BackBuilderController;
import poseidon.entities.LevelBuilderModel;

/**
 *  TODO AboutBuilder documentation
 *  
 *  @author Alex Titus
 */
public class AboutBuilderView extends JPanel implements IBuilderScreen{
	
	LevelBuilderView application;  // The top-level GUI object
	LevelBuilderModel model;
	JButton nextButton;
	JButton backButton;

	/**
	 *  Constructor.
	 *  
	 *  @param model  
	 *  @param view  
	 */
	public AboutBuilderView(LevelBuilderModel model, LevelBuilderView view){
		this.application = view;
		this.model = model;
		initialize();
	}

	
	/**
	 *  Creates the panel.
	 */
	public void initialize(){
		setLayout(null);
		
		JLabel pageTitle = new JLabel("About");
		pageTitle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		pageTitle.setBounds(269, 20, 115, 45);
		add(pageTitle);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Here is some text about how to make builder work");
		textPane.setBounds(35, 90, 610, 420);
		add(textPane);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		backButton.setBounds(35, 550, 145, 45);
		backButton.addActionListener(new BackBuilderController(model, application));
		add(backButton);
		
		nextButton = new JButton("Next");
		nextButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		nextButton.setBounds(485, 550, 145, 45);
		add(nextButton);
	}
	
	
	/**
	 *  Updates display when model changes.
	 *  
	 *  Nothing can change on this screen.
	 *  @return false
	 */
	@Override
	public Boolean modelUpdated(){
		return false;
	}
	
	/** Returns the Back button, used to navigate back to the main menu. */
	public JButton getBackButton(){
		return backButton;
	}
}
