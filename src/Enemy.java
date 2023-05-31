import greenfoot.*;

import java.awt.*;

/**
 * A superclass for all Enemies
 */
public class Enemy extends Actor {
    private int health;
    private final int maxHealth;
    private final int speed;
    private double distance = 0;
    private final int offset;
    private final int value;

    /**
     * Creates an Enemy with the specified stats
     * @param mh Maximum health of Enemy
     * @param s Speed of Enemy
     * @param o Offset of Pathing checks
     * @param v Money gained on Enemy death
     */
    public Enemy(int mh, int s, int o, int v) {
        mh = (int)(Math.max((mh * Spawner.getWave())/10.0, mh));
        health = mh;
        maxHealth = mh;
        speed = (int)Math.min(Math.max((s * Spawner.getWave())/10.0, s), 3);
        offset = o;
        value = v;
    }

    public void act() {
        boolean atEdge = false;
        for(int i = 0; i <= speed; i++){
            path();
            move();
            if(isAtEdge()){
                atEdge = true;
                break;
            }
        }
        if(atEdge){
            Lives.alterLives(-maxHealth);
            getWorld().removeObject(this);
        }
    }
    public void damage(int d) {
        health -= d;
        if(health <= 0) {
            Cash.alterCash(value);
            getWorld().removeObject(this);
        }
    }
    private void path(){
        PathFollow path = new PathFollow(getRotation(), getX(), getY(), offset);
        getWorld().addObject(path, 0, 0);
        int c = path.pathCheck();
        if(c == 1){
            turn(4);
        }else if(c == 2){
            turn(-4);
        }
        getWorld().removeObject(path);
    }
    private void move(){
        move(1);
        distance += 1;
    }

    /**
     * Returns the location of the Enemy in the form of a Point
     */
    public Point locate(){
        return new Point(getX(), getY());
    }

    /**
     * Returns the progress the Enemy has made across the path
     * @return
     */
    public double progress(){
        return distance;
    }
    public int maxHealth(){
        return maxHealth;
    }
}
