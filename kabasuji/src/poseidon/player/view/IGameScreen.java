package poseidon.player.view;

/**
 *  All screens displayable in the Level Player's frame implement this.
 *  
 *  @author Alex Titus
 */
public interface IGameScreen
{
	/** @return  The level currently being played, or null if none exists. */
	public LevelView getCurrentlyPlaying();
	
	
	/**
	 *  Sets the level GUI currently being played.
	 *  
	 *  @param newGame  the new level GUI being played
	 *  @return  Indicator of whether the operation completed successfully.
	 */
	public Boolean setCurrentlyPlaying(LevelView newGame);

}
