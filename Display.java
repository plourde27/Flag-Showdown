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
    
    public Display(Game g, Mouse m, Keyboard k) {
        super();
        game = g;
        mouse = m;
        kb = k;
        
    }
    
    public void draw(){
        super.repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        rect(300, 300, 100, 100, g);
    }
}