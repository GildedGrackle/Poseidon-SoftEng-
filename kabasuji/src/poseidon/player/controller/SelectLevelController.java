package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.player.view.LevelSelectView;
import poseidon.player.view.StarView;

public class SelectLevelController implements ActionListener
{
	LevelSelectView view;  // The GUI object to modify
	
	
	public SelectLevelController(LevelSelectView view)
	{
		this.view = view;
	}

	
	/**
	 *  Sets the selectedLevel LevelContainer in LevelSelectView to the
	 *  LevelContainer associated with the StarView that produced this
	 *  ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		StarView source = (StarView) ae.getSource();
		
		view.setSelectedLevel(source.getLevelContainer());
		// TODO update view so that the level is clearly selected
	}

}
