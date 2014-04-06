package chess.Pieces;

/**
 * @author ddurbin
 *
 */
public class King extends ChessPiece {

	public King(char column, int row, String color){
		super(column, row, color);
	}
	
	public boolean validMove(String toPosition){
		if(!super.validMove(toPosition)){
			return false;
		}
		int toColumn = toPosition.charAt(0) - 97;
		int toRow = Integer.parseInt(toPosition.substring(1)) - 1;
		if(Math.abs(toColumn - (column-97)) <= 1 && Math.abs(toRow - (row-1)) <= 1){	
			return true;
		}else
			return false;
	}

	public String toString(){
		if(this.color.equals(Color.BLACK)){
			return "bK";
		}else{
			return "wK";
		}
	}
}
