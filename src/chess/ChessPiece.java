package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//classe peça de xadrez
public abstract class ChessPiece extends Piece{

	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	//checa se tem uma peça do oponente na posição
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color; //verifica se a posição é nula e se a peça na posição é da mesma cor da minha
	}
}
