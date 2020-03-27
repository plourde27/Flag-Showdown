import java.awt.event.*;
public class Mouse implements MouseListener{
    boolean clicked;
    boolean pressed;
    int x, y;
    public Mouse(){
        clicked = false;
        pressed = false;
    }
    
    public int xx(MouseEvent e) {
        return e.getX();
    }
    
    public int yy(MouseEvent e) {
        return e.getY();
    }
    
    public void mousePressed(MouseEvent e){
        x = e.getX();
        y = e.getY() - 30;
        if (!pressed) {
            clicked = true;
            pressed = true;
        }
        else {
            clicked = false;
            pressed = true;
        }
    }
    public void mouseReleased(MouseEvent e){
        //clicked = false;
        //pressed = false;
    }
    public void mouseEntered(MouseEvent e) {
        x = e.getX();
        y = e.getY() - 30;
    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseClicked(MouseEvent e) {
        pressed = false;
    }
}