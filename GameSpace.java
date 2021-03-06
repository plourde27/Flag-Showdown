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
    final int CENTER_RADIUS = 500;
    final int MIN_POINTS = 50;
    final int MAX_POINTS = 60;
    final int CLOSE_DIST = 40;
    final int PLAYER_DIST = 2000;
    final int PLAYER_COUNT = 12;
    ArrayList<Player> players;
    ArrayList<Turret> turrets;
    ArrayList<Flag> flags;
    ArrayList<Wall> walls;
    
    public GameSpace() {
        bonusPoints = new ArrayList<BonusPoint>();
        bullets = new ArrayList<Bullet>();
        players = new ArrayList<Player>();
        turrets = new ArrayList<Turret>();
        flags = new ArrayList<Flag>();
        walls = new ArrayList<Wall>();
        
        int num = (int) (Math.random() * (MAX_POINTS - MIN_POINTS) + MIN_POINTS);
        
        for (int i = 0 ; i < num ; i++) {
            double ang;
            int xx;
            int yy;
            double r = Math.random();
            int val = 0;
            int dst;
            double c;
            if (r < 0.9) {
                val = (int) (r / 0.18 + 1);
                c = 1;
            }
            else if (r <= 0.99) {
                val = 6 + ((int) ((r - 0.9) / 0.03))*2;
                c = 0.5;
            }
            else {
                if (Math.random() <= 0.5) {
                    val = 12;
                }
                else {
                    val = 15;
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
            double cang = ang * (Math.PI / 180.0);
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
            Flag f = new Flag((int)(SIZE/2 + (PLAYER_DIST+400) * Math.cos(ang * (Math.PI/180.0))), (int)(SIZE/2 + (PLAYER_DIST+400) * Math.sin(ang * (Math.PI/180.0))), i, pp.c, t);
            flags.add(f);
            
            //Wall w = new Wall(t.x - 100, t.y, t.x, t.y + 100, i);
            /*walls.add(new Wall(i, t.x, t.y, ang, 100, 80, -100, 80));
            walls.add(new Wall(i, t.x, t.y, ang, 100, -80, -100, -80));
            walls.add(new Wall(i, t.x, t.y, ang, -100, -80, -100, -180));
            walls.add(new Wall(i, t.x, t.y, ang, -100, -180, 200, -180));
            walls.add(new Wall(i, t.x, t.y, ang, -100, 80, -100, 180));
            walls.add(new Wall(i, t.x, t.y, ang, -100, 180, 200, 180));*/
            walls.add(new Wall(i, t.x, t.y, ang, -100, -100, 100, -100));
            walls.add(new Wall(i, t.x, t.y, ang, -100, 100, 100, 100));
            walls.add(new Wall(i, t.x, t.y, ang, 100, -100, 100, 100));
            walls.add(new Wall(i, t.x, t.y, ang, -200, -60, -200, -300));
            walls.add(new Wall(i, t.x, t.y, ang, -200, -300, 300, -300));
            walls.add(new Wall(i, t.x, t.y, ang, 300, -300, 300, 300));
            walls.add(new Wall(i, t.x, t.y, ang, -200, 60, -200, 300));
            walls.add(new Wall(i, t.x, t.y, ang, -200, 300, 300, 300));
            walls.add(new Wall(i, t.x, t.y, ang, 200, -90, 200, -50));
            walls.add(new Wall(i, t.x, t.y, ang, 200, 90, 200, 50));
            walls.add(new Wall(i, t.x, t.y, ang, -100, -200, -60, -200));
            walls.add(new Wall(i, t.x, t.y, ang, 40, -200, 80, -200));
            walls.add(new Wall(i, t.x, t.y, ang, -100, 200, -60, 200));
            walls.add(new Wall(i, t.x, t.y, ang, 40, 200, 80, 200));
            
            for (int j = 520 ; j <= 1700 ; j += 240) {
                walls.add(new Wall(i, SIZE / 2, SIZE / 2, (int) (ang + (180.0 / PLAYER_COUNT)), j, 0, j + 180, 0));
            }
            
        }
        
        int inc = 10;
        int sz = SIZE / 2 + 500;
        for (int i = 0 ; i < 360 ; i += inc) {
            walls.add(new Wall((int) (SIZE / 2 + cos(i) * (sz)), (int) (SIZE / 2 + sin(i) * (sz)), (int) (SIZE / 2 + cos(i + inc) * (sz)), (int) (SIZE / 2 + sin(i + inc) * (sz)), 0));
        }
    }
    
    public void drawMap(Graphics g, int tx, int ty, Display d, Keyboard kb) {
        for (int i = 0 ; i < bonusPoints.size() ; i++) {
            bonusPoints.get(i).draw(g, tx, ty);
        }
        
         for (int i = 0 ; i < walls.size(); i ++) {
            walls.get(i).display(g, tx, ty, d);
        }
        
        for (int i = 0 ; i < bullets.size() ; i++) {
            bullets.get(i).display(g, tx, ty, d);
        }
        
        for (int i = bullets.size() - 1 ; i >= 0 ; i--) {
            if (bullets.get(i).dead) {
                bullets.remove(i);
            }
        }
        
        for (int i = 0 ; i < players.size() ; i++) {
            players.get(i).display(g, kb, tx, ty, d);
        }
        
        for (int i = 0 ; i < turrets.size() ; i++) {
            turrets.get(i).display(g, d, tx, ty);
        }
        
        for (int i = 0 ; i < flags.size() ; i++) {
            flags.get(i).display(g, tx, ty, d);
        }
        
        ArrayList<Integer> fn = new ArrayList<Integer>();
        for (int i = 0 ; i < 12 ; i++) {
            fn.add((12-i-1) + players.get(i).flagNums.size() * 12);
        }
        
        Collections.sort(fn);
        
        ArrayList<Integer> inds = new ArrayList<Integer>();
        for (int i = 11 ; i >= 0 ; i--) {
            //System.out.println((int)(fn.get(i) / 12) + " " + (fn.get(i)%12));
            inds.add(12 - fn.get(i) % 12 - 1);
        }
        
        int i = 0;
        int IC = 22;
        
        for (int l = 0 ; l < 12 ; l++) {
            i = inds.get(l);
            if (i == 0) {
                fill(0, 200, 200, 100, g);
            }
            else {
                fill(0, 0, 0, 100, g);
            }
            rect(0, 200+l*IC, 320, 20, g, 0, 0);
            Player p = players.get(i);
            fill(p.c[0], p.c[1], p.c[2], 200, g);
            textSize(12, g);
            text("Player " + (i + 1), 10, 205 + l * IC, g, 0, 0);
            for (int j = 0 ; j < p.flagNums.size() ; j++) {
                Flag f = d.map.flags.get(p.flagNums.get(j));
                int[] color = f.color;
                int x = 76 + j * 8;
                int y = 200+l*IC;
                fill(color[0], color[1], color[2], 250, g);
                rect(x, y, 2, 16, g, 0, 0);
                fill(color[0], color[1], color[2], 160, g);
                vertex(x + 1, y - 8);
                vertex(x + 5, y - 5);
                vertex(x + 1, y - 2);
                vertex(x + 1, y - 8);
                endShape(g, 0, 0);
            }
            i++;
        }
    }
}