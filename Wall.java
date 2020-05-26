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
    ArrayList<Integer> coords;
    int num;
    
    public Wall(int xx1, int yy1, int xx2, int yy2, int n) {
        num = n;
        x1 = xx1;
        x2 = xx2;
        y1 = yy1;
        y2 = yy2;
        
        coords = new ArrayList<Integer>();
        
        int ang = (int) (Math.atan(((double)(y2 - y1)) / (x2 - x1)) * (180.0 / Math.PI));
        if (x1 < x2) {
            ang += 180;
        }
        
        coords.add((int) (x1 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y1 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (x2 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y2 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (x2 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y2 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (x1 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y1 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        
    }
    
    public Wall(int n, int tx, int ty, int a, int a1, int b1, int a2, int b2) {
        num = n;
        x1 = (int) (tx + cos(a) * a1 + cos(a + 90) * b1) + 2;
        y1 = (int) (ty + sin(a) * a1 + sin(a + 90) * b1) + 4;
        x2 = (int) (tx + cos(a) * a2 + cos(a + 90) * b2) + 6;
        y2 = (int) (ty + sin(a) * a2 + sin(a + 90) * b2) + 8;
        
        coords = new ArrayList<Integer>();
        
        int ang = (int) (Math.atan(((double)(y2 - y1)) / (x2 - x1)) * (180.0 / Math.PI));
        if (x1 < x2) {
            ang += 180;
        }
        
        coords.add((int) (x1 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y1 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (x2 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y2 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (x2 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y2 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (x1 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC));
        coords.add((int) (y1 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        
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
    
    public static int ri(int n) {
        return (int) (Math.random() * n);
    }
    
    public void update(Display d) {
        
    }
    
    public void display(Graphics g, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(d);
    }
}
