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
    }
    
    public void update(Display d) {
        for (int i = 0 ; i < d.map.players.size() ; i++) {
            if (d.map.players.get(i).num == playerNum) {
                continue;
            }
            Player p = d.map.players.get(i);
            if (Math.sqrt((p.x-x)*(p.x-x) + (p.y-y)*(p.y-y)) <= shootRadius && timeout <= 0) {
                int shootAngle = (int) (Math.atan((y - p.y)/((double)(x - p.x)))*(180/Math.PI));
                if (p.x < x) {
                    shootAngle += 180;
                }
                shoot(shootAngle, d);
                timeout = fireRate;
                break;
            }
        }
        timeout--;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(color[0], color[1], color[2], g);
        ellipse(x, y, 30, 30, g, tx, ty);
        fill(color[0], color[1], color[2], 30, g);
        ellipse(x, y, shootRadius*2, shootRadius*2, g, tx, ty);
    }
    
    public void display(Graphics g, Display d, int tx, int ty) {
        draw(g, tx, ty);
        update(d);
    }
    
    public void shoot(int ang, Display d) {
        d.map.bullets.add(new Bullet((int) (x + Math.cos(ang*(Math.PI / 180))*16), (int) (y + Math.sin(ang*(Math.PI / 180))*16), ang, bulletSpeed, color));
    }
    
    public void upgrade() {
        
    }
    
}