package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board; 
	}

	protected Board getBoard() {
		return board;
	}
	
	//metodo abstrato que faz a regra de mexidas possiveis
	public abstract boolean [][] possibleMoves();
	
	//metodo concreto que utiliza o metodo abstrato para dizer se pode mexer (hook method/método de gancho)
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];  
	}
	
	//varre as linhas e colunas conferindo se o movimento é possivel, se for retorna verdadeiro, se não, false
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
