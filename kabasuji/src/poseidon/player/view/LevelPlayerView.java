package poseidon.player.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import poseidon.player.controller.AboutPlayerController;
import poseidon.player.controller.ContinueController;
import poseidon.player.controller.ExitPlayerController;
import poseidon.player.controller.LevelSelectController;

public class LevelPlayerView
{
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
	public LevelPlayerView()
	{
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
		
		JLabel lblNewLabel = new JLabel("Kabasuji");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 35));
		lblNewLabel.setBounds(219, 38, 225, 60);
		mainMenu.add(lblNewLabel);
		
		continueGameButton = new JButton("Start Game");
		continueGameButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		continueGameButton.setBounds(229, 207, 200, 50);
		continueGameButton.addActionListener(new ContinueController(this));
		mainMenu.add(continueGameButton);
		
		levelSelectButton = new JButton("Level Select");
		levelSelectButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		levelSelectButton.setBounds(229, 299, 200, 50);
		levelSelectButton.addActionListener(new LevelSelectController(this));
		mainMenu.add(levelSelectButton);
		
		aboutButton = new JButton("About");
		aboutButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		aboutButton.setBounds(229, 388, 200, 50);
		aboutButton.addActionListener(new AboutPlayerController(this));
		mainMenu.add(aboutButton);
		
		exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		exitButton.setBounds(229, 477, 200, 50);
		exitButton.addActionListener(new ExitPlayerController(this));
		mainMenu.add(exitButton);
		
		kabasuji.setContentPane(mainMenu);
	}

}
