
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
    int mx;
    boolean left;
    boolean start;
    int shootx, shooty;
    int ERROR;
    int tg;

    public CPUPlayer(int xx, int yy, int nnum) {
        super(xx, yy, nnum);
        ox = x;
        oy = y;
        left = true;
        mx = 5;
        start = true;
        shootx = -1;
        shooty = -1;
        tg = 0;
        ERROR = 8;
    }
    
    public double[] goForFlag(int nm) {
        int count = 12;
        int a = (int) (nm * (360.0 / count));
        int xx = (int)(SIZE/2+1800*cos(a));
        int yy = (int)(SIZE/2+1800*sin(a));
        return target(xx, yy);
    }
    
    public double[] move(Keyboard kb, Display d) {
        int md = 500;
        int mi = -1;
        shootx = -1;
        shooty = -1;
        tg = 0;
        for (int i = 0 ; i < 12 ; i++) {
            if (i == num) {
                continue;
            }
            int xx = d.map.players.get(i).x;
            int yy = d.map.players.get(i).y;
            if (Math.sqrt((x-xx)*(x-xx)+(y-yy)*(y-yy)) <= md) {
                md = (int)Math.sqrt((x-xx)*(x-xx)+(y-yy)*(y-yy));
                mi = i;
            }
        }
        if (mi != -1) {
            shootx = d.map.players.get(mi).x;
            shooty = d.map.players.get(mi).y;
            tg = (int)(Math.atan((shooty - y) / ((double)(shootx - x))) * (180 / Math.PI));
            if (shootx < x) tg += 180;
            tg += (int)(Math.random() * ERROR * 2 - 5);
        }
        
        if (timeout <= 0 && mi != -1) {
            shoot(d, tg);
            timeout = fireRate;
        }
        
        if (start) {
            int count = 12;
            int a = (int) (num * (360.0 / count));
            int xx = (int)(SIZE/2+2000*cos(a));
            int yy = (int)(SIZE/2+2000*sin(a));
            if (Math.sqrt((xx-x)*(xx-x)+(yy-y)*(yy-y))<=30 && !left) {
                start = false;
            }
            return openingPush(d);
        }
        
        return goForFlag((num + 11) % 12);
    }
    
    public double[] openingPush(Display d) {
        if (bplock.length > 0 && d.map.bonusPoints.get(bpind).alive) {
            return target((int)bplock[0], (int)bplock[1]);
        }
        
        if (left) {
            return getBonusPoints(d);
        }
        
        return goHome();
    }
    
    public double[] goHome() {
        int aang = (int)(Math.atan(((double)y-SIZE/2.0)/(x-SIZE/2.0))*(180.0/Math.PI));
        if (x < SIZE / 2.0) {
            aang = (aang + 180) % 360;
        }
        int count = 12;
        int a = (int) (num * (360.0 / count));
        int t = 8;
        if (Math.abs(a - aang) <= t || Math.abs(a - aang) >= 360 - t) {
            return target((int)(SIZE/2+2000*cos(a)),(int)(SIZE/2+2000*sin(a)));
        }
        int ppx = (int) (SIZE/2 + 400*cos(a));
        int ppy = (int) (SIZE/2 + 400*sin(a));
        return target(ppx, ppy);
    }
    
    public double[] getBonusPoints(Display d) {
        if (points >= mx) {
            left = false;
        }
        
        int mni = 0;
        int mn = 100000000;
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
        
        left = false;
        return new double[]{};
    }
    
    public double[] target(int xx, int yy) {
        int SIZE = 5000;
        int ang = (int) (Math.atan((y - yy) / ((double)(x - xx + 0.01))) * (180.0 / Math.PI));
        if (x >= xx) {
            ang += 180;
        }
        int i = bpind;
        return new double[]{cos(ang), sin(ang)};
    }
    
    public double[] goToCenter() {
        if (Math.sqrt((x-SIZE/2)*(x-SIZE/2)+(y-SIZE/2)*(y-SIZE/2)) <= 500 || Math.atan((y-SIZE/2)/((double)(x-SIZE/2+0.01)))-Math.atan((y-oy)/((double)(x-ox+0.01)))<0.4) {
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
