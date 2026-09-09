package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece { // classe "Peão" 

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; // matriz booleana com as
																						// dimenções do tabuleiro
		Position p = new Position(0, 0);
		
		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() -1, position.getColumn()); //pega a posição da peça menos 1, que seria a posição de cima na matriz
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //se a linha dele acima estiver vazia ele pode ir pra la
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() -2, position.getColumn()); //pega a posição da peça menos 1, que seria a posição de cima na matriz
			Position p2 = new Position(position.getRow() -1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() -1, position.getColumn() -1); //avança em diagonal a esquerda
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //se a posição existe e se tem peça inimiga
				mat[p.getRow()][p.getColumn()] = true;
			}	
			p.setValues(position.getRow() -1, position.getColumn() +1); //avança em diagonal a direita
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //se a posição existe e se tem peça inimiga
				mat[p.getRow()][p.getColumn()] = true;
			}	
		}
		else {
			p.setValues(position.getRow() +1, position.getColumn()); //pega a posição da peça menos 1, que seria a posição de cima na matriz
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //se a linha dele acima estiver vazia ele pode ir pra la
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() +2, position.getColumn()); //pega a posição da peça menos 1, que seria a posição de cima na matriz
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() +1, position.getColumn() -1); //avança em diagonal a esquerda
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //se a posição existe e se tem peça inimiga
				mat[p.getRow()][p.getColumn()] = true;
			}	
			p.setValues(position.getRow() +1, position.getColumn() +1); //avança em diagonal a direita
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //se a posição existe e se tem peça inimiga
				mat[p.getRow()][p.getColumn()] = true;
			}	
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
