package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Classe refente a peça da Torre no tabuleiro
public class Rook extends ChessPiece
{

	public Rook(Board board, Color color)
	{
		super(board, color);
	}
	
	@Override
	public String toString()
	{
		return "R";
	}
	
	//Verifica os movimentos possíveis para a peça Torre.
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//Posições acima da torre.
		p.setValues((position.getRow() - 1), position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Posições à esquerda da torre.
		p.setValues((position.getRow()), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Posições à direita da torre.
		p.setValues((position.getRow()), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Posições abaixo da torre.
		p.setValues((position.getRow() + 1), position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}

}
