package app;

import model.Board;
import model.Position;
import model.Utils;

import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Board board = Utils.createGame();
        board.printBoard();
        
        board.movePiece(1,1,3,1);
        Position originP = new Position(0,2);
        int startRow=originP.row,startCol=originP.col;
        // Move a piece example
        List<Position> possible_mov = board.calculatePossibleMoves(startRow,startCol);
        System.out.println(possible_mov.size()+"Initial");
        /*
        board.movePiece(6, 2, 2, 2);
        board.movePiece(6, 0, 2, 0);
        board.printBoard(); */
        for (int i=0;i<2;i++ ){        
        possible_mov = board.calculatePossibleMoves(startRow,startCol);
        
        System.out.println(possible_mov.size()+"Final");
        Position destinationP = possible_mov.get(0);
        int destRow=destinationP.row,destCol=destinationP.col;
        
        board.movePiece(startRow,startCol,destRow,destCol);
        System.out.println("Possible movements"+possible_mov.size());
        board.printBoard();
        
        startRow=destRow;
        startCol=destCol;

        }
    }
}

