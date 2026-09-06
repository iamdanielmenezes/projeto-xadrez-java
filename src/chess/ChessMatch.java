package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//classe partida de xadrez
public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board; //toda partida precisa de um tabuleiro
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>(); 
	
	public ChessMatch() {
		board = new Board(8, 8); //construtor que define o tamanho do tabuleiro
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	// Percorre todas as linhas e colunas do tabuleiro,
	// copia cada peça para uma matriz de ChessPiece e retorna essa matriz
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()] [board.getColumns()];
		
		for (int i=0; i<board.getRows(); i++) { // percorre as linhas
			for (int j=0; j<board.getColumns(); j++) { // percorre as colunas
				mat[i][j] = (ChessPiece) board.piece(i, j); // pega a peça dessa posição
			}
		}
		return mat;
	}
	
	//metodo para mover as peças
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) { //recebe a posição inicial e a posição de destino
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece)capturedPiece;
	} 
	
	//imprime movientos possiveis
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves(); //retorna as posições possiveis para determinada peça na sua posição de origem
	}
	
	//faz o movimento	
	private Piece makeMove(Position source, Position target) {  //recebe a posição inicial e a posição de destino
		Piece p = board.removePiece(source); //remove a peça na posição de origem
		Piece capturedPiece = board.removePiece(target); //se tiver uma peça na posição de destino ela é removida(capturada)
		board.placePiece(p, target); //pega a peça que saiu da origem e coloca no destino
		
		if (capturedPiece != null) { //sempre que capturar remove da lista de peças no tabuleiro e adiciona a lista de peças capturadas
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	//valida se a posição da peça que quero mudar existe
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("A peça escolhida não é sua");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) { //se não tiver nenhum movimento possivel retorna a exceção
			throw new ChessException("Não existe movimentos possiveis para a peça escolhida"); 
		}
	}
	
	//valida se a posição de destino é valida
	public void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) //se para a peça de origem, a posição de destino não for um movimento possivel ...
		throw new ChessException("A peça escolhida não pode se mover para a posição de destino"); //significa que é invalido e lança a exceção
	}
	
	//metodo para trocar de turno/jogador a cada jogada
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; //logica= se o jogador é color.WHITE agora vai ser color.BLACK etc...
	}
	
	//recebe peça + posição de xadrez → converte a posição → coloca a peça no tabuleiro.
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	//metodo responsavel para colocar as peças no tabuleiro
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
}
