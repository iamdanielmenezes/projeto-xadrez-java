package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro, as posições validas são de a1 até h8.");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	// Converte uma posição do xadrez (ex: c5) para a posição da matriz [linha][coluna].
	// Inverte a linha e transforma a letra da coluna em número (a=0, b=1, c=2...).
	protected Position toPosition() { 
		return new Position(8 - row, column - 'a'); 
	}
	
	// Converte uma posição da matriz [linha][coluna] de volta para o xadrez (ex: c5).
	// Transforma o número da coluna em letra e inverte a linha.
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
