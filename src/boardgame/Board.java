package boardgame;

public class Board
{

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns)
	{
		//Verifica se o tabuleiro informado tem ao menos 1 linha e 1 coluna;
		if (rows < 1 || columns < 1)
		{
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	//Getters dos atributos rows e columns.
	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
	}

	//Método que retorna uma peça e sua posição.
	public Piece piece(int row, int column)
	{
		//Chama o método positionExists para verificar se a posição existe.
		if (!positionExists(row, column))
		{
			throw new BoardException("Position not on the board");
		}
		
		return pieces[row][column];
	}
	
	//Sobrecarga do método que retorna uma peça e uma posição obtida.
	public Piece piece(Position position)
	{
		//Chama o método positionExists para verificar se a posição existe.
		if (!positionExists(position))
		{
			throw new BoardException("Position not on the board");
		}
		
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//Método que coloca uma x peça em uma x posição.
	public void placePiece(Piece piece, Position position)
	{
		//Chama o método thereIsAPiece para verificar se já tem alguma peça na posição informada.
		if (thereIsAPiece(position))
		{
			throw new BoardException("There is already a piece on position " + position);
		}
		
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//Retorna verdadeiro ou falso para uma posição informada.
	private boolean positionExists(int row, int column)
	{
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//Reutiliza o método positionExists anterior para verificar se uma posição existe.
	public boolean positionExists(Position position)
	{
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position)
	{
		//Chama o método positionExists para verificar se a posição existe.
		if (!positionExists(position))
		{
			throw new BoardException("Position not on the board");
		}
		
		return piece(position) != null;
	}
}
