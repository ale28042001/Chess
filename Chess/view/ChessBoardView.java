package view;

import model.Position;

import javax.swing.*;
import java.awt.*;

public class ChessBoardView extends JPanel {

    private Square[][] squares;
    //private GridLayout grid;
    public ChessBoardView() {
        this.squares = setSquares();

        // Creates a grid where squares (JButtons) will be placed.
        super.setLayout(new GridLayout(8,8));

        // Adds squares (JButtons) to grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.add(squares[i][j]);
            }
        }
    }

    // Sets the squares (JButtons) to the grid.
    public Square[][] setSquares(){
        Square[][] squares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = createSquare(i,j);
            }
        }

        return squares;
    }

    // Creates a new sauqre and sets its color depending on the position of the button in the grid
    public Square createSquare(int row, int col){
        Color color;
        Square square = new Square(row, col);

        if((row + col) % 2 == 0){
            color = ViewConstants.COLOR_BEIGE;
        }

        else{
            color = ViewConstants.COLOR_BROWN;
        }
        square.setBackground(color);
        return square;
    }

    public void setPiece(String iconKey, Position position){
        int row = position.getRow();
        int col = position.getCol();
        Icon icon = ViewConstants.ICONS.getIcon(iconKey);
        this.squares[row][col].setIcon(icon);
    }

    public void removePiece(Position position){
        int row = position.getRow();
        int col = position.getCol();
        this.squares[row][col].setIcon(null);
    }

    public void setSquareColor(Position position){
        this.squares[position.getRow()][position.getCol()].setBackground(Color.GREEN);
    }

    //Getters
    public JButton[][] getSquares() {
        return squares;
    }
}
