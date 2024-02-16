package model;

import view.ChessView;

public class Utils {


    // Creates a new chess board and initializes the pieces to the standard game setup.
    public static Board createGame(ChessView chessBoard){
        Board board = new Board(chessBoard);

        // WHITE pieces
        board.placePiece(new Piece("ROOK", "WHITE"), 0,0);
        board.placePiece(new Piece("KNIGHT", "WHITE"), 0, 1);
        board.placePiece(new Piece("BISHOP", "WHITE"), 0, 2);
        board.placePiece(new Piece("QUEEN", "WHITE"), 0, 3);
        board.placePiece(new Piece("KING", "WHITE"), 0, 4);
        board.placePiece(new Piece("BISHOP", "WHITE"), 0, 5);
        board.placePiece(new Piece("KNIGHT", "WHITE"), 0, 6);
        board.placePiece(new Piece("ROOK", "WHITE"), 0, 7);
        for (int i = 0; i < 8; i++) {
            board.placePiece(new Piece("PAWN", "WHITE"), 1, i);
        }

        // BLACK pieces
        board.placePiece(new Piece("ROOK", "BLACK"), 7, 0);
        board.placePiece(new Piece("KNIGHT", "BLACK"), 7, 1);
        board.placePiece(new Piece("BISHOP", "BLACK"), 7, 2);
        board.placePiece(new Piece("QUEEN", "BLACK"), 7, 3);
        board.placePiece(new Piece("KING", "BLACK"), 7, 4);
        board.placePiece(new Piece("BISHOP", "BLACK"), 7, 5);
        board.placePiece(new Piece("KNIGHT", "BLACK"), 7, 6);
        board.placePiece(new Piece("ROOK", "BLACK"), 7, 7);
        for (int i = 0; i < 8; i++) {
            board.placePiece(new Piece("PAWN", "BLACK"), 6, i);
        }

        return board;
    }
}
