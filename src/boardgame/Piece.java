package boardgame;

public abstract class Piece
{
	
	protected Position position;
	private Board board;
	
	//Construtor da classe Piece.
	public Piece(Board board)
	{
		this.board = board;
		position = null;
	}

	//Getter do atributo board (protected para controlar o acesso ao tabuleiro).
	protected Board getBoard() {
		return board;
	}
	
	//Método que retorna uma matriz dos movimentos possiveis de uma peça.
	public abstract boolean[][] possibleMoves();
	
	//Método que retorna os movimentos peça.
	//Template Method.
	public boolean possibleMove(Position position)
	{
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//Método que retorna uma matriz que verifica se a peça pode se mover para algum lugar.
	//Template Method.
	public boolean isThereAnyPossibleMove()
	{
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++)
		{
			for (int j = 0; j < mat.length; j++)
			{
				if (mat[i][j])
				{
					return true;
				}
			}
		}
		return false;
	}
}
