package controller;

import model.Board;
import model.Piece;
import model.Position;
import view.ChessView;
import view.Square;

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
        Square square = (Square) e.getSource();
        // TODO ALL OF THIS IN MODEL
        //TODO check if square is occupied (to set startPosition)
        //TODO if square is empty, do not set startPosition
        //TODO if square is occupied, set startPosition
        //TODO if there is startPosition set, set destPosition
        //TODO after moving, reset startPosition and destPosition

    }

    public void setPieces(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Optional<Piece> optionalPiece = Optional.ofNullable(this.model.getPositions()[i][j]);
                if(!optionalPiece.isEmpty()){
                    Piece piece = optionalPiece.get();
                    this.view.getChessBoard().setPiece(piece.getIconKey(), i, j);
                }
            }
        }
    }
}
