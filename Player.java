import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Player extends drawInterface {
    int SIZE = 5000;
    int x, y;
    double ang;
    int fireRate;
    int bulletSpeed;
    final int SPEED = 18;
    final int ANGDIST = 4;
    int timeout = 0;
    int points = 0;
    int[] c;
    int num;
    int ox, oy;
    int prevx, prevy;
    double[] lock = new double[]{};
    ArrayList<Integer> flagNums;
    int respawnTime = 0;
    int FPS = 50;
    
    public Player(int xx, int yy, int nnum) {
        x = xx;
        y = yy;
        ox = x;
        oy = y;
        num = nnum;
        ang = -90;
        fireRate = 20;
        bulletSpeed = 18;
        c = new int[]{(int)(Math.random()*180 + 30), (int)(Math.random()*180 + 30), (int)(Math.random()*180 + 30)};
    }
    
    public void draw(Graphics g, int tx, int ty) {
        if (respawnTime <= 0) {
            fill(c[0], c[1], c[2], g);
            ellipse(x, y, 30, 30, g, tx, ty);
        }
        else {
            fill(0, 0, 0, g);
            textSize(24, g);
            text(Integer.toString(respawnTime/FPS + 1), x, y, g, tx, ty);
        }
        
    }
    
    public void update(Keyboard kb, Display d) {
        flagNums = new ArrayList<Integer>();
        for (int i = 0 ; i < d.map.flags.size() ; i++) {
            if (d.map.flags.get(i).ownedPlayer == num) {
                flagNums.add(i);
            }
        }
        
        timeout--;
        for (int i = 0 ; i < d.map.bonusPoints.size() ; i++) {
            if (!d.map.bonusPoints.get(i).alive) {
                continue;
            }
            int xx = d.map.bonusPoints.get(i).x;
            int yy = d.map.bonusPoints.get(i).y;
            if (Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy)) <= 25) {
                points += d.map.bonusPoints.get(i).points;
                d.map.bonusPoints.get(i).alive = false;
                lock = new double[]{};
                break;
            }
        }
        prevx = x;
        prevy = y;
        double[] ps = move(kb, d);
        if (ps.length > 0) {
            x += (int) (ps[0] * (SPEED / 3.0));
            y += (int) (ps[1] * (SPEED / 3.0));
        }
        for (int i = 0 ; i < d.map.walls.size() ; i++) {
            Wall wl = d.map.walls.get(i);
            int x1 = wl.x1;
            int x2 = wl.x2;
            int y1 = wl.y1;
            int y2 = wl.y2;
            
            for (int j = 0 ; j < wl.coords.size() ; j += 2) {
                int[] ans = circleIntersectsLine(x, y, 30, wl.coords.get(j), wl.coords.get(j + 1), wl.coords.get((j + 2)%wl.coords.size()), wl.coords.get((j + 3)%wl.coords.size()));
                
                if (ans[0] != -1) {
                    x = ans[0];
                    y = ans[1];
                    break;
                }
            }
        }
    }
    
    public double[] move(Keyboard kb, Display d) {
        return new double[]{};
    }
    
    public void shoot(Display d, int a) {
        d.map.bullets.add(new Bullet(null, 0, x, y, (int)a, bulletSpeed, c, num));
    }
    
    public void display(Graphics g, Keyboard kb, int tx, int ty, Display d) {
        draw(g, tx, ty);
        if (respawnTime <= 0) {
            update(kb, d);
        }
        respawnTime--;
    }
    
    public void die(Display d) {
        x = ox;
        y = oy;
        respawnTime = 10 * FPS;
        for (int i = 0 ; i < d.map.flags.size() ; i++) {
            Flag f = d.map.flags.get(i);
            if (f.heldPlayer == this) {
                f.heldPlayer = null;
            }
        }
    }
}