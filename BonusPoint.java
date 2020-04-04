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
    
    Color[] colors = new Color[]{new Color(200, 0, 0), new Color(0, 200, 0), new Color(0, 0, 200), new Color(200, 200, 0), new Color(200, 0, 200), new Color(0, 200, 200), new Color(200, 100, 0), new Color(0, 200, 100), new Color(120, 120, 120)};
    
    public BonusPoint(int value, int xx, int yy) {
        points = value;
        x = xx;
        y = yy;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(colors[points % colors.length], g);
        ellipse(x, y, 40, 40, g, tx, ty);
        fill(255, 255, 255, g);
        text(Integer.toString(points), x - 15, y - 15, g, tx, ty);
    }
}