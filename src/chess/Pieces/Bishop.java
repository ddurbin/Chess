package chess.Pieces;

/**
 * @author ddurbin
 *
 */
public class Bishop extends ChessPiece {

	public Bishop(char column, int row, String color){
		super(column, row, color);
	}
	
	public boolean validMove(String toPosition){
		if(!super.validMove(toPosition)){
			return false;
		}
		int toColumn = toPosition.charAt(0) - 97;
		int toRow = Integer.parseInt(toPosition.substring(1)) - 1;
		int colDiff = toColumn - (column - 97);
		int rowDiff = toRow - (row-1);
		if(colDiff > 0 && rowDiff > 0){ //Bishop move to top right
			if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
				return false;
			}
			for(int i=column-97,j=row-1; i<toColumn; i++, j++){
				if(chess.Board.ChessBoard.available[i+1][j+1] == false && (i+1 != toColumn && j+1 != toRow)){
					return false;
				}
			}
			return true;
		}else if(colDiff < 0 && rowDiff > 0){ //Bishop moving to top left
			if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
				return false;
			}
			for(int i=column-97,j=row-1; i>toColumn; i--, j++){
				if(chess.Board.ChessBoard.available[i-1][j+1] == false && (i-1 != toColumn && j+1 != toRow)){
					return false;
				}
			}
			return true;
		}else if(colDiff < 0 && rowDiff < 0){ //Bishop moving bottom left
			if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
				return false;
			}
			for(int i=column-97,j=row-1; i>toColumn; i--, j--){
				if(chess.Board.ChessBoard.available[i-1][j-1] == false && (i-1 != toColumn && j-1 != toRow)){
					return false;
				}
			}
			return true;
		}else{ //Bishop moving bottom right
			if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
				return false;
			}
			for(int i=column-97,j=row-1; i<toColumn; i++, j--){
				if(chess.Board.ChessBoard.available[i+1][j-1] == false && (i+1 != toColumn && j-1 != toRow)){
					return false;
				}
			}
			return true;
		}
	}

	public String toString(){
		if(this.color.equals(Color.BLACK)){
			return "bB";
		}else{
			return "wB";
		}
	}
}	
