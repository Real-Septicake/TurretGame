import greenfoot.*;

public class TurretBase extends Actor
{
    private double range;
    private enum Targeting {
        FIRST(0),
        LAST(1),
        CLOSE(2),
        FAR(3);

        private final int value;
        Targeting(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }
    Targeting mode = Targeting.FIRST;
    int id;
    private int damage;
    private static int count = 0;
    public TurretBase(double r, int d, String imageFileName){
        setImage(imageFileName);
        id = count++;
        range = r;
        damage = d;
    }
    public void act() 
    {

    }
    private double distanceTo(int x, int y){
        return (int)Math.sqrt(Math.pow((getX()-x),2)+Math.pow((getY()-y),2));
    }

    public int getDamage() {
        return damage;
    }
    public void alterDamage(int val){
        damage += val;
    }
    public double getRange(){
        return range;
    }
    public void alterRange(double val){
        range += val;
    }
}
