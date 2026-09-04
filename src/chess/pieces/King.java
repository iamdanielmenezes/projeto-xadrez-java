package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{ //classe "Rei"

	public King(Board board, Color color) {
		super(board, color); 
	}

	@Override
	public String toString() {
		return "R"; 
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; //matriz booleana com as dimenções do tabuleiro
		
		Position p = new Position(0, 0);
		
		//testando possiveis posições
		
		//posição acima
		p.setValues(position.getRow() - 1, position.getColumn()); //pega a posição da peça menos 1, que seria a posição de cima na matriz
		if (getBoard().positionExists(p) && canMove(p)) { //se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//posição abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); //pega a posição da peça mais 1, que seria a posição de baixo na matriz
		if (getBoard().positionExists(p) && canMove(p)) { //se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//posição a esquerda
		p.setValues(position.getRow(), position.getColumn() - 1); //pega a posição da peça menos 1, que seria a posição a esquerda na matriz
		if (getBoard().positionExists(p) && canMove(p)) { //se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//posição a direita
		p.setValues(position.getRow(), position.getColumn() + 1); //pega a posição da peça menos 1, que seria a posição a direita na matriz
		if (getBoard().positionExists(p) && canMove(p)) { //se a posição existir e tiver vaga, ela vai ser verdadeira
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//posição vertical para esquerda acima (noroeste)
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { 
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//posição vertical para direita acima (nordeste)
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { 
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//posição vertical para esquerda abaixo (suldoeste)
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { 
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//posição vertical para direita abaixo (suldeste)
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { 
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
