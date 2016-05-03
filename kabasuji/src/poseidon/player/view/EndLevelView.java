package poseidon.player.view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JButton;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.LevelSelectController;
import poseidon.player.controller.NextLevelController;
import poseidon.player.controller.ResetController;

import javax.swing.SwingConstants;

import java.awt.Insets;

/**
 *  The end level screen, with options to replay the current level, go to the
 *  level select screen, or go to the next level.
 *  
 *  @author Alex Titus
 */
public class EndLevelView extends JPanel implements IGameScreen
{
	/** The top-level model. */
	LevelPlayerModel model;
	/** The top-level GUI object. */
	LevelPlayerView view;
	/** JButton linking to the next playable level */ 
	JButton nextLevelButton;

	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level model
	 *  @param view  the top-level GUI object
	 */
	public EndLevelView(LevelPlayerModel model, LevelPlayerView view)
	{
		this.view = view;  // The only view this could have come from
		setLayout(null);
		setBackground(new Color(0, 191, 255));
		
		JLabel title = new JLabel(model.getPlayingLevel().getLevel().getLevelName());
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 25));
		title.setBounds(190, 20, 300, 35);
		add(title);
		
		JButton replayButton = new JButton();
		replayButton.setOpaque(false);
		replayButton.setBounds(90, 500, 80, 80);
		replayButton.setBackground(new Color(0, 191, 255));
		replayButton.addActionListener(new ResetController(model, view));
		add(replayButton);
		
		JButton levelSelectButton = new JButton();
		levelSelectButton.setOpaque(false);
		levelSelectButton.setBounds(300, 500, 80, 80);
		levelSelectButton.setBackground(new Color(0, 191, 255));
		levelSelectButton.addActionListener(new LevelSelectController(model, view));
		add(levelSelectButton);
		
		nextLevelButton = new JButton();
		nextLevelButton.setMargin(new Insets(0, 14, 2, 14));
		nextLevelButton.setOpaque(false);
		nextLevelButton.setBounds(510, 500, 80, 80);
		nextLevelButton.setBackground(new Color(0, 191, 255));
		nextLevelButton.addActionListener(new NextLevelController(model, view));
		// If the next level hasn't been unlocked or there are no levels after this
		// one in this gamemode
		if(model.getPlayingLevel().getScore() < 1 ||
				model.getLevels().get(model.getPlayingLevel().getLevel().getGameMode() - 1).size()
				<= model.getPlayingLevel().getLevelNumber() + 1)
		{
			// Then disable this button, can't move on
			nextLevelButton.setEnabled(false);
		}
		add(nextLevelButton);
		
		try
		{
			Image replayImage = ImageIO.read(getClass().getClassLoader().getResource("images/Replay level.png"));
			Image lvlSelectImage = ImageIO.read(getClass().getClassLoader().getResource("images/Level select.png"));
			Image nextImage = ImageIO.read(getClass().getClassLoader().getResource("images/Next level.png"));
			replayButton.setIcon(new ImageIcon(replayImage));
			levelSelectButton.setIcon(new ImageIcon(lvlSelectImage));
			nextLevelButton.setIcon(new ImageIcon(nextImage));
		}
		catch (IOException e) {}
		
		JLabel firstStar = new JLabel();
		firstStar.setHorizontalAlignment(SwingConstants.CENTER);
		firstStar.setBounds(90, 195, 150, 150);
		add(firstStar);
		
		JLabel secondStar = new JLabel();
		secondStar.setHorizontalAlignment(SwingConstants.CENTER);
		secondStar.setBounds(265, 195, 150, 150);
		add(secondStar);
		
		JLabel thirdStar = new JLabel();
		thirdStar.setHorizontalAlignment(SwingConstants.CENTER);
		thirdStar.setBounds(440, 195, 150, 150);
		add(thirdStar);
		
		try
		{
			Image grayStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star-grey.png"));
			Image filledStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star.png"));
			switch(model.getPlayingLevel().getScore())
			{
			case 0:
				firstStar.setIcon(new ImageIcon(grayStar));
				secondStar.setIcon(new ImageIcon(grayStar));
				thirdStar.setIcon(new ImageIcon(grayStar));
				break;
			case 1:
				firstStar.setIcon(new ImageIcon(filledStar));
				secondStar.setIcon(new ImageIcon(grayStar));
				thirdStar.setIcon(new ImageIcon(grayStar));
				break;
			case 2:
				firstStar.setIcon(new ImageIcon(filledStar));
				secondStar.setIcon(new ImageIcon(filledStar));
				thirdStar.setIcon(new ImageIcon(grayStar));
				break;
			case 3:
				firstStar.setIcon(new ImageIcon(filledStar));
				secondStar.setIcon(new ImageIcon(filledStar));
				thirdStar.setIcon(new ImageIcon(filledStar));
				break;
			default:
				firstStar.setIcon(new ImageIcon());
				secondStar.setIcon(new ImageIcon());
				thirdStar.setIcon(new ImageIcon());
			}
		}
		catch (IOException e) {}
	}

	
	/** @return  Null, nothing to do here. */
	@Override
	public LevelView getCurrentlyPlaying()
	{
		return null;
	}

	
	/**
	 *  Does nothing, no level to set.
	 *  
	 *  @return  False, nothing done
	 */
	@Override
	public Boolean setCurrentlyPlaying(LevelView newGame)
	{
		return false;
	}

	public JButton getNext(){
		return nextLevelButton;
	}
}
