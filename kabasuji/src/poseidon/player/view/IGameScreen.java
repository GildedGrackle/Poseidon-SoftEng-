package poseidon.player.view;

/**
 *  TODO IGameScreen documentation
 *  
 *  @author Alex Titus
 */
public interface IGameScreen
{
	public LevelView getCurrentlyPlaying();
	public Boolean setCurrentlyPlaying(LevelView newGame);

}
