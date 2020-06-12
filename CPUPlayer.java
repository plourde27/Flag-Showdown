import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class CPUPlayer extends Player {
    int ox, oy;
    double[] bplock = new double[]{};
    int bpind = 0;
    
    public CPUPlayer(int xx, int yy, int nnum) {
        super(xx, yy, nnum);
        ox = x;
        oy = y;
    }
    
    public double[] move(Keyboard kb, Display d) {
        if (bplock.length > 0 && d.map.bonusPoints.get(bpind).alive) {
            return target((int)bplock[0], (int)bplock[1]);
        }
        int mni = -1;
        int mn = 1000000000;
        for (int i = 0 ; i < d.map.bonusPoints.size() ; i++) {
            if (!d.map.bonusPoints.get(i).alive) {
                continue;
            }
            int xx = d.map.bonusPoints.get(i).x;
            int yy = d.map.bonusPoints.get(i).y;
            if (Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy)) <= mn) {
                mn = (int)Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));
                mni = i;
            }
        }
        if (mni != -1) {
            int i = mni;
            int xx = d.map.bonusPoints.get(i).x;
            int yy = d.map.bonusPoints.get(i).y;
            bplock = new double[]{xx, yy};
            bpind = i;
            return target(xx, yy);
        }
        return goToCenter();
        
    }
    
    public double[] target(int xx, int yy) {
        int SIZE = 5000;
        int ang = (int) (Math.atan((y - yy) / ((double)(x - xx + 0.01))) * (180.0 / Math.PI));
        if (x >= xx) {
            ang += 180;
        }
        int i = bpind;
        System.out.println(x + " " + y + " " + xx + " " + yy + " " + ang + " " + bpind);
        return new double[]{cos(ang), sin(ang)};
    }
    
    public double[] goToCenter() {
        if (Math.atan((y-SIZE/2)/((double)(x-SIZE/2+0.01)))-Math.atan((y-oy)/((double)(x-ox+0.01)))<0.4) {
            return target(SIZE / 2, SIZE / 2);
        }
        else {
            return target(ox, oy);
        }
    }
    
    public void display(Graphics g, Keyboard kb, int tx, int ty, Display d) {
        super.display(g, kb, tx, ty, d);
    }
}