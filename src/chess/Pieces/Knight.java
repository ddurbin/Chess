package chess.Pieces;

/**
 * @author ddurbin
 *
 */
public class Knight extends ChessPiece {

	public Knight(char column, int row, String color){
		super(column, row, color);
	}
	
	public boolean validMove(String toPosition){
		if(!super.validMove(toPosition)){
			return false;
		}
		int toColumn = (toPosition.charAt(0) - 97);
		int toRow = Integer.parseInt(toPosition.substring(1)) - 1;
		int colDiff = toColumn - (column - 97);
		int rowDiff = toRow - (row - 1);
		if(colDiff > 0 && rowDiff > 0){	//Knight moving top right
			if((column -97) + 2 == toColumn && row == toRow){ // +2 column +1 row
				return true;
			}else if((column - 97) + 1 == toColumn && row + 1 == toRow){ // +1 column +2 row
				return true;
			}else
				return false;
		}else if(colDiff < 0 && rowDiff > 0){ //Knight moving to top left
			if((column -97) - 2 == toColumn && row == toRow){ // -2 column +1 row
				return true;
			}else if((column - 97) - 1 == toColumn && row + 1 == toRow){ // -1 column +2 row
				return true;
			}else
				return false;
		}else if(colDiff < 0 && rowDiff < 0){ //Knight moving bottom left
			if((column -97) - 2 == toColumn && (row-1)-1 == toRow){ // -2 column -1 row
				return true;
			}else if((column - 97) - 1 == toColumn && (row-1)-2 == toRow){ // -1 column +2 row
				return true;
			}else
				return false;
		}else{ //knight moving bottom right
			if((column -97) + 2 == toColumn && (row-1)-1 == toRow){ // +2 column -1 row
				return true;
			}else if((column - 97) + 1 == toColumn && (row-1)-2 == toRow){ // +1 column -2 row
				return true;
			}else
				return false;
		}
	}

	public String toString(){
		if(this.color.equals(Color.BLACK)){
			return "bN";
		}else{
			return "wN";
		}
	}
}
