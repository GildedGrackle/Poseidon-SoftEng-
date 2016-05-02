package poseidon.common.view;

import poseidon.entities.LevelModel;

/**
 *  Interface for interacting with a Kabasuji Level GUI.
 *  
 * @author Alex Titus
 */
public interface ILevelView extends IModelUpdated
{
	/** @return  The BoardView object associated with the ILevelView. */
	public BoardView getBoard();
	
	/** @return  The BullpenView object (the displayed Bullpen) associated with the ILevelView. */
	public BullpenView getBullpen();
	
	/** @return  The LevelModel object associated with the ILevelView. */
	public LevelModel getModel();
}
