package model;

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
        // For a rook
        if (piece.name.equals("Rook")) {
            // Check moves in the same row
            for (int newCol = col - 1; newCol >= 0; newCol--) {
                if (positions[row][newCol] == null) {
                    possibleMoves.add(new Position(row, newCol));
                } else {
                    if (!positions[row][newCol].color.equals(piece.color)) {
                        possibleMoves.add(new Position(row, newCol));
                    }
                    break;  // Stop checking further in this direction
                }
            }
            for (int newCol = col + 1; newCol < 8; newCol++) {
                if (positions[row][newCol] == null) {
                    possibleMoves.add(new Position(row, newCol));
                } else {
                    if (!positions[row][newCol].color.equals(piece.color)) {
                        possibleMoves.add(new Position(row, newCol));
                    }
                    break;  // Stop checking further in this direction
                }
            }

            // Check moves in the same column
            for (int newRow = row - 1; newRow >= 0; newRow--) {
                if (positions[newRow][col] == null) {
                    possibleMoves.add(new Position(newRow, col));
                } else {
                    if (!positions[newRow][col].color.equals(piece.color)) {
                        possibleMoves.add(new Position(newRow, col));
                    }
                    break;  // Stop checking further in this direction
                }
            }
            for (int newRow = row + 1; newRow < 8; newRow++) {
                if (positions[newRow][col] == null) {
                    possibleMoves.add(new Position(newRow, col));
                } else {
                    if (!positions[newRow][col].color.equals(piece.color)) {
                        possibleMoves.add(new Position(newRow, col));
                    }
                    break;  // Stop checking further in this direction
                }
            }
        }
        // For a bishop
        if (piece.name.equals("Bishop")) {
            // Check moves in diagonal directions
            // Upper left diagonal
            for (int newRow = row - 1, newCol = col - 1; newRow >= 0 && newCol >= 0; newRow--, newCol--) {
                if (positions[newRow][newCol] == null) {
                    possibleMoves.add(new Position(newRow, newCol));
                } else {
                    if (!positions[newRow][newCol].color.equals(piece.color)) {
                        possibleMoves.add(new Position(newRow, newCol));
                    }
                    break;  // Stop checking further in this direction
                }
            }

            // Upper right diagonal
            for (int newRow = row - 1, newCol = col + 1; newRow >= 0 && newCol < 8; newRow--, newCol++) {
                if (positions[newRow][newCol] == null) {
                    possibleMoves.add(new Position(newRow, newCol));
                } else {
                    if (!positions[newRow][newCol].color.equals(piece.color)) {
                        possibleMoves.add(new Position(newRow, newCol));
                    }
                    break;  // Stop checking further in this direction
                }
            }

            // Lower left diagonal
            for (int newRow = row + 1, newCol = col - 1; newRow < 8 && newCol >= 0; newRow++, newCol--) {
                if (positions[newRow][newCol] == null) {
                    possibleMoves.add(new Position(newRow, newCol));
                } else {
                    if (!positions[newRow][newCol].color.equals(piece.color)) {
                        possibleMoves.add(new Position(newRow, newCol));
                    }
                    break;  // Stop checking further in this direction
                }
            }

            // Lower right diagonal
            for (int newRow = row + 1, newCol = col + 1; newRow < 8 && newCol < 8; newRow++, newCol++) {
                if (positions[newRow][newCol] == null) {
                    possibleMoves.add(new Position(newRow, newCol));
                } else {
                    if (!positions[newRow][newCol].color.equals(piece.color)) {
                        possibleMoves.add(new Position(newRow, newCol));
                    }
                    break;  // Stop checking further in this direction
                }
            }
        }
        // For a queen
        if (piece.name.equals("Queen")) {
            // Check horizontal and vertical moves (rook-like moves)
            possibleMoves.addAll(calculateRookMoves(row, col));

            // Check diagonal moves (bishop-like moves)
            possibleMoves.addAll(calculateBishopMoves(row, col));
        }
        // For a king
        if (piece.name.equals("King")) {
            // Define all possible king move offsets
            int[][] kingMoves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

            for (int[] move : kingMoves) {
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
        System.out.println("model.Piece moved successfully.");
    }
}