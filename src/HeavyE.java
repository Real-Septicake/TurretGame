public class HeavyE extends Enemy {

    public HeavyE(){
        super((int)Math.max((7 * Spawner.getWave())/10.0, 7), (int)Math.max((2 * Spawner.getWave())/10.0, 2), 4, 10);
        setImage("HeavyEnemy.png");
    }

    public void act() {
        super.act();
    }
}
