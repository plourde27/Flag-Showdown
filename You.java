import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Math.*;
    
public class You extends Player {
    public You(int xx, int yy, int nnum) {
        super(xx, yy, nnum);
    }
    
    public void move(Keyboard kb, Display d) {
        ang = (Math.atan((d.mm.y - 360.0) / (d.mm.x - 540.0)) * (180.0 / Math.PI));
        if (d.mm.x <= 540) {
            ang += 180;
        }
        
        if (kb.keys[87]) {
            x += Math.cos(ang * (Math.PI / 180)) * SPEED;
            y += Math.sin(ang * (Math.PI / 180)) * SPEED;
        }
        if (kb.keys[32] && timeout <= 0) {
            shoot(d);
            timeout = fireRate;
        }
    }
    
    public void update(Keyboard kb, Display d, Graphics g, int tx, int ty) {
        super.update(kb, d);
    }
    
    public void display(Graphics g, Keyboard kb, int tx, int ty, Display d) {
        super.display(g, kb, tx, ty, d);
    }
}