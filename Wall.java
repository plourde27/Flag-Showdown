import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Wall extends drawInterface {
    int centerX, centerY;
    int dist;
    int ang1;
    int ang2;
    public Wall(int x, int y, int a1, int a2, int dst) {
        ang1 = a1;
        ang2 = a2;
        centerX = x;
        centerY = y;
        dist = dst;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(125, 60, 0, g);
        for (int i = ang1 ; i <= ang2 ; i++) {
            vertex(centerX + cos(i) * (dst - 4), centerY + sin(i) * (dst - 4));
        }
        for (int i = ang2 ; i >= ang1 ; i--) {
            vertex(centerX + cos(i) * (dst + 4), centerY + sin(i) * (dst + 4));
        }
        endShape(g, tx, ty);
    }
    
    public void update(Display d) {
        
    }
    
    public void display(Graphics g, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(d);
    }
}
