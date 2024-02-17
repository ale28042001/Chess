package controller;

import model.Board;
import model.Piece;
import model.Position;
import view.ChessView;
import view.Square;
import view.ViewConstants;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class Controller implements ActionListener {

    private ChessView view;
    private Board model;

    public Controller(ChessView view, Board model) {
        this.view = view;
        this.model = model;
        this.view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // If a square gets clicked, sends the message to model
        if(source instanceof Square){
            Square a= new Square(0, 0);
            List<Position> pieceMoves = model.calculatorMoves.calculatePieceMoves(a.getPosition(),model.getPositions());
            List<Position> playerMoves = model.calculatorMoves.calculatePlayerMoves(
                model.getPositions()[a.getRow()][a.getCol()].color, model.getPositions());
            System.out.println(pieceMoves);
            
            System.out.println("Start " + this.model.getStartPosition());
            System.out.println("End " + this.model.getDestPosition());
            setPieces();

        }
    }

    public void setPieces(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(i,j);
                Optional<Piece> optionalPiece = Optional.ofNullable(this.model.getPositions()[i][j]);
                if(!optionalPiece.isEmpty()){
                    Piece piece = optionalPiece.get();

                    this.view.getChessBoard().setPiece(piece.getIconKey(), position);
                }
                else{
                    this.view.getChessBoard().removePiece(position);
                }

            }
        }
    }
}
