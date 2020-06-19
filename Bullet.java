import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Bullet extends drawInterface {
    double x, y, ox, oy, px, py;
    int ang, speed;
    boolean dead;
    int[] c;
    int num;
    int SHOOT_DIST = 500;
    Player targetPlayer;
    int mspeed;
    
    public Bullet(Player p, int ms, double xx, double yy, int aang, int sspeed, int[] cc, int nm) {
        mspeed = ms;
        x = xx;
        y = yy;
        if (p != null) {
            targetPlayer = p;
        }
        ox = x;
        oy = y;
        ang = aang;
        speed = sspeed;
        c = cc;
        dead = false;
        num = nm;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(c[0], c[1], c[2], g);
        ellipse((int)x, (int)y, 5, 5, g, tx, ty);
    }
    
    public void update(Display d) {
        px = x;
        py = y;
        if (targetPlayer != null) {
            int targetAng = (int) (Math.atan((y-targetPlayer.y)/(x-targetPlayer.x))*(180.0/Math.PI));
            if (targetPlayer.x < x) {
                targetAng = (targetAng + 180) % 360;
            }
            if (Math.abs(ang - targetAng) <= mspeed || Math.abs(ang - targetAng) >= 360 - mspeed) {
                ang = targetAng;
            }
            else if ((ang < targetAng && targetAng - ang > 180) || (targetAng < ang && ang - targetAng <= 180)) {
                ang = (ang + 360 - mspeed) % 360;
            }
            else {
                ang = (ang + mspeed) % 360;
            }
            x += (Math.cos(ang * (Math.PI / 180.0)) * speed);
            y += (Math.sin(ang * (Math.PI / 180.0)) * speed);
        }
        
        if (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) >= SHOOT_DIST) {
            dead = true;
        }
        for (int i = 0 ; i < d.map.players.size() ; i++) {
            if (i == num) {
                continue;
            }
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
        for (int i = 0 ; i < d.map.walls.size() ; i++) {
            Wall wl = d.map.walls.get(i);
            for (int j = 0 ; j < wl.coords.size() ; j += 2) {
                if (circleIntersectsLine((int)x, (int)y, 5, wl.coords.get(j), wl.coords.get(j + 1), wl.coords.get((j + 2)%wl.coords.size()), wl.coords.get((j + 3)%wl.coords.size()))[0] != -1) {
                    dead = true;
                    break;
                }
            }
            if (linesIntersect((int)px, (int)py, (int)x, (int)y, wl.x1, wl.y1, wl.x2, wl.y2)) {
                dead = true;
                break;
            }
        }
    }
    
    public boolean linesIntersect(int sx1, int sy1, int ex1, int ey1, int sx2, int sy2, int ex2, int ey2) {
        double m1 = ((ey1 - sy1) / ((double)ex1 - sx1));
        double m2 = ((ey2 - sy2) / ((double)((double) (ex2 - sx2) + 0.00001)));
        double b1 = sy1 - m1 * sx1;
        double b2 = sy2 - m2 * sx2;
        double ex = ((b2 - b1) / ((double) (m1 - m2)));
        double ey = m1 * ex + b1;
        return (ex >= Math.min(sx1,ex1) && ex <= Math.max(sx1,ex1) && ex >= Math.min(sx2,ex2) && ex <= Math.max(sx2,ex2) && ey >= Math.min(sy1,ey1) && ey <= Math.max(sy1,ey1) && ey >= Math.min(sy2,ey2) && ey <= Math.max(sy2,ey2));
    }
    
    public void display(Graphics g, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(d);
    }
}