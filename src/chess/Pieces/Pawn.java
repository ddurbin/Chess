package chess.Pieces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ddurbin
 *
 */
public class Pawn extends ChessPiece{
	boolean moved;

	public Pawn(char column, int row, String color){
		super(column, row, color);
		moved = false;
	}

	public boolean validMove(String toPosition){
		if(!super.validMove(toPosition)){
			return false;
		}
		int toColumn = (int)(toPosition.charAt(0) - 97);
		int toRow = Integer.parseInt(toPosition.substring(1)) - 1;
		if(moved){ //Pawn has already moved
			if(this.color.equals(Color.BLACK)){ //black piece
				if((column-97) == toColumn){ //moving in same column
					if((row-1)-1 == toRow && chess.Board.ChessBoard.available[toColumn][toRow] == true){
						if(color.equals(Color.BLACK) && toRow == 0){
							promotion(column-97, row-1);
							return true;
						}else if(color.equals(Color.WHITE) && toRow == 7){
							promotion(column-97, row-1);
							return true;
						}else
							return true;
					}else
						return false;
				}else if(((column-97)+1 == toColumn || (column-97)-1 == toColumn) && (row-1)-1 == toRow){
					if(chess.Board.ChessBoard.available[toColumn][toRow] == false){
						if(color.equals(Color.BLACK) && toRow == 0){
							promotion(column-97, row-1);
							return true;
						}else if(color.equals(Color.WHITE) && toRow == 7){
							promotion(column-97, row-1);
							return true;
						}else
							return true;
					}else
						return false;
				}else
					return false;
			}else{ //white piece
				if((column-97) == toColumn){ //moving in same column
					if(row == toRow && chess.Board.ChessBoard.available[toColumn][toRow] == true){
						if(color.equals(Color.BLACK) && toRow == 0){
							promotion(column-97, row-1);
							return true;
						}else if(color.equals(Color.WHITE) && toRow == 7){
							promotion(column-97, row-1);
							return true;
						}else
							return true;
					}else
						return false;
				}else if(((column-97)+1 == toColumn || (column-97)-1 == toColumn) && row == toRow){
					if(chess.Board.ChessBoard.available[toColumn][toRow] == false){
						if(color.equals(Color.BLACK) && toRow == 0){
							promotion(column-97, row-1);
							return true;
						}else if(color.equals(Color.WHITE) && toRow == 7){
							promotion(column-97, row-1);
							return true;
						}else
							return true;
					}else
						return false;
				}else
					return false;
			}
		}else{ //Pawn first move
			if(this.color.equals(Color.BLACK)){ //piece is black
				if((column-97) == toColumn){ //moving in same column
					if(((row-1)-1 == toRow || (row-1)-2 == toRow) && chess.Board.ChessBoard.available[toColumn][toRow] == true){
						moved = true;
						return true;
					}else
						return false;
				}else if(((column-97)+1 == toColumn || (column-97)-1 == toColumn) && (row-1)-1 == toRow){
					if(chess.Board.ChessBoard.available[toColumn][toRow] == false){
						moved = true;
						return true;
					}else
						return false;
				}else 
					return false;
			}else{//piece is white
				if((column-97) == toColumn){ //moving in same column
					if((row == toRow || (row+1) == toRow) && chess.Board.ChessBoard.available[toColumn][toRow] == true){
						moved = true;
						return true;
					}else
						return false;
				}else if(((column-97)+1 == toColumn || (column-97)-1 == toColumn) && row == toRow){
					if(chess.Board.ChessBoard.available[toColumn][toRow] == false){
						moved = true;
						return true;
					}else
						return false;
				}else
					return false;
			}
		}
	}
	
	//Promotes Pawn to player's request
	private void promotion(int toColumn, int toRow){
		System.out.printf("Column: %d Row: %d\n", toColumn, toRow);
		String input = null;
		boolean correct = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("Your Pawn has been promoted");
		System.out.println("What would you like to be promoted to? (Queen, Rook, Bishop, or Knight)");
		while(!correct){
			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(input.equalsIgnoreCase("queen")){
				char column = (char)(toColumn+97);
				Queen p = new Queen(column, toRow+1, this.getStringColor());
				chess.Board.ChessBoard.board[toColumn][toRow] = null;
				chess.Board.ChessBoard.board[toColumn][toRow] = p;
				correct = true;
			}else if(input.equalsIgnoreCase("rook")){
				char column = (char)(toColumn+97);
				Rook p = new Rook(column, toRow+1, this.getStringColor());
				chess.Board.ChessBoard.board[toColumn][toRow] = null;
				chess.Board.ChessBoard.board[toColumn][toRow] = p;
				correct = true;
			}else if(input.equalsIgnoreCase("bishop")){
				char column = (char)(toColumn+97);
				Bishop p = new Bishop(column, toRow+1, this.getStringColor());
				chess.Board.ChessBoard.board[toColumn][toRow] = null;
				chess.Board.ChessBoard.board[toColumn][toRow] = p;
				correct = true;
			}else if(input.equalsIgnoreCase("knight")){
				char column = (char)(toColumn+97);
				Knight p = new Knight(column, toRow+1, this.getStringColor());
				chess.Board.ChessBoard.board[toColumn][toRow] = p;
				correct = true;
			}else{
				System.out.println("Invalid input, try again.");
				try {
					input = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return;
	}


	@Override
	public String toString(){
		if(this.color.equals(Color.BLACK)){
			return "bp";
		}else{
			return "wp";
		}
	}
}

