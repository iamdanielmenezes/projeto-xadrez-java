package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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
		
		Position p = new Position(0, 0);
		
		//marcar as posições de verdadeiro ACIMA da peça
		p.setValues(position.getRow() -1, position.getColumn()); //pega a posição da peça menos 1, que seria a posição de cima na matriz
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1); //enquanto der verdadeiro(casas vazias) repete
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//marcar as posições de verdadeiro a ESQUERDA da peça
		p.setValues(position.getRow(), position.getColumn() -1 ); //pega a posição da peça menos 1, que seria a posição a esquerda na matriz
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1); //enquanto der verdadeiro(casas vazias) repete
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//marcar as posições de verdadeiro a DIREITA da peça
		p.setValues(position.getRow(), position.getColumn() +1 ); //pega a posição da peça mais 1, que seria a posição a direita na matriz
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1); //enquanto der verdadeiro(casas vazias) repete
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//marcar as posições de verdadeiro a ABAIXO da peça
				p.setValues(position.getRow() +1, position.getColumn()); //pega a posição da peça mais 1, que seria a posição de baixo na matriz
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posição existir e tiver vaga, ela vai ser verdadeira
					mat[p.getRow()][p.getColumn()] = true;
					p.setRow(p.getRow() + 1); //enquanto der verdadeiro(casas vazias) repete
				}
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
		
		return mat;
	}
}
