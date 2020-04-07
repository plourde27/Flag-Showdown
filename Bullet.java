import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Bullet extends drawInterface {
    int x, y, ang, speed;
    int[] c;
    public Bullet(int xx, int yy, int aang, int sspeed, int[] cc) {
        x = xx;
        y = yy;
        ang = aang;
        speed = sspeed;
        c = cc;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(c[0], c[1], c[2], g);
        ellipse(x, y, 5, 5, g, tx, ty);
    }
    
    public void update() {
        x += (int) (Math.cos(ang * (Math.PI / 180.0)) * speed);
        y += (int) (Math.sin(ang * (Math.PI / 180.0)) * speed);
    }
    
    public void display(Graphics g, int tx, int ty) {
        draw(g, tx, ty);
        update();
    }
}