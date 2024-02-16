package view;

import javax.swing.*;
import java.util.HashMap;

public class IconManager {

    private HashMap<String, ImageIcon> iconMap;


    public IconManager(){
        this.iconMap = new HashMap<String, ImageIcon>();
        this.loadIcons();
    }

    public void loadIcons(){

        // Load piece icons

        ImageIcon Black_Bishop = new ImageIcon("Chess/images/BLACK_BISHOP.png");
        ImageIcon Black_King   = new ImageIcon("Chess/images/BLACK_KING.png");
        ImageIcon Black_Knight = new ImageIcon("Chess/images/BLACK_KNIGHT.png");
        ImageIcon Black_Pawn   = new ImageIcon("Chess/images/BLACK_PAWN.png");
        ImageIcon Black_Queen  = new ImageIcon("Chess/images/BLACK_QUEEN.png");
        ImageIcon Black_Rook   = new ImageIcon("Chess/images/BLACK_ROOK.png");
        ImageIcon White_Bishop = new ImageIcon("Chess/images/WHITE_BISHOP.png");
        ImageIcon White_King   = new ImageIcon("Chess/images/WHITE_KING.png");
        ImageIcon White_Knight = new ImageIcon("Chess/images/WHITE_KNIGHT.png");
        ImageIcon White_Pawn   = new ImageIcon("Chess/images/WHITE_PAWN.png");
        ImageIcon White_Queen  = new ImageIcon("Chess/images/WHITE_QUEEN.png");
        ImageIcon White_Rook   = new ImageIcon("Chess/images/WHITE_ROOK.png");

        this.iconMap.put("Black_Bishop", Black_Bishop);
        this.iconMap.put("Black_King  ", Black_King  );
        this.iconMap.put("Black_Knight", Black_Knight);
        this.iconMap.put("Black_Pawn  ", Black_Pawn  );
        this.iconMap.put("Black_Queen ", Black_Queen );
        this.iconMap.put("Black_Rook  ", Black_Rook  );
        this.iconMap.put("White_Bishop", White_Bishop);
        this.iconMap.put("White_King  ", White_King  );
        this.iconMap.put("White_Knight", White_Knight);
        this.iconMap.put("White_Pawn  ", White_Pawn  );
        this.iconMap.put("White_Queen ", White_Queen );
        this.iconMap.put("White_Rook  ", White_Rook  );

    }

    public ImageIcon getIcon(String key){
        return this.iconMap.get(key);
    }

}
