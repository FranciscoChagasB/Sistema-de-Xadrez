package application;

import chess.ChessMatch;

public class Program
{

	public static void main(String[] args)
	{
		
		ChessMatch chessMatch = new ChessMatch();
		
		//Recebe a matriz de peças da partida.
		UserInterface.printBoard(chessMatch.getPieces());
		
	}

}
