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
                open();
            }
        }
        setImage(isOpen? TO_CLOSE : TO_OPEN);
    }

    private void open(){
        getWorld().addObject(new ShopBG(),getWorld().getWidth()-ShopBG.IMAGE.getWidth()/2,getWorld().getHeight()/2);
        setLocation(getX()-ShopBG.IMAGE.getWidth(),getY());
        isOpen = true;
    }

    public void close(){
        setLocation(getWorld().getWidth()- TO_OPEN.getWidth()/2,50);
        getWorld().removeObjects(getWorld().getObjects(ShopBG.class));
        isOpen = false;
    }

    public static int getWidth(){
        return TO_OPEN.getWidth();
    }
}