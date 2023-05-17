import greenfoot.*;

public class UpgradePath extends ShopCloseRemoves {
    private int upgradeNum = 0;
    private final Upgrade[] upgrades;
    private final TurretGunBase TURRET;
    public UpgradePath(TurretGunBase t, Upgrade... u){
        upgrades = u;
        updateImage();
        TURRET = t;
    }
    public void act(){
        if(Greenfoot.mouseClicked(this) && Cash.getCash() >= upgrades[upgradeNum].getCost()){
            Cash.alterCash(upgrades[upgradeNum].getCost());
            if(TURRET != null){
                TURRET.applyUpgrade(upgrades[upgradeNum]);
            }
            upgradeNum++;
            updateImage();
        }
    }
    public void updateImage(){
        GreenfootImage gi = new GreenfootImage(120, 168);
        gi.drawImage(upgrades[upgradeNum].getImage(), 0, 0);
        GreenfootImage text = new GreenfootImage(upgrades[upgradeNum].getName(), 24, Color.BLACK, null);
        gi.drawImage(text, gi.getWidth()/2 - text.getWidth()/2, 120);
        GreenfootImage text2 = new GreenfootImage("$"+upgrades[upgradeNum].getCost(), 24, Color.BLACK, null);
        gi.drawImage(text2, (gi.getWidth()/2) - text2.getWidth()/2, 144);
        setImage(gi);
    }
    public int getUpgradeNum(){
        return upgradeNum;
    }
}
