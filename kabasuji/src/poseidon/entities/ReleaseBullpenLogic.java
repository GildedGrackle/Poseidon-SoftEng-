package poseidon.entities;

/**
 * Handles the actions on the bullpen in the release game mode.
 * 
 * Note: Not finished. will be finished after clarification.
 * @author Natalia
 *
 */
public class ReleaseBullpenLogic implements IBullpenLogic{
	ReleaseBullpenLogic() {
		
	}
	
	
	public Boolean addPiece(Bullpen bullpen, PieceContainer piece) {
		return false;										//Impossible to move items to bullpen in release
	}

	
	public Boolean removePiece(Bullpen bullpen, PieceContainer piece) {
		bullpen.removePieceFromList(piece);
		return true;
	}
}
