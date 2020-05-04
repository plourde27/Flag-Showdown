import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Wall extends drawInterface {
    int x1, x2, y1, y2;
    final int FAC = 10;
    public Wall(int xx1, int yy1, int xx2, int yy2) {
        x1 = xx1;
        x2 = xx2;
        y1 = yy1;
        y2 = yy2;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(125, 60, 0, g);
        int ang = (int) (Math.atan(((double)(y2 - y1)) / (x2 - x1)) * (180.0 / Math.PI));
        if (x1 < x2) {
            ang += 180;
        }
        
        vertex((int) (x1 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y1 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x2 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y2 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x2 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y2 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x1 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y1 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x1 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y1 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));

        endShape(g, tx, ty);
    }
    
    public void update(Display d) {
        
    }
    
    public void display(Graphics g, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(d);
    }
}
