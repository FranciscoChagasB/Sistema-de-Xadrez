package chess;

import boardgame.Position;

public class ChessPosition
{
	private char column;
	private int row;
	
	public ChessPosition(char column, int row)
	{
		//Verificação se as posições informadas são válidas.
		if (column < 'a' || column > 'h' || row < 1 || row > 8)
		{
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		}
		
		this.column = column;
		this.row = row;
	}

	//Getters dos atributos column e row.
	public char getColumn()
	{
		return column;
	}
	public int getRow()
	{
		return row;
	}
	
	//Converte uma posição do xadrez em uma posição da matriz.
	protected Position toPosition()
	{
		return new Position(8 - row, column - 'a');
	}
	
	//Converte uma posição da matriz em uma posição do xadrez.
	protected static ChessPosition fromPosition(Position position)
	{
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	//toString que retorna a posição do xadrez na ordem coluna-linha, ex: a1, b1, c2, etc.
	@Override
	public String toString()
	{
		return "" + column + row;
	}
}
