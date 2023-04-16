package boardgame;

public class Piece
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
	
}
