package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Rook;

//classe partida de xadrez
public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board; //toda partida precisa de um tabuleiro
	private boolean check; //boolano para logica de check
	private boolean checkMate; //booleano para logica de xeque mate
	
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
	
	public boolean getCheck() {
		return 	check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Você não pode se colocar em xeque");
		}
		
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
		nextTurn();
		}
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
		ChessPiece p = (ChessPiece)board.removePiece(source); //remove a peça na posição de origem
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target); //se tiver uma peça na posição de destino ela é removida(capturada)
		board.placePiece(p, target); //pega a peça que saiu da origem e coloca no destino
		
		if (capturedPiece != null) { //sempre que capturar remove da lista de peças no tabuleiro e adiciona a lista de peças capturadas
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	//dizfaz o movimento caso não seja permitido, como colcoar o proprio rei em xeque
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target); //tira a peça que foi movida do destino
		p.decreaseMoveCount();
		board.placePiece(p, source); //devolve a peça capturada para a posição de origem
		
		if (capturedPiece != null) { //se tiver capturado uma peça tem que devolvela
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
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
	
	//se o oponente for de uma cor vc é de outra
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor " + color + " no tabuleiro");
	}
	
	//metodo que testa o check arrendo as peças do adversario e vendo se alguma pode dar check 
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition(); //pega a posição do rei em formato de matriz
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		 for (Piece p : opponentPieces) {
			 boolean[][] mat = p.possibleMoves();
			 if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				 return true;
			 }
		 }
		 return false;
	}
	
	//metodo de xeque mate
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) { //testa se não esta em xeque, poruqe tambem não vai estar em xeque mate
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) { //se encontrar algum movimento que tira do xeque da falso, se não retorna true (esta em xeque)
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			} 
		}
		return true;
	}
	
	//recebe peça + posição de xadrez → converte a posição → coloca a peça no tabuleiro.
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	//metodo responsavel para colocar as peças no tabuleiro
	private void initialSetup() {
		 	placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		 	placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		 	placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
	        placeNewPiece('e', 1, new King(board, Color.WHITE));
	        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
	        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
	        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
	        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

	        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
	        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
	        placeNewPiece('e', 8, new King(board, Color.BLACK));
	        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
	        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
	        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}
	
}
