import java.util.*;

public class Game implements Runnable{
    int x;
    String scene;
    public Game(){
        x = 0;
        scene = "Login";
    }
    public void run(){
        while(true) {
            try{
                Thread.sleep(10);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}