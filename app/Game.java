package app;

import controller.Controller;
import model.Board;
import model.Utils;
import view.ChessView;

public class Game {
    public void run()
    {
        ChessView view = new ChessView();
        Board model = Utils.createGame(view);
        Controller controller = new Controller(view, model);
        controller.repaintPieces();
    }
}
