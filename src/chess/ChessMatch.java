package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch
{
	private int turn;
	private Color currentPlayer;
	private Board board;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	private boolean check;
	private boolean checkMate;
	
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
	
	//Método que retorna a propiedade check
	public boolean getCheck()
	{
		return check;
	}
	
	//Método que retorna a propiedade checkMate
	public boolean getCheckMate()
	{
		return checkMate;
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
		
		if (testCheck(currentPlayer))
		{
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put  yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if (testCheckMate(opponent(currentPlayer)))
		{
			checkMate = true;
		}
		else
		{
			nextTurn();
		}
		
		return (ChessPiece)capturedPiece;
	}
	
	//Método que configura o movimento de uma peça.
	private Piece makeMove(Position source, Position target)
	{
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if (capturedPiece != null)
		{
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	//Método para desfazer um movimento.
	private void undoMove(Position source, Position target, Piece capturedPiece)
	{
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if (capturedPiece != null)
		{
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
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
	
	//Método que retorna a cor do oponente.
	private Color opponent(Color color)
	{
		return (color == color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//Método para retornar o rei e sua cor no tabuleiro.
	private ChessPiece king(Color color)
	{
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list)
		{
			if (p instanceof King)	
			{
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There's no " + color + "king on the board");
	}
	
	//Método para testar se o rei está em CHECK.
	private boolean testCheck(Color color)
	{
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces)
		{
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()])
			{
				return true;
			}
		}
		return false;
		
	}
	
	//Método para testar se o rei está em CHECKMATE.
	private boolean testCheckMate(Color color)
	{
		//Se não está em check, também não está em CheckMate.
		if (!testCheck(color))
		{
			return false;
		}
		
		//Testa se existe algum movimento que tire o rei do Check.
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list)
		{
			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++)
			{
				for (int j = 0; j < board.getColumns(); j++)
				{
					if (mat[i][j])
					{
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck)
						{
							return false;
						}
					}
				}
			}
		}
		
		//Se está em check e não há movimento possível, então é CheckMate.
		return true;
	}
	
	//Método para colocar uma nova peça no tabuleiro.
	private void placeNewPiece(char column, int row, ChessPiece piece)
	{
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	//Método que cria uma configuração inicial para as peças no tabuleiro.
	private void initialSetup()
	{
		//Colocando peças brancas.
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        //Colocando peças pretas.
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
	}
}
