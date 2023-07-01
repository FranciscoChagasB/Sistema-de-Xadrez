package boardgame;

public class Position
{

	private int row;
	private int column;
	
	//Construtor da classe Position.
	public Position(int row, int column)
	{
		super();
		this.row = row;
		this.column = column;
	}

	//Getters e Setters dos atributos row e column.
	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getColumn()
	{
		return column;
	}

	public void setColumn(int column)
	{
		this.column = column;
	}
	
	public void setValues(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	
	//Sobreposição para imprimir a posição.
	@Override
	public String toString()
	{
		return row + ", " + column;
	}
}
