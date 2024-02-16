package app;

import model.Board;
import model.Utils;
import view.ChessView;

public class Main {
    
    public static void main(String[] args) {

        ChessView view = new ChessView();
        Board board = Utils.createGame(view);


        }
    }


