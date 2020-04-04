import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;

public class Player extends drawInterface {
    int x, y, ang;
    final int SPEED = 3;
    final int ANGDIST = 4;
    public Player() {
        x = 2500;
        y = 2500;
        ang = -90;
    }
    
    public void draw(Graphics g, int tx, int ty) {
        fill(0, 0, 200, g);
        ellipse(x, y, 30, 30, g, tx, ty);
    }
    
    public void update(Keyboard kb) {
        
        if (kb.keys[87]) {
            x += Math.cos(ang * (Math.PI / 180)) * SPEED;
            y += Math.sin(ang * (Math.PI / 180)) * SPEED;
        }
        if (kb.keys[68]) {
            ang += ANGDIST;
        }
        if (kb.keys[65]) {
            ang -= ANGDIST;
        }
    }
    
    public void display(Graphics g, Keyboard kb, int tx, int ty) {
        draw(g, tx, ty);
        update(kb);
    }
}