package poseidon.entities;

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
