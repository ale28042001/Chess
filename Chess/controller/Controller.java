package controller;

import model.Board;
import view.ChessView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    }
}
