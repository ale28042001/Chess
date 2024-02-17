package view;

import model.Position;

import javax.swing.*;

public class Square extends JButton {

    private int row;
    private int col;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position getPosition(){
        return new Position(this.row, this.col);
    }

    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.col;
    }
}
