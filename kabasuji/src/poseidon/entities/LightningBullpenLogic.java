package poseidon.entities;

import poseidon.entities.Point;

public class LightningBullpenLogic implements IBullpenLogic {
	LightningBullpenLogic() {

	}

	@Override
	public Boolean addPiece(Bullpen bullpen, PieceContainer piece) {
		return false;										//Can't add pieces to bullpen after game started
	}

	@Override
	public Boolean removePiece(Bullpen bullpen, PieceContainer piece) {
		bullpen.removePieceFromList(piece);
		//TODO add random piece to bullpen to replace the one taken away
		return true; 	
	}
}
