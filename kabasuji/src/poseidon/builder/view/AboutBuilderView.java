package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JButton;


import poseidon.builder.controller.BackBuilderController;
import poseidon.entities.LevelBuilderModel;

public class AboutBuilderView extends JPanel implements IBuilderScreen{
	
	LevelBuilderView application;  // The top-level GUI object
	LevelBuilderModel model;
	JButton nextButton;
	JButton backButton;

	/**
	 * Create the panel.
	 */
	public AboutBuilderView(LevelBuilderModel model, LevelBuilderView view){
		this.application = view;
		this.model = model;
		initialize();
	}

	
	/**
	 *  
	 */
	public void initialize(){
		setLayout(null);
		
		JLabel pageTitle = new JLabel("About");
		pageTitle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		pageTitle.setBounds(277, 21, 115, 49);
		add(pageTitle);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Here is some text about how to make builder work");
		textPane.setBounds(34, 94, 598, 374);
		add(textPane);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		backButton.setBounds(34, 529, 148, 86);
		backButton.addActionListener(new BackBuilderController(model, application));
		add(backButton);
		
		nextButton = new JButton("Next");
		nextButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		nextButton.setBounds(484, 529, 148, 86);
		add(nextButton);
	}
	
	
	/**
	 *  Updates display when model changes
	 */
	@Override
	public void update(){
		// TODO Auto-generated method stub
		
	}
	
	public JButton getBackButton(){
		return backButton;
	}
}
