package controller;

import model.Board;
import model.Piece;
import model.Position;
import view.ChessView;
import view.Square;
import view.ViewConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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

        if(source instanceof Square){
            Square square = (Square) source;
            this.model.setPosition(square.getPosition());
            System.out.println("Start " + this.model.getStartPosition());
            System.out.println("End " + this.model.getDestPosition());
            repaintPieces();
        }
    }

    public void repaintPieces(){
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
