package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch
{
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	//Construtor que inicia o tabuleiro 8x8.
	public ChessMatch()
	{
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	//Getter do atributo referente ao turno em andamento.
	public int getTurn()
	{
		return turn;
	}
	
	//Getter do atributo referente a cor do jogador atual.
	public Color getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	//Método que retorna uma matriz de peças correspondentes à partida.
	public ChessPiece[][] getPieces()
	{
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++)
		{
			for (int j = 0; j < board.getColumns(); j++)
			{
				mat[i][j] = (ChessPiece)board.piece(i, j);
			}
		}
		
		return mat;
	}
	
	//Valida se uma posição tem movimentos possíveis.
	public boolean[][] possibleMoves(ChessPosition sourcePosition)
	{
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	//Método que irá realizar o movimento de uma peça informada.
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition)
	{
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	//Método que configura o movimento de uma peça.
	private Piece makeMove(Position source, Position target)
	{
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	//Método para validar a posição source informada pelo usuário.
	private void validateSourcePosition(Position position)
	{
		if (!board.thereIsAPiece(position))
		{
			throw new ChessException("There's no piece on source position");
		}
		
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor())
		{
			throw new ChessException("The chosen piece isn't yours");
		}
		
		if (!board.piece(position).isThereAnyPossibleMove())
		{
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	//Método para contabilizar e ir para o próximo turno.
	private void nextTurn()
	{
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//Método para validar a posição target informada pelo usuário.
	private void validateTargetPosition(Position source, Position target)
	{
		if (!board.piece(source).possibleMove(target)) 
		{
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	//Método para colocar uma nova peça no tabuleiro.
	private void placeNewPiece(char column, int row, ChessPiece piece)
	{
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	//Método que cria uma configuração inicial para as peças no tabuleiro.
	private void initialSetup()
	{
		//Colocando peças brancas.
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        //Colocando peças pretas.
        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
