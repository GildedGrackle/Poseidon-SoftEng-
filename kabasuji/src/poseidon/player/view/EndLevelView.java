package poseidon.player.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.ContinueController;
import poseidon.player.controller.LevelSelectController;
import poseidon.player.controller.ResetController;

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
		
		JLabel title = new JLabel(model.getPlayingLevel().getLevel().getLevelName());
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 25));
		title.setBounds(250, 20, 115, 35);
		add(title);
		
		ScoreView levelScore = new ScoreView(model.getPlayingLevel().getLevel());
		levelScore.setBounds(90, 85, 500, 350);
		add(levelScore);
		
		JButton replayButton = new JButton("Replay");
		replayButton.setBounds(90, 500, 80, 80);
		replayButton.addActionListener(new ResetController(model, view));
		add(replayButton);
		
		JButton levelSelectButton = new JButton("Level Select");
		levelSelectButton.setBounds(300, 500, 80, 80);
		levelSelectButton.addActionListener(new LevelSelectController(model, view));
		add(levelSelectButton);
		
		JButton nextLevelButton = new JButton("Next Level");
		nextLevelButton.setBounds(510, 500, 80, 80);
		nextLevelButton.addActionListener(new ContinueController(model, view));
		if(model.getPlayingLevel().getScore() < 1)  // If the next level hasn't been unlocked
		{
			// Then disable this button, can't move on
			nextLevelButton.setEnabled(false);
		}
		add(nextLevelButton);
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

}
