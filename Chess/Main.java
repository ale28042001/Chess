public class Main {
    
    public static void main(String[] args) {
        Board board = new Board();
        board.createGame();
        board.printBoard();

        // Move a piece example
        board.movePiece(6, 0, 4, 0); // Move a black pawn from (6, 0) to (4, 0)
        board.printBoard();
    }
}

