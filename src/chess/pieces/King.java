package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Classe refente a peça do Rei no tabuleiro
public class King extends ChessPiece
{

	public King(Board board, Color color)
	{
		super(board, color);
	}
	
	@Override
	public String toString()
	{
		return "K";
	}

	//Verifica se na posição source onde está o rei existe algum movimento possível.
	private boolean canMove(Position position)
	{
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//Posições acima do rei.
		p.setValues((position.getRow() - 1), position.getColumn());

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Posições abaixo do rei.
		p.setValues((position.getRow() + 1), position.getColumn());

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
				
		//Posições à esquerda do rei.
		p.setValues(position.getRow(), (position.getColumn() - 1));

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Posições à direita do rei.
		p.setValues(position.getRow(), (position.getColumn() + 1));

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}	
				
		//Posições ao noroeste do rei.
		p.setValues((position.getRow() - 1), (position.getColumn() - 1));

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Posições ao nordeste do rei.
		p.setValues((position.getRow() - 1), (position.getColumn() + 1));

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		//Posições ao sudoeste do rei.
		p.setValues((position.getRow() + 1), (position.getColumn() - 1));

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Posições ao sudeste do rei.
		p.setValues((position.getRow() + 1), (position.getColumn() + 1));

		if (getBoard().positionExists(p) && canMove(p))
		{
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		return mat;
	}

}
