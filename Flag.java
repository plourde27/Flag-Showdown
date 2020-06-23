import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Flag extends drawInterface {
    int x, y;
    int playerNum;
    int[] color;
    Player heldPlayer;
    int xMargin;
    int yMargin;
    Turret turret;
    int ownedPlayer;
    
    public Flag(int xx, int yy, int nnum, int[] ccolor, Turret t) {
        x = xx;
        y = yy;
        playerNum = nnum;
        color = ccolor;
        turret = t;
        ownedPlayer = nnum;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(color[0], color[1], color[2], 250, g);
        rect(x, y, 4, 30, g, tx, ty);
        fill(color[0], color[1], color[2], 160, g);
        vertex(x + 2, y - 15);
        vertex(x + 9, y - 10);
        vertex(x + 2, y - 5);
        vertex(x + 2, y - 15);
        endShape(g, tx, ty);
    }
    
    public void update(Display d) {
        for (int i = 0 ; i < d.map.players.size() ; i++) {
            Player p = d.map.players.get(i);
            if (Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y)) <= 15 && heldPlayer == null) {
                if (turret != null) {
                    if (turret.playerNum == p.num) {
                        continue;
                    }
                }
                ownedPlayer = -1;
                xMargin = p.x - x;
                yMargin = p.y - y;
                heldPlayer = p;
                turret = null;
                break;
            }
        }
        if (heldPlayer != null) {
            x = heldPlayer.x - xMargin;
            y = heldPlayer.y - yMargin;
            for (int i = 0 ; i < d.map.turrets.size() ; i++) {
                Turret t = d.map.turrets.get(i);
                if (Math.sqrt((t.x-x)*(t.x-x)+(t.y-y)*(t.y-y)) <= t.shootRadius && t.playerNum == heldPlayer.num) {
                    double ang = Math.random() * Math.PI * 2;
                    int dst = (int) (Math.random() * t.shootRadius);
                    int aang = (int) (i * (360.0 / d.map.PLAYER_COUNT));
                    
                    x = (int)(d.map.SIZE/2 + (d.map.PLAYER_DIST+400) * cos(aang));
                    y = (int)(d.map.SIZE/2 + (d.map.PLAYER_DIST+400) * sin(aang));
                    x += (int) (Math.random() * 40 - 20);
                    y += (int) (Math.random() * 40 - 20);
                    
                    heldPlayer = null;
                    ownedPlayer = i;
                    turret = t;
                    break;
                }
            }
        }
    }
    
    public void display(Graphics g, int tx, int ty, Display d) {
        draw(g, tx, ty);
        update(d);
    }
}