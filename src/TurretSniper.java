import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TurretSniper extends TurretGunBase
{
    private static final GreenfootImage TURRET_NORM = new GreenfootImage("SnipeyTurret.png");
    private static final GreenfootImage BASE = new GreenfootImage("SnipeyBase.png");

    public TurretSniper(){
        super(250,3,75,30);
        setImage(TURRET_NORM);
    }

    public void act()
    {
        super.act();
    }

    @Override
    public int getOffset() {
        return 12;
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
