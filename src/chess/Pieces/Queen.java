package chess.Pieces;

/**
 * @author ddurbin
 *
 */
public class Queen extends ChessPiece {

	public Queen(char column, int row, String color){
		super(column, row, color);
	}
	
	public boolean validMove(String toPosition){
		int direction;
		if(!super.validMove(toPosition)){
			return false;
		}
		int toColumn = (int)(toPosition.charAt(0) - 97);
		int toRow = Integer.parseInt(toPosition.substring(1)) - 1;
		if(toColumn == column-97 || toRow == row-1){ //Queen moving vertically or horizontally
			if(toColumn == column-97){
				direction = toRow - row;
				if(direction > 0){ //moving piece up
					for(int i=row; i < toRow; i++){
						if(chess.Board.ChessBoard.available[toColumn][i] == false){
							return false;
						}
					}
					return true;
				}else{ //moving piece down
					for(int i=row-2; i > toRow; i--){
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
		}else{ //Queen moving diagonally
			int colDiff = toColumn - (column -97);
			int rowDiff = toRow - (row-1);
			if(colDiff > 0 && rowDiff > 0){ //Queen move to top right
				if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
					return false;
				}
				for(int i=column-97,j=row-1; i<toColumn; i++, j++){
					if(chess.Board.ChessBoard.available[i+1][j+1] == false){
						return false;
					}
				}
				return true;
			}else if(colDiff < 0 && rowDiff > 0){ //Queen moving to top left
				if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
					return false;
				}
				for(int i=column-97,j=row-1; i>toColumn; i--, j++){
					if(chess.Board.ChessBoard.available[i-1][j+1] == false){
						return false;
					}
				}
				return true;
			}else if(colDiff < 0 && rowDiff < 0){ //Queen moving bottom left
				if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
					return false;
				}
				for(int i=column-97,j=row-1; i>toColumn; i--, j--){
					if(chess.Board.ChessBoard.available[i-1][j-1] == false){
						return false;
					}
				}
				return true;
			}else{ //Queen moving bottom right
				if(Math.abs(colDiff) != Math.abs(rowDiff)){ // not moving diagonally
					return false;
				}
				for(int i=column-97,j=row-1; i<toColumn; i++, j--){
					if(chess.Board.ChessBoard.available[i+1][j-1] == false){
						return false;
					}
				}
				return true;
			}
		}
	}

	public String toString(){
		if(this.color.equals(Color.BLACK)){
			return "bQ";
		}else{
			return "wQ";
		}
	}
}
