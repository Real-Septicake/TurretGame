import greenfoot.*;

public class BackGround extends Actor {
    public BackGround(){
        GreenfootImage i = new GreenfootImage(MyWorld.image().getWidth(), MyWorld.image().getHeight());
        i.setTransparency(0);
        setImage(i);
    }
    public void act() {
        if(Greenfoot.mouseClicked(this)){
            //Remove things once they're added
        }
    }
}