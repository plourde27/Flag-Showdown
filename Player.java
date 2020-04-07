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
    
    public Player() {
        x = 2500;
        y = 2500;
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
        if (kb.keys[87]) {
            x += Math.cos(ang * (Math.PI / 180)) * SPEED;
            y += Math.sin(ang * (Math.PI / 180)) * SPEED;
        }
        /*if (kb.keys[68]) {
            ang += ANGDIST;
        }
        if (kb.keys[65]) {
            ang -= ANGDIST;
        }*/
        ang = (int) (Math.atan((d.mouse.y - 360) / (d.mouse.x - 540)) * (180.0 / Math.PI));
        if (kb.keys[32] && timeout <= 0) {
            shoot(d);
            timeout = fireRate;
        }
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