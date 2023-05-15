import greenfoot.*;

import java.util.function.Supplier;

public class EnemySpawner<T extends Enemy> extends Actor {
    private final Supplier<T> ENEMY;
    private int count = 0;
    private final int total;
    private final int delay;
    private int lastCheck = -1;

    public EnemySpawner(Supplier<T> enemyType, int enemyCount, int spawnDelay){
        GreenfootImage i = new GreenfootImage(1, 1);
        i.setTransparency(0);
        setImage(i);
        ENEMY = enemyType;
        total = enemyCount;
        delay = spawnDelay;
    }
    public void act() {
        if(MyWorld.Timer - lastCheck >= delay && count < total){
            getWorld().addObject(ENEMY.get(),19, 470);
            count++;
            lastCheck = MyWorld.Timer;
        }
    }
    public boolean isFinished(){
        return count >= total;
    }
}