package poseidon.player.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.AboutPlayerController;
import poseidon.player.controller.ContinueController;
import poseidon.player.controller.ExitPlayerController;
import poseidon.player.controller.LevelSelectController;

/**
 *  The top-level GUI object, containing the application frame (window).
 *  
 *  It also creates the main menu.
 *  
 *  @author Alex Titus
 */
public class LevelPlayerView
{
	/** The top-level entity object, representing the game. */
	LevelPlayerModel model;
	/** The frame holding the screens. */
	JFrame kabasuji;
	/** The panel containing the menu buttons. */
	JPanel mainMenu;
	/** Goes to most recently unlocked Level. */
	JButton continueGameButton;
	/** Goes to the Level Select screen. */
	JButton levelSelectButton;
	/** Goes to the Custom Level Select screen. */
	JButton customLevelsButton;
	/** Goes to the About page. */
	JButton aboutButton;
	/** Closes the application. */
	JButton exitButton;
	/** The current screen displayed in the frame (besides this one). */
	IGameScreen currentView;


	/**
	 *  Constructor.
	 * 
	 *  @param model  the top-level application-state object
	 */
	public LevelPlayerView(LevelPlayerModel model){
		
		this.model = model;
		kabasuji = new JFrame();
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize()
	{
		kabasuji.setTitle("Kabasuji Level Player");
		kabasuji.setBounds(100, 100, 695, 660);
		kabasuji.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kabasuji.setResizable(false);
		
		mainMenu = new JPanel();
		mainMenu.setLayout(null);
		
		JLabel title = new JLabel("Kabasuji");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Handwriting", Font.BOLD, 35));
		title.setBounds(219, 40, 225, 60);
		mainMenu.add(title);
		
		continueGameButton = new JButton("Start Game");
		continueGameButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		continueGameButton.setBounds(231, 197, 200, 50);
		continueGameButton.addActionListener(new ContinueController(model, this));
		mainMenu.add(continueGameButton);
		
		levelSelectButton = new JButton("Level Select");
		levelSelectButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		levelSelectButton.setBounds(231, 281, 200, 50);
		levelSelectButton.addActionListener(new LevelSelectController(model, this));
		mainMenu.add(levelSelectButton);
		
		aboutButton = new JButton("About");
		aboutButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		aboutButton.setBounds(231, 359, 200, 50);
		aboutButton.addActionListener(new AboutPlayerController(model, this));
		mainMenu.add(aboutButton);
		
		exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		exitButton.setBounds(231, 439, 200, 50);
		exitButton.addActionListener(new ExitPlayerController(this));
		mainMenu.add(exitButton);
		
		kabasuji.setContentPane(mainMenu);
	}
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The top-level application-state object. */
	public LevelPlayerModel getModel()
	{
		return model;
	}
	/** @return  The JFrame containing the the application. */
	public JFrame getfrmKabasuji()
	{
		return kabasuji;
	}
	/** @return  The screen currently being displayed in the application frame. */
	public IGameScreen getCurrentView()
	{
		return currentView; 
	}
	/** @return  The "About" button, used to get to the "about" screen. */
	public JButton getAbout(){
		return aboutButton;
	}
	/** @return  The "Continue" button, used to start a game immediately. */
	public JButton getContinue(){
		return continueGameButton;
	}
	/** @return  The "Level Select" button, used to get to the level select screen. */
	public JButton getLvlSelect(){
		return levelSelectButton;
	}
	/** @return  The "Exit" button, used to close the application. */
	public JButton getExit(){
		return exitButton;
	}
	
	/**
	 *  Sets the screen currently being displayed in the application frame.
	 *  
	 *  @param newScreen  the new screen to display
	 */
	public void setCurrentView(IGameScreen newScreen)
	{
		currentView = newScreen;
	}

}
