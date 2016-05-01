package poseidon.player.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import poseidon.player.view.LevelSelectView;
import poseidon.player.view.StarView;

/**
 *  Handles selecting a level to play.
 *  
 *  @author Alex Titus
 */
public class SelectLevelController implements ActionListener
{
	/** The select level screen to modify. */
	LevelSelectView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  The select level screen to modify
	 */
	public SelectLevelController(LevelSelectView view)
	{
		this.view = view;
	}

	
	/**
	 *  Sets the selectedLevel LevelContainer in LevelSelectView to the
	 *  LevelContainer associated with the StarView that produced this
	 *  ActionEvent
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		StarView source = (StarView) ae.getSource();
		
		view.setSelectedLevel(source.getLevelContainer());

		// Update display
		view.resetSelectColors();
		source.setBackground(Color.yellow);
		view.getPlayButton().setEnabled(true);
		view.repaint();
	}

}
