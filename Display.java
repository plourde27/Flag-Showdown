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
    Frame frame;
    int tx, ty;
    String scene = "menu";
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    Cursor arrowCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    int[][] cs;
    
    
    public Display(Game g, Mouse m, Keyboard k, MoveMouse mmw, Frame frm) {
        super();
        frame = frm;
        game = g;
        mouse = m;
        kb = k;
        mm = mmw;
        
        map = new GameSpace();
        //player = new You(map.SIZE/2 + map.PLAYER_DIST, map.SIZE/2);
        //turret = new Turret(player.c, player.x, player.y);
        
        tx = 0;
        ty = 0;
        
        cs = new int[220][3];
        for (int i = 0 ; i < 220 ; i++) {
            cs[i] = new int[]{(int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)};
        }
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
        
        if (scene == "menu") {
            fill(240, 240, 240, g);
            rect(0, 0, 2400, 2400, g, tx, ty);
            fill(0, 0, 50, g);
            textSize(100, g, "Avenir");
            text("Flag Showdown", 180, 160, g, tx, ty);
            fill(0, 0, 0, g);
            textSize(18, g);
            text("Created and Coded by Xavier Plourde", 390, 190, g, tx, ty);
            textSize(38, g);
            text("Click to Play", 350, 520, g, tx, ty);
            for (int x = 20 ; x <= 1080 ; x += 40) {
                int i = (x - 20) / 40;
                int y = 410;
                int SZ = 3;
                fill(cs[i][0], cs[i][1], cs[i][2], 250, g);
                rect(x, y, 4*SZ, 30*SZ, g, 0, 0);
                fill(cs[i][0], cs[i][1], cs[i][2], 160, g);
                
                vertex(x + SZ*2, y - SZ*15);
                vertex(x + SZ*9, y - SZ*10);
                vertex(x + SZ*2, y - SZ*5);
                vertex(x + SZ*2, y - SZ*15);
                endShape(g, 0, 0);
                
                y = 290;
                
                fill(cs[i+100][0], cs[i+100][1], cs[i+100][2], 250, g);
                rect(x, y, 4*SZ, 30*SZ, g, 0, 0);
                fill(cs[i+100][0], cs[i+100][1], cs[i+100][2], 160, g);
                
                vertex(x + SZ*2, y - SZ*15);
                vertex(x + SZ*9, y - SZ*10);
                vertex(x + SZ*2, y - SZ*5);
                vertex(x + SZ*2, y - SZ*15);
                endShape(g, 0, 0);
            }
            if (mouse.clicked) {
                scene = "play";
            }
        }
        else if (scene == "play") {
            
            
            resetMatrix();
            translate(map.players.get(0).x - 540, map.players.get(0).y - 360);
            
            map.drawMap(g, tx, ty, this, kb);
        }
        
        
        
        
        /*fill(120, 120, 120, g);
        for (int i = -10000 ; i < 10000 ; i += 200) {
            line(-10000, i, 10000, i, g, tx, ty);
            line(i, -10000, i, 10000, g, tx, ty);
        }*/
        //frame.setCursor(arrowCursor);
        mouse.clicked = false;
    }
}