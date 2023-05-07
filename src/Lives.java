import greenfoot.*;

public class Lives extends Counter {
    private static int lives;
    public Lives(int l){
        lives = l;
    }
    public void act(){
        updateImage("Lives: ", String.valueOf(Math.max(lives, 0)));
    }
    public static void alterLives(int val){
        lives += val;
        lifeCheck();
    }
    public static int getLives(){
        return lives;
    }
    private static void lifeCheck(){
        if(lives <= 0){
            System.out.println("Game Over!");
            Greenfoot.stop();
        }
    }
}