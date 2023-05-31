import greenfoot.*;

import java.util.function.Supplier;

public class TurretBuy<T extends TurretGunBase> extends ShopCloseRemoves {
    private final int COST;
    private final Supplier<T> TURRET;
    private final String BIND;
    public TurretBuy(Supplier<T> supplier, String bind){
        TURRET = supplier;
        TurretGunBase TU = supplier.get();
        COST = TU.getCost();
        BIND = bind;
        GreenfootImage i = new GreenfootImage(120, 140);
        GreenfootImage t1 = new GreenfootImage("$"+COST, 20, Color.BLACK, null);
        GreenfootImage t2 = new GreenfootImage(TU.getName(), 20, Color.BLACK, null);
        i.drawImage(t1,i.getWidth()/2-t1.getWidth()/2,i.getHeight()-t1.getHeight());
        i.drawImage(t2,i.getWidth()/2-t2.getWidth()/2,i.getHeight()-t1.getHeight()-t2.getHeight());
        i.drawImage(TU.getBaseImage(),i.getWidth()/2- TU.getBaseImage().getWidth()/2, TU.getBaseImage().getHeight()/2);
        i.drawImage(TU.getTurretImage(),i.getWidth()/2- TU.getBaseImage().getWidth()/2+TU.getOffset(), TU.getBaseImage().getHeight()/2);
        setImage(i);
    }
    public void act() {
        if((Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown(BIND)) && Cash.getCash() >= COST){
            getWorld().addObject(TURRET.get(), 0, 0);
            Cash.alterCash(-TURRET.get().getCost());
            getWorld().getObjects(ShopButton.class).get(0).close();
        }
    }
}