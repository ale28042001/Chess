package app;

import model.Board;
import model.Position;
import model.Utils;

import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Board board = Utils.createGame();
        board.printBoard();
        board.movePiece(1,0,3,0);
        board.printBoard();

        }
    }


