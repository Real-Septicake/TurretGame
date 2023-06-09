import greenfoot.*;

import java.text.DecimalFormat;

public class UpgradePath extends ShopCloseRemoves {
    private int upgradeNum = 0;
    private static final int BASE_HEIGHT = 168;
    private static final Upgrade MAX_UPGRADE = new Upgrade(0,0,0,0,null,"",Integer.MAX_VALUE);
    private final Upgrade[] upgrades;
    private final TurretGunBase TURRET;
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###");
    private GreenfootImage previous;
    public UpgradePath(TurretGunBase t, Upgrade... u){
        upgrades = u;
        updateImage();
        TURRET = t;
    }
    public void act(){
        if(Greenfoot.mouseClicked(this) && Cash.getCash() >= getCurrent().getCost()){
            Cash.alterCash(-getCurrent().getCost());
            if(TURRET != null){
                TURRET.applyUpgrade(getCurrent());
            }
            upgradeNum++;
            updateImage();
        }
    }
    public void updateImage(){
        GreenfootImage text = new GreenfootImage(getCurrent().getName(), 24, Color.BLACK, null);
        GreenfootImage text2 = new GreenfootImage(((getCurrent()!=MAX_UPGRADE)?"$"+FORMATTER.format(getCurrent().getCost()):""), 24, Color.BLACK, null);
        GreenfootImage gi = new GreenfootImage(120, 120 + text.getHeight() + text2.getHeight());
        gi.drawImage(getCurrent().getImage(), 0, 0);
        gi.drawImage(text, gi.getWidth()/2 - text.getWidth()/2, 120);
        gi.drawImage(text2, (gi.getWidth()/2) - text2.getWidth()/2, 120+ text.getHeight());
        if(previous != null){
            setLocation(getX(), getY()-((previous.getHeight() - gi.getHeight())/2));
        }
        setImage(gi);
        previous = gi;
    }
    private Upgrade getCurrent(){
        return (upgradeNum>=upgrades.length)?MAX_UPGRADE:upgrades[upgradeNum];
    }
}