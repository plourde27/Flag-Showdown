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
    final int SPEED = 3;
    final int ANGDIST = 4;
    public Player() {
        x = 2500;
        y = 2500;
        ang = -90;
        fireRate = 30;
        bulletSpeed = 6;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(0, 0, 200, g);
        ellipse(x, y, 30, 30, g, tx, ty);
    }
    
    public void update(Keyboard kb, Display d) {
        timeout--;
        if (kb.keys[87]) {
            x += Math.cos(ang * (Math.PI / 180)) * SPEED;
            y += Math.sin(ang * (Math.PI / 180)) * SPEED;
        }
        if (kb.keys[68]) {
            ang += ANGDIST;
        }
        if (kb.keys[65]) {
            ang -= ANGDIST;
        }
        if (kb.keys[32] && timeout <= 0) {
            shoot(d);
            timeout = fireRate;
        }
    }
    
    public void shoot(Display d) {
        d.bullets.add(new Bullet(x, y, ang, bulletSpeed));
    }
    
    public void display(Graphics g, Keyboard kb, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(kb, d);
    }
}