import greenfoot.*;

public class TurretSniper extends TurretGunBase
{
    private static final GreenfootImage TURRET_NORM = new GreenfootImage("SnipeyTurret.png");
    private static final GreenfootImage BASE = new GreenfootImage("SnipeyBase.png");

    public TurretSniper(){
        super(250,3,75,20, 100);
        setPaths(new UpgradePath(this,
                        new Upgrade(45,1,10,3,null,"AP Rounds",95),
                        new Upgrade(125,4,30,9,null,".50 BMG",400)),
                new UpgradePath(this,
                        new Upgrade(0,0,-10,-6,null,"Faster Firing",70),
                        new Upgrade(0,0,-50,-15,null,"Semi-Auto\nRifle",280)));
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
