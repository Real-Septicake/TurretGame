import greenfoot.*;

import java.awt.*;

public class Enemy extends Actor {
    private int health;
    private final int maxHealth;
    private final int speed;
    private double distance = 0;
    private static int count = 0;
    private final int id;
    private final int offset;
    private final int value;

    public Enemy(int mh, int s, int o, int v) {
        health = mh;
        maxHealth = mh;
        speed = s;
        offset = o;
        value = v;
        id = count++;
    }

    public void act() {
        path();
        move();
    }
    public void damage(int d) {
        health -= d;
        if(health <= 0) {
            Cash.alterCash(value);
            getWorld().removeObject(this);
        }
    }
    private void path(){
        PathFollow path = new PathFollow(getRotation(), getX(), getY());
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
        move(speed);
        distance += speed;
        if(isAtEdge()){
            Lives.alterLives(-maxHealth);
            getWorld().removeObject(this);
        }
    }
    public Point locate(){
        return new Point(getX(), getY());
    }

    public double progress(){
        return distance;
    }
}
