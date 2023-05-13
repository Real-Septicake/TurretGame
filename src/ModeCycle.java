import greenfoot.*;

import java.util.List;

public class ModeCycle extends ShopCloseRemoves {
    private final int CURRENT_ID;
    private List<TurretGunBase> l;
    private boolean start = false;
    public ModeCycle(int id){
        CURRENT_ID = id;
    }
    public void act() {
        if(!start){
            updateImage();
            start = true;
        }
        if(Greenfoot.mouseClicked(this)){
            l = getWorld().getObjects(TurretGunBase.class);
            l.get(CURRENT_ID).cycleMode();
            updateImage();
        }
    }

    public void updateImage(){
        l = getWorld().getObjects(TurretGunBase.class);
        GreenfootImage i = new GreenfootImage(120, 140);
        GreenfootImage t1 = new GreenfootImage("< " + l.get(CURRENT_ID).getMode() + " >", 20, Color.BLACK, null);
        GreenfootImage t2 = new GreenfootImage(l.get(CURRENT_ID).getName(), 20, Color.BLACK, null);
        i.drawImage(t1,i.getWidth()/2-t1.getWidth()/2,i.getHeight()-t1.getHeight());
        i.drawImage(t2,i.getWidth()/2-t2.getWidth()/2,i.getHeight()-t1.getHeight()-t2.getHeight());
        i.drawImage(l.get(CURRENT_ID).getBaseImage(),i.getWidth()/2- l.get(CURRENT_ID).getBaseImage().getWidth()/2, l.get(CURRENT_ID).getBaseImage().getHeight()/2);
        i.drawImage(l.get(CURRENT_ID).getTurretImage(),i.getWidth()/2- l.get(CURRENT_ID).getBaseImage().getWidth()/2+8, l.get(CURRENT_ID).getBaseImage().getHeight()/2-1);
        setImage(i);
    }
}
