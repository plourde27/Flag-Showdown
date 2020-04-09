import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Player extends drawInterface {
    int x, y, ang;
    int fireRate;
    int bulletSpeed;
    final int SPEED = 6;
    final int ANGDIST = 4;
    int timeout = 0;
    int points = 0;
    int[] c;
    
    public Player(int xx, int yy) {
        x = xx;
        y = yy;
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
    }
    
    public void shoot(Display d) {
        d.map.bullets.add(new Bullet(x, y, ang, bulletSpeed, c));
    }
    
    public void display(Graphics g, Keyboard kb, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(kb, d);
    }
}