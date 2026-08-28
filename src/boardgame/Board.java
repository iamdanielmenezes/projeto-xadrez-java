package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //a matriz de peças vai ser instanciada com a quantidade de linhas e colunas informadas
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//metodo que retorna a peça quando damos uma linha e uma coluna. recebe linha e coluna separadamente
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	//Recebe uma posição e retorna a peça que está nessa posição do tabuleiro. recebe um objeto que já contém linha e coluna
	public Piece piece(Position position) {
		return pieces [position.getRow()] [position.getColumn()]; 
	}
}
