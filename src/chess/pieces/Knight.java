package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {
	
	public Knight(Board board, Color color) {
		super(board, color); 
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; // matriz booleana com as
																						// dimenções do tabuleiro

		Position p = new Position(0, 0);

		
		p.setValues(position.getRow() - 1, position.getColumn() - 2); // pega a posição da peça menos 1 na linha e menos 2 na coluna							
		if (getBoard().positionExists(p) && canMove(p)) { // se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() - 1); // pega a posição da peça menos 2 na linha e menos 1 na coluna																
		if (getBoard().positionExists(p) && canMove(p)) { // se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() + 1); // pega a posição da peça menos 2 na linha e mais 1 na coluna															// a esquerda na matriz
		if (getBoard().positionExists(p) && canMove(p)) { // se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() + 2); // pega a posição da peça menos 1 na linha e mais 2 na coluna																// a direita na matriz
		if (getBoard().positionExists(p) && canMove(p)) { // se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() + 2); // pega a posição da peça mais 1 na linha e mais 2 na coluna
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() + 1); // pega a posição da peça mais 2 na linha e mais 1 na coluna
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() - 1); // pega a posição da peça mais 2 na linha e menos 1 na coluna
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() - 2); // pega a posição da peça mais 1 na linha e menos 2 na coluna
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
}
