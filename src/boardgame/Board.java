package boardgame;

public class Board
{

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	//Getters e Setter dos atributos rows e columns.
	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getColumns()
	{
		return columns;
	}

	public void setColumns(int columns)
	{
		this.columns = columns;
	}
	
	//Método que retorna uma peça e sua posição.
	public Piece piece(int row, int column)
	{
		return pieces[row][column];
	}
	
	//Sobrecarga do método que retorna uma peça e uma posição obtida.
	public Piece piece(Position position)
	{
		return pieces[position.getRow()][position.getColumn()];
	}
}
