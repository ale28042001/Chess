package view;

import model.Constants;

import javax.swing.*;

public class ChessFrame extends JFrame {

    private ChessBoard chessBoard;

    public ChessFrame(){
        this.chessBoard = new ChessBoard();
        this.initComponents();
        this.setChessFrame();
    }

    public void initComponents(){
        this.add(chessBoard);
    }

    public void setChessFrame(){
        this.setVisible(true);
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

}
