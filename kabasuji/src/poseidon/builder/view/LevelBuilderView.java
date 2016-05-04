package poseidon.builder.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import poseidon.builder.controller.AboutBuilderController;
import poseidon.builder.controller.ToEditLevelController;
import poseidon.builder.controller.ExitBuilderController;
import poseidon.builder.controller.NewLevelController;
import poseidon.common.view.ITopView;
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
public class LevelBuilderView implements IBuilderScreen, ITopView{

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
	 *  
	 *  @param model  the top-level model to base the application on
	 */
	public LevelBuilderView(LevelBuilderModel model)
	{
		builder = new JFrame("Kabasuji Level Builder");
		this.model = model;
		initialize();
		currentScreen = null;
	}

	
	/**
	 *  Initializes builder JFrame to show the main menu
	 */
	 public void initialize()
	 {
		 builder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 builder.setBounds(100, 100, 695, 670);
		 builder.setResizable(false);
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
		 editLevelButton.addActionListener(new ToEditLevelController(model, this));
		 mainMenu.add(editLevelButton);
			
		 exitButton = new JButton("Quit ");
		 exitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		 exitButton.setBounds(264, 441, 141, 57);
		 exitButton.addActionListener(new ExitBuilderController(model, this));
		 mainMenu.add(exitButton);
			
		 builder.setContentPane(mainMenu);
	 }


	 /**
	  *  Updates the display when the underlying model changes.
	  *  
	  *  @return  False - nothing can be updated on this screen.
	  */
	 @Override
	 public Boolean modelUpdated()
	 {
		 return false;
	 }
	
	
	 /** @return  False - this is not the Level Player. */
		public Boolean isGame()
		{
			return false;
		}
				/*************************
				 *  Getters and setters  *
				 *************************/
	/** @return  The application frame. */
	public JFrame getFrame()
	{
		return builder;
	}
	
	/** @return  The screen currently displayed in the application frame. */
	public IBuilderScreen getCurrentScreen()
	{
		return currentScreen;
	}

	/** @return  The "About" button, which goes to the About screen. */
	public JButton getAbout(){
		return aboutButton;
	}
	
	/** @return  The "New Level" button, which goes to the new level creation screen. */
	public JButton getNewLevel(){
		return newLevelButton;
	}
	
	/** @return  The "Edit Level" button, which goes to the edit level screen. */
	public JButton getEditLvl(){
		return editLevelButton;
	}
	
	/** @return  The "Exit" button, which closes the application. */
	public JButton getExit(){
		return exitButton;
	}
	
	/**
	 *  Sets the current screen.
	 *  
	 *  Doesn't actually change the screen, use setContentPane().
	 *  
	 *  @param newScreen  new screen
	 */
	public void setCurrentScreen(IBuilderScreen newScreen)
	{
		currentScreen = newScreen;
	}
	
}
