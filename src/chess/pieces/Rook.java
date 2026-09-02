package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{ //classe "Torre"

	public Rook(Board board, Color color) {
		super(board, color); 
	}

	@Override
	public String toString() {
		return "T"; 
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; //matriz booleana com as dimenções do tabuleiro
		return mat;
	}
}
