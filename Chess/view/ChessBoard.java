package view;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {

    private JButton[][] squares;
    private GridLayout grid;
    public ChessBoard() {
        this.squares = setButtons();
        super.setLayout(new GridLayout(8,8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.add(squares[i][j]);
            }
        }
    }

    public JButton[][] setButtons(){
        JButton[][] buttons = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = createButton(i,j);
            }
        }

        return buttons;
    }

    public JButton createButton(int row, int col){
        Color color;
        JButton btn = new JButton();

        if((row + col) % 2 == 0){
            color = Color.WHITE;
        }

        else{
            color = Color.BLACK;
        }

        btn.setBackground(color);
        return btn;
    }
}
