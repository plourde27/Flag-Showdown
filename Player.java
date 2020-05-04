import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Player extends drawInterface {
    int x, y;
    double ang;
    int fireRate;
    int bulletSpeed;
    final int SPEED = 6;
    final int ANGDIST = 4;
    int timeout = 0;
    int points = 0;
    int[] c;
    int num;
    int ox, oy;
    int prevx, prevy;
    
    public Player(int xx, int yy, int nnum) {
        x = xx;
        y = yy;
        ox = x;
        oy = y;
        num = nnum;
        ang = -90;
        fireRate = 10;
        bulletSpeed = 20;
        c = new int[]{(int)(Math.random()*180 + 30), (int)(Math.random()*180 + 30), (int)(Math.random()*180 + 30)};
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(c[0], c[1], c[2], g);
        ellipse(x, y, 30, 30, g, tx, ty);
    }
    
    public void update(Keyboard kb, Display d) {
        timeout--;
        for (int i = 0 ; i < d.map.bonusPoints.size() ; i++) {
            int xx = d.map.bonusPoints.get(i).x;
            int yy = d.map.bonusPoints.get(i).y;
            if (Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy)) <= 25) {
                points += d.map.bonusPoints.remove(i).points;
                break;
            }
        }
        prevx = x;
        prevy = y;
        move(kb, d);
        for (int i = 0 ; i < d.map.walls.size() ; i++) {
            int x1 = d.map.walls.get(i).x1;
            int x2 = d.map.walls.get(i).x2;
            int y1 = d.map.walls.get(i).y1;
            int y2 = d.map.walls.get(i).y2;
            double error = Math.abs(Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)) - (Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1)) + Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2))));
            
            if (error <= 10) {
                x = prevx;
                y = prevy;
            }
        }
    }
    
    public void move(Keyboard kb, Display d) {
        
    }
    
    public void shoot(Display d) {
        d.map.bullets.add(new Bullet(x, y, (int)ang, bulletSpeed, c));
    }
    
    public void display(Graphics g, Keyboard kb, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(kb, d);
    }
    
    public void die(Display d) {
        x = ox;
        y = oy;
        for (int i = 0 ; i < d.map.flags.size() ; i++) {
            Flag f = d.map.flags.get(i);
            if (f.heldPlayer == this) {
                f.heldPlayer = null;
            }
        }
    }
}