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
    MoveMouse mm;
    Keyboard kb;
    Player player;
    Turret turret;
    GameSpace map;
    int tx, ty;
    
    
    public Display(Game g, Mouse m, Keyboard k, MoveMouse mmw) {
        super();
        game = g;
        mouse = m;
        kb = k;
        mm = mmw;
        player = new Player();
        turret = new Turret(player.c, player.x, player.y);
        map = new GameSpace();
        map.players.add(player);
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
        
        map.drawMap(g, tx, ty);
        player.display(g, kb, tx, ty, this);
        turret.display(g, this, tx, ty);
        
        //System.out.println("hello!");
        //System.out.println(player.x + " " + player.y);
        
        
        
        fill(120, 120, 120, g);
        for (int i = -10000 ; i < 10000 ; i += 200) {
            line(-10000, i, 10000, i, g, tx, ty);
            line(i, -10000, i, 10000, g, tx, ty);
        }
    }
}