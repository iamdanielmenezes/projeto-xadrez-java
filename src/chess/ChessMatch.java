package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//classe partida de xadrez
public class ChessMatch {

	private Board board; //toda partida precisa de um tabuleiro
	
	public ChessMatch() {
		board = new Board(8, 8); //construtor que define o tamanho do tabuleiro
		initialSetup();
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
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	} 
	
	//faz o movimento	
	private Piece makeMove(Position source, Position target) {  //recebe a posição inicial e a posição de destino
		Piece p = board.removePiece(source); //remove a peça na posição de origem
		Piece capturedPiece = board.removePiece(target); //se tiver uma peça na posição de destino ela é removida(capturada)
		board.placePiece(p, target); //pega a peça que saiu da origem e coloca no destino
		return capturedPiece;
	}
	
	//valida se a posição da peça que quero mudar existe
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) { //se não tiver nenhum movimento possivel retorna a exceção
			throw new ChessException("Não existe movimentos possiveis para a peça escolhida"); 
		}
	}
	
	//recebe peça + posição de xadrez → converte a posição → coloca a peça no tabuleiro.
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
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
