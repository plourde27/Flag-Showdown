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
    ArrayList<Bullet> bullets;
    final int SIZE = 5000;
    final int CENTER_RADIUS = 400;
    final int MIN_POINTS = 20;
    final int MAX_POINTS = 30;
    final int CLOSE_DIST = 20;
    final int PLAYER_DIST = 1000;
    final int PLAYER_COUNT = 12;
    ArrayList<Player> players;
    ArrayList<Turret> turrets;
    ArrayList<Flag> flags;
    
    public GameSpace() {
        bonusPoints = new ArrayList<BonusPoint>();
        bullets = new ArrayList<Bullet>();
        players = new ArrayList<Player>();
        turrets = new ArrayList<Turret>();
        flags = new ArrayList<Flag>();
        
        int num = (int) (Math.random() * (MAX_POINTS - MIN_POINTS) + MIN_POINTS);
        
        for (int i = 0 ; i < num ; i++) {
            double ang;
            int xx;
            int yy;
            double r = Math.random();
            int val = 0;
            int dst;
            double c;
            if (r <= 0.9) {
                val = (int) (r / 0.18 + 1);
                c = 1;
            }
            else if (r <= 0.99) {
                val = 6 + ((int) ((r - 0.9) / 0.03)) * 2;
                c = 0.5;
            }
            else {
                if (Math.random() <= 0.5) {
                    val = 20;
                }
                else {
                    val = 50;
                }
                c = 0.2;
            }
            
            if (i == 0) {
                ang = (Math.random() * 2 * Math.PI);
                dst = (int) (Math.random() * (CENTER_RADIUS * c));
                xx = (SIZE / 2) + (int) (Math.cos(ang) * dst);
                yy = (SIZE / 2) + (int) (Math.sin(ang) * dst);
                bonusPoints.add(new BonusPoint(val, xx, yy));
            }
            int t = 0;
            while (true) {
                ang = (Math.random() * 2 * Math.PI);
                dst = (int) (Math.random() * (CENTER_RADIUS * c));
                xx = (SIZE / 2) + (int) (Math.cos(ang) * dst);
                yy = (SIZE / 2) + (int) (Math.sin(ang) * dst);
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
        
        for (int i = 0 ; i < PLAYER_COUNT ; i++) {
            int ang = (int) (i * (360.0 / PLAYER_COUNT));
            Player pp;
            if (i > 0) {
                CPUPlayer p = new CPUPlayer((int)(SIZE/2 + PLAYER_DIST * Math.cos(ang * (Math.PI/180.0))), (int)(SIZE/2 + PLAYER_DIST * Math.sin(ang * (Math.PI/180.0))), i);
                pp = (Player) p;
                players.add(pp);
            }
            else {
                You p = new You((int)(SIZE/2 + PLAYER_DIST * Math.cos(ang * (Math.PI/180.0))), (int)(SIZE/2 + PLAYER_DIST * Math.sin(ang * (Math.PI/180.0))), i);
                pp = (Player) p;
                players.add(pp);
            }
            Turret t = new Turret(pp.c, (int)(SIZE/2 + (PLAYER_DIST+200) * Math.cos(ang * (Math.PI/180.0))), (int)(SIZE/2 + (PLAYER_DIST+200) * Math.sin(ang * (Math.PI/180.0))), i, ang);
            turrets.add(t);
            Flag f = new Flag((int)(SIZE/2 + (PLAYER_DIST+230) * Math.cos(ang * (Math.PI/180.0))), (int)(SIZE/2 + (PLAYER_DIST+230) * Math.sin(ang * (Math.PI/180.0))), i, pp.c, t);
            flags.add(f);
        }
        System.out.println(players.size());
    }
    
    public void drawMap(Graphics g, int tx, int ty, Display d, Keyboard kb) {
        for (int i = 0 ; i < bonusPoints.size() ; i++) {
            //System.out.println(bonusPoints.get(i).x + " " + bonusPoints.get(i).y);
            bonusPoints.get(i).draw(g, tx, ty);
        }
        
        for (int i = 0 ; i < bullets.size() ; i++) {
            bullets.get(i).display(g, tx, ty);
        }
        
        for (int i = 0 ; i < players.size() ; i++) {
            System.out.println(players.get(i));
            players.get(i).display(g, kb, tx, ty, d);
        }
        
        for (int i = 0 ; i < turrets.size() ; i++) {
            turrets.get(i).display(g, d, tx, ty);
        }
        
        for (int i = 0 ; i < flags.size() ; i++) {
            flags.get(i).display(g, tx, ty, d);
        }
    }
}