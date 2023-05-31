import greenfoot.*;

/**
 * Controls the creation of Enemy spawners and the progression of the waves
 */
public class Spawner extends Actor {
    private static int wave = 0;

    //{Enemy count for wave, enemy spawn delay}
    private final int[][] basicSpawnInfo = new int[][]{{4, 50}};
    private final int[][] heavySpawnInfo = new int[][]{{4, 50}};

    public Spawner(){
        GreenfootImage gi = new GreenfootImage(1,1);
        gi.setTransparency(0);
        setImage(gi);
        wave = 0;
    }
    public void act() {
        if(WaveStart.isGoing()){
            for(EnemySpawner es : getWorld().getObjects(EnemySpawner.class)){
                if(!es.isFinished()){
                    return;
                }
            }
            if(getWorld().getObjects(Enemy.class).size() == 0){
                getWorld().getObjects(WaveStart.class).get(0).waveDone();
                wave++;
            }
        }
    }

    public void startWave(){
        if(wave < basicSpawnInfo.length){
            getWorld().addObject(new EnemySpawner<>(BasicE::new, basicSpawnInfo[wave][0], basicSpawnInfo[wave][1]), 0, 0);
            getWorld().addObject(new EnemySpawner<>(HeavyE::new, heavySpawnInfo[wave][0], heavySpawnInfo[wave][1]), 0, 0);
        }else{
            getWorld().addObject(new EnemySpawner<>(BasicE::new, (int)Math.ceil(wave*(wave*0.1)), (350/wave)), 0, 0);
            getWorld().addObject(new EnemySpawner<>(HeavyE::new, (int)Math.ceil(wave*(wave*0.075)), (475/wave)), 0, 0);
        }
    }
    public static int getWave(){
        return wave;
    }
}