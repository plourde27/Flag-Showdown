import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class BonusPoint extends drawInterface {
    int points;
    int x, y;
    
    boolean alive;
    
    TreeMap<Integer, Color> colors;
    
    public BonusPoint(int value, int xx, int yy) {
        colors = new TreeMap<Integer, Color>();
        colors.put(1, new Color(200, 0, 0));
        colors.put(2, new Color(0, 200, 0));
        colors.put(3, new Color(0, 0, 200));
        colors.put(4, new Color(200, 200, 0));
        colors.put(5, new Color(0, 200, 200));
        colors.put(6, new Color(200, 100, 0));
        colors.put(8, new Color(0, 100, 200));
        colors.put(10, new Color(120, 120, 120));
        colors.put(20, new Color(0, 200, 100));
        colors.put(50, new Color(100, 200, 0));
        points = value;
        x = xx;
        y = yy;
        alive = true;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        if (alive) {
            fill(colors.get(points), g);
            ellipse(x, y, 25, 25, g, tx, ty);
            fill(255, 255, 255, g);
            if (points < 10) {
                textSize(19, g);
                text(Integer.toString(points), x - 6, y + 7, g, tx, ty);
            }
            else {
                textSize(14, g);
                text(Integer.toString(points), x - 9, y + 6, g, tx, ty);
            }
        }
    }
}