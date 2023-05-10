import greenfoot.*;

public class Spawner extends Actor {
    private static int wave = 0;

    //{Enemy count for wave, enemy spawn delay}
    private final int[][] basicSpawnInfo = new int[][]{{4, 50}};
    private int basicLastCheck = -1;
    private int basicCount = 0;

    public Spawner(){
        GreenfootImage gi = new GreenfootImage(1,1);
        gi.setTransparency(0);
        setImage(gi);
        wave = 0;
    }
    public void act() {
        if(WaveStart.isGoing()){
            if(basicCount < basicSpawnInfo[wave][0] && MyWorld.Timer - basicLastCheck >= basicSpawnInfo[wave][1]){
                getWorld().addObject(new BasicE(), 19, 470);
                basicCount++;
                basicLastCheck = MyWorld.Timer;
            }else if(basicCount >= basicSpawnInfo[wave][0]){
                WaveStart.waveDone();
                basicCount = 0;
                wave++;
            }
        }
    }
}