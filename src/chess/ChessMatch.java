package chess;

import boardgame.Board;

public class ChessMatch
{
	private Board board;
	
	//Construtor que inicia o tabuleiro 8x8.
	public ChessMatch()
	{
		board = new Board(8, 8);
	}
	
	//Método que retorna uma matriz de peças correspondentes à partida.
	public ChessPiece[][] getPieces()
	{
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++)
		{
			for (int j = 0; j < board.getColumns(); j++)
			{
				mat[i][j] = (ChessPiece)board.piece(i, j);
			}
		}
		
		return mat;
	}
}
