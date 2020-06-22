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
        homingSpeed = 0;
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
    
    public void draw(Display d, Graphics g, int tx, int ty) {
        fill(color[0], color[1], color[2], g);
        rect(x, y, 30, 30, g, tx, ty);
        
        vertex((int) (x + cos(finalShoot) * 50 + cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 50 + sin(finalShoot+90) * 4));
        vertex((int) (x + cos(finalShoot) * 50 - cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 50 - sin(finalShoot+90) * 4));
        vertex((int) (x + cos(finalShoot) * 0 - cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 0 - sin(finalShoot+90) * 4));
        vertex((int) (x + cos(finalShoot) * 0 + cos(finalShoot+90) * 4), (int) (y + sin(finalShoot) * 0 + sin(finalShoot+90) * 4));
        
        endShape(g, tx, ty);
        fill(color[0], color[1], color[2], 30, g);
        ellipse(x, y, shootRadius*2, shootRadius*2, g, tx, ty);
        
        if (playerNum == 0) {
            Player p = d.map.players.get(0);
            if (p.points >= 2 && Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)) <= shootRadius) {
                fill(200, 200, 200, 150, g);
                rect(540, 75, 1080, 150, g, 0, 0);
                fill(0, 0, 0, g);
                textSize(30, g);
                text("Upgrade Your Turret", 50, 60, g, 0, 0);
                textSize(24, g);
                text("Current Balance: " + p.points + " Bonus Points", 40, 115, g, 0, 0);
                String[] names = {"Fire Rate", "Turret Speed", "Bullet Speed", "Bullet Homing"};
                int[] vars = {(int)(100.0/fireRate), (int)moveRate, bulletSpeed, homingSpeed};
                int[] costs = {8, 2, 6, 10};
                d.frame.setCursor(d.arrowCursor);
                for (int i = 0 ; i < 4 ; i++) {
                    if (p.points >= costs[i]) {
                        fill(0, 0, 0, g);
                        textSize(18, g);
                        text(names[i], 460 + i * 150, 30, g, 0, 0);
                        textSize(14, g);
                        text("Current Value: " + vars[i], 460 + i * 150, 55, g, 0, 0);
                        text("Cost: " + costs[i] + " Points", 460 + i * 150, 85, g, 0, 0);
                        fill(240, 240, 240, 150, g);
                        rect(500 + i * 150, 115, 80, 25, g, 0, 0);
                        fill(0, 0, 0, g);
                        textSize(16, g);
                        text("Upgrade", 467 + i * 150, 121, g, 0, 0);
                        if (d.mm.x >= 460+i*150 && d.mm.x <= 540+i*150 && d.mm.y >= 102.5 && d.mm.y <= 127.5 && p.points >= costs[i]) {
                            if (d.mouse.clicked) {
                                upgrade(i);
                                p.points -= costs[i];
                            }
                            d.frame.setCursor(d.handCursor);
                        }
                    }
                    
                }
                //System.out.println(d.mouse.clicked);
            }
            else {
                fill(0, 0, 0, g);
                textSize(22, g);
                text(p.points + " Bonus Points", 100, 25, g, 0, 0);
            }
        }
    }
    
    public void display(Graphics g, Display d, int tx, int ty) {
        draw(d, g, tx, ty);
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
            moveRate = Math.max(moveRate+1, (int) (moveRate * 1.3));
        }
        else if (type == 2) {
            bulletSpeed = Math.max(bulletSpeed+1,(int) (bulletSpeed * 1.25));
        }
        else if (type == 3) {
            
            homingSpeed = Math.max(homingSpeed+1, (int) (homingSpeed * 1.5));
            
        }
    }
    
}