package chess.Pieces;

/**
 * @author ddurbin
 *
 */
public class Rook extends ChessPiece{

	public Rook(char column, int row, String color){
		super(column, row, color);
	}

	public boolean validMove(String toPosition){
		int direction;
		if(!super.validMove(toPosition)){
			return false;
		}
		char toColumn = toPosition.charAt(0);
		int toRow = Integer.parseInt(toPosition.substring(1)) - 1;
		if(toColumn == column){
			direction = toRow - row;
			if(direction > 0){ //moving piece towards opposite color side
				for(int i=row; i < toRow; i++){
					if(chess.Board.ChessBoard.available[(int)toColumn - 97][i] == false){
						return false;
					}
				}
				return true;
			}else{ //moving piece towards same color side
				for(int i=row; i > toRow; i--){
					if(chess.Board.ChessBoard.available[toColumn][i] == false){
						return false;
					}
				}
				return true;
			}

		}else if(toRow == (row-1)){
			direction = toColumn - column;
			if(direction > 0){ //moving piece to right
				for(int i=column+1; i < toColumn; i++){
					if(chess.Board.ChessBoard.available[(int)i -97][toRow] == false){
						return false;
					}
				}
				return true;

			}else{ // moving piece to left
				for(int i=column+1; i > toColumn; i--){
					if(chess.Board.ChessBoard.available[(int)i - 97][toRow] == false){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public String toString(){
		if(this.color.equals(Color.BLACK)){
			return "bR";
		}else{
			return "wR";
		}
	}
}
