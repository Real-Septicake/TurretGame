import greenfoot.*;

public class ModeCycle extends ShopCloseRemoves {
    private final TurretGunBase TURRET;
    private boolean start = false;
    public ModeCycle(TurretGunBase t){
        TURRET = t;
    }
    public void act() {
        if(!start){
            updateImage();
            start = true;
        }
        if(Greenfoot.mouseClicked(this)){
            TURRET.cycleMode();
            updateImage();
        }
    }

    public void updateImage(){
        GreenfootImage i = new GreenfootImage(120, 140);
        GreenfootImage t1 = new GreenfootImage("< " + TURRET.getMode() + " >", 20, Color.BLACK, null);
        GreenfootImage t2 = new GreenfootImage(TURRET.getName(), 20, Color.BLACK, null);
        i.drawImage(t1,i.getWidth()/2-t1.getWidth()/2,i.getHeight()-t1.getHeight());
        i.drawImage(t2,i.getWidth()/2-t2.getWidth()/2,i.getHeight()-t1.getHeight()-t2.getHeight());
        i.drawImage(TURRET.getBaseImage(),i.getWidth()/2- TURRET.getBaseImage().getWidth()/2, TURRET.getBaseImage().getHeight()/2);
        i.drawImage(TURRET.getTurretImage(),i.getWidth()/2- TURRET.getBaseImage().getWidth()/2+8, TURRET.getBaseImage().getHeight()/2-1);
        setImage(i);
    }
}
