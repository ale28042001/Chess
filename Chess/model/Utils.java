package model;

import view.ChessView;

public class Utils {


    // Creates a new chess board and initializes the pieces to the standard game setup.
    public static Board createGame(ChessView chessBoard){
        Board board = new Board(chessBoard);

        // White pieces
        board.placePiece(new Piece("Rook", "White"), 0,0);
        board.placePiece(new Piece("Knight", "White"), 0, 1);
        board.placePiece(new Piece("Bishop", "White"), 0, 2);
        board.placePiece(new Piece("Queen", "White"), 0, 3);
        board.placePiece(new Piece("King", "White"), 0, 4);
        board.placePiece(new Piece("Bishop", "White"), 0, 5);
        board.placePiece(new Piece("Knight", "White"), 0, 6);
        board.placePiece(new Piece("Rook", "White"), 0, 7);
        for (int i = 0; i < 8; i++) {
            board.placePiece(new Piece("Pawn", "White"), 1, i);
        }

        // Black pieces
        board.placePiece(new Piece("Rook", "Black"), 7, 0);
        board.placePiece(new Piece("Knight", "Black"), 7, 1);
        board.placePiece(new Piece("Bishop", "Black"), 7, 2);
        board.placePiece(new Piece("Queen", "Black"), 7, 3);
        board.placePiece(new Piece("King", "Black"), 7, 4);
        board.placePiece(new Piece("Bishop", "Black"), 7, 5);
        board.placePiece(new Piece("Knight", "Black"), 7, 6);
        board.placePiece(new Piece("Rook", "Black"), 7, 7);
        for (int i = 0; i < 8; i++) {
            board.placePiece(new Piece("Pawn", "Black"), 6, i);
        }

        return board;
    }
}
