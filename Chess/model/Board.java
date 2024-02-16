package model;

import view.ChessView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Board {

    private Piece[][] positions;
    private ChessView chessBoard;
    Position startPosition;
    Position destPosition;
    String playerInTurn;


    public Board(ChessView chessBoard) {
        this.positions = new Piece[8][8];
        this.startPosition = new Position(-1,-1);
        this.destPosition = new Position(-1,-1);
        this.playerInTurn = "White";
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
    public List<Position> calculatePossibleMoves(int row, int col) { ////////// TODO use position
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
            int[][] knightMoves = Constants.KNIGHT_MOVES;

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
            //possibleMoves.addAll(calculateRookMoves(row, col));

            // Check diagonal moves (bishop-like moves)
            //possibleMoves.addAll(calculateBishopMoves(row, col));
        }
        // For a king
        if (piece.name.equals("King")) {
            // Define all possible king move offsets
            int[][] kingMoves = Constants.KING_MOVES;

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

    public void movePiece() {
        int startRow = this.startPosition.getRow();
        int startCol = this.startPosition.getCol();
        int destRow = this.destPosition.getRow();
        int destCol = this.destPosition.getCol();

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

    public void placePiece( Piece piece, int row, int col){
        this.positions[row][col] = piece;
    }

    // Basic Functions

    public void changePlayerInTurn(){
        if(this.playerInTurn.equals("White")){
            this.playerInTurn = "Black";
        }
        else{
            this.playerInTurn = "White";
        }
    }

    public void setPosition(Position position){

        if(isStartPositionSet() && !isDestPositionSet()){
            destPosition = position;
            movePiece();
            resetPositions();
            changePlayerInTurn();
        }

        else if(!isStartPositionSet() && !isEmpty(position) && playerInTurn.equals(getPositionColor(position))){
            startPosition = position;

        }
    }

    public String getPositionColor(Position position){
        int row = position.getRow();
        int col = position.getCol();
        return positions[row][col].color;
    }

    public boolean isEmpty(Position position){
        int row = position.getRow();
        int col = position.getCol();
        Optional<Piece> optionalPiece = Optional.ofNullable(this.positions[row][col]);
        return optionalPiece.isEmpty();

    }

    public boolean isStartPositionSet(){
        Position unsetPosition = new Position(-1,-1);
        return !this.startPosition.equals(unsetPosition);
    }

    public boolean isDestPositionSet(){
        Position unsetPosition = new Position(-1,-1);
        return !this.destPosition.equals(unsetPosition);
    }

    public Position unsetPosition(){
        return new Position(-1,-1);
    }

    public void resetPositions(){
        Position position = new Position(-1,-1);
        startPosition = position;
        destPosition = position;
    }

    // Getters and setters
    public Piece[][] getPositions() {
        return positions;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getDestPosition() {
        return destPosition;
    }
}