package poseidon.entities;

public class PuzzleBullpenLogic implements IBullpenLogic{
	PuzzleBullpenLogic(){
		//TODO: Change return value
	}
	
	public Boolean removePiece(Bullpen bullpen, PieceContainer piece) {
		bullpen.removePieceFromList(piece);
		return true;
	}

	public Boolean addPiece(Bullpen bullpen, PieceContainer piece) {
		//TODO Not sure how to find location on the bullpen
		return null;
	}
}
