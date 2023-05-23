import greenfoot.*;

public class TurretSniper extends TurretGunBase
{
    private static final GreenfootImage TURRET_NORM = new GreenfootImage("SnipeyTurret.png");
    private static final GreenfootImage BASE = new GreenfootImage("SnipeyBase.png");

    public TurretSniper(){
        super(250,3,75,20, 100);
        setPaths(new UpgradePath(this,
                        new Upgrade(0,0,0,0,null,"11",0),
                        new Upgrade(0,0,0,0,null,"12",0)),
                new UpgradePath(this,
                        new Upgrade(0,0,0,0,null,"21",0),
                        new Upgrade(0,0,-50,-15,null,"Even Faster Firing",0)));
        setImage(TURRET_NORM);
    }

    public void act()
    {
        super.act();
    }

    @Override
    public int getOffset() {
        return (MyWorld.Timer - lastAnimCheck >= animLength)?12:8;
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
    public String getName() {
        return "Sniper Turret";
    }

    @Override
    protected void animate(boolean cool) {
        setImage(TURRET_NORM);
    }
}
