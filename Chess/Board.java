import java.util.ArrayList;
import java.util.List;


public class Board {
    public Piece[][] positions;

    public Board() {
        this.positions = new Piece[8][8];
    }

    // Method to initialize the game with original pieces
    public void createGame() {
        // White pieces
        positions[0][0] = new Piece("Rook", "White");
        positions[0][1] = new Piece("Knight", "White");
        positions[0][2] = new Piece("Bishop", "White");
        positions[0][3] = new Piece("Queen", "White");
        positions[0][4] = new Piece("King", "White");
        positions[0][5] = new Piece("Bishop", "White");
        positions[0][6] = new Piece("Knight", "White");
        positions[0][7] = new Piece("Rook", "White");
        for (int i = 0; i < 8; i++) {
            positions[1][i] = new Piece("Pawn", "White");
        }

        // Black pieces
        positions[7][0] = new Piece("Rook", "Black");
        positions[7][1] = new Piece("Knight", "Black");
        positions[7][2] = new Piece("Bishop", "Black");
        positions[7][3] = new Piece("Queen", "Black");
        positions[7][4] = new Piece("King", "Black");
        positions[7][5] = new Piece("Bishop", "Black");
        positions[7][6] = new Piece("Knight", "Black");
        positions[7][7] = new Piece("Rook", "Black");
        for (int i = 0; i < 8; i++) {
            positions[6][i] = new Piece("Pawn", "Black");
        }
    }

    // Print the board
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (positions[i][j] != null) {  //////////////// Change to the one the teacher said
                    System.out.print(positions[i][j].getName() + positions[i][j].getColor() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

// Method to calculate possible moves for a piece
    public List<Position> calculatePossibleMoves(int row, int col) { //////////use position
        Piece piece = positions[row][col];
        
        if (piece == null) {
            System.out.println("No piece found at the source position.");
            return new ArrayList<>();
        }

        List<Position> possibleMoves = new ArrayList<>();

        // For a pawn
        if (piece.name.equals("Pawn")) {
            
            int direction = piece.color.equals("White") ? 1 : -1;

            // Forward move
            int newRow = row + direction;

            if (isValidPosition(newRow, col) && positions[newRow][col] == null) {
                possibleMoves.add(new Position(newRow, col));
            }

            // Diagonal capture moves
            int[] captureCols = {col - 1, col + 1};
            for (int captureCol : captureCols) {
                if (isValidPosition(newRow, captureCol) && positions[newRow][captureCol] != null &&
                        !positions[newRow][captureCol].color.equals(piece.color)) {
                    possibleMoves.add(new Position(newRow, captureCol));
                }
            }
        }
        // For a knight
        if (piece.name.equals("Knight")) {
            // Define all possible knight move offsets
            int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

            for (int[] move : knightMoves) {
                int newRow = row + move[0];
                int newCol = col + move[1];

                if (isValidPosition(newRow, newCol) &&
                        (positions[newRow][newCol] == null || !positions[newRow][newCol].color.equals(piece.color))) {
                    possibleMoves.add(new Position(newRow, newCol));
                }
            }
        }


        return possibleMoves;
    }

    // Method to check if a position is valid (within bounds of the board)
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public void movePiece(int startRow, int startCol, int destRow, int destCol) {
        Piece piece = positions[startRow][startCol];
        if (piece == null) {
            System.out.println("No piece found at the starting position.");
            return;
        }

        // Check if the destination position is within bounds
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8) {
            System.out.println("Invalid destination position.");
            return;
        }

        // Check if the destination position is occupied by own piece
        if (positions[destRow][destCol] != null && positions[destRow][destCol].color.equals(piece.color)) {
            System.out.println("Cannot move to a position occupied by your own piece.");
            return;
        }

        // Check for valid move based on piece type (not implemented here)
        // You need to implement rules for each piece type to determine if the move is valid

        // If all checks pass, move the piece
        positions[destRow][destCol] = piece;
        positions[startRow][startCol] = null;
        System.out.println("Piece moved successfully.");
    }
}