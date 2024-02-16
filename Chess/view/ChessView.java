package view;

import controller.Controller;

import javax.swing.*;

public class ChessView extends JFrame {

    private ChessBoardView chessBoardView;
    private Controller controller;

    public ChessView(){
        this.chessBoardView = new ChessBoardView();
        this.initComponents();
        this.setChessFrame();
    }

    public void initComponents(){
        this.add(chessBoardView);
    }

    public void setChessFrame(){
        this.setVisible(true);
        this.setSize(ViewConstants.SCREEN_WIDTH, ViewConstants.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    //Getters
    public ChessBoardView getChessBoard() {
        return chessBoardView;
    }
}
