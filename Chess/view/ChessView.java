package view;

import javax.swing.*;

public class ChessView extends JFrame {

    private ChessBoard chessBoard;

    public ChessView(){
        this.chessBoard = new ChessBoard();
        this.initComponents();
        this.setChessFrame();
    }

    public void initComponents(){
        this.add(chessBoard);
    }

    public void setChessFrame(){
        this.setVisible(true);
        this.setSize(ViewConstants.SCREEN_WIDTH, ViewConstants.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
