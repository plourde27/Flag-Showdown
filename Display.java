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
    int tx, ty;
    
    public Display(Game g, Mouse m, Keyboard k) {
        super();
        game = g;
        mouse = m;
        kb = k;
        player = new Player();
        tx = 0;
        ty = 0;
    }
    
    public void draw(){
        super.repaint();
    }
    
    public void resetMatrix() {
        tx = 0;
        ty = 0;
    }
    
    public void translate(int xx, int yy) {
        tx += xx;
        ty += yy;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        resetMatrix();
        translate(player.x - 540, player.y - 360);
        
        player.display(g, kb, tx, ty);
        
        
        
        fill(120, 120, 120, g);
        for (int i = -10000 ; i < 10000 ; i += 200) {
            line(-10000, i, 10000, i, g, tx, ty);
            line(i, -10000, i, 10000, g, tx, ty);
        }
        
        
    }
}