package poseidon.entities;

public class BuilderBullpenLogic implements IBullpenLogic {

	BuilderBullpenLogic() {
	
	}

	public Boolean addPiece(Bullpen bullpen, PieceContainer piece) {
		return false;										//Pieces don't dissappear, so we can't put them on bullpen
	}

	public Boolean removePiece(Bullpen bullpen, PieceContainer piece) {
		return true;										//Removing a piece is always possible.
															//No need to remove from list since pieces don't disappear 
	}

}
