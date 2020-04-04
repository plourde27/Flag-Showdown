import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class GameSpace extends drawInterface {
    
    ArrayList<BonusPoint> bonusPoints;
    final int SIZE = 10000;
    final int CENTER_RADIUS = 3000;
    final int MIN_POINTS = 200;
    final int MAX_POINTS = 300;
    final int CLOSE_DIST = 50;
    
    public GameSpace() {
        bonusPoints = new ArrayList<BonusPoint>();
        int num = (int) (Math.random() * (MAX_POINTS - MIN_POINTS) + MIN_POINTS);
        
        for (int i = 0 ; i < num ; i++) {
            int ang;
            int xx;
            int yy;
            double r = Math.random();
            int val = 0;
            if (r <= 0.9) {
                val = (int) (r / 0.18 + 1);
            }
            else if (r <= 0.99) {
                val = 6 + ((int) ((r - 0.9) / 0.03)) * 2;
            }
            else {
                if (Math.random() <= 0.5) {
                    val = 20;
                }
                else {
                    val = 50;
                }
            }
            
            if (i == 0) {
                ang = (int) (Math.random() * 2 * Math.PI);
                xx = (SIZE / 2) + (int) (Math.cos(ang) * CENTER_RADIUS);
                yy = (SIZE / 2) + (int) (Math.sin(ang) * CENTER_RADIUS);
                bonusPoints.add(new BonusPoint(val, xx, yy));
            }
            int t = 0;
            while (true) {
                ang = (int) (Math.random() * 2 * Math.PI);
                xx = (SIZE / 2) + (int) (Math.cos(ang) * CENTER_RADIUS);
                yy = (SIZE / 2) + (int) (Math.sin(ang) * CENTER_RADIUS);
                boolean valid = true;
                for (int j = 0 ; j < bonusPoints.size() ; j++) {
                    int x = bonusPoints.get(j).x;
                    int y = bonusPoints.get(j).y;
                    if (Math.sqrt((xx-x)*(xx-x) + (yy-y)*(yy-y)) <= CLOSE_DIST) {
                        valid = false;
                    }
                }
                if (valid || t >= 1000) {
                    bonusPoints.add(new BonusPoint(val, xx, yy));
                    break;
                }
                t++;
            }
                    
        }
    }
    
    public void drawMap(Graphics g, int tx, int ty) {
        for (int i = 0 ; i < bonusPoints.size() ; i++) {
            bonusPoints.get(i).draw(g, tx, ty);
        }
    }
}