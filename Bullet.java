import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Bullet extends drawInterface {
    int x, y, ang, speed, ox, oy;
    boolean dead;
    int[] c;
    int SHOOT_DIST = 500;
    
    public Bullet(int xx, int yy, int aang, int sspeed, int[] cc) {
        x = xx;
        y = yy;
        ox = x;
        oy = y;
        ang = aang;
        speed = sspeed;
        c = cc;
        dead = false;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(c[0], c[1], c[2], g);
        ellipse(x, y, 5, 5, g, tx, ty);
    }
    
    public void update(Display d) {
        x += (int) (Math.cos(ang * (Math.PI / 180.0)) * speed);
        y += (int) (Math.sin(ang * (Math.PI / 180.0)) * speed);
        if (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) >= SHOOT_DIST) {
            dead = true;
        }
        for (int i = 0 ; i < d.map.players.size() ; i++) {
            Player p = d.map.players.get(i);
            if (Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)) <= 15) {
                p.die(d);
                dead = true;
                break;
            }
        }
        for (int i = 0 ; i < d.map.turrets.size() ; i++) {
            Turret t = d.map.turrets.get(i);
            if (Math.sqrt((x-t.x)*(x-t.x)+(y-t.y)*(y-t.y)) <= 15) {
                dead = true;
                break;
            }
        }
    }
    
    public void display(Graphics g, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(d);
    }
}