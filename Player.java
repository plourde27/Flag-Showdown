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
        x = 500;
        y = 500;
        ang = 0;
    }
    
    public void draw(Graphics g) {
        fill(0, 0, 200, g);
        ellipse(x, y, 30, 30, g);
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
    
    public void display(Graphics g, Keyboard kb) {
        draw(g);
        update(kb);
    }
}