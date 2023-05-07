import greenfoot.*;

public class MyWorld extends World
{
    public static int Timer = 0;
    public static final GreenfootImage IMAGE = new GreenfootImage("Map(1).png");
    private static final int START_CASH = 200;
    private static final int START_LIVES = 50;
    public MyWorld()
    {
        super(800, 650, 1);
        setBackground(IMAGE);
        setup();
    }
    public void act(){
        Timer++;
    }
    private void setup(){
        setPaintOrder(WaveStart.class, TurretGunBase.class, TurretBase.class, Counter.class);
        addObject(new EnemyPath(), getWidth()/2, getHeight()/2);
        addObject(new BackGround(), getWidth()/2, getHeight()/2);
        addObject(new WaveStart(), 45, 110);
        addObject(new Cash(START_CASH),50,23);
        addObject(new Lives(START_LIVES),50,58);
    }
    public static GreenfootImage image(){
        return IMAGE;
    }
    public static boolean check(Color c){
        return c.equals(IMAGE.getColorAt(0, 487));
    }
}
