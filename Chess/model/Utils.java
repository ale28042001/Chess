package model;

public class Utils {


    // Creates a new chess board and initializes the pieces to the standard game setup.
    public static Board createGame(){
        Board board = new Board();

        // White pieces
        board.positions[0][0] = new Piece("Rook", "White");
        board.positions[0][1] = new Piece("Knight", "White");
        board.positions[0][2] = new Piece("Bishop", "White");
        board.positions[0][3] = new Piece("Queen", "White");
        board.positions[0][4] = new Piece("King", "White");
        board.positions[0][5] = new Piece("Bishop", "White");
        board.positions[0][6] = new Piece("Knight", "White");
        board.positions[0][7] = new Piece("Rook", "White");
        for (int i = 0; i < 8; i++) {
            board.positions[1][i] = new Piece("Pawn", "White");
        }

        // Black pieces
        board.positions[7][0] = new Piece("Rook", "Black");
        board.positions[7][1] = new Piece("Knight", "Black");
        board.positions[7][2] = new Piece("Bishop", "Black");
        board.positions[7][3] = new Piece("Queen", "Black");
        board.positions[7][4] = new Piece("King", "Black");
        board.positions[7][5] = new Piece("Bishop", "Black");
        board.positions[7][6] = new Piece("Knight", "Black");
        board.positions[7][7] = new Piece("Rook", "Black");
        for (int i = 0; i < 8; i++) {
            board.positions[6][i] = new Piece("Pawn", "Black");
        }

        return board;
    }
}
