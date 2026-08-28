package chess;

import boardgame.Board;

//classe partida de xadrez
public class ChessMatch {

	private Board board; //toda partida precisa de um tabuleiro
	
	public ChessMatch() {
		board = new Board(8, 8); //construtor que define o tamanho do tabuleiro
	}
	
	// Percorre todas as linhas e colunas do tabuleiro,
	// copia cada peça para uma matriz de ChessPiece e retorna essa matriz
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()] [board.getColumns()];
		
		for (int i=0; i<board.getRows(); i++) { // percorre as linhas
			for (int j=0; j<board.getColumns(); j++) { // percorre as colunas
				mat[i][j] = (ChessPiece) board.piece(i, j); // pega a peça dessa posição
			}
		}
		
		return mat;
	}
	
}
