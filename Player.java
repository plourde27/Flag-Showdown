import java.lang.Math.*;

public class Player extends drawInterface {
    int x, y, ang;
    public Player() {
        x = 500;
        y = 500;
        ang = 0;
    }
    
    public void draw() {
    }
    
    public void update() {
    }
    
    public void display() {
        draw();
        update();
    }
}