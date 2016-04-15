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

public class LevelPlayerView
{
	LevelPlayerModel model;  // The state of the game
	JFrame kabasuji;  // The frame holding the screens
	JPanel mainMenu;  // The panel containing the menu buttons
	JButton continueGameButton;  // Goes to most recently unlocked Level
	JButton levelSelectButton;  // Goes to the Level Select screen
	JButton customLevelsButton;  // Goes to the Custom Level Select screen
	JButton aboutButton;  // Goes to the About page
	JButton exitButton;  // Closes the application
	IGameScreen currentView;  // The current screen displayed in the frame (besides this one)
//	LevelPlayerModel model;  // The top-level entity object, representing the game


	/**
	 * Create the application.
	 */
	LevelPlayerView(LevelPlayerModel model)
	{
		this.model = model;
		kabasuji = new JFrame();
		initialize();
	}
	
	
	/***********************
	 *  Getters & Setters  *
	 ***********************/
	public JFrame getfrmKabasuji()
	{
		return kabasuji;
	}
	
	public void setCurrentView(IGameScreen newScreen)
	{
		currentView = newScreen;
	}
	
	public IGameScreen getCurrentView()
	{
		return currentView;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize()
	{
		kabasuji.setTitle("Kabasuji Level Player");
		kabasuji.setBounds(100, 100, 695, 660);
		kabasuji.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainMenu = new JPanel();
		mainMenu.setLayout(null);
		
		JLabel title = new JLabel("Kabasuji");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Handwriting", Font.BOLD, 35));
		title.setBounds(219, 40, 225, 60);
		mainMenu.add(title);
		
		continueGameButton = new JButton("Start Game");
		continueGameButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		continueGameButton.setBounds(229, 190, 200, 50);
		continueGameButton.addActionListener(new ContinueController(model, this));
		mainMenu.add(continueGameButton);
		
		levelSelectButton = new JButton("Level Select");
		levelSelectButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		levelSelectButton.setBounds(229, 265, 200, 50);
		levelSelectButton.addActionListener(new LevelSelectController(model, this));
		mainMenu.add(levelSelectButton);
		
		aboutButton = new JButton("About");
		aboutButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		aboutButton.setBounds(229, 415, 200, 50);
		aboutButton.addActionListener(new AboutPlayerController(model, this));
		mainMenu.add(aboutButton);
		
		exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		exitButton.setBounds(229, 490, 200, 50);
		exitButton.addActionListener(new ExitPlayerController(this));
		mainMenu.add(exitButton);
		
		kabasuji.setContentPane(mainMenu);
	}

}
