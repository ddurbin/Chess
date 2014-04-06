package chess.Board;

import chess.Pieces.Bishop;
import chess.Pieces.ChessPiece;
import chess.Pieces.ChessPiece.Color;
import chess.Pieces.King;
import chess.Pieces.Knight;
import chess.Pieces.Pawn;
import chess.Pieces.Queen;
import chess.Pieces.Rook;
/**
 * @author ddurbin
 *
 */
public class ChessBoard {
	
	public static ChessPiece[][] board;
	public static Boolean[][] available;
	public static ChessPiece bKing, wKing;
	
	public ChessBoard(){
		board = new ChessPiece[8][8];
		available = new Boolean[8][8];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				available[i][j] = true;
			}
		}
	}
	
	public  Boolean[][] getAvailable(){
		return available;
	}
	
	public void buildBoard(){
		String color = "WHITE";
		Rook rb1 = new Rook('a', 1, color);
		board[0][0] = rb1;
		available[0][0] = false;
		Knight kn1 = new Knight('b', 1, color);
		board[1][0] = kn1;
		available[1][0] = false;
		Bishop bb1 = new Bishop('c', 1, color);
		board[2][0] = bb1;
		available[2][0] = false;
		Queen bq1 = new Queen('d', 1, color);
		board[3][0] = bq1;
		available[3][0] = false;
		King kb1 = new King('e', 1, color);
		board[4][0] = kb1;
		wKing = kb1;
		available[4][0] = false;
		Bishop bb2 = new Bishop('f', 1, color);
		board[5][0] = bb2;
		available[5][0] = false;
		Knight kn2 = new Knight('g', 1, color);
		board[6][0] = kn2; 
		available[6][0] = false;
		Rook rb2 = new Rook('h', 1, color);
		board[7][0] = rb2;
		available[7][0] = false;
		Pawn p;
		char c = 'a';
		for(int i=0; i<8; i++){
			p = new Pawn(c, 2, color);
			board[i][1] = p;
			available[i][1] = false;
			c = (char)(c + 1);
		}
		c = 'a';
		color = "BLACK";
		for(int i=0; i<8; i++){
			p = new Pawn(c, 7, color);
			board[i][6] = p;
			available[i][6] = false;
			c = (char)(c + 1);
		}
		Rook rb3 = new Rook('a', 8, color);
		board[0][7] = rb3;
		available[0][7] = false;
		Knight kn3 = new Knight('b', 8, color);
		board[1][7] = kn3;
		available[1][7] = false;
		Bishop bb3 = new Bishop('c', 8, color);
		board[2][7] = bb3;
		available[2][7] = false;
		Queen bq2 = new Queen('d', 8, color);
		board[3][7] = bq2;
		available[3][7] = false;
		King kb2 = new King('e', 8, color);
		board[4][7] = kb2;
		bKing = kb2;
		available[4][7] = false;
		Bishop bb4 = new Bishop('f', 8, color);
		board[5][7] = bb4;
		available[5][7] = false;
		Knight kn4 = new Knight('g', 8, color);
		board[6][7] = kn4;
		available[6][7] = false;
		Rook rb4 = new Rook('h', 8, color);
		board[7][7] = rb4;
		available[7][7] = false;
	}
	
	public static ChessPiece getPiece(String position){
		int column = (int)(position.charAt(0) - 97);
		int row = Integer.parseInt(position.substring(1)) - 1;
		if(available[column][row] == false){
			return board[column][row];
		}else
			return null;
	}
	
	public static boolean movePiece(String from, String to){
		int currColumn = (int)(from.charAt(0) - 97);
		int currRow = Integer.parseInt(from.substring(1)) - 1;
		int toColumn = (int)(to.charAt(0) - 97);
		int toRow = Integer.parseInt(to.substring(1)) - 1;
		ChessPiece fromPiece = getPiece(from);
		ChessPiece toPiece = getPiece(to);
		if(fromPiece != null){ //piece to be moved exists
			if(fromPiece.validMove(to)){ //valid move for piece
				if(available[toColumn][toRow] == true){ // to location is empty
					if(fromPiece instanceof Pawn){
						fromPiece = getPiece(from);
					}
					available[currColumn][currRow] = true;
					board[currColumn][currRow] = null;
					available[toColumn][toRow] = false;
					fromPiece.setLocation(to);
					board[toColumn][toRow] = fromPiece;
					return true;
				}else if(!toPiece.getColor().equals(fromPiece.getColor())){ //to location not empty and piece is enemy, knock it out!!
					if(fromPiece instanceof Pawn){
						fromPiece = getPiece(from);
					}
					available[currColumn][currRow] = true;
					board[currColumn][currRow] = null;
					available[toColumn][toRow] = false;
					fromPiece.setLocation(to);
					board[toColumn][toRow] = fromPiece;
					return true;
				}else //piece moving to location of same color
					return false;
			}else // invalid move for piece
				return false; 
		}else // location of piece to be moved doesn't exist
			return false;
		
	}
	
	public static void reverseMove(String from, String to){
		int currColumn = from.charAt(0) - 97;
		int currRow = Integer.parseInt(from.substring(1)) - 1;
		int toColumn = to.charAt(0) - 97;
		int toRow = Integer.parseInt(to.substring(1)) - 1;
		ChessPiece temp = getPiece(to);
		board[currColumn][currRow] = temp;
		temp.setLocation(from);
		board[toColumn][toRow] = null;
		available[currColumn][currRow] = false;
		available[toColumn][toRow] = true;
		return;
	}
	
	//returns true if white is in check, false otherwise.
	public static boolean blackCheck(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(available[i][j] == false && board[i][j].getColor().equals(Color.BLACK)){
					if(board[i][j].validMove(wKing.getposition())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//returns true if black is in check, false otherwise.
	public static boolean whiteCheck(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(available[i][j] == false && board[i][j].getColor().equals(Color.WHITE)){
					if(board[i][j].validMove(bKing.getposition())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//returns true if white is in checkmate, false otherwise.
	public static boolean blackCheckMate(){
		boolean hasMove = false;
		int currColumn = wKing.getposition().indexOf(0) - 97;
		int currRow = Integer.parseInt(wKing.getposition().substring(1, 2));
		int tempColumn, tempRow;
		String pos;
		tempColumn = currColumn;
		tempRow = currRow+1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}else
				movePiece(wKing.getposition(), pos);
				hasMove = true;
		}
		tempColumn = currColumn + 1;
		tempRow = currRow+1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}
			movePiece(wKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn + 1;
		tempRow = currRow;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}
			movePiece(wKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn + 1;
		tempRow = currRow-1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}
			movePiece(wKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn;
		tempRow = currRow-1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}
			movePiece(wKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn - 1;
		tempRow = currRow-1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}
			movePiece(wKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn - 1;
		tempRow = currRow;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}
			movePiece(wKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn - 1;
		tempRow = currRow+1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(wKing.validMove(pos)){
			movePiece(wKing.getposition(), pos);
			if(blackCheck()){
				reverseMove(wKing.getposition(), pos);
			}
			movePiece(wKing.getposition(), pos);
			hasMove = true;
		}
		return !hasMove;
	}
	
	//returns true if black is in checkmate, false otherwise.
	public static boolean whiteCheckMate(){
		boolean hasMove = false;
		int currColumn = bKing.getposition().indexOf(0) - 97;
		int currRow = Integer.parseInt(bKing.getposition().substring(1, 2));
		int tempColumn, tempRow;
		String pos;
		tempColumn = currColumn;
		tempRow = currRow+1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}else
				movePiece(bKing.getposition(), pos);
				hasMove = true;
		}
		tempColumn = currColumn + 1;
		tempRow = currRow+1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}
			movePiece(bKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn + 1;
		tempRow = currRow;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}
			movePiece(bKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn + 1;
		tempRow = currRow-1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}
			movePiece(bKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn;
		tempRow = currRow-1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}
			movePiece(bKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn - 1;
		tempRow = currRow-1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}
			movePiece(bKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn - 1;
		tempRow = currRow;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}
			movePiece(bKing.getposition(), pos);
			hasMove = true;
		}
		tempColumn = currColumn - 1;
		tempRow = currRow+1;
		pos = ((char)tempColumn+97) + "" + (tempRow);
		if(bKing.validMove(pos)){
			movePiece(bKing.getposition(), pos);
			if(whiteCheck()){
				reverseMove(bKing.getposition(), pos);
			}
			movePiece(bKing.getposition(), pos);
			hasMove = true;
		}
		return !hasMove;
	}
	
	
	public void printBoard(){
		System.out.println();
		for(int i=7; i>=0; i--){
			for(int j=0; j<8; j++){
				if(available[j][i] == false){
					System.out.print(board[j][i] + " ");
				}else{
					if(j % 2 == 1){
						if(i % 2 == 1){
							System.out.print("## ");
						}else{
							System.out.print("   ");
						}
					}else{
						if(i % 2 == 0){
							System.out.print("## ");
						}else{
							System.out.print("   ");
						}
					}
				}
			}
			System.out.println(i+1);
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}
}
