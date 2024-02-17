package app;

import controller.Controller;
import model.Board;
import model.Position;
import model.Utils;
import view.ChessView;

public class Main {
    
    public static void main(String[] args) {
        
        ChessView view = new ChessView();
        Board model = Utils.createGame(view);
        Controller controller = new Controller(view, model);
        controller.setPieces();        
        ChessView view = new ChessView();
        Board model = Utils.createGame(view);
        Controller controller = new Controller(view, model);
        controller.setPieces();
        }
    }