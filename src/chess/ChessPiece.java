package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece
{
	private Color color;
	private int moveCount;

	//Construtor que recebe board e color, também repassa para o construtor da super classe Piece.
	public ChessPiece(Board board, Color color)
	{
		super(board);
		this.color = color;
	}

	//Getter do atributo color.
	public Color getColor()
	{
		return color;
	}
	
	//Getter do atributo moveCount.
	public int getMoveCount()
	{
		return moveCount;
	}
	
	//Incrementar um movimento no contador.
	public void increaseMoveCount()
	{
		moveCount++;
	}
	
	//Decrementar um movimento no contador.
	public void decreaseMoveCount()
	{
		moveCount--;
	}
	
	//Getter do atributo position convertido.
	public ChessPosition getChessPosition()
	{
		return ChessPosition.fromPosition(position);
		
	}

	//Verifica se um target informado é uma peça adversária ou uma peça sua.
	protected Boolean isThereOpponentPiece(Position position)
	{
		ChessPiece p  = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
}
