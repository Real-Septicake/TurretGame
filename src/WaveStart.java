import greenfoot.*;

public class WaveStart extends Actor {
    private static final GreenfootImage start = new GreenfootImage("WaveStart.png");
    private static final GreenfootImage going = new GreenfootImage("WaveGoing.png");
    private static boolean isGoing = false;
    public WaveStart(){
        isGoing = false;
        setImage(start);
    }
    public void act() {
        if(!isGoing && Greenfoot.mouseClicked(this)){
            isGoing = true;
            getWorld().getObjects(Spawner.class).get(0).startWave();
        }
        setImage((isGoing)?going:start);
    }

    public void waveDone(){
        isGoing = false;
        getWorld().removeObjects(getWorld().getObjects(EnemySpawner.class));
    }

    public static boolean isGoing(){
        return isGoing;
    }


}