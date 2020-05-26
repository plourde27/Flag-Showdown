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
            Wall wl = d.map.walls.get(i);
            int x1 = wl.x1;
            int x2 = wl.x2;
            int y1 = wl.y1;
            int y2 = wl.y2;
            
            for (int j = 0 ; j < wl.coords.size() ; j += 2) {
                int[] ans = circleIntersectsLine(x, y, 30, wl.coords.get(j), wl.coords.get(j + 1), wl.coords.get((j + 2)%wl.coords.size()), wl.coords.get((j + 3)%wl.coords.size()));
                if (ans[0] != 0) {
                    x = ans[0];
                    y = ans[1];
                    break;
                }
            }
        }
    }
    
    public int[] circleIntersectsLine(int x, int y, int r, int x1, int y1, int x2, int y2) {
        
        double m1 = (y2 - y1) / ((double)(x2 - x1));
        double b1 = y1 - m1 * x1;
        double m2 = -1.0 / m1;
        double b2 = y - m2 * x;
        double ix = (b2 - b1) / (m1 - m2);
        double iy = ix * m2 + b2;
        if (!(Math.sqrt((ix-x)*(ix-x)+(iy-y)*(iy-y)) <= r / 2 && ix >= Math.min(x1, x2) && ix <= Math.max(x1, x2) && iy >= Math.min(y1, y2) && iy <= Math.max(y1, y2))) {
            return new int[]{0, 0};
        }
        else {
            int ang = (int) (Math.atan(((double)(iy - y)) / (ix - x)) * (180.0 / Math.PI));
            if (x < ix) {
                ang += 180;
            }
            int ds = r / 2;
            return new int[]{(int)ix + (int) (Math.cos(ang * (Math.PI / 180.0)) * (ds)), (int)iy + (int) (Math.sin(ang * (Math.PI / 180.0)) * (ds))};
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