package poseidon.player.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.BackPlayerController;

/**
 *  Renders the "About" screen.
 *  
 *  @author Alex Titus
 *
 */
public class AboutPlayerView extends JPanel implements IGameScreen
{
	/** The top-level entity object, representing the state of the game. */
	LevelPlayerModel model;
	/** The top-level GUI object. */
	LevelPlayerView game;
	/** Returns to the main menu (LevelPlayerView). */
	JButton backButton;


	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object
	 *  @param view  the top-level GUI object
	 */
	public AboutPlayerView(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		game = view;
		setLayout(null);
		setBackground(new Color(0, 191, 255));
		
		JTextArea aboutUsTextArea = new JTextArea();
		aboutUsTextArea.setEditable(false);
		aboutUsTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		aboutUsTextArea.setText("Welcome to the Kabasuji Level Player. Enjoy!\r\n\r\nMade by Team Poseidon:\r\nMonika Danielewicz, Morgan Hopeman , Natalia Kononenko, Alex Titus, Jacob Wennersten");
		aboutUsTextArea.setBounds(25, 50, 615, 485);
		add(aboutUsTextArea);
		
		JLabel title = new JLabel("About");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 30));
		title.setBounds(272, 10, 135, 35);
		add(title);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		backButton.setBounds(25, 555, 130, 45);
		backButton.addActionListener(new BackPlayerController(model, game));
		add(backButton);
	}


	/**
	 *  To satisfy IGameScreen.
	 */
	@Override
	public LevelView getCurrentlyPlaying()
	{
		// Because there are no children from this screen
		return null;
	}
	
	
	/**
	 *  To satisfy IGameScreen.
	 */
	@Override
	public Boolean setCurrentlyPlaying(LevelView newGame)
	{
		return false;
	}
	
	
	/** @return  The "Back" button. */
	public JButton getBack(){
		return backButton;
	}
}
