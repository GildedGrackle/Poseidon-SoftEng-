package poseidon.entities;

/**
 * Handles the actions on the bullpen in the release game mode.
 * 
 * Note: Not finished. will be finished after clarification.
 * @author Natalia
 *
 */
public class ReleaseBullpenLogic extends IBullpenLogic{
	ReleaseBullpenLogic() {
		
	}
	
	
	public Boolean shouldAddPiece(Bullpen bullpen, PieceContainer piece) {
		return false;										//Impossible to move items to bullpen in release
	}

	
	public Boolean shouldRemovePiece(Bullpen bullpen, PieceContainer piece) {
		return true;
	}
}
