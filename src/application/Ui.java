package application;

import chess.ChessPiece;

public class Ui {

	//Imprime o tabuleiro completo. percorre a matriz do tabuleiro linha por linha e imprime cada posição,
	// chamando printPiece para mostrar a peça ou uma casa vazia.
	public static void printBoard(ChessPiece[][] pieces) {
		
		for (int i=0; i<pieces.length; i++) {
			System.out.print((8 - i) + " ");
			
			for (int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			
			System.out.println();
		}
		
		System.out.println("  a b c d e f g h");
	}
	
	// Imprime uma posição do tabuleiro: "-" se estiver vazia ou a peça se existir.
	private static void printPiece(ChessPiece piece) {
		
		if (piece == null) {
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" "); 
	}
}
