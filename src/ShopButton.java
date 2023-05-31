import greenfoot.*;

public class ShopButton extends Actor {
    private static final GreenfootImage TO_OPEN = new GreenfootImage("ShopOpenButton.png");
    private static final GreenfootImage TO_CLOSE = new GreenfootImage("ShopCloseButton.png");
    private static boolean isOpen = false;
    private static boolean buttonIsDown = false;
    public ShopButton(){
        setImage(TO_OPEN);
        isOpen = false;
    }
    public void act() {
        if((Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("tab")) && !buttonIsDown){
            buttonIsDown = true;
            if(isOpen){
                close();
            }else{
                openTurret();
            }
        }else if(!Greenfoot.isKeyDown("tab")){
            buttonIsDown = false;
        }
        setImage(isOpen? TO_CLOSE : TO_OPEN);
    }

    public void openTurret(){
        open();
        getWorld().addObject(new TurretBuy<>(TurretBasic::new, "1"),getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2,50);
        getWorld().addObject(new TurretBuy<>(TurretSniper::new, "2"), getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2, 175);
    }

    public void openUpgrade(TurretGunBase t, UpgradePath... paths){
        open();
        getWorld().addObject(new RangeDisplay(t), t.getX(), t.getY());
        getWorld().addObject(new ModeCycle(t), getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2,60);
        for(int i = 0; i < paths.length; i++){
            getWorld().addObject(paths[i], getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2, (200*(i+1))+30);
        }
        getWorld().addObject(new SellButton(t), getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2, getWorld().getHeight()-40);
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