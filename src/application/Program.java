package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while(!chessMatch.getCheckMate())
		{
			try
			{
				//Limpar a tela a cada iteração do loop.
				UserInterface.clearScreen();
				
				//Inicia o tabuleiro
				UserInterface.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UserInterface.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UserInterface.clearScreen();
				UserInterface.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UserInterface.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null)
				{
					captured.add(capturedPiece);
				}
				
			}
			catch (ChessException e)
			{
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e)
			{
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UserInterface.clearScreen();
		UserInterface.printMatch(chessMatch, captured);
	}

}
