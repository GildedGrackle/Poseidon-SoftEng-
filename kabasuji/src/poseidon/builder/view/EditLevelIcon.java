package poseidon.builder.view;

import javax.swing.JButton;

import poseidon.entities.LevelContainer;

/**
 *  Associates a JButton with a LevelContainer for selecting levels to edit.
 *  
 *  @author Alex Titus
 *
 */
public class EditLevelIcon extends JButton
{
	/** The Level Container associated with this StarView. */
	LevelContainer levelContainer;

	/**
	 *  Construct selectable icon
	 */
	public EditLevelIcon(LevelContainer lc)
	{
		this.levelContainer = lc;
	}
	
	
	/** @return  The LevelContainer associated with this object. */
	public LevelContainer getLevelContainer()
	{
		return levelContainer;
	}

}
