import greenfoot.*;

import java.util.function.Supplier;

public class EnemySpawner<T extends Enemy> extends Actor {
    private final Supplier<T> ENEMY;
    private int count = 0;
    private final int total;
    private final int delay;
    private int lastCheck = -1;

    /**
     * Creates a spawner for the specified Enemy with the inputted parameters
     * @param enemyType Supplier for type of Enemy to be spawned
     * @param enemyCount Amount of Enemies to be spawned for that Wave
     * @param spawnDelay Delay between Enemy spawns for that Wave
     */
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

    /**
     * Returns if the Spawner has spawned all of its enemies
     */
    public boolean isFinished(){
        return count >= total;
    }
}