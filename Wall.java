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
    final int FAC = 40;
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
    
    public void draw(Graphics g, int tx, int ty) {
        fill(125, 60, 0, g);
        int ang = (int) (Math.atan(((double)(y2 - y1)) / (x2 - x1)) * (180.0 / Math.PI));
        if (x1 < x2) {
            ang += 180;
        }
        
        /*vertex((int) (x1 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y1 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x2 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y2 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x2 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y2 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x1 + Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y1 + Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        vertex((int) (x1 - Math.cos((ang + 90) * (Math.PI / 180.0)) * FAC), (int) (y1 - Math.sin((ang + 90) * (Math.PI / 180.0)) * FAC));
        
        */
       for (int j = 0 ; j < coords.size() ; j += 2) {
            //System.out.println(wl.coords.get(j) + " " + wl.coords.get(j + 1) + " " + wl.coords.get((j + 2)%wl.coords.size()) + " " + wl.coords.get((j + 3)%wl.coords.size()));
            line(coords.get(j), coords.get(j + 1), coords.get((j + 2)%coords.size()), coords.get((j + 3)%coords.size()), g, tx, ty);
          
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
