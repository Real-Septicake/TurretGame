import greenfoot.*;

public class BackGround extends Actor {
    public BackGround(){
        GreenfootImage i = new GreenfootImage(MyWorld.image().getWidth(), MyWorld.image().getHeight());
        setImage(i);
    }
    public void act() {
        if(Greenfoot.mouseClicked(this)){
            getWorld().getObjects(ShopButton.class).get(0).close();
            getWorld().removeObjects(getWorld().getObjects(RangeDisplay.class));
        }
    }
}