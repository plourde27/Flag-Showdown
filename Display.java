import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Display extends drawInterface {
    
    Game game;
    Mouse mouse;
    Keyboard kb;
    Player player;
    
    public Display(Game g, Mouse m, Keyboard k) {
        super();
        game = g;
        mouse = m;
        kb = k;
        player = new Player();
        
    }
    
    public void draw(){
        super.repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        resetMatrix();
        
        
        
        translate(player.x, player.y);
        player.display(g, kb);
        
        
        
        fill(0, 0, 0, g);
        for (int i = -10000 ; i < 10000 ; i += 200) {
            line(-10000, i, 10000, i, g);
            line(i, -10000, i, 10000, g);
        }
        
        
    }
}