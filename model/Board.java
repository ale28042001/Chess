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
    private Moves calculatorMoves = new Moves();

    // Kings' position
    private Position blackKing;
    private Position whiteKing;

    private boolean checkOnBlack;
    private boolean checkOnWhite;


    public Board(ChessView chessBoard) {
        this.chessBoard = chessBoard;
        this.positions = new Piece[8][8];
        this.startPosition = new Position(-1,-1);
        this.destPosition = new Position(-1,-1);
        this.playerInTurn = "White";
        this.checkOnWhite = false;
        this.checkOnBlack = false;
    }

    // Method to calculate possible moves for a piece
    public List<Position> calculatePossibleMoves(Position position) { ////////// TODO use position
        int row = position.getRow();
        int col = position.getCol();
        Piece piece = positions[row][col];
        
        if (piece == null) {
            System.out.println("No piece found at the source position.");
        }

        List<Position> possibleMoves = calculatorMoves.calculatePieceMoves(position,positions);

        return possibleMoves;
    }


    public boolean movePiece() {
        int startRow = this.startPosition.getRow();
        int startCol = this.startPosition.getCol();
        int destRow = this.destPosition.getRow();
        int destCol = this.destPosition.getCol();

        String enemyColor = this.playerInTurn.equals("White") ? "Black" : "White";
        Position myKing = this.playerInTurn.equals("White") ? this.whiteKing : this.blackKing;
        Position enemyKing = this.playerInTurn.equals("White") ? this.blackKing : this.whiteKing;

        Piece piece = positions[startRow][startCol];
        if (piece == null) {
            System.out.println("No piece found at the starting position.");
            return false;
        }

        // Check if the destination position is within bounds
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8) {

            return false;
        }

        // Check if the destination position is occupied by own piece
        if (positions[destRow][destCol] != null && positions[destRow][destCol].color.equals(piece.color)) {

            return false;
        }

        // Check for valid move based on piece type (not implemented here)
        // You need to implement rules for each piece type to determine if the move is valid

        // If all checks pass, move the piece

        positions[destRow][destCol] = piece;

        List<Position> newPositions = calculatorMoves.calculatePieceMoves(this.destPosition,this.positions);
        for (Position p: newPositions){
            System.out.println("Verificando " + p);
            if(p.equals(enemyKing)){
                System.out.println("Hay Jaque");
            }
        }

        if (piece.getName().equals("King")){
            if (piece.getColor().equals("Black")){
                this.setBlackKing(new Position(destRow, destCol));
            }
            else if (piece.getColor().equals("White")){
                this.setWhiteKing(new Position(destRow, destCol));
            }
        }

        if(calculatorMoves.jaque(myKing, calculatorMoves.calculatePlayerMoves(enemyColor, positions))){

            positions[destRow][destCol] = null;
            positions[startRow][startCol] = piece;
            this.resetPositions();
            return false;

        }


        positions[startRow][startCol] = null;

        return true;
    }

    // Getters and setters
    public Piece[][] getPositions() {
        return positions;
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
            
            this.destPosition = position;
            if(calculatorMoves.containsPosition(position))
            {
                if(movePiece()){
                    this.chessBoard.getChessBoard().resetSquareColors();
                    resetPositions();
                    changePlayerInTurn();
                }

            }
            else            
            resetPositions();
            this.chessBoard.getChessBoard().resetSquareColors();

                
        }

        else if(!isStartPositionSet() && !isEmpty(position) && playerInTurn.equals(getPositionColor(position))){
            this.chessBoard.getChessBoard().resetSquareColors();
            startPosition = position;
            List<Position> possibleMoves = calculatorMoves.calculatePieceMoves(position, this.positions);
            if(!possibleMoves.isEmpty()){
                for (Position p : possibleMoves){
                    this.chessBoard.getChessBoard().setSquareColor(p);
                }
            }
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

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getDestPosition() {
        return destPosition;
    }

    public void setDestPosition(Position destPosition)
    {
        this.destPosition = destPosition;
        movePiece();
    }
    public void setStartPosition(Position startPosition)
    {
        this.startPosition = startPosition;
    }

    public Position getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(Position blackKing) {
        this.blackKing = blackKing;
    }

    public Position getWhiteKing() {
        return whiteKing;
    }

    public void setWhiteKing(Position whiteKing) {
        this.whiteKing = whiteKing;
    }
}