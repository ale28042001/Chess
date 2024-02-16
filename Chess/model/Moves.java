package model;


import java.util.*;
import java.util.function.BiConsumer;

public class Moves {
    private final Map<String, BiConsumer<Position, Piece[][]>> moveCalculations;

    
    public Moves() {
        moveCalculations = new HashMap<>();
        moveCalculations.put("Pawn", this::calculatePawnMoves);
        moveCalculations.put("Knight", this::calculateKnightMoves);
        moveCalculations.put("Rook", this::calculateRookMoves);
        moveCalculations.put("Bishop", this::calculateBishopMoves);
        // Add other pieces as needed
    }

    public List<Position> calculatePieceMoves(Position position, Piece[][] positions) {
        Piece piece = positions[position.getRow()][position.getCol()];

        if (piece == null) {
            System.out.println("No piece found at the source position.");
            return new ArrayList<>();
        }

        List<Position> possibleMoves = new ArrayList<>();
        moveCalculations.getOrDefault(piece.name, (p, pos) -> {}).accept(position, positions);
        return possibleMoves;
    
    }


    public boolean isValidPosition(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    // Method to calculate possible moves for a Pawn
    public List<Position> calculatePawnMoves(Position position, Piece[][] positions) {
        int row = position.getRow();
        int col = position.getCol();
        List<Position> possibleMoves = new ArrayList<>();
        Piece piece = positions[row][col];

        int direction = piece.color.equals("White") ? 1 : -1;

        // Forward move
        int newRow = row + direction;
        Position newPosition= new Position(newRow, col);

        if (isValidPosition(newPosition) && positions[newRow][col] == null) {
            possibleMoves.add(newPosition);
        }

        // Diagonal capture moves
        int[] captureCols = {col - 1, col + 1};
        for (int captureCol : captureCols) {
            newPosition = new Position(newRow, captureCol);
            if (isValidPosition(newPosition) && positions[newRow][captureCol] != null 
                &&!positions[newRow][captureCol].color.equals(piece.color)) {
                possibleMoves.add(newPosition);
            }
        }
        return possibleMoves;
    }

    // Method to calculate possible moves for a Knight
    public List<Position> calculateKnightMoves(Position position, Piece[][] positions) {
        int row = position.getRow();
        int col = position.getCol();
        List<Position> possibleMoves = new ArrayList<>();
        // Implement the logic for calculating possible moves for the Knight
        int[][] knightMoves = Constants.KNIGHT_MOVES;

        for (int[] move : knightMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            Position newPosition = new Position(newRow, newCol);
            if (isValidPosition(newPosition) &&
                    (positions[newRow][newCol] == null || !positions[newRow][newCol].color.equals(piece.color))) {
                possibleMoves.add(newPosition);
            }
        }
        return possibleMoves;
    }

    // Method to calculate possible moves for a Rook
    public List<Position> calculateRookMoves(Position position, Piece[][] positions) {
        int row = position.getRow();
        int col = position.getCol();
        Piece piece = positions[row][col];
        List<Position> possibleMoves = new ArrayList<>();

        // Implement the logic for calculating possible moves for the Rook

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


        return possibleMoves;
    }

    // Method to calculate possible moves for a Bishop
    public List<Position> calculateBishopMoves(Position position, Piece[][] positions) {
        int row = position.getRow();
        int col = position.getCol();
        Piece piece = positions[row][col];
        List<Position> possibleMoves = new ArrayList<>();
        // Implement the logic for calculating possible moves for the Bishop


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

        return possibleMoves;
    }

    // Method to calculate possible moves for a Queen
    public List<Position> calculateQueenMoves(Position position, Piece[][] positions) {
        List<Position> possibleMoves = new ArrayList<>();
        // Implement the logic for calculating possible moves for the Queen

        // Check horizontal and vertical moves (rook-like moves)
        possibleMoves.addAll(calculateRookMoves(position,positions));

        // Check diagonal moves (bishop-like moves)
        possibleMoves.addAll(calculateBishopMoves(position,positions));

        return possibleMoves;
    }

    // Method to calculate possible moves for a King
    public List<Position> calculateKingMoves(Position position, Piece[][] positions) {
        int row = position.getRow();
        int col = position.getCol();
        String color = positions[row][col].getColor();
        List<Position> possibleMoves = new ArrayList<>();

        // Define all possible king move offsets

        int[][] kingMoves = Constants.KING_MOVES;

        for (int[] move : kingMoves) {
            int newRow = position.getRow() + move[0];
            int newCol = position.getCol() + move[1];

            if (isValidPosition(new Position(newRow, newCol)) &&
                    (positions[newRow][newCol] == null || !positions[newRow][newCol].color.equals(color))) {
                possibleMoves.add(new Position(newRow, newCol));
            }
        }

        return possibleMoves;
    }

    public List<Position> calculatePlayerMoves(String color, Piece[][] positions)
    {
        List<Position> possibleMoves = new ArrayList<>();
        for (int i=0;i<8;i++) 
        {
            for (int j=0;j<8;j++) 
            {
                if(positions[i][j].color.equals(color))
                {
                    possibleMoves.add(new Position(i, j));
                }
            }
        }
        return possibleMoves;
    }
    
    public boolean jaque(Position kingPosition, List<Position> enemiAttacList)
    {
        return enemiAttacList.contains(kingPosition);
    }

}
