package chess.Pieces;

/**
 * @author ddurbin
 *
 */
public abstract class ChessPiece {
	public enum Color{
		BLACK, WHITE;
	}
	protected char column;
	protected int row;
	protected Color color;
	
	public ChessPiece(char column, int row, String color){
		this.column = column;
		this.row = row;
		if(color.compareToIgnoreCase("BLACK") == 0){
			this.color = Color.BLACK;
		}else
			this.color = Color.WHITE;
	}
	
	public void setLocation(String position){
		if(position.length() != 2){
			System.out.printf("Error: invalid board location\n");
		}
		char column = position.charAt(0);
		int row = Integer.parseInt(position.substring(1));
		if(column < 96 || column > 104 || row < 1 || row > 8){
			System.out.printf("Error: invalid board location\n");
		}
		this.column = column;
		this.row = row;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String getStringColor(){
		if(color.equals(Color.BLACK)){
			return "BLACK";
		}else
			return "WHITE";
	}
	
	public String getposition(){
		return column + "" + row;
	}
	
	public boolean validMove(String toPosition){
		if(toPosition.charAt(0) == column){
			if(Integer.parseInt(toPosition.substring(1)) == row){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString(){
		return column + row + "";
	}
}
