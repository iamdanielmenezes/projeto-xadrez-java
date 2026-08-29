package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro: é necessário que haja pelo menos uma linha e uma coluna.");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //a matriz de peças vai ser instanciada com a quantidade de linhas e colunas informadas
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	//metodo que retorna a peça quando damos uma linha e uma coluna. recebe linha e coluna separadamente
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posição não está no tabuleiro.");
		}
		return pieces[row][column];
	}
	
	//Recebe uma posição e retorna a peça que está nessa posição do tabuleiro. recebe um objeto que já contém linha e coluna
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não está no tabuleiro.");
		}
		return pieces [position.getRow()] [position.getColumn()]; 
	}
	
	//metodo que pega a matriz de peças(atributo dessa classe) na posição da linha e coluna dada e atribuir a peça conrrespondente
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Ja tem uma peça na posição " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	} 
	
	private boolean positionExists(int row, int column) { //testa se uma posição existe pela linha e coluna
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) { //testa se uma posição existe pela posição
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) { //testa se tem uma peça na posição dada
		if (!positionExists(position)) {
			throw new BoardException("Posição não está no tabuleiro.");
		}
		return piece(position) != null; 
				
	}
}
