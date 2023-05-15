import greenfoot.*;

public class ShopButton extends Actor {
    private static final GreenfootImage TO_OPEN = new GreenfootImage("ShopOpenButton.png");
    private static final GreenfootImage TO_CLOSE = new GreenfootImage("ShopCloseButton.png");
    private static boolean isOpen = false;
    public ShopButton(){
        setImage(TO_OPEN);
        isOpen = false;
    }
    public void act() {
        if(Greenfoot.mouseClicked(this)){
            if(isOpen){
                close();
            }else{
                openTurret();
            }
        }
        setImage(isOpen? TO_CLOSE : TO_OPEN);
    }

    private void openTurret(){
        open();
        getWorld().addObject(new TurretBuy<>(50, TurretBasic::new),getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2,50);
        getWorld().addObject(new TurretBuy<>(100, TurretSniper::new), getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2, 150);
    }

    public void openUpgrade(TurretGunBase t){
        open();
        getWorld().addObject(new RangeDisplay(t), t.getX(), t.getY());
        getWorld().addObject(new ModeCycle(t), getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2,50);
    }

    private void open(){
        close();
        getWorld().addObject(new ShopBG(),getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2,getWorld().getHeight()/2);
        setLocation(getX()-ShopBG.IMAGE.getWidth(),getY());
        isOpen = true;
    }

    public void close(){
        setLocation(getWorld().getWidth()- TO_OPEN.getWidth()/2,50);
        getWorld().removeObjects(getWorld().getObjects(ShopCloseRemoves.class));
        isOpen = false;
    }

    public static int getWidth(){
        return TO_OPEN.getWidth();
    }
}