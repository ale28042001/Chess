package app;

import controller.Controller;
import model.Board;
import model.Utils;
import view.ChessView;

public class Game {
    private ChessView view=null;
    private Board model=null;
    private Controller controller=null;

    public void run()
    {
        this.view = new ChessView();
        this.model = Utils.createGame(view);
        this.controller = new Controller(view, model);
        this.controller.repaintPieces();
    }

    public ChessView getView() {
        return view;
    }

    public void setView(ChessView view) {
        this.view = view;
    }

    public Board getModel() {
        return model;
    }

    public void setModel(Board model) {
        this.model = model;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    
}
