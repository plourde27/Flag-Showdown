import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class drawInterface extends JComponent {
    
    int tx = 0;
    int ty = 0;
    double scl = 1.0;
    int ang = 0;
    int[] coords = new int[]{0, 0, 1080, 720};
    ArrayList<Integer> xs = new ArrayList<Integer>();
    ArrayList<Integer> ys = new ArrayList<Integer>();
    
    
    public drawInterface() {
        
    }
    
    public void repaint() {
        super.repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    public int[] transform(int x, int y) {
        x = (int) ((x - coords[0]) * scl);
        y = (int) ((y - coords[1]) * scl);
        return new int[]{x, y};
    }
    
    public void rect(int x, int y, int w, int h, Graphics g) {
        System.out.println(x + " " + y);
        int ox = x;
        int oy = y;
        x = transform(ox, oy)[0];
        y = transform(ox, oy)[1];
        System.out.println(x + " " + y);
        g.fillRect(x - w / 2, y - h / 2, (int) (w * scl), (int) (h * scl));
    }
    
    public void ellipse(int x, int y, int w, int h, Graphics g) {
        x = transform(x, y)[0];
        y = transform(x, y)[1];
        g.fillOval((int) (x - (w * scl) / 2), (int) (y - (h * scl) / 2), (int) (w * scl), (int) (h * scl));
    }
    
    public void fill(int r, int g, int b, Graphics gr) {
        gr.setColor(new Color(r, g, b));
    }
    
    public void textSize(int sz, Graphics g) {
        g.setFont(new Font("Avenir", Font.PLAIN, sz));
    }
    
    public void text(String txt, int x, int y, Graphics g) {
        x = transform(x, y)[0];
        y = transform(x, y)[1];
        g.drawString(txt, x, y);
    }
    
    public void line(int x1, int y1, int x2, int y2, Graphics g) {
        int ox1 = x1;
        int oy1 = y1;
        int ox2 = x2;
        int oy2 = y2;
        x1 = transform(ox1, oy1)[0];
        y1 = transform(ox1, oy1)[1];
        x2 = transform(ox2, oy2)[0];
        y2 = transform(ox2, oy2)[1];
        g.drawLine(x1, y1, x2, y2);
    }
    
    public void vertex(int x, int y) {
        int x1 = transform(x, y)[0];
        int y1 = transform(x, y)[1];
        xs.add(x1);
        ys.add(y1);
    }
    
    public void endShape(Graphics g) {
        int[] x = new int[xs.size()];
        int[] y = new int[ys.size()];
        for (int i = 0 ; i < xs.size() ; i++) {
            x[i] = xs.get(i);
            y[i] = ys.get(i);
        }
        g.fillPolygon(new Polygon(x, y, xs.size()));
        xs = new ArrayList<Integer>();
        ys = new ArrayList<Integer>();
    }
}