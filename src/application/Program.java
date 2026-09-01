package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); // Scanner usado para ler as jogadas digitadas pelo jogador
		
		ChessMatch chessMatch = new ChessMatch(); // Cria a partida de xadrez e inicializa o tabuleiro com as peças
		
		while (true) { // Mantém o jogo rodando continuamente, repetindo as jogadas
			Ui.printBoard(chessMatch.getPieces()); // Pega as peças atuais da partida e imprime o tabuleiro no console
			System.out.println();
			System.out.print("Origem: "); // Solicita ao usuario a posição da peça que será movimentada
			ChessPosition source = Ui.readChessPosition(sc); // Lê a posição digitada e guarda como origem da peça
			
			System.out.println();
			System.out.print("Destino: "); // Solicita ao usuario a posição de destino
			ChessPosition target = Ui.readChessPosition(sc); 
			
			// Executa o movimento da origem até o destino e guarda a peça capturada, se houver
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target); 
		}
	}

}
