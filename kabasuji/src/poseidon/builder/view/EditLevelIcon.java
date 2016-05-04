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
	/** The Level Container associated with this icon. */
	LevelContainer levelContainer;

	/**
	 *  Constructor.
	 *  
	 *  @param lc  the LevelContainer to associate
	 */
	public EditLevelIcon(LevelContainer lc)
	{
		this.levelContainer = lc;
		
		setText("" + lc.getLevelNumber());
		setToolTipText(lc.getLevelFileName());
	}
	
	
	/** @return  The LevelContainer associated with this object. */
	public LevelContainer getLevelContainer()
	{
		return levelContainer;
	}

}
