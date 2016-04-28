package poseidon.common.view;

import poseidon.entities.LevelModel;

/**
 *  Interface for interacting with a Kabasuji Level GUI.
 *  
 * @author Alex Titus
 */
public interface ILevelView extends IModelUpdated
{
	/** Returns the BoardView object associated with the ILevelView. */
	public BoardView getBoard();
	/** Returns the BullpenView object (the displayed Bullpen) associated with the ILevelView. */
	public BullpenView getBullpen();
	/** Returns the LevelModel object associated with the ILevelView. */
	public LevelModel getModel();
}
