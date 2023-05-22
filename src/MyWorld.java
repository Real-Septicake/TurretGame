import greenfoot.*;

public class MyWorld extends World {
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
        Timer=0;
        setActOrder(MyWorld.class, TurretGunBase.class, RangeDisplay.class);
        setPaintOrder(PathFollow.class, WaveStart.class, ShopButton.class, ShopCloseRemoves.class, ShopBG.class, TurretGunBase.class, BackGround.class, TurretBase.class, RangeDisplay.class, Enemy.class, Counter.class);
        addObject(new EnemyPath(), getWidth()/2, getHeight()/2);
        addObject(new BackGround(), getWidth()/2, getHeight()/2);
        addObject(new WaveStart(), 45, 125);
        addObject(new Cash(START_CASH),Cash.START_X_POS,23);
        addObject(new Lives(START_LIVES),Lives.START_X_POS,53);
        addObject(new Wave(),Wave.START_X_POS,83);
        addObject(new Spawner(), 0, 0);
        addObject(new ShopButton(),getWidth()-ShopButton.getWidth()/2,50);
    }
    public static GreenfootImage image(){
        return IMAGE;
    }
    public static boolean check(Color c){
        return c.equals(IMAGE.getColorAt(0, 487));
    }
}
