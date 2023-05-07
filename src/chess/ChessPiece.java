package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece
{
	private Color color;

	//Construtor que recebe board e color, também repassa para o construtor da super classe Piece.
	public ChessPiece(Board board, Color color)
	{
		super(board);
		this.color = color;
	}

	//Getter do atributo color.
	public Color getColor() {
		return color;
	}

	
	
}
