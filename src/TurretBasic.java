import greenfoot.*;

public class TurretBasic extends TurretGunBase {
    public static final GreenfootImage TURRET_NORM = new GreenfootImage("BaseTurret.png");
    public static final GreenfootImage TURRET_FIRE = new GreenfootImage("BaseTurretFire.png");
    public static final GreenfootImage BASE = new GreenfootImage("BasicTurretBase.png");
    public TurretBasic(){
        super(200, 1, 30, 8, 50);
        setPaths(new UpgradePath(this,
                        new Upgrade(40,0,0,0,null,"Better Aim", 55),
                        new Upgrade(80,0,5,2,null,"Bigger\nRange", 200)),
                new UpgradePath(this,
                        new Upgrade(0,0,-5,-1,null,"Faster Firing",90),
                        new Upgrade(0,0,-10,-2,null,"Quick Reload",300)));
        setImage(TURRET_NORM);
    }

    public void act() {
        super.act();
    }

    @Override
    public int getOffset() {
        return (MyWorld.Timer - lastAnimCheck >= animLength)?8:12;
    }

    @Override
    public GreenfootImage getBaseImage() {
        return BASE;
    }

    @Override
    public GreenfootImage getTurretImage() {
        return TURRET_NORM;
    }

    @Override
    public String getName(){
        return "Basic Turret";
    }

    @Override
    protected void animate(boolean cool) {
        setImage((cool)?TURRET_NORM:TURRET_FIRE);
    }
}