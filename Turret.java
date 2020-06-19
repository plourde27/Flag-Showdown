import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Turret extends drawInterface {
    
    int playerNum;
    int[] color;
    int fireRate;
    int bulletSpeed;
    int shootRadius;
    int x, y;
    int timeout;
    int ang;
    double finalShoot = 0;
    double targetShoot = 0;
    double moveRate;
    int homingSpeed;
    
    public Turret(int[] ccolor, int xx, int yy, int num, int aang) {
        playerNum = num;
        color = ccolor;
        fireRate = 40;
        bulletSpeed = 9;
        shootRadius = 600;
        timeout = 0;
        x = xx;
        y = yy;
        ang = aang;
        finalShoot = playerNum * 30;
        moveRate = 3.0;
        homingSpeed = 1;
    }
    
    public void update(Display d) {
        for (int i = 0 ; i < d.map.players.size() ; i++) {
            if (d.map.players.get(i).num == playerNum) {
                continue;
            }
            Player p = d.map.players.get(i);
            if (Math.sqrt((p.x-x)*(p.x-x) + (p.y-y)*(p.y-y)) <= shootRadius) {
                double shootAngle = (Math.atan((y - p.y)/((double)(x - p.x)))*(180/Math.PI));
                if (p.x < x) {
                    shootAngle += 180;
                }
                targetShoot = shootAngle;
                if (timeout <= 0) {
                    shoot(d.map.players.get(i), shootAngle, d);
                    timeout = fireRate;
                    break;
                }
            }
        }
        timeout--;
        if (Math.abs(finalShoot - targetShoot) <= moveRate || Math.abs(finalShoot - targetShoot) >= 360 - moveRate) {
            finalShoot = targetShoot;
        }
        else if ((finalShoot < targetShoot && targetShoot - finalShoot > 180) || (targetShoot < finalShoot && finalShoot - targetShoot <= 180)) {
            finalShoot = (finalShoot + 360 - moveRate) % 360;
        }
        else {
            finalShoot = (finalShoot + moveRate) % 360;
        }
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(color[0], color[1], color[2], g);
        rect(x, y, 30, 30, g, tx, ty);
        
        vertex((int) (x + cos(finalShoot) * 50 + cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 50 + sin(finalShoot+90) * 4));
        vertex((int) (x + cos(finalShoot) * 50 - cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 50 - sin(finalShoot+90) * 4));
        vertex((int) (x + cos(finalShoot) * 0 - cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 0 - sin(finalShoot+90) * 4));
        vertex((int) (x + cos(finalShoot) * 0 + cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 0 + sin(finalShoot+90) * 4));
        
        endShape(g, tx, ty);
        fill(color[0], color[1], color[2], 30, g);
        ellipse(x, y, shootRadius*2, shootRadius*2, g, tx, ty);
    }
    
    public void display(Graphics g, Display d, int tx, int ty) {
        draw(g, tx, ty);
        update(d);
    }
    
    public void shoot(Player p, double ang, Display d) {
        d.map.bullets.add(new Bullet(p, homingSpeed, (int) (x + Math.cos(ang*(Math.PI / 180))*16), (int) (y + Math.sin(ang*(Math.PI / 180))*16), (int)ang, bulletSpeed, color, playerNum));
    }
    
    public void upgrade(int type) {
        if (type == 0) {
            fireRate = (int) (fireRate * (4.0 / 5));
        }
        else if (type == 1) {
            moveRate = (int) (moveRate * 1.3);
        }
        else if (type == 2) {
            bulletSpeed = (int) (bulletSpeed * 1.25);
        }
        else if (type == 3) {
            if (homingSpeed == 1) {
                homingSpeed = 2;
            }
            else {
                homingSpeed = (int) (homingSpeed * 1.5);
            }
        }
    }
    
}