package poseidon.player.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import poseidon.player.controller.BackPlayerController;

public class AboutPlayerView extends JPanel implements IGameScreen
{
	LevelPlayerView game;  // The top-level GUI object
	JButton backButton;  // Returns to the main menu (LevelPlayerView)


	/**
	 * Create the panel.
	 */
	public AboutPlayerView(LevelPlayerView view)
	{
		game = view;
		setLayout(null);
		
		JTextArea aboutUsTextArea = new JTextArea();
		aboutUsTextArea.setEditable(false);
		aboutUsTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		aboutUsTextArea.setText("This describes us and the project\r\n\r\nTeam Poseidon:\r\nMonika Danielewicz, Morgan Hopeman , Natalia Kononenko, Alex Titus, Jacob Wennersten");
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
		backButton.addActionListener(new BackPlayerController(game));
		add(backButton);
	}


	/**
	 *  To satisfy IGameScreen
	 */
	@Override
	public LevelView getCurrentlyPlaying()
	{
		// Because there are no children from this screen
		return null;
	}
	
	
	/**
	 *  To satisfy IGameScreen
	 */
	@Override
	public Boolean setCurrentlyPlaying(LevelView newGame)
	{
		return false;
	}
	
}
