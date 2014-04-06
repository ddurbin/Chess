package chess.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chess.Board.ChessBoard;
import chess.Pieces.ChessPiece.Color;

/**
 * @author ddurbin
 *
 */
public class Chess {
	public static ChessBoard board;

	public static void main(String[] args) throws IOException{
		board = new ChessBoard();
		board.buildBoard();
		board.printBoard();
		String wInput = null;
		String bInput = null;
		String[] tokenized;
		boolean wDraw = false;
		boolean bDraw = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;;i++){
			if(i % 2 == 0){ //white move
				System.out.println();
				System.out.print("White's move: ");
				while(true){
					wInput = reader.readLine();
					if(validEntry(wInput, "white")){
						tokenized = wInput.split(" ");
						if(bDraw && tokenized[0].equals("draw")){ //draw accepted
							System.out.println("Draw");
							System.exit(0);
						}
						if(tokenized.length == 1 && !tokenized[0].equals("draw")){ //resign
							System.out.println("Black wins");
							System.exit(0);
						}else{ //play
							if(tokenized.length == 3){ //ask for draw
									wDraw = true;
							}
							if(tokenized.length >= 2 && ChessBoard.movePiece(tokenized[0], tokenized[1])){
								if(chess.Board.ChessBoard.blackCheck()){ //move places player in check
									ChessBoard.reverseMove(tokenized[0], tokenized[1]);
									board.printBoard();
								}else{
									board.printBoard();
									if(chess.Board.ChessBoard.whiteCheck()){
										if(chess.Board.ChessBoard.whiteCheckMate()){
											System.out.println("Checkmate");
											System.out.println("White wins");
											System.exit(0);
										}else{
											System.out.println("Check");
											System.out.println();
										}
									}
									break;
								}
							}
						}
					}
					System.out.println("Illegal move, try again.");
					System.out.println();
					board.printBoard();
					System.out.print("White's move: ");
					System.out.println();

				}
			}else{ //black move
				System.out.println();
				System.out.print("Black's move: ");
				while(true){
					bInput = reader.readLine();
					if(validEntry(bInput, "black")){
						tokenized = bInput.split(" ");
						if(wDraw && tokenized[0].equals("draw")){ //draw accepted
							System.out.println("Draw");
							System.exit(0);
						}
						if(tokenized.length == 1 && !tokenized[0].equals("draw")){ //resign
							System.out.println("White wins");
							System.exit(0);
						}else{ //play
							if(tokenized.length == 3){ //ask for draw
								bDraw = true;
							}
							if(tokenized.length >= 2 && ChessBoard.movePiece(tokenized[0], tokenized[1])){
								if(chess.Board.ChessBoard.whiteCheck()){ //move places player in check
									ChessBoard.reverseMove(tokenized[0], tokenized[1]);
									board.printBoard();
								}else{
									board.printBoard();
									if(chess.Board.ChessBoard.blackCheck()){
										if(chess.Board.ChessBoard.blackCheckMate()){
											System.out.println("Checkmate");
											System.out.println("Black wins");
											System.exit(0);
										}else{
											System.out.println("Check");
											System.out.println();
										}
									}
									break;
								}
							}
						}

					}
					System.out.println("Illegal move, try again.");
					System.out.println();
					board.printBoard();
					System.out.print("Black's move: ");
					System.out.println();
				}
			}
		}
	}

	public static Boolean validEntry(String input, String color){
		Color piece;
		if(color.equalsIgnoreCase("black")){
			piece = Color.BLACK;
		}else{
			piece = Color.WHITE;
		}
		if(input.equals("resign")){
			return true;
		}else if(input.length() == 11){
			if(input.substring(6).equals("draw?")){
				return true;
			}else{
				return false;
			}
		}else if(input.length() == 4){
			if(input.equals("draw")){
				return true;
			}else
				return false;
		}else{
			if(input.length() == 5){
				char fromCol = input.charAt(0);
				char toCol = input.charAt(3);
				int fromRow = Integer.parseInt(input.substring(1, 2));
				int toRow = Integer.parseInt(input.substring(4));
				if(fromCol < 96 || fromCol > 104 || toCol < 96 || toCol > 104 || fromRow < 1 || fromRow > 8 || toRow < 1 || toRow > 8){
					return false;
				}else
					if(ChessBoard.getPiece(input.substring(0, 2)) == null){
						return false;
					}
				if(ChessBoard.getPiece(input.substring(0, 2)).getColor().equals(piece)){
					return true;
				}else
					return false;
			}
		}
		return false;
	}
}
