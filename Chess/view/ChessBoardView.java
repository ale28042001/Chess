package view;

import javax.swing.*;
import java.awt.*;

public class ChessBoardView extends JPanel {

    private JButton[][] squares;
    private GridLayout grid;
    public ChessBoardView() {
        this.squares = setButtons();
        super.setLayout(new GridLayout(8,8));

        // adds aquares (JButtons) to grid
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

    // Creates a new button and sets its color depending on the position of the button in the grid
    public JButton createButton(int row, int col){
        Color color;
        JButton btn = new JButton();

        if((row + col) % 2 == 0){
            color = ViewConstants.COLOR_BEIGE;
        }

        else{
            color = ViewConstants.COLOR_BROWN;
        }
        btn.setBackground(color);
        return btn;
    }

    public void setPiece(String iconKey, int row, int column){
//        Icon icon = ViewConstants.ICONS.getIcon(iconKey);
//        this.squares[row][column].setIcon(icon);
        this.squares[0][]
    }
}
