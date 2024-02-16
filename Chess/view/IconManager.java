package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class IconManager {

    private HashMap<String, ImageIcon> iconMap;


    public IconManager(){
        this.iconMap = new HashMap<String, ImageIcon>();
        this.loadIcons();
    }

    public void loadIcons(){

        // Load piece icons

        ImageIcon Black_Bishop = getResizedImageIcon("Chess/images/BLACK_BISHOP.png");
        ImageIcon Black_King   = getResizedImageIcon("Chess/images/BLACK_KING.png");
        ImageIcon Black_Knight = getResizedImageIcon("Chess/images/BLACK_KNIGHT.png");
        ImageIcon Black_Pawn   = getResizedImageIcon("Chess/images/BLACK_PAWN.png");
        ImageIcon Black_Queen  = getResizedImageIcon("Chess/images/BLACK_QUEEN.png");
        ImageIcon Black_Rook   = getResizedImageIcon("Chess/images/BLACK_ROOK.png");
        ImageIcon White_Bishop = getResizedImageIcon("Chess/images/WHITE_BISHOP.png");
        ImageIcon White_King   = getResizedImageIcon("Chess/images/WHITE_KING.png");
        ImageIcon White_Knight = getResizedImageIcon("Chess/images/WHITE_KNIGHT.png");
        ImageIcon White_Pawn   = getResizedImageIcon("Chess/images/WHITE_PAWN.png");
        ImageIcon White_Queen  = getResizedImageIcon("Chess/images/WHITE_QUEEN.png");
        ImageIcon White_Rook   = getResizedImageIcon("Chess/images/WHITE_ROOK.png");

        this.iconMap.put("Black_Bishop", Black_Bishop);
        this.iconMap.put("Black_King", Black_King  );
        this.iconMap.put("Black_Knight", Black_Knight);
        this.iconMap.put("Black_Pawn", Black_Pawn  );
        this.iconMap.put("Black_Queen", Black_Queen );
        this.iconMap.put("Black_Rook", Black_Rook  );
        this.iconMap.put("White_Bishop", White_Bishop);
        this.iconMap.put("White_King", White_King  );
        this.iconMap.put("White_Knight", White_Knight);
        this.iconMap.put("White_Pawn", White_Pawn  );
        this.iconMap.put("White_Queen", White_Queen );
        this.iconMap.put("White_Rook", White_Rook  );

    }

    public ImageIcon getResizedImageIcon(String path){
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage();
        Image newImage = image.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImage);
        return newIcon;
    }


    // Returns an instance of the icon based on the key.
    public ImageIcon getIcon(String key){
        return this.iconMap.get(key);
    }

}
