package poseidon.builder.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.CardLayout;

import javax.swing.JButton;

import poseidon.builder.controller.AboutBuilderController;
import poseidon.builder.controller.EditLevelController;
import poseidon.builder.controller.ExitBuilderController;
import poseidon.builder.controller.NewLevelController;
import poseidon.entities.LevelBuilderModel;

/**
 *  The base GUI class, containing both the application frame and the main menu.
 *  
 *  Also has a reference to the current screen being displayed in the frame.
 *  
 *  @author Morgan Hopeman
 *  @author Alex Titus
 *
 */
public class LevelBuilderView implements IBuilderScreen{

	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel model;
	/** The frame containing the screens. */
	JFrame builder;
	/** The main menu, with buttons. */
	JPanel mainMenu;
	/** To create a new level (NewLevelView). */
	JButton newLevelButton;
	/** To edit a new level (EditLevelView). */
	JButton editLevelButton;
	/** To see information about Level Builder (AboutBuilderView). */
	JButton aboutButton;
	/** To exit the application. */
	JButton exitButton;
	/** The current screen displayed in the frame. */
	IBuilderScreen currentScreen;

	/**
	 *  Constructor.
	 *  
	 *  Creates the frame and initializes the frame, model, and other variables.
	 */
	public LevelBuilderView()
	{
		builder = new JFrame("Kabasuji Level Builder");
		initialize();
		model = new LevelBuilderModel();
		currentScreen = null;
	}
	
	
	/*************************
	 *  Getters and setters  *
	 *************************/
	public JFrame getBuilder()
	{
		return builder;
	}
	
	public void setCurrentScreen(IBuilderScreen newScreen)
	{
		currentScreen = newScreen;
	}
	
	public IBuilderScreen getCurrentScreen()
	{
		return currentScreen;
	}

	
	
	/**
	 *  Initializes builder JFrame to show the main menu
	 */
	 public void initialize()
	 {
		 builder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 builder.setBounds(100, 100, 695, 670);
		 mainMenu = new JPanel();
		 mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		 mainMenu.setLayout(null);
			
		 JLabel title = new JLabel("Kabasuji Level Builder");
		 title.setBounds(117, 40, 442, 63);
		 title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		 mainMenu.add(title);
			
		 aboutButton = new JButton("About");
		 aboutButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		 aboutButton.setBounds(262, 141, 145, 52);
		 aboutButton.addActionListener(new AboutBuilderController(model, this));
		 mainMenu.add(aboutButton);
		 
		 newLevelButton = new JButton("New Level");
		 newLevelButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		 newLevelButton.setBounds(262, 235, 145, 52);
		 newLevelButton.addActionListener(new NewLevelController(model, this));
		 mainMenu.add(newLevelButton);
			
		 editLevelButton = new JButton("Edit Level");
		 editLevelButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		 editLevelButton.setBounds(255, 334, 159, 52);
		 editLevelButton.addActionListener(new EditLevelController(model, this));
		 mainMenu.add(editLevelButton);
			
		 exitButton = new JButton("Quit ");
		 exitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		 exitButton.setBounds(264, 441, 141, 57);
		 exitButton.addActionListener(new ExitBuilderController(model, this));
		 mainMenu.add(exitButton);
			
		 builder.setContentPane(mainMenu);
	 }


	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	public JButton getAbout(){
		return aboutButton;
	}
	
	public JButton getNewLevel(){
		return newLevelButton;
	}
	
	public JButton getEditLvl(){
		return editLevelButton;
	}
	
	public JButton getExit(){
		return exitButton;
	}

	
	
	
}
